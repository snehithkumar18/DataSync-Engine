package com.syncforge.snapshot;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.compression.CompressionEngine;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.DirectoryEntry;
import com.syncforge.metadata.SymlinkEntry;
import com.syncforge.serialization.BinarySerializer;
import com.syncforge.snapshot.SnapshotModel.MerkleNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * SnapshotWriter serializes a {@link SnapshotModel} into a custom high-integrity binary
 * format matching the SFSN (SyncForge Snapshot) specification.
 *
 * <p>The file format layout is as follows:</p>
 * <pre>
 * +---------------------------+
 * | Header Table (44 bytes)   |
 * +---------------------------+
 * | Payload Block (Variable)  |
 * | - Path Table              |
 * | - Metadata Table (Fixed + |
 * |   Heap)                   |
 * | - Checksum Table          |
 * +---------------------------+
 * | SHA-256 Footer (32 bytes) |
 * +---------------------------+
 * </pre>
 */
public class SnapshotWriter {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(SnapshotWriter.class);
    private static final String MAGIC = "SFSN";
    private static final int VERSION = 1;

    private final boolean useCompression;

    /**
     * Constructs a SnapshotWriter.
     *
     * @param useCompression true if the payload block should be compressed using Zlib/Deflate.
     */
    public SnapshotWriter(boolean useCompression) {
        this.useCompression = useCompression;
    }

    /**
     * Serializes the provided {@link SnapshotModel} to a binary byte array.
     *
     * @param model the snapshot model to serialize. Must not be null.
     * @return the serialized snapshot file as a byte array.
     * @throws IOException         if serialization fails.
     * @throws ValidationException if the model contains invalid or inconsistent data.
     */
    public byte[] write(SnapshotModel model) throws IOException {
        Objects.requireNonNull(model, "SnapshotModel must not be null");
        List<EntryMetadata> entries = model.getEntries();
        if (entries == null) {
            throw new ValidationException("SnapshotModel entries list must not be null");
        }

        LOGGER.info("Starting snapshot serialization. Total entries: %d, Compression: %b",
            entries.size(), useCompression);

        // Sort entries by path to ensure deterministic layout and Merkle Tree
        List<EntryMetadata> sortedEntries = new ArrayList<>(entries);
        sortedEntries.sort(Comparator.comparing(EntryMetadata::getNormalizedPath));

        // 1. Serialize Path Table (with prefix path compression)
        byte[] pathTableBytes = serializePathTable(sortedEntries);
        LOGGER.debug("Path Table serialized: %d bytes", pathTableBytes.length);

        // 2. Build Merkle Tree & Serialize Checksum Table
        List<String> merkleParentHashes = new ArrayList<>();
        MerkleNode merkleRoot = MerkleTreeBuilder.build(sortedEntries, merkleParentHashes);
        model.setRootNode(merkleRoot);

        Map<FileEntry, Long> fileHashOffsets = new HashMap<>();
        Map<FileEntry.AlternateDataStream, Long> adsHashOffsets = new HashMap<>();
        byte[] checksumTableBytes = serializeChecksumTable(sortedEntries, merkleParentHashes, fileHashOffsets, adsHashOffsets);
        LOGGER.debug("Checksum Table serialized: %d bytes", checksumTableBytes.length);

        // 3. Serialize Metadata Table (Fixed-width records + Variable heap)
        byte[] metadataTableBytes = serializeMetadataTable(sortedEntries, fileHashOffsets, adsHashOffsets);
        LOGGER.debug("Metadata Table serialized: %d bytes", metadataTableBytes.length);

        // Assemble uncompressed payload
        int pathTableLen = pathTableBytes.length;
        int metadataTableLen = metadataTableBytes.length;
        int checksumTableLen = checksumTableBytes.length;

        byte[] uncompressedPayload = new byte[pathTableLen + metadataTableLen + checksumTableLen];
        System.arraycopy(pathTableBytes, 0, uncompressedPayload, 0, pathTableLen);
        System.arraycopy(metadataTableBytes, 0, uncompressedPayload, pathTableLen, metadataTableLen);
        System.arraycopy(checksumTableBytes, 0, uncompressedPayload, pathTableLen + metadataTableLen, checksumTableLen);

        int flags = 0;
        byte[] payloadBytes;
        if (useCompression) {
            byte[] compressed = CompressionEngine.compress(uncompressedPayload);
            payloadBytes = new byte[compressed.length + 4];
            // Write original length as 4-byte prefix
            int uncompressedLen = uncompressedPayload.length;
            payloadBytes[0] = (byte) (uncompressedLen >>> 24);
            payloadBytes[1] = (byte) (uncompressedLen >>> 16);
            payloadBytes[2] = (byte) (uncompressedLen >>> 8);
            payloadBytes[3] = (byte) uncompressedLen;
            System.arraycopy(compressed, 0, payloadBytes, 4, compressed.length);
            flags |= 1;
            LOGGER.debug("Payload compressed: %d bytes -> %d bytes (Ratio: %.2f%%)",
                uncompressedPayload.length, payloadBytes.length,
                (payloadBytes.length * 100.0) / uncompressedPayload.length);
        } else {
            payloadBytes = uncompressedPayload;
        }

        // Set absolute offsets in the uncompressed file stream
        long pathTableOffset = 44;
        long metadataTableOffset = 44 + pathTableLen;
        long checksumTableOffset = 44 + pathTableLen + metadataTableLen;

        // Serialize Header Table
        BinarySerializer header = new BinarySerializer();
        header.writeBytes(MAGIC.getBytes(StandardCharsets.US_ASCII));
        header.writeShort(VERSION);
        header.writeShort(flags);
        header.writeInt(sortedEntries.size());
        header.writeLong(pathTableOffset);
        header.writeLong(metadataTableOffset);
        header.writeLong(checksumTableOffset);
        header.writeLong(payloadBytes.length);

        byte[] headerBytes = header.toByteArray();
        if (headerBytes.length != 44) {
            throw new IllegalStateException("Header length must be exactly 44 bytes, but was: " + headerBytes.length);
        }

        // Assemble entire file layout: Header + Payload + Footer
        byte[] fileData = new byte[headerBytes.length + payloadBytes.length + 32];
        System.arraycopy(headerBytes, 0, fileData, 0, headerBytes.length);
        System.arraycopy(payloadBytes, 0, fileData, headerBytes.length, payloadBytes.length);

        // Compute SHA-256 checksum over header + payload
        byte[] dataToHash = new byte[headerBytes.length + payloadBytes.length];
        System.arraycopy(fileData, 0, dataToHash, 0, dataToHash.length);
        String hashHex = ChecksumHasher.computeSHA256(dataToHash);
        byte[] hashBytes = hexToBytes(hashHex);

        System.arraycopy(hashBytes, 0, fileData, dataToHash.length, 32);

        LOGGER.info("Snapshot serialization complete. Total output size: %d bytes. Checksum footer: %s",
            fileData.length, hashHex);

        return fileData;
    }

