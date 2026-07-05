package com.syncforge.metadata;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.util.Objects;

/**
 * An abstract base class representing the core metadata properties shared by all
 * filesystem entries (files, directories, symlinks, etc.) tracked within the SyncForge synchronization engine.
 * 
 * <p>Ensures that paths are always normalized to use Unix-style forward slashes ('/')
 * and that parent paths are correctly computed. Inputs are strictly validated at construction time.</p>
 */
public abstract class EntryMetadata {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(EntryMetadata.class);

    private final String normalizedPath;
    private final String parentPath;
    private final FileTimestamp timestamp;
    private final FileMode mode;
    private final long size;

    /**
     * Initializes a new {@link EntryMetadata} instance with core file attributes.
     * 
     * @param rawPath   the raw path of the entry. Must not be null or blank.
     * @param timestamp the timestamps associated with the entry. Must not be null.
     * @param mode      the file mode defining entry type and permissions. Must not be null.
     * @param size      the size of the entry in bytes. Must not be negative.
     * @throws ValidationException if the rawPath is null/blank, or if size is negative.
     * @throws NullPointerException if timestamp or mode is null.
     */
    protected EntryMetadata(String rawPath, FileTimestamp timestamp, FileMode mode, long size) {
        Objects.requireNonNull(rawPath, "Raw path must not be null");
        if (rawPath.isBlank()) {
            throw new ValidationException("Entry path must not be empty or blank.");
        }
        this.timestamp = Objects.requireNonNull(timestamp, "Timestamp must not be null");
        this.mode = Objects.requireNonNull(mode, "Mode must not be null");
        if (size < 0) {
            throw new ValidationException("Entry size must not be negative: " + size);
        }

        this.normalizedPath = normalizePath(rawPath);
        this.parentPath = computeParentPath(this.normalizedPath);
        this.size = size;

        LOGGER.trace("EntryMetadata initialized for path='%s' (parent='%s', size=%d, type=%s)",
            this.normalizedPath, this.parentPath, this.size, this.mode.getType());
    }

    /**
     * Normalizes a file system path, converting all backslashes to Unix forward slashes,
     * trimming leading/trailing spaces, and resolving duplicate slashes or trailing slashes.
     * 
     * @param rawPath the raw path to normalize. Must not be null.
     * @return the normalized path.
     */
    public static String normalizePath(String rawPath) {
        Objects.requireNonNull(rawPath, "Path to normalize must not be null");
        String normalized = rawPath.replace('\\', '/').trim();
        
        // Remove duplicate slashes (e.g. "//" -> "/")
        while (normalized.contains("//")) {
            normalized = normalized.replace("//", "/");
        }
        
        // Remove trailing slash unless it is the root path "/"
        if (normalized.length() > 1 && normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        
        if (normalized.isEmpty()) {
            return ".";
        }
        
        return normalized;
    }

    /**
     * Automatically computes the parent directory path from a normalized path.
     * 
     * @param normalizedPath the normalized path of the entry. Must not be null.
     * @return the parent path, or null if the entry represents the root.
     */
    public static String computeParentPath(String normalizedPath) {
        Objects.requireNonNull(normalizedPath, "Normalized path must not be null");
        if (normalizedPath.equals("/") || normalizedPath.equals(".")) {
            return null;
        }
        
        int lastSlash = normalizedPath.lastIndexOf('/');
        if (lastSlash < 0) {
            // Top-level relative file (e.g., "file.txt") -> parent is current directory "."
            return ".";
        }
        if (lastSlash == 0) {
            // Absolute path in root (e.g., "/file.txt") -> parent is root "/"
            return "/";
        }
        
        return normalizedPath.substring(0, lastSlash);
    }

    /**
     * Gets the normalized path of the filesystem entry.
     * 
     * @return the normalized path.
     */
    public final String getNormalizedPath() {
        return normalizedPath;
    }

    /**
     * Gets the parent path of the filesystem entry.
     * 
     * @return the parent path, or null if this is a root path.
     */
    public final String getParentPath() {
        return parentPath;
    }

    /**
     * Gets the timestamps associated with the entry (mtime, ctime, atime).
     * 
     * @return the {@link FileTimestamp}.
     */
    public final FileTimestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the file mode defining the type and permissions of the entry.
     * 
     * @return the {@link FileMode}.
     */
    public final FileMode getMode() {
        return mode;
    }

    /**
     * Gets the size of the filesystem entry in bytes.
     * 
     * @return the size in bytes.
     */
    public final long getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntryMetadata other)) return false;
        return size == other.size &&
               Objects.equals(normalizedPath, other.normalizedPath) &&
               Objects.equals(parentPath, other.parentPath) &&
               Objects.equals(timestamp, other.timestamp) &&
               Objects.equals(mode, other.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalizedPath, parentPath, timestamp, mode, size);
    }

    @Override
    public String toString() {
        return String.format("EntryMetadata[path=%s, size=%d, type=%s, timestamp=%s]", 
            normalizedPath, size, mode.getType(), timestamp);
    }
}
