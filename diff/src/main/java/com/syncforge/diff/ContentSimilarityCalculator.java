package com.syncforge.diff;

import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.metadata.EntryMetadata;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Calculates content similarity scores between two files.
 * Uses shingle Jaccard similarity when physical files are present,
 * and falls back to weighted metadata similarity heuristics if files are missing.
 */
public class ContentSimilarityCalculator {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(ContentSimilarityCalculator.class);
    private static final int SHINGLE_SIZE = 5;

    /**
     * Private constructor to prevent instantiation.
     */
    private ContentSimilarityCalculator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Calculates a similarity score between 0.0 and 1.0.
     *
     * @param before    the metadata of the baseline entry. Must not be null.
     * @param after     the metadata of the target entry. Must not be null.
     * @param baseDir   the directory containing the baseline files. May be null.
     * @param targetDir the directory containing the target files. May be null.
     * @return a double value between 0.0 and 1.0 (1.0 meaning identical).
     */
    public static double calculate(EntryMetadata before, EntryMetadata after, String baseDir, String targetDir) {
        Objects.requireNonNull(before, "Before entry must not be null");
        Objects.requireNonNull(after, "After entry must not be null");

        // Check type matches
        if (before.getMode().getType() != after.getMode().getType()) {
            return 0.0;
        }

        // Try reading content
        byte[] bytesBefore = null;
        byte[] bytesAfter = null;

        if (baseDir != null) {
            Path pBefore = Paths.get(baseDir).resolve(before.getNormalizedPath());
            if (Files.exists(pBefore) && !Files.isDirectory(pBefore)) {
                try {
                    bytesBefore = Files.readAllBytes(pBefore);
                } catch (IOException e) {
                    LOGGER.warn("Failed to read baseline file: %s", pBefore);
                }
            }
        }

        if (targetDir != null) {
            Path pAfter = Paths.get(targetDir).resolve(after.getNormalizedPath());
            if (Files.exists(pAfter) && !Files.isDirectory(pAfter)) {
                try {
                    bytesAfter = Files.readAllBytes(pAfter);
                } catch (IOException e) {
                    LOGGER.warn("Failed to read target file: %s", pAfter);
                }
            }
        }

        if (bytesBefore != null && bytesAfter != null) {
            return calculateShingleSimilarity(bytesBefore, bytesAfter);
        }

        // Fallback to metadata similarity
        return calculateMetadataHeuristics(before, after);
    }

    private static double calculateShingleSimilarity(byte[] a, byte[] b) {
        if (a.length == 0 && b.length == 0) {
            return 1.0;
        }
        if (a.length == 0 || b.length == 0) {
            return 0.0;
        }

        Set<Long> shinglesA = getShingles(a);
        Set<Long> shinglesB = getShingles(b);

        if (shinglesA.isEmpty() && shinglesB.isEmpty()) {
            return 1.0;
        }
        if (shinglesA.isEmpty() || shinglesB.isEmpty()) {
            return 0.0;
        }

        int intersectionSize = 0;
        for (Long shingle : shinglesA) {
            if (shinglesB.contains(shingle)) {
                intersectionSize++;
            }
        }

        int unionSize = shinglesA.size() + shinglesB.size() - intersectionSize;
        return (double) intersectionSize / unionSize;
    }

    private static Set<Long> getShingles(byte[] bytes) {
        Set<Long> shingles = new HashSet<>();
        if (bytes.length < SHINGLE_SIZE) {
            // Simple single hash for small content
            long hash = 0;
            for (byte b : bytes) {
                hash = (hash * 31) + (b & 0xFF);
            }
            shingles.add(hash);
            return shingles;
        }

        for (int i = 0; i <= bytes.length - SHINGLE_SIZE; i++) {
            long windowHash = 0;
            for (int j = 0; j < SHINGLE_SIZE; j++) {
                windowHash = (windowHash * 31) + (bytes[i + j] & 0xFF);
            }
            shingles.add(windowHash);
        }
        return shingles;
    }

    private static double calculateMetadataHeuristics(EntryMetadata before, EntryMetadata after) {
        // Compute path similarity
        String p1 = before.getNormalizedPath();
        String p2 = after.getNormalizedPath();

        String n1 = p1.substring(p1.lastIndexOf('/') + 1);
        String n2 = p2.substring(p2.lastIndexOf('/') + 1);

        double nameSim = calculateStringSimilarity(n1, n2);
        
        // Compute size similarity
        double sizeSim = 0.0;
        long s1 = before.getSize();
        long s2 = after.getSize();
        if (s1 == 0 && s2 == 0) {
            sizeSim = 1.0;
        } else {
            sizeSim = 1.0 - ((double) Math.abs(s1 - s2) / Math.max(s1, s2));
        }

        // Weighted score: 60% name similarity, 40% size similarity
        return (nameSim * 0.6) + (sizeSim * 0.4);
    }

    private static double calculateStringSimilarity(String a, String b) {
        if (a.isEmpty() && b.isEmpty()) {
            return 1.0;
        }
        if (a.isEmpty() || b.isEmpty()) {
            return 0.0;
        }

        Set<String> shinglesA = getStringShingles(a);
        Set<String> shinglesB = getStringShingles(b);

        int intersection = 0;
        for (String s : shinglesA) {
            if (shinglesB.contains(s)) {
                intersection++;
            }
        }
        int union = shinglesA.size() + shinglesB.size() - intersection;
        return (double) intersection / union;
    }

    private static Set<String> getStringShingles(String str) {
        Set<String> shingles = new HashSet<>();
        int k = Math.min(3, str.length());
        for (int i = 0; i <= str.length() - k; i++) {
            shingles.add(str.substring(i, i + k));
        }
        return shingles;
    }
}
