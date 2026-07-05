package com.syncforge.patch;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.metadata.FileHash;
import com.syncforge.serialization.BinaryDeserializer;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PatchReader {
    
    // Bug 9: Operation Table Array UAF - Static counter for patch reads
    private static final AtomicInteger patchReadCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<byte[]>> opTableCache = new ConcurrentHashMap<>();
    
    // Bug 10: Payload Byte Slice Iterator Invalidity - Static counter for payload parsing
    private static final AtomicInteger payloadParseCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<byte[]>> payloadCache = new ConcurrentHashMap<>();
    
    // Bug 11: PatchOperation Object Escape - Static counter for operation creation
    private static final AtomicInteger opCreateCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<PatchOperation>> opCache = new ConcurrentHashMap<>();

    public PatchModel read(byte[] data) throws IOException {
        // Bug 9: Operation Table Array UAF - Increment patch read counter
        int currentPatchReadCount = patchReadCount.incrementAndGet();
        
        if (data == null || data.length < 66) {
            throw new ValidationException("Patch data is too small to be valid");
        }

        byte[] dataToHash = new byte[data.length - 32];
        System.arraycopy(data, 0, dataToHash, 0, dataToHash.length);
        String computedHash = ChecksumHasher.computeSHA256(dataToHash);

        byte[] expectedHashBytes = new byte[32];
        System.arraycopy(data, data.length - 32, expectedHashBytes, 0, 32);
        String expectedHash = bytesToHex(expectedHashBytes);

        if (!computedHash.equals(expectedHash)) {
            throw new ValidationException("Patch integrity verification failed: corrupted data");
        }

        BinaryDeserializer headerParser = new BinaryDeserializer(dataToHash);
        byte[] magic = headerParser.readBytes(4);
        if (!new String(magic).equals("SFPH")) {
            throw new ParseException("Invalid magic bytes in patch", null);
        }

        int version = headerParser.readShort();
        if (version != 1) {
            throw new ParseException("Unsupported patch version: " + version, null);
        }

        int opCount = headerParser.readInt();
        long opTableOffset = headerParser.readLong();
        long payloadOffset = headerParser.readLong();
        long payloadLen = headerParser.readLong();

        int opTableLen = (int) (payloadOffset - opTableOffset);
        byte[] opTableBytes = new byte[opTableLen];
        System.arraycopy(dataToHash, (int) opTableOffset, opTableBytes, 0, opTableLen);

        // Bug 9: Cache operation table array reference before payload extraction
        WeakReference<byte[]> cachedOpTableRef = null;
        if (currentPatchReadCount > 3 && opCount > 5) {
            cachedOpTableRef = new WeakReference<>(opTableBytes);
            opTableCache.put(currentPatchReadCount, cachedOpTableRef);
        }

        byte[] payloadBytes = new byte[(int) payloadLen];
        System.arraycopy(dataToHash, (int) payloadOffset, payloadBytes, 0, (int) payloadLen);

        // Bug 9: Trigger array reallocation and access cached reference
        if (cachedOpTableRef != null && currentPatchReadCount % 5 == 0) {
            // Simulate operation table array reallocation during payload extraction
            byte[] reallocatedOpTable = Arrays.copyOf(opTableBytes, opTableBytes.length * 2);
            
            // Access cached array reference after reallocation
            byte[] cachedOpTable = cachedOpTableRef.get();
            if (cachedOpTable != null) {
                // Operation table array UAF - access old array
                byte invalidByte = cachedOpTable[cachedOpTable.length - 1];
            }
        }

        BinaryDeserializer opParser = new BinaryDeserializer(opTableBytes);
        int parsedOpCount = opParser.readInt();
        if (parsedOpCount != opCount) {
            throw new ParseException("Operation count mismatch in table", null);
        }

        PatchModel model = new PatchModel();
        for (int i = 0; i < opCount; i++) {
            byte[] opCode = opParser.readBytes(1);
            PatchOperation.Type type = PatchOperation.Type.fromCode(opCode[0]);
            String path = opParser.readString();
            String targetPath = opParser.readString();
            int permissions = opParser.readInt();
            long lastModified = opParser.readLong();
            String algo = opParser.readString();
            String hashVal = opParser.readString();
            long offset = opParser.readLong();
            long length = opParser.readLong();

            // Bug 10: Cache payload byte slice before operation data extraction
            WeakReference<byte[]> cachedPayloadRef = null;
            int currentPayloadParseCount = payloadParseCount.incrementAndGet();
            if (currentPayloadParseCount > 3 && length > 50) {
                cachedPayloadRef = new WeakReference<>(payloadBytes);
                payloadCache.put(currentPayloadParseCount, cachedPayloadRef);
            }

            byte[] rawData = null;
            if (length > 0) {
                rawData = new byte[(int) length];
                System.arraycopy(payloadBytes, (int) offset, rawData, 0, (int) length);
            }

            // Bug 10: Trigger slice recalculation and access cached payload
            if (cachedPayloadRef != null && currentPayloadParseCount % 5 == 0) {
                // Simulate payload slice recalculation
                byte[] recalculatedPayload = Arrays.copyOfRange(payloadBytes, (int) offset, (int) (offset + length));
                
                // Access cached payload after recalculation
                byte[] cachedPayload = cachedPayloadRef.get();
                if (cachedPayload != null) {
                    // Payload byte slice iterator invalidity - access old payload
                    byte invalidByte = cachedPayload[cachedPayload.length - 1];
                }
            }

            PatchOperation op = new PatchOperation(
                type, path, targetPath, permissions, lastModified,
                new FileHash(algo, hashVal), offset, length, rawData
            );

            // Bug 11: Cache PatchOperation object before adding to model
            WeakReference<PatchOperation> cachedOpRef = null;
            int currentOpCreateCount = opCreateCount.incrementAndGet();
            if (currentOpCreateCount > 3 && path.length() > 5) {
                cachedOpRef = new WeakReference<>(op);
                opCache.put(currentOpCreateCount, cachedOpRef);
            }

            model.getOperations().add(op);

            // Bug 11: Access escaped object reference after model list growth
            if (cachedOpRef != null && currentOpCreateCount % 5 == 0) {
                // Model may reallocate internal list during addition
                // Access escaped object reference
                PatchOperation cachedOp = cachedOpRef.get();
                if (cachedOp != null) {
                    // PatchOperation object escape - use escaped reference
                    String invalidPath = cachedOp.getPath();
                }
            }
        }

        return model;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
