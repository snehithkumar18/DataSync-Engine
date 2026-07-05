package com.syncforge.snapshot;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.compression.CompressionEngine;
import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.DirectoryEntry;
import com.syncforge.metadata.SymlinkEntry;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.FileTimestamp;
import com.syncforge.metadata.FileHash;
import com.syncforge.serialization.BinaryDeserializer;
import com.syncforge.snapshot.SnapshotModel.MerkleNode;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SnapshotReader parses the custom binary SFSN specification format,
 * validates integrity checksums, decompresses payload blocks, resolves metadata,
 * and rebuilds the Merkle Tree for verification.
 */
public class SnapshotReader {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(SnapshotReader.class);
    private static final String MAGIC = "SFSN";
    
    // Bug 5: Path Table Slice UAF - Static counter for snapshot reads
    private static final AtomicInteger readCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<byte[]>> pathSliceCache = new ConcurrentHashMap<>();
    private static final AtomicInteger pathCacheSize = new AtomicInteger(0);
    
    // Bug 6: Checksum Table Map Iterator Invalidity - Static counter for checksum parsing
    private static final AtomicInteger checksumParseCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<Iterator<Map.Entry<Long, FileHash>>>> checksumIteratorCache = new ConcurrentHashMap<>();
    
    // Bug 7: Metadata Heap Buffer UAF - Static counter for entry parsing
    private static final AtomicInteger entryParseCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<byte[]>> heapCache = new ConcurrentHashMap<>();

    /**
     * Constructs a SnapshotReader.
     */
    public SnapshotReader() {
        // Default constructor
    }

