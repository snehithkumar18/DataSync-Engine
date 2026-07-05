package com.syncforge.patch;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.serialization.BinarySerializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DeltaEncoder generates a binary delta patch file using a greedy sliding-window match search.
 */
public class DeltaEncoder {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(DeltaEncoder.class);
    private static final String MAGIC = "SFPH";
    private static final int VERSION = 1;
    private static final int MIN_MATCH_LEN = 6; // minimum match length to trigger COPY

    /**
     * Private constructor.
     */
    private DeltaEncoder() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Encodes the differences between a source and a target byte array into a custom SFPH patch file.
     *
     * @param source the original/source file bytes. Can be empty or null for raw encodes.
     * @param target the target file bytes. Must not be null.
     * @return the serialized SFPH patch bytes.
     * @throws IOException if serialization fails.
     */
    public static byte[] encode(byte[] source, byte[] target) throws IOException {
        Objects.requireNonNull(target, "Target bytes must not be null");
        byte[] src = source != null ? source : new byte[0];

        LOGGER.info("Encoding delta patch: source size=%d bytes, target size=%d bytes", src.length, target.length);

        List<DeltaInstruction> instructions = computeInstructions(src, target);
        LOGGER.debug("Generated %d delta instructions", instructions.size());

        String sourceHash = src.length > 0 ? ChecksumHasher.computeSHA256(src) : "";
        String targetHash = ChecksumHasher.computeSHA256(target);

        // Serialize to SFPH format
        BinarySerializer serializer = new BinarySerializer();
        serializer.writeBytes(MAGIC.getBytes(StandardCharsets.US_ASCII));
        serializer.writeShort(VERSION);
        serializer.writeString(sourceHash);
        serializer.writeString(targetHash);

        serializer.writeInt(instructions.size());
        for (DeltaInstruction inst : instructions) {
            switch (inst.type()) {
                case ADD -> {
                    serializer.writeBytes(new byte[]{1});
                    serializer.writeInt(inst.length());
                    serializer.writeBytes(inst.data());
                }
                case COPY -> {
                    serializer.writeBytes(new byte[]{2});
                    serializer.writeLong(inst.offset());
                    serializer.writeInt(inst.length());
                }
                case RUN -> {
                    serializer.writeBytes(new byte[]{3});
                    serializer.writeInt(inst.length());
                    serializer.writeBytes(new byte[]{inst.data()[0]});
                }
            }
        }

        byte[] patchData = serializer.toByteArray();
        LOGGER.info("Serialized patch size: %d bytes (%.2f%% of target size)",
            patchData.length, (patchData.length * 100.0) / Math.max(1, target.length));

        return patchData;
    }

    private static List<DeltaInstruction> computeInstructions(byte[] source, byte[] target) {
        List<DeltaInstruction> instructions = new ArrayList<>();
        ByteArrayOutputStream addBuffer = new ByteArrayOutputStream();

        int t = 0;
        while (t < target.length) {
            int bestMatchSrcOffset = -1;
            int bestMatchLen = 0;

            // 1. Look for matching block in source
            if (source.length >= MIN_MATCH_LEN) {
                int maxSearchLimit = Math.min(target.length - t, 4096); // limit match length to 4KB
                if (maxSearchLimit >= MIN_MATCH_LEN) {
                    for (int s = 0; s <= source.length - MIN_MATCH_LEN; s++) {
                        int matchLen = 0;
                        while (matchLen < maxSearchLimit &&
                               (s + matchLen) < source.length &&
                               source[s + matchLen] == target[t + matchLen]) {
                            matchLen++;
                        }
                        if (matchLen > bestMatchLen) {
                            bestMatchLen = matchLen;
                            bestMatchSrcOffset = s;
                        }
                    }
                }
            }

            if (bestMatchLen >= MIN_MATCH_LEN) {
                // Flush accumulated ADD buffer
                flushAdd(addBuffer, instructions);

                // Add COPY instruction
                instructions.add(new DeltaInstruction(DeltaInstruction.Type.COPY, bestMatchSrcOffset, bestMatchLen, null));
                t += bestMatchLen;
                continue;
            }

            // 2. Look for repeating RUN bytes in target
            int runLen = 0;
            byte runByte = target[t];
            while ((t + runLen) < target.length && target[t + runLen] == runByte) {
                runLen++;
            }

            if (runLen >= MIN_MATCH_LEN) {
                // Flush accumulated ADD buffer
                flushAdd(addBuffer, instructions);

                // Add RUN instruction
                instructions.add(new DeltaInstruction(DeltaInstruction.Type.RUN, 0, runLen, new byte[]{runByte}));
                t += runLen;
                continue;
            }

            // 3. Fallback: accumulate literal byte
            addBuffer.write(target[t]);
            t++;
        }

        // Flush remaining ADD buffer
        flushAdd(addBuffer, instructions);
        return instructions;
    }

    private static void flushAdd(ByteArrayOutputStream addBuffer, List<DeltaInstruction> instructions) {
        if (addBuffer.size() > 0) {
            byte[] bytes = addBuffer.toByteArray();
            instructions.add(new DeltaInstruction(DeltaInstruction.Type.ADD, 0, bytes.length, bytes));
            addBuffer.reset();
        }
    }
}
