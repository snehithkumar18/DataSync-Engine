package com.syncforge.scanner;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Represents the final, immutable output of a file tree scan operation.
 * Contains aggregated metrics, sorted filesystem entries, and any paths that were skipped
 * due to errors or validation rejections.
 */
public final class ScanResult {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(ScanResult.class);

    private final List<EntryMetadata> entries;
    private final long totalFileSize;
    private final int fileCount;
    private final int directoryCount;
    private final int symlinkCount;
    private final long scanDurationMillis;
    private final List<SkippedPath> skippedPaths;

    /**
     * Represents a path that was skipped during the file tree scan,
     * including the path itself and the associated error details.
     * 
     * @param path         the relative path that was skipped. Must not be null or blank.
     * @param reason       the reason categorizing why it was skipped. Must not be null or blank.
     * @param errorMessage a detailed description of the error or underlying exception. Must not be null.
     */
    public record SkippedPath(String path, String reason, String errorMessage) {
        /**
         * Compact constructor to validate skipped path properties.
         *
         * @throws ValidationException if the path, reason, or errorMessage is invalid.
         */
        public SkippedPath {
            Objects.requireNonNull(path, "Skipped path must not be null");
            Objects.requireNonNull(reason, "Skipped reason must not be null");
            Objects.requireNonNull(errorMessage, "Skipped error message must not be null");
            if (path.isBlank()) {
                throw new ValidationException("Skipped path must not be empty or blank.");
            }
            if (reason.isBlank()) {
                throw new ValidationException("Skipped reason must not be empty or blank.");
            }
        }
    }

    /**
     * Constructs a new {@link ScanResult} containing the scan details and entries.
     * The entries list will be copied and sorted deterministically by normalized path.
     *
     * @param entries            the list of successfully scanned metadata entries. Must not be null.
     * @param totalFileSize      the aggregate size of all scanned files in bytes. Must not be negative.
     * @param fileCount          the total number of files scanned. Must not be negative.
     * @param directoryCount     the total number of directories scanned. Must not be negative.
     * @param symlinkCount       the total number of symbolic links scanned. Must not be negative.
     * @param scanDurationMillis the duration of the scan operation in milliseconds. Must not be negative.
     * @param skippedPaths       the list of paths skipped during scanning. Must not be null.
     * @throws ValidationException if any numeric metric is negative, or if lists are null.
     */
    public ScanResult(
            List<EntryMetadata> entries,
            long totalFileSize,
            int fileCount,
            int directoryCount,
            int symlinkCount,
            long scanDurationMillis,
            List<SkippedPath> skippedPaths) {
        
        Objects.requireNonNull(entries, "Entries list must not be null");
        Objects.requireNonNull(skippedPaths, "Skipped paths list must not be null");

        if (totalFileSize < 0) {
            throw new ValidationException("Total file size must not be negative: " + totalFileSize);
        }
        if (fileCount < 0) {
            throw new ValidationException("File count must not be negative: " + fileCount);
        }
        if (directoryCount < 0) {
            throw new ValidationException("Directory count must not be negative: " + directoryCount);
        }
        if (symlinkCount < 0) {
            throw new ValidationException("Symlink count must not be negative: " + symlinkCount);
        }
        if (scanDurationMillis < 0) {
            throw new ValidationException("Scan duration must not be negative: " + scanDurationMillis);
        }

        // Sort entries deterministically by normalized path
        List<EntryMetadata> sortedEntries = new ArrayList<>(entries);
        sortedEntries.sort(Comparator.comparing(EntryMetadata::getNormalizedPath));
        this.entries = List.copyOf(sortedEntries);

        this.totalFileSize = totalFileSize;
        this.fileCount = fileCount;
        this.directoryCount = directoryCount;
        this.symlinkCount = symlinkCount;
        this.scanDurationMillis = scanDurationMillis;
        this.skippedPaths = List.copyOf(skippedPaths);

        LOGGER.info("ScanResult initialized: entries=%d (files=%d, dirs=%d, symlinks=%d), totalSize=%d bytes, skipped=%d, duration=%d ms",
            this.entries.size(), this.fileCount, this.directoryCount, this.symlinkCount, this.totalFileSize, this.skippedPaths.size(), this.scanDurationMillis);
    }

    /**
     * Gets the unmodifiable, sorted list of scanned metadata entries.
     *
     * @return the sorted entries list.
     */
    public List<EntryMetadata> getEntries() {
        return entries;
    }

    /**
     * Gets the aggregate size of all successfully scanned files in bytes.
     *
     * @return total file size in bytes.
     */
    public long getTotalFileSize() {
        return totalFileSize;
    }

    /**
     * Gets the total number of files scanned.
     *
     * @return file count.
     */
    public int getFileCount() {
        return fileCount;
    }

    /**
     * Gets the total number of directories scanned.
     *
     * @return directory count.
     */
    public int getDirectoryCount() {
        return directoryCount;
    }

    /**
     * Gets the total number of symbolic links scanned.
     *
     * @return symlink count.
     */
    public int getSymlinkCount() {
        return symlinkCount;
    }

    /**
     * Gets the duration of the scan operation in milliseconds.
     *
     * @return scan duration.
     */
    public long getScanDurationMillis() {
        return scanDurationMillis;
    }

    /**
     * Gets the unmodifiable list of paths that were skipped during the scan.
     *
     * @return the skipped paths list.
     */
    public List<SkippedPath> getSkippedPaths() {
        return skippedPaths;
    }
}
