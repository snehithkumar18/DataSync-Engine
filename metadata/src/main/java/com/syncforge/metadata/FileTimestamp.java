package com.syncforge.metadata;

import com.syncforge.core.logging.SyncForgeLogger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a set of file system timestamps, tracking modification time (mtime),
 * creation time (ctime), and last access time (atime) with nanosecond precision.
 * 
 * <p>Uses Java's {@link Instant} to store the timestamps, ensuring nanosecond accuracy,
 * and provides static factory methods to query timestamps directly from the file system attributes.</p>
 * 
 * @param mtime the last modification time. Must not be null.
 * @param ctime the creation time. Must not be null.
 * @param atime the last access time. Must not be null.
 */
public record FileTimestamp(Instant mtime, Instant ctime, Instant atime) {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(FileTimestamp.class);

    /**
     * Compact constructor to validate that none of the timestamps are null.
     * 
     * @throws NullPointerException if mtime, ctime, or atime is null.
     */
    public FileTimestamp {
        Objects.requireNonNull(mtime, "Modification time (mtime) must not be null");
        Objects.requireNonNull(ctime, "Creation time (ctime) must not be null");
        Objects.requireNonNull(atime, "Access time (atime) must not be null");
    }

    /**
     * Creates a {@link FileTimestamp} with all times set to the current instant.
     * 
     * @return a new {@link FileTimestamp} initialized to {@link Instant#now()}.
     */
    public static FileTimestamp now() {
        Instant current = Instant.now();
        return new FileTimestamp(current, current, current);
    }

    /**
     * Creates a {@link FileTimestamp} using milliseconds-since-epoch values.
     * 
     * @param mtimeMillis modification time in milliseconds.
     * @param ctimeMillis creation time in milliseconds.
     * @param atimeMillis access time in milliseconds.
     * @return a new {@link FileTimestamp} representing the specified epoch milliseconds.
     */
    public static FileTimestamp fromEpochMilli(long mtimeMillis, long ctimeMillis, long atimeMillis) {
        return new FileTimestamp(
            Instant.ofEpochMilli(mtimeMillis),
            Instant.ofEpochMilli(ctimeMillis),
            Instant.ofEpochMilli(atimeMillis)
        );
    }

    /**
     * Reads and parses standard file attributes from the specified {@link Path}
     * to build a {@link FileTimestamp} instance.
     * 
     * @param path the path from which to read file attributes. Must not be null.
     * @return a parsed {@link FileTimestamp} with nanosecond-precision values.
     * @throws IOException          if an I/O error occurs while reading the attributes.
     * @throws NullPointerException if the path is null.
     */
    public static FileTimestamp fromPath(Path path) throws IOException {
        Objects.requireNonNull(path, "Path must not be null");
        LOGGER.trace("Reading file attributes for timestamp generation from path: %s", path);
        try {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            Instant mtime = attrs.lastModifiedTime().toInstant();
            Instant ctime = attrs.creationTime().toInstant();
            Instant atime = attrs.lastAccessTime().toInstant();
            
            LOGGER.debug("Successfully read file timestamps for '%s' (mtime=%s, ctime=%s, atime=%s)", 
                path.toString().replace('\\', '/'), mtime, ctime, atime);
            return new FileTimestamp(mtime, ctime, atime);
        } catch (IOException e) {
            LOGGER.error(e, "Failed to read file attributes for path: %s", path);
            throw e;
        }
    }

    /**
     * Convenience method to retrieve the modification time (mtime) as an {@link Instant}.
     * 
     * @return the modification time {@link Instant}.
     */
    public Instant toInstant() {
        return mtime;
    }

    /**
     * Returns the modification time in milliseconds since the epoch.
     * 
     * @return epoch milliseconds for mtime.
     */
    public long mtimeMillis() {
        return mtime.toEpochMilli();
    }

    /**
     * Returns the creation time in milliseconds since the epoch.
     * 
     * @return epoch milliseconds for ctime.
     */
    public long ctimeMillis() {
        return ctime.toEpochMilli();
    }

    /**
     * Returns the access time in milliseconds since the epoch.
     * 
     * @return epoch milliseconds for atime.
     */
    public long atimeMillis() {
        return atime.toEpochMilli();
    }

    /**
     * Returns a new {@link FileTimestamp} with the modification time updated.
     * 
     * @param newMtime the new modification time. Must not be null.
     * @return a new updated {@link FileTimestamp} instance.
     * @throws NullPointerException if newMtime is null.
     */
    public FileTimestamp withMtime(Instant newMtime) {
        return new FileTimestamp(newMtime, this.ctime, this.atime);
    }

    /**
     * Returns a new {@link FileTimestamp} with the creation time updated.
     * 
     * @param newCtime the new creation time. Must not be null.
     * @return a new updated {@link FileTimestamp} instance.
     * @throws NullPointerException if newCtime is null.
     */
    public FileTimestamp withCtime(Instant newCtime) {
        return new FileTimestamp(this.mtime, newCtime, this.atime);
    }

    /**
     * Returns a new {@link FileTimestamp} with the access time updated.
     * 
     * @param newAtime the new access time. Must not be null.
     * @return a new updated {@link FileTimestamp} instance.
     * @throws NullPointerException if newAtime is null.
     */
    public FileTimestamp withAtime(Instant newAtime) {
        return new FileTimestamp(this.mtime, this.ctime, newAtime);
    }

    /**
     * Compares this timestamp with another to see if this modification time is newer.
     * 
     * @param other the other {@link FileTimestamp} to compare against. Must not be null.
     * @return true if this modification time is strictly after the other's modification time.
     * @throws NullPointerException if other is null.
     */
    public boolean isNewerThan(FileTimestamp other) {
        Objects.requireNonNull(other, "Other timestamp must not be null");
        return this.mtime.isAfter(other.mtime);
    }

    @Override
    public String toString() {
        return String.format("FileTimestamp[mtime=%s, ctime=%s, atime=%s]", mtime, ctime, atime);
    }
}