    private byte[] serializePathTable(List<EntryMetadata> sortedEntries) throws IOException {
        BinarySerializer serializer = new BinarySerializer();
        // First write entry count
        serializer.writeInt(sortedEntries.size());

        String prevPath = "";
        for (EntryMetadata entry : sortedEntries) {
            String path = entry.getNormalizedPath();
            int commonPrefixLen = 0;
            int maxLen = Math.min(prevPath.length(), path.length());
            while (commonPrefixLen < maxLen && prevPath.charAt(commonPrefixLen) == path.charAt(commonPrefixLen)) {
                commonPrefixLen++;
            }
            String suffix = path.substring(commonPrefixLen);
            serializer.writeShort(commonPrefixLen);
            serializer.writeString(suffix);
            prevPath = path;
        }
        return serializer.toByteArray();
    }

    private byte[] serializeChecksumTable(
            List<EntryMetadata> sortedEntries,
            List<String> merkleParentHashes,
            Map<FileEntry, Long> fileHashOffsets,
            Map<FileEntry.AlternateDataStream, Long> adsHashOffsets) throws IOException {
        BinarySerializer serializer = new BinarySerializer();

        // 1. Calculate file/ADS content hashes count
        int contentHashCount = 0;
        for (EntryMetadata entry : sortedEntries) {
            if (entry instanceof FileEntry fileEntry) {
                contentHashCount++;
                contentHashCount += fileEntry.getAlternateDataStreams().size();
            }
        }
        serializer.writeInt(contentHashCount);

        // 2. Write file/ADS content hashes, keeping track of offsets
        for (EntryMetadata entry : sortedEntries) {
            if (entry instanceof FileEntry fileEntry) {
                long offset = serializer.toByteArray().length; // offset relative to Checksum Table start
                fileHashOffsets.put(fileEntry, offset);

                serializer.writeString(fileEntry.getContentHash().algorithm());
                serializer.writeString(fileEntry.getContentHash().value());

                for (FileEntry.AlternateDataStream ads : fileEntry.getAlternateDataStreams()) {
                    long adsOffset = serializer.toByteArray().length;
                    adsHashOffsets.put(ads, adsOffset);

                    serializer.writeString(ads.hash().algorithm());
                    serializer.writeString(ads.hash().value());
                }
            }
        }

        // 3. Write Merkle Tree intermediate and root hashes
        serializer.writeInt(merkleParentHashes.size());
        for (String hash : merkleParentHashes) {
            byte[] rawHashBytes = hexToBytes(hash);
            if (rawHashBytes.length != 32) {
                throw new IllegalStateException("Merkle parent hash must be exactly 32 bytes: " + hash);
            }
            serializer.writeBytes(rawHashBytes);
        }

        return serializer.toByteArray();
    }