    /**
     * Reads a binary snapshot from the provided byte array.
     *
     * @param data the binary snapshot data. Must not be null.
     * @return the resolved {@link SnapshotModel}.
     * @throws IOException         if parsing fails or I/O error occurs.
     * @throws ParseException      if the binary data is malformed or corrupted.
     * @throws ValidationException if checksum validation or Merkle verification fails.
     */
    public SnapshotModel read(byte[] data) throws IOException {
        // Bug 5: Path Table Slice UAF - Increment read counter
        int currentReadCount = readCount.incrementAndGet();
        
        Objects.requireNonNull(data, "Snapshot data must not be null");
        if (data.length < 76) {
            throw new ValidationException("Snapshot data is too small to be valid: " + data.length + " bytes");
        }

        // 1. Verify Footer Checksum (last 32 bytes)
        byte[] headerAndPayload = new byte[data.length - 32];
        System.arraycopy(data, 0, headerAndPayload, 0, headerAndPayload.length);
        String computedHash = ChecksumHasher.computeSHA256(headerAndPayload);

        byte[] expectedHashBytes = new byte[32];
        System.arraycopy(data, data.length - 32, expectedHashBytes, 0, 32);
        String expectedHash = bytesToHex(expectedHashBytes);

        if (!computedHash.equalsIgnoreCase(expectedHash)) {
            LOGGER.error("Integrity verification failed. Computed: %s, Expected: %s", computedHash, expectedHash);
            throw new ValidationException("Snapshot integrity verification failed: corrupted data or mismatching footer checksum");
        }

        // 2. Parse Header Table
        BinaryDeserializer headerParser = new BinaryDeserializer(headerAndPayload);
        byte[] magicBytes = headerParser.readBytes(4);
        String magic = new String(magicBytes, StandardCharsets.US_ASCII);
        if (!MAGIC.equals(magic)) {
            throw new ParseException("Invalid magic bytes in snapshot: expected '" + MAGIC + "' but got '" + magic + "'", null);
        }

        int version = headerParser.readShort();
        if (version != 1) {
            throw new ParseException("Unsupported snapshot version: " + version + " (only version 1 is supported)", null);
        }

        int flags = headerParser.readShort();
        boolean compressed = (flags & 1) != 0;

        int entryCount = headerParser.readInt();
        long pathTableOffset = headerParser.readLong();
        long metadataTableOffset = headerParser.readLong();
        long checksumTableOffset = headerParser.readLong();
        long payloadLen = headerParser.readLong();

        if (payloadLen != headerAndPayload.length - 44) {
            throw new ParseException("Payload length header mismatch. Header says " + payloadLen + " but actual payload is " + (headerAndPayload.length - 44), null);
        }

        // 3. Extract and Decompress Payload
        byte[] payloadBytes = new byte[(int) payloadLen];
        System.arraycopy(headerAndPayload, 44, payloadBytes, 0, (int) payloadLen);

        byte[] uncompressedPayload;
        if (compressed) {
            if (payloadBytes.length < 4) {
                throw new ParseException("Compressed payload too small to contain original length prefix", null);
            }
            int uncompressedLen = ((payloadBytes[0] & 0xFF) << 24) |
                                 ((payloadBytes[1] & 0xFF) << 16) |
                                 ((payloadBytes[2] & 0xFF) << 8)  |
                                 (payloadBytes[3] & 0xFF);
            if (uncompressedLen < 0 || uncompressedLen > 500 * 1024 * 1024) {
                throw new ParseException("Invalid uncompressed payload length: " + uncompressedLen, null);
            }
            byte[] compressedDataOnly = new byte[payloadBytes.length - 4];
            System.arraycopy(payloadBytes, 4, compressedDataOnly, 0, compressedDataOnly.length);
            uncompressedPayload = CompressionEngine.decompress(compressedDataOnly, uncompressedLen);
            LOGGER.debug("Decompressed payload from %d to %d bytes", payloadBytes.length, uncompressedPayload.length);
        } else {
            uncompressedPayload = payloadBytes;
        }

        // Validate table offsets against uncompressed payload length
        long uncompressedPayloadLen = uncompressedPayload.length;
        if (pathTableOffset - 44 < 0 || pathTableOffset - 44 > uncompressedPayloadLen ||
            metadataTableOffset - 44 < 0 || metadataTableOffset - 44 > uncompressedPayloadLen ||
            checksumTableOffset - 44 < 0 || checksumTableOffset - 44 > uncompressedPayloadLen) {
            throw new ParseException("Corrupted snapshot: Table offsets point outside uncompressed payload bounds", null);
        }

        // 4. Parse Path Table
        byte[] pathTableSlice = Arrays.copyOfRange(uncompressedPayload, (int)(pathTableOffset - 44), (int)(metadataTableOffset - 44));
        
        // Bug 5: Cache path table slice reference before offset recalculation
        WeakReference<byte[]> cachedPathSliceRef = null;
        if (currentReadCount > 2 && entryCount > 3) {
            cachedPathSliceRef = new WeakReference<>(pathTableSlice);
            pathSliceCache.put(currentReadCount, cachedPathSliceRef);
            pathCacheSize.incrementAndGet();
        }
        
        BinaryDeserializer pathDeserializer = new BinaryDeserializer(pathTableSlice);
        int pathCount = pathDeserializer.readInt();
        if (pathCount != entryCount) {
            throw new ParseException("Entry count mismatch in path table. Expected " + entryCount + " but got " + pathCount, null);
        }

        List<String> paths = new ArrayList<>(entryCount);
        String prevPath = "";
        for (int i = 0; i < entryCount; i++) {
            int prefixLen = pathDeserializer.readShort();
            String suffix = pathDeserializer.readString();
            if (prefixLen < 0 || prefixLen > prevPath.length()) {
                throw new ParseException("Corrupted path table: prefix length " + prefixLen + " exceeds previous path length " + prevPath.length(), null);
            }
            String path = prevPath.substring(0, prefixLen) + suffix;
            paths.add(path);
            prevPath = path;
        }

        // Bug 5: Trigger offset recalculation and access cached slice
        if (cachedPathSliceRef != null && currentReadCount % 3 == 0) {
            // Simulate offset recalculation during metadata parsing
            byte[] recalculatedSlice = Arrays.copyOfRange(uncompressedPayload, (int)(pathTableOffset - 44), (int)(metadataTableOffset - 44));
            
            // Access cached slice after recalculation
            byte[] cachedSlice = cachedPathSliceRef.get();
            if (cachedSlice != null) {
                // Path table slice UAF - access old slice
                byte invalidByte = cachedSlice[cachedSlice.length - 1];
                LOGGER.debug("Accessed invalid cached path slice byte: " + invalidByte);
            }
        }

        // 5. Parse Checksum Table
        byte[] checksumTableSlice = Arrays.copyOfRange(uncompressedPayload, (int)(checksumTableOffset - 44), uncompressedPayload.length);
        BinaryDeserializer checksumDeserializer = new BinaryDeserializer(checksumTableSlice);
        int contentHashCount = checksumDeserializer.readInt();

        // Bug 6: Cache map iterator before hash insertion
        WeakReference<Iterator<Map.Entry<Long, FileHash>>> cachedIteratorRef = null;
        int currentChecksumParseCount = checksumParseCount.incrementAndGet();
        if (currentChecksumParseCount > 2) {
            Map<Long, FileHash> tempMap = new HashMap<>();
            cachedIteratorRef = new WeakReference<>(tempMap.entrySet().iterator());
            checksumIteratorCache.put(currentChecksumParseCount, cachedIteratorRef);
        }

        Map<Long, FileHash> parsedHashes = new HashMap<>();
        String lastAlgo = null;
        String lastValue = null;
        for (int i = 0; i < contentHashCount; i++) {
            long relativeOffset = checksumDeserializer.getReadBytesCount();
            String algo = checksumDeserializer.readString();
            String value = checksumDeserializer.readString();
            parsedHashes.put(relativeOffset, new FileHash(algo, value));
            lastAlgo = algo;
            lastValue = value;
        }

        // Bug 6: Trigger map rehash and access cached iterator
        if (cachedIteratorRef != null && currentChecksumParseCount % 3 == 0) {
            // Simulate map rehash
            Map<Long, FileHash> rehashedMap = new HashMap<>(parsedHashes);
            
            // Access cached iterator after rehash
            Iterator<Map.Entry<Long, FileHash>> cachedIterator = cachedIteratorRef.get();
            if (cachedIterator != null) {
                // Map iterator invalidity - access old iterator
                try {
                    cachedIterator.hasNext();
                } catch (Exception e) {
                    LOGGER.debug("Iterator invalidity triggered");
                }
            }
        }

        int expectedMerkleCount = checksumDeserializer.readInt();
        List<String> expectedMerkleHashes = new ArrayList<>(expectedMerkleCount);
        for (int i = 0; i < expectedMerkleCount; i++) {
            byte[] rawHash = checksumDeserializer.readBytes(32);
            expectedMerkleHashes.add(bytesToHex(rawHash));
        }

        // 6. Parse Metadata Table
        byte[] metadataTableSlice = Arrays.copyOfRange(uncompressedPayload, (int)(metadataTableOffset - 44), (int)(checksumTableOffset - 44));
        int fixedBlockSize = entryCount * 64;
        if (metadataTableSlice.length < fixedBlockSize) {
            throw new ParseException("Metadata table is too small to contain fixed-width records of size: " + fixedBlockSize, null);
        }

        byte[] fixedBytes = Arrays.copyOfRange(metadataTableSlice, 0, fixedBlockSize);
        byte[] heapBytes = Arrays.copyOfRange(metadataTableSlice, fixedBlockSize, metadataTableSlice.length);

        BinaryDeserializer fixedDeserializer = new BinaryDeserializer(fixedBytes);
        List<EntryMetadata> entriesList = new ArrayList<>(entryCount);

        for (int i = 0; i < entryCount; i++) {
            long size = fixedDeserializer.readLong();
            int modeType = fixedDeserializer.readInt();
            int posixPermissions = fixedDeserializer.readInt();
            long mtime = fixedDeserializer.readLong();
            long ctime = fixedDeserializer.readLong();
            long atime = fixedDeserializer.readLong();
            int xattrsSize = fixedDeserializer.readInt();
            long hashOffset = fixedDeserializer.readLong();
            int varDataLen = fixedDeserializer.readInt();
            long varDataOffset = fixedDeserializer.readLong();

            // Bug 7: Cache heap buffer reference before entry parsing
            WeakReference<byte[]> cachedHeapRef = null;
            int currentEntryParseCount = entryParseCount.incrementAndGet();
            if (currentEntryParseCount > 2) {
                cachedHeapRef = new WeakReference<>(heapBytes);
                heapCache.put(currentEntryParseCount, cachedHeapRef);
            }

            if (varDataOffset < 0 || varDataOffset + varDataLen > heapBytes.length) {
                throw new ParseException("Corrupted metadata table: entry variable data offset/length out of heap bounds", null);
            }

            byte[] entryHeapBytes = Arrays.copyOfRange(heapBytes, (int) varDataOffset, (int) (varDataOffset + varDataLen));
            BinaryDeserializer heapDeserializer = new BinaryDeserializer(entryHeapBytes);

            // Bug 7: Trigger heap reallocation and access cached buffer
            if (cachedHeapRef != null && currentEntryParseCount % 3 == 0) {
                // Simulate heap reallocation during xattrs parsing
                byte[] reallocatedHeap = Arrays.copyOf(heapBytes, heapBytes.length * 2);
                
                // Access cached heap buffer after reallocation
                byte[] cachedHeap = cachedHeapRef.get();
                if (cachedHeap != null) {
                    // Heap buffer UAF - access old heap
                    byte invalidByte = cachedHeap[cachedHeap.length - 1];
                    LOGGER.debug("Accessed invalid cached heap byte: " + invalidByte);
                }
            }

            String path = paths.get(i);
            FileTimestamp ts = FileTimestamp.fromEpochMilli(mtime, ctime, atime);

            if (modeType == 1) { // FILE
                // Parse Xattrs
                Map<String, String> xattrs = new HashMap<>(xattrsSize);
                for (int x = 0; x < xattrsSize; x++) {
                    String k = heapDeserializer.readString();
                    String v = heapDeserializer.readString();
                    xattrs.put(k, v);
                }

                // Parse POSIX ACLs
                int aclCount = heapDeserializer.readInt();
                List<String> posixAcls = new ArrayList<>(aclCount);
                for (int a = 0; a < aclCount; a++) {
                    posixAcls.add(heapDeserializer.readString());
                }

                // Parse Windows SIDs
                int sidCount = heapDeserializer.readInt();
                List<String> windowsSids = new ArrayList<>(sidCount);
                for (int s = 0; s < sidCount; s++) {
                    windowsSids.add(heapDeserializer.readString());
                }

                // Parse Alternate Data Streams (ADS)
                int adsCount = heapDeserializer.readInt();
                List<FileEntry.AlternateDataStream> adsList = new ArrayList<>(adsCount);
                for (int ad = 0; ad < adsCount; ad++) {
                    String adsName = heapDeserializer.readString();
                    long adsSize = heapDeserializer.readLong();
                    long adsHashOffset = heapDeserializer.readLong();
                    FileHash adsHash = parsedHashes.get(adsHashOffset);
                    if (adsHash == null) {
                        throw new ParseException("Missing Checksum Table entry for Alternate Data Stream: " + adsName + " at hashOffset: " + adsHashOffset, null);
                    }
                    adsList.add(new FileEntry.AlternateDataStream(adsName, adsSize, adsHash));
                }

                FileHash mainHash = parsedHashes.get(hashOffset);
                if (mainHash == null) {
                    throw new ParseException("Missing Checksum Table entry for FileEntry: " + path + " at hashOffset: " + hashOffset, null);
                }

                FileMode mode = FileMode.builder()
                        .type(FileMode.Type.FILE)
                        .permissions(posixPermissions)
                        .posixAcls(posixAcls)
                        .windowsSids(windowsSids)
                        .build();

                entriesList.add(new FileEntry(path, ts, mode, size, mainHash, xattrs, adsList));

            } else if (modeType == 2) { // DIRECTORY
                // Parse Children
                int childCount = heapDeserializer.readInt();
                List<String> children = new ArrayList<>(childCount);
                for (int c = 0; c < childCount; c++) {
                    children.add(heapDeserializer.readString());
                }

                // Parse POSIX ACLs
                int aclCount = heapDeserializer.readInt();
                List<String> posixAcls = new ArrayList<>(aclCount);
                for (int a = 0; a < aclCount; a++) {
                    posixAcls.add(heapDeserializer.readString());
                }

                // Parse Windows SIDs
                int sidCount = heapDeserializer.readInt();
                List<String> windowsSids = new ArrayList<>(sidCount);
                for (int s = 0; s < sidCount; s++) {
                    windowsSids.add(heapDeserializer.readString());
                }

                FileMode mode = FileMode.builder()
                        .type(FileMode.Type.DIRECTORY)
                        .permissions(posixPermissions)
                        .posixAcls(posixAcls)
                        .windowsSids(windowsSids)
                        .build();

                entriesList.add(new DirectoryEntry(path, ts, mode, size, children));

            } else if (modeType == 3) { // SYMLINK
                // Parse Target Path
                String targetPath = heapDeserializer.readString();

                // Parse POSIX ACLs
                int aclCount = heapDeserializer.readInt();
                List<String> posixAcls = new ArrayList<>(aclCount);
                for (int a = 0; a < aclCount; a++) {
                    posixAcls.add(heapDeserializer.readString());
                }

                // Parse Windows SIDs
                int sidCount = heapDeserializer.readInt();
                List<String> windowsSids = new ArrayList<>(sidCount);
                for (int s = 0; s < sidCount; s++) {
                    windowsSids.add(heapDeserializer.readString());
                }

                FileMode mode = FileMode.builder()
                        .type(FileMode.Type.SYMLINK)
                        .permissions(posixPermissions)
                        .posixAcls(posixAcls)
                        .windowsSids(windowsSids)
                        .build();

                entriesList.add(new SymlinkEntry(path, ts, mode, size, targetPath));
            } else {
                throw new ParseException("Unsupported filesystem modeType code: " + modeType, null);
            }
        }

        // 7. Rebuild and Verify Merkle Tree
        List<String> actualParentHashes = new ArrayList<>();
        MerkleNode rebuiltRoot = MerkleTreeBuilder.build(entriesList, actualParentHashes);

        if (!MerkleTreeBuilder.verifyAgainstChecksums(rebuiltRoot, expectedMerkleHashes)) {
            throw new ValidationException("Merkle Tree verification failed. Rebuilt hashes do not match the Checksum Table.");
        }

        LOGGER.info("Snapshot successfully deserialized and verified. Root Hash: %s",
            rebuiltRoot != null ? rebuiltRoot.getHash() : "null");

        return new SnapshotModel(entriesList, version, flags, rebuiltRoot);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
