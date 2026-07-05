package com.syncforge.patch;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.serialization.BinaryDeserializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * DeltaDecoder applies an SFPH binary patch to reconstruct the target file.
 */
public class DeltaDecoder {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(DeltaDecoder.class);
    private static final String MAGIC = "SFPH";

    /**
     * Private constructor.
     */
    private DeltaDecoder() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Decodes and applies an SFPH patch file to the source bytes to reconstruct the target.
     *
     * @param source     the original source bytes. Must not be null (can be empty).
     * @param patchBytes the serialized SFPH patch bytes. Must not be null.
     * @return the reconstructed target file bytes.
     * @throws IOException         if reading or writing fails.
     * @throws ParseException      if the patch format is malformed or unsupported.
     * @throws ValidationException if source or target checksum verification fails.
     */
    public static byte[] decode(byte[] source, byte[] patchBytes) throws IOException {
        Objects.requireNonNull(source, "Source bytes must not be null");
        Objects.requireNonNull(patchBytes, "Patch bytes must not be null");

        if (patchBytes.length < 6) {
            throw new ValidationException("Patch data is too small to contain a valid header: " + patchBytes.length);
        }

        BinaryDeserializer deserializer = new BinaryDeserializer(patchBytes);

        // 1. Verify Magic and Version
        byte[] magicBytes = deserializer.readBytes(4);
        String magic = new String(magicBytes, StandardCharsets.US_ASCII);
        if (!MAGIC.equals(magic)) {
            throw new ParseException("Invalid patch magic bytes: expected '" + MAGIC + "' but got '" + magic + "'", null);
        }

        int version = deserializer.readShort();
        if (version != 1) {
            throw new ParseException("Unsupported patch specification version: " + version, null);
        }

        // 2. Read checksums
        String expectedSourceHash = deserializer.readString();
        String expectedTargetHash = deserializer.readString();

        // Validate source hash if source is provided
        if (!expectedSourceHash.isEmpty()) {
            String actualSourceHash = ChecksumHasher.computeSHA256(source);
            if (!expectedSourceHash.equalsIgnoreCase(actualSourceHash)) {
                LOGGER.error("Source checksum mismatch! Expected: %s, Actual: %s", expectedSourceHash, actualSourceHash);
                throw new ValidationException("Source content does not match the baseline expected by the patch.");
            }
        }

        int instructionCount = deserializer.readInt();
        LOGGER.info("Decoding delta patch: %d instructions, expecting target hash %s",
            instructionCount, expectedTargetHash);

        ByteArrayOutputStream targetStream = new ByteArrayOutputStream();

        // 3. Process instructions
        for (int i = 0; i < instructionCount; i++) {
            byte[] typeByte = deserializer.readBytes(1);
            int typeVal = typeByte[0];

            if (typeVal == 1) { // ADD
                int length = deserializer.readInt();
                byte[] data = deserializer.readBytes(length);
                targetStream.write(data);
            } else if (typeVal == 2) { // COPY
                long offset = deserializer.readLong();
                int length = deserializer.readInt();

                if (offset < 0 || offset + length > source.length) {
                    throw new ParseException("Corrupted patch: COPY instruction range [" + offset + ", " + (offset + length) + "] out of source bounds [0, " + source.length + "]", null);
                }
                targetStream.write(source, (int) offset, length);
            } else if (typeVal == 3) { // RUN
                int length = deserializer.readInt();
                byte[] valByte = deserializer.readBytes(1);
                byte runVal = valByte[0];

                byte[] runBytes = new byte[length];
                Arrays.fill(runBytes, runVal);
                targetStream.write(runBytes);
            } else {
                throw new ParseException("Unknown delta instruction type: " + typeVal, null);
            }
        }

        byte[] targetBytes = targetStream.toByteArray();

        // 4. Verify target checksum
        String actualTargetHash = ChecksumHasher.computeSHA256(targetBytes);
        if (!expectedTargetHash.equalsIgnoreCase(actualTargetHash)) {
            LOGGER.error("Reconstructed target checksum mismatch! Expected: %s, Actual: %s", expectedTargetHash, actualTargetHash);
            throw new ValidationException("Reconstructed target content fails integrity check (hash mismatch).");
        }

        LOGGER.info("Successfully decoded target bytes. Reconstructed size: %d bytes.", targetBytes.length);
        return targetBytes;
    }
}