    private byte[] serializeMetadataTable(
            List<EntryMetadata> sortedEntries,
            Map<FileEntry, Long> fileHashOffsets,
            Map<FileEntry.AlternateDataStream, Long> adsHashOffsets) throws IOException {
        BinarySerializer fixedSerializer = new BinarySerializer();
        BinarySerializer heapSerializer = new BinarySerializer();

        for (EntryMetadata entry : sortedEntries) {
            long size = entry.getSize();
            int modeType;
            if (entry instanceof FileEntry) {
                modeType = 1;
            } else if (entry instanceof DirectoryEntry) {
                modeType = 2;
            } else if (entry instanceof SymlinkEntry) {
                modeType = 3;
            } else {
                modeType = 4; // HARDLINK / OTHER
            }

            int posixPermissions = entry.getMode().getPosixPermissions();
            long mtime = entry.getTimestamp().mtimeMillis();
            long ctime = entry.getTimestamp().ctimeMillis();
            long atime = entry.getTimestamp().atimeMillis();

            Map<String, String> xattrs = Collections.emptyMap();
            if (entry instanceof FileEntry fe) {
                xattrs = fe.getExtendedAttributes();
            }
            int xattrsSize = xattrs.size();

            long hashOffset = -1;
            if (entry instanceof FileEntry fe) {
                Long offsetObj = fileHashOffsets.get(fe);
                if (offsetObj != null) {
                    hashOffset = offsetObj;
                }
            }

            long varDataOffset = heapSerializer.toByteArray().length;
            
            // Serialize variable data to heap
            if (entry instanceof FileEntry fe) {
                // Xattrs: key-value pairs
                for (Map.Entry<String, String> xattr : xattrs.entrySet()) {
                    heapSerializer.writeString(xattr.getKey());
                    heapSerializer.writeString(xattr.getValue());
                }
                // POSIX ACLs
                heapSerializer.writeInt(fe.getMode().getPosixAcls().size());
                for (String acl : fe.getMode().getPosixAcls()) {
                    heapSerializer.writeString(acl);
                }
                // Windows SIDs
                heapSerializer.writeInt(fe.getMode().getWindowsSids().size());
                for (String sid : fe.getMode().getWindowsSids()) {
                    heapSerializer.writeString(sid);
                }
                // Alternate Data Streams (ADS)
                heapSerializer.writeInt(fe.getAlternateDataStreams().size());
                for (FileEntry.AlternateDataStream ads : fe.getAlternateDataStreams()) {
                    heapSerializer.writeString(ads.name());
                    heapSerializer.writeLong(ads.size());
                    long adsHashOffset = adsHashOffsets.getOrDefault(ads, -1L);
                    heapSerializer.writeLong(adsHashOffset);
                }
            } else if (entry instanceof DirectoryEntry de) {
                // Children list
                heapSerializer.writeInt(de.getChildren().size());
                for (String child : de.getChildren()) {
                    heapSerializer.writeString(child);
                }
                // POSIX ACLs
                heapSerializer.writeInt(de.getMode().getPosixAcls().size());
                for (String acl : de.getMode().getPosixAcls()) {
                    heapSerializer.writeString(acl);
                }
                // Windows SIDs
                heapSerializer.writeInt(de.getMode().getWindowsSids().size());
                for (String sid : de.getMode().getWindowsSids()) {
                    heapSerializer.writeString(sid);
                }
            } else if (entry instanceof SymlinkEntry se) {
                // Target path
                heapSerializer.writeString(se.getTargetPath());
                // POSIX ACLs
                heapSerializer.writeInt(se.getMode().getPosixAcls().size());
                for (String acl : se.getMode().getPosixAcls()) {
                    heapSerializer.writeString(acl);
                }
                // Windows SIDs
                heapSerializer.writeInt(se.getMode().getWindowsSids().size());
                for (String sid : se.getMode().getWindowsSids()) {
                    heapSerializer.writeString(sid);
                }
            }

            int varDataLen = heapSerializer.toByteArray().length - (int) varDataOffset;

            // Write 64-byte fixed-width record
            fixedSerializer.writeLong(size);
            fixedSerializer.writeInt(modeType);
            fixedSerializer.writeInt(posixPermissions);
            fixedSerializer.writeLong(mtime);
            fixedSerializer.writeLong(ctime);
            fixedSerializer.writeLong(atime);
            fixedSerializer.writeInt(xattrsSize);
            fixedSerializer.writeLong(hashOffset);
            fixedSerializer.writeInt(varDataLen);
            fixedSerializer.writeLong(varDataOffset);
        }

        byte[] fixedBytes = fixedSerializer.toByteArray();
        byte[] heapBytes = heapSerializer.toByteArray();
        byte[] metadataTableBytes = new byte[fixedBytes.length + heapBytes.length];
        System.arraycopy(fixedBytes, 0, metadataTableBytes, 0, fixedBytes.length);
        System.arraycopy(heapBytes, 0, metadataTableBytes, fixedBytes.length, heapBytes.length);

        return metadataTableBytes;
    }

    private static byte[] hexToBytes(String hex) {
        Objects.requireNonNull(hex, "Hex string must not be null");
        String trimmed = hex.trim();
        if (trimmed.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string length must be even: " + trimmed);
        }
        byte[] bytes = new byte[trimmed.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(trimmed.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
}
