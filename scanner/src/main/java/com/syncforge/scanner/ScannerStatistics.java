package com.syncforge.scanner;

import java.util.HashMap;
import java.util.Map;

/**
 * Collects and reports statistics about scanning operations.
 * Provides detailed metrics about scan performance and results.
 */
public class ScannerStatistics {
    
    private final long startTime;
    private long endTime;
    private long totalFilesScanned;
    private long totalDirectoriesScanned;
    private long totalSymlinksScanned;
    private long totalBytesScanned;
    private long totalErrors;
    private long skippedFiles;
    private Map<String, Long> fileExtensionCounts;
    private Map<String, Long> directoryDepthCounts;
    private long averageFileSize;
    private long maxFileSize;
    private long minFileSize;
    
    public ScannerStatistics() {
        this.startTime = System.currentTimeMillis();
        this.fileExtensionCounts = new HashMap<>();
        this.directoryDepthCounts = new HashMap<>();
        this.maxFileSize = 0;
        this.minFileSize = Long.MAX_VALUE;
    }
    
    /**
     * Records a scanned file.
     */
    public void recordFile(long size, String extension, int depth) {
        totalFilesScanned++;
        totalBytesScanned += size;
        
        // Update file extension counts
        fileExtensionCounts.put(extension, fileExtensionCounts.getOrDefault(extension, 0L) + 1);
        
        // Update directory depth counts
        String depthKey = String.valueOf(depth);
        directoryDepthCounts.put(depthKey, directoryDepthCounts.getOrDefault(depthKey, 0L) + 1);
        
        // Update size statistics
        if (size > maxFileSize) {
            maxFileSize = size;
        }
        if (size < minFileSize) {
            minFileSize = size;
        }
    }
    
    /**
     * Records a scanned directory.
     */
    public void recordDirectory() {
        totalDirectoriesScanned++;
    }
    
    /**
     * Records a scanned symlink.
     */
    public void recordSymlink() {
        totalSymlinksScanned++;
    }
    
    /**
     * Records an error.
     */
    public void recordError() {
        totalErrors++;
    }
    
    /**
     * Records a skipped file.
     */
    public void recordSkipped() {
        skippedFiles++;
    }
    
    /**
     * Marks the scan as complete.
     */
    public void complete() {
        this.endTime = System.currentTimeMillis();
        
        // Calculate average file size
        if (totalFilesScanned > 0) {
            averageFileSize = totalBytesScanned / totalFilesScanned;
        }
        
        if (minFileSize == Long.MAX_VALUE) {
            minFileSize = 0;
        }
    }
    
    /**
     * Gets the scan duration in milliseconds.
     */
    public long getDuration() {
        long end = endTime > 0 ? endTime : System.currentTimeMillis();
        return end - startTime;
    }
    
    /**
     * Gets the scan duration in seconds.
     */
    public double getDurationSeconds() {
        return getDuration() / 1000.0;
    }
    
    /**
     * Gets the scanning rate in files per second.
     */
    public double getFilesPerSecond() {
        double duration = getDurationSeconds();
        return duration > 0 ? totalFilesScanned / duration : 0;
    }
    
    /**
     * Gets the scanning rate in bytes per second.
     */
    public double getBytesPerSecond() {
        double duration = getDurationSeconds();
        return duration > 0 ? totalBytesScanned / duration : 0;
    }
    
    /**
     * Gets the scanning rate in MB per second.
     */
    public double getMegabytesPerSecond() {
        return getBytesPerSecond() / (1024 * 1024);
    }
    
    /**
     * Gets total files scanned.
     */
    public long getTotalFilesScanned() {
        return totalFilesScanned;
    }
    
    /**
     * Gets total directories scanned.
     */
    public long getTotalDirectoriesScanned() {
        return totalDirectoriesScanned;
    }
    
    /**
     * Gets total symlinks scanned.
     */
    public long getTotalSymlinksScanned() {
        return totalSymlinksScanned;
    }
    
    /**
     * Gets total bytes scanned.
     */
    public long getTotalBytesScanned() {
        return totalBytesScanned;
    }
    
    /**
     * Gets total errors encountered.
     */
    public long getTotalErrors() {
        return totalErrors;
    }
    
    /**
     * Gets skipped files count.
     */
    public long getSkippedFiles() {
        return skippedFiles;
    }
    
    /**
     * Gets file extension counts.
     */
    public Map<String, Long> getFileExtensionCounts() {
        return new HashMap<>(fileExtensionCounts);
    }
    
    /**
     * Gets directory depth counts.
     */
    public Map<String, Long> getDirectoryDepthCounts() {
        return new HashMap<>(directoryDepthCounts);
    }
    
    /**
     * Gets average file size.
     */
    public long getAverageFileSize() {
        return averageFileSize;
    }
    
    /**
     * Gets maximum file size.
     */
    public long getMaxFileSize() {
        return maxFileSize;
    }
    
    /**
     * Gets minimum file size.
     */
    public long getMinFileSize() {
        return minFileSize;
    }
    
    /**
     * Gets the most common file extension.
     */
    public String getMostCommonExtension() {
        return fileExtensionCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("");
    }
    
    /**
     * Gets the most common directory depth.
     */
    public int getMostCommonDepth() {
        return directoryDepthCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .map(Integer::parseInt)
            .orElse(0);
    }
    
    /**
     * Generates a summary report.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Scan Statistics:\n");
        report.append("================\n");
        report.append(String.format("Duration: %.2f seconds\n", getDurationSeconds()));
        report.append(String.format("Files scanned: %d\n", totalFilesScanned));
        report.append(String.format("Directories scanned: %d\n", totalDirectoriesScanned));
        report.append(String.format("Symlinks scanned: %d\n", totalSymlinksScanned));
        report.append(String.format("Total bytes: %d (%.2f MB)\n", totalBytesScanned, 
                                   totalBytesScanned / (1024.0 * 1024.0)));
        report.append(String.format("Errors: %d\n", totalErrors));
        report.append(String.format("Skipped: %d\n", skippedFiles));
        report.append(String.format("Scan rate: %.2f files/sec\n", getFilesPerSecond()));
        report.append(String.format("Throughput: %.2f MB/sec\n", getMegabytesPerSecond()));
        report.append(String.format("Average file size: %d bytes\n", averageFileSize));
        report.append(String.format("Min file size: %d bytes\n", minFileSize));
        report.append(String.format("Max file size: %d bytes\n", maxFileSize));
        report.append(String.format("Most common extension: %s\n", getMostCommonExtension()));
        report.append(String.format("Most common depth: %d\n", getMostCommonDepth()));
        
        return report.toString();
    }
    
    /**
     * Creates a statistics collector.
     */
    public static ScannerStatistics create() {
        return new ScannerStatistics();
    }
}
