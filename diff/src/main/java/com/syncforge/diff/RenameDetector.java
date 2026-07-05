package com.syncforge.diff;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;

import java.util.*;

/**
 * Detects file renames by comparing content similarity and metadata.
 * Uses multiple heuristics to identify likely rename operations.
 */
public class RenameDetector {
    
    private final double similarityThreshold;
    private final boolean considerSize;
    private final boolean considerChecksum;
    private final boolean considerNameSimilarity;
    
    public RenameDetector() {
        this(0.8, true, true, true);
    }
    
    public RenameDetector(double similarityThreshold, boolean considerSize, 
                         boolean considerChecksum, boolean considerNameSimilarity) {
        this.similarityThreshold = Math.max(0.0, Math.min(1.0, similarityThreshold));
        this.considerSize = considerSize;
        this.considerChecksum = considerChecksum;
        this.considerNameSimilarity = considerNameSimilarity;
    }
    
    /**
     * Detects renames between two sets of entries.
     */
    public List<RenameOperation> detectRenames(Map<String, EntryMetadata> oldEntries,
                                               Map<String, EntryMetadata> newEntries) {
        List<RenameOperation> renames = new ArrayList<>();
        
        // Find deleted and added files
        List<FileEntry> deletedFiles = new ArrayList<>();
        List<FileEntry> addedFiles = new ArrayList<>();
        
        for (EntryMetadata entry : oldEntries.values()) {
            if (entry instanceof FileEntry fileEntry && !newEntries.containsKey(entry.getNormalizedPath())) {
                deletedFiles.add(fileEntry);
            }
        }
        
        for (EntryMetadata entry : newEntries.values()) {
            if (entry instanceof FileEntry fileEntry && !oldEntries.containsKey(entry.getNormalizedPath())) {
                addedFiles.add(fileEntry);
            }
        }
        
        // Compare each deleted file with each added file
        for (FileEntry deleted : deletedFiles) {
            for (FileEntry added : addedFiles) {
                double similarity = calculateRenameSimilarity(deleted, added);
                
                if (similarity >= similarityThreshold) {
                    renames.add(new RenameOperation(deleted.getNormalizedPath(), added.getNormalizedPath(), similarity));
                }
            }
        }
        
        // Sort by similarity (highest first) and resolve conflicts
        renames.sort((a, b) -> Double.compare(b.similarity(), a.similarity()));
        renames = resolveConflicts(renames);
        
        return renames;
    }
    
    /**
     * Calculates similarity score between two files for rename detection.
     */
    private double calculateRenameSimilarity(FileEntry a, FileEntry b) {
        double score = 0.0;
        int factors = 0;
        
        // Size similarity
        if (considerSize) {
            double sizeScore = calculateSizeSimilarity(a.getSize(), b.getSize());
            score += sizeScore;
            factors++;
        }
        
        // Checksum similarity
        if (considerChecksum && a.getContentHash() != null && b.getContentHash() != null) {
            double checksumScore = a.getContentHash().equals(b.getContentHash()) ? 1.0 : 0.0;
            score += checksumScore * 2.0; // Weight checksum more heavily
            factors += 2;
        }
        
        // Name similarity
        if (considerNameSimilarity) {
            double nameScore = calculateNameSimilarity(a.getNormalizedPath(), b.getNormalizedPath());
            score += nameScore * 0.5; // Weight name less heavily
            factors++;
        }
        
        return factors > 0 ? score / factors : 0.0;
    }
    
    /**
     * Calculates size similarity between two files.
     */
    private double calculateSizeSimilarity(long sizeA, long sizeB) {
        if (sizeA == sizeB) {
            return 1.0;
        }
        
        if (sizeA == 0 || sizeB == 0) {
            return 0.0;
        }
        
        long maxSize = Math.max(sizeA, sizeB);
        long minSize = Math.min(sizeA, sizeB);
        
        return (double) minSize / maxSize;
    }
    
    /**
     * Calculates name similarity using Levenshtein distance.
     */
    private double calculateNameSimilarity(String pathA, String pathB) {
        String nameA = getFileName(pathA);
        String nameB = getFileName(pathB);
        
        if (nameA.equals(nameB)) {
            return 1.0;
        }
        
        int distance = levenshteinDistance(nameA, nameB);
        int maxLength = Math.max(nameA.length(), nameB.length());
        
        if (maxLength == 0) {
            return 1.0;
        }
        
        return 1.0 - ((double) distance / maxLength);
    }
    
    /**
     * Computes Levenshtein distance between two strings.
     */
    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        
        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }
        
        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                                   Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        
        return dp[a.length()][b.length()];
    }
    
    /**
     * Resolves conflicting rename operations (one-to-many mappings).
     */
    private List<RenameOperation> resolveConflicts(List<RenameOperation> renames) {
        List<RenameOperation> resolved = new ArrayList<>();
        Set<String> usedSourcePaths = new HashSet<>();
        Set<String> usedTargetPaths = new HashSet<>();
        
        for (RenameOperation rename : renames) {
            if (!usedSourcePaths.contains(rename.sourcePath()) && 
                !usedTargetPaths.contains(rename.targetPath())) {
                resolved.add(rename);
                usedSourcePaths.add(rename.sourcePath());
                usedTargetPaths.add(rename.targetPath());
            }
        }
        
        return resolved;
    }
    
    private String getFileName(String path) {
        int lastSlash = path.lastIndexOf('/');
        return lastSlash >= 0 ? path.substring(lastSlash + 1) : path;
    }
    
    /**
     * Represents a detected rename operation.
     */
    public record RenameOperation(
        String sourcePath,
        String targetPath,
        double similarity
    ) {}
    
    /**
     * Builder for creating rename detectors.
     */
    public static class Builder {
        private double similarityThreshold = 0.8;
        private boolean considerSize = true;
        private boolean considerChecksum = true;
        private boolean considerNameSimilarity = true;
        
        public Builder withSimilarityThreshold(double threshold) {
            this.similarityThreshold = threshold;
            return this;
        }
        
        public Builder withSizeComparison(boolean enabled) {
            this.considerSize = enabled;
            return this;
        }
        
        public Builder withChecksumComparison(boolean enabled) {
            this.considerChecksum = enabled;
            return this;
        }
        
        public Builder withNameSimilarity(boolean enabled) {
            this.considerNameSimilarity = enabled;
            return this;
        }
        
        public RenameDetector build() {
            return new RenameDetector(similarityThreshold, considerSize, 
                                     considerChecksum, considerNameSimilarity);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
