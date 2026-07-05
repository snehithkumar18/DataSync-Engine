package com.syncforge.metadata;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Represents a symbolic link entry in the filesystem, extending {@link EntryMetadata}.
 * 
 * <p>Tracks the symlink target path (where the link points). Provides resolution checks
 * to determine whether the target exists, is absolute, or if circular symbolic links exist
 * in a traversal path.</p>
 */
public final class SymlinkEntry extends EntryMetadata {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(SymlinkEntry.class);

    private final String targetPath;

    /**
     * Constructs a new {@link SymlinkEntry} representation.
     * 
     * @param rawPath    the raw path of the symlink. Must not be null or blank.
     * @param timestamp  the timestamps of the symlink. Must not be null.
     * @param mode       the file mode (type and permissions). Must not be null.
     * @param size       the size of the symlink in bytes (typically target path string length). Must not be negative.
     * @param targetPath the path the symlink points to. Must not be null or blank.
     * @throws ValidationException  if any parameters violate constraints.
     * @throws NullPointerException if any mandatory parameters are null.
     */
    public SymlinkEntry(
            String rawPath,
            FileTimestamp timestamp,
            FileMode mode,
            long size,
            String targetPath) {
        super(rawPath, timestamp, mode, size);
        
        Objects.requireNonNull(targetPath, "Symlink target path must not be null");
        if (targetPath.isBlank()) {
            throw new ValidationException("Symlink target path must not be empty or blank.");
        }
        
        // Target path is also normalized using the entry path standard
        this.targetPath = normalizePath(targetPath);

        LOGGER.trace("SymlinkEntry successfully constructed for: %s pointing to: %s", 
            getNormalizedPath(), this.targetPath);
    }

    /**
     * Resolves a {@link SymlinkEntry} from the physical filesystem.
     * Reads timestamps, permissions, size, and reads the symbolic link target path.
     * 
     * @param path the filesystem symlink {@link Path}. Must not be null.
     * @return a fully populated {@link SymlinkEntry}.
     * @throws IOException          if an I/O error occurs reading attributes or the symlink target.
     * @throws NullPointerException if the path is null.
     */
    public static SymlinkEntry fromPath(Path path) throws IOException {
        Objects.requireNonNull(path, "Path must not be null");
        LOGGER.trace("Resolving SymlinkEntry from physical path: %s", path);

        if (!Files.isSymbolicLink(path)) {
            throw new ValidationException("Path is not a symbolic link: " + path);
        }

        FileTimestamp timestamp = FileTimestamp.fromPath(path);
        FileMode mode = FileMode.fromPath(path);
        long size = Files.size(path);
        
        Path target;
        try {
            target = Files.readSymbolicLink(path);
        } catch (IOException e) {
            LOGGER.error(e, "Failed to read symbolic link target for path: %s", path);
            throw e;
        }

        LOGGER.debug("Resolved SymlinkEntry for '%s' pointing to '%s'", path, target);
        return new SymlinkEntry(path.toString(), timestamp, mode, size, target.toString());
    }

    /**
     * Gets the normalized path of the target that this symbolic link references.
     * 
     * @return the normalized target path.
     */
    public String getTargetPath() {
        return targetPath;
    }

    /**
     * Checks if the symlink target path is an absolute path.
     * 
     * @return true if the target path is absolute.
     */
    public boolean isAbsoluteTarget() {
        // Absolute Windows path (e.g. "C:/...") or absolute Unix path (starts with "/")
        return targetPath.startsWith("/") || targetPath.matches("^[a-zA-Z]:/.*");
    }

    /**
     * Resolves the target relative to the directory containing this symbolic link.
     * 
     * @param symlinkPath the actual path of the symlink file. Must not be null.
     * @return the resolved target {@link Path}.
     */
    public Path resolveTarget(Path symlinkPath) {
        Objects.requireNonNull(symlinkPath, "Symlink path must not be null");
        Path parent = symlinkPath.toAbsolutePath().getParent();
        if (parent == null || isAbsoluteTarget()) {
            return Path.of(targetPath);
        }
        return parent.resolve(targetPath).normalize();
    }

    /**
     * Verifies if the resolved target path exists on the physical filesystem.
     * 
     * @param symlinkPath the actual path of the symlink file. Must not be null.
     * @return true if the target exists.
     */
    public boolean targetExists(Path symlinkPath) {
        Path resolved = resolveTarget(symlinkPath);
        return Files.exists(resolved);
    }

    /**
     * Detects if the symlink forms a circular reference loop during resolution traversal.
     * 
     * @param symlinkPath the actual path of the symlink file. Must not be null.
     * @param maxDepth    the maximum lookup depth before concluding circularity (e.g., 20). Must be greater than 0.
     * @return true if a circular reference is detected, or lookup limits are exceeded.
     * @throws IllegalArgumentException if maxDepth is less than or equal to 0.
     */
    public boolean isCircular(Path symlinkPath, int maxDepth) {
        Objects.requireNonNull(symlinkPath, "Symlink path must not be null");
        if (maxDepth <= 0) {
            throw new IllegalArgumentException("Max depth must be greater than zero.");
        }

        Path target = symlinkPath.toAbsolutePath();
        Path originalTarget = target;

        for (int i = 0; i < maxDepth; i++) {
            if (!Files.isSymbolicLink(target)) {
                return false;
            }
            try {
                Path nextTarget = Files.readSymbolicLink(target);
                Path parent = target.getParent();
                if (parent != null && !nextTarget.isAbsolute()) {
                    target = parent.resolve(nextTarget).normalize();
                } else {
                    target = nextTarget.normalize();
                }

                if (target.equals(originalTarget)) {
                    LOGGER.warn("Circular symbolic link chain detected at: %s", originalTarget);
                    return true;
                }
            } catch (IOException e) {
                // Link is broken or unreadable; circular path is broken/unreachable
                return false;
            }
        }
        
        LOGGER.warn("Symbolic link chain at '%s' exceeded max traversal depth (%d). Flagging as circular.", 
            originalTarget, maxDepth);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SymlinkEntry other)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(targetPath, other.targetPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), targetPath);
    }

    @Override
    public String toString() {
        return String.format("SymlinkEntry[path=%s, target=%s]", getNormalizedPath(), targetPath);
    }

    /**
     * Creates a builder to configure and construct a {@link SymlinkEntry}.
     * 
     * @return a new {@link Builder}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing {@link SymlinkEntry} instances.
     */
    public static final class Builder {
        private String path;
        private FileTimestamp timestamp;
        private FileMode mode;
        private long size = 0;
        private String targetPath;

        private Builder() {}

        /**
         * Sets the symlink path.
         * 
         * @param path the path. Must not be null.
         * @return this builder.
         */
        public Builder path(String path) {
            this.path = Objects.requireNonNull(path, "Path must not be null");
            return this;
        }

        /**
         * Sets the timestamps of the symlink entry.
         * 
         * @param timestamp the timestamps. Must not be null.
         * @return this builder.
         */
        public Builder timestamp(FileTimestamp timestamp) {
            this.timestamp = Objects.requireNonNull(timestamp, "Timestamp must not be null");
            return this;
        }

        /**
         * Sets the symlink file mode.
         * 
         * @param mode the file mode. Must not be null.
         * @return this builder.
         */
        public Builder mode(FileMode mode) {
            this.mode = Objects.requireNonNull(mode, "Mode must not be null");
            return this;
        }

        /**
         * Sets the symlink size.
         * 
         * @param size the size in bytes. Must not be negative.
         * @return this builder.
         * @throws ValidationException if size is negative.
         */
        public Builder size(long size) {
            if (size < 0) {
                throw new ValidationException("Size must not be negative: " + size);
            }
            this.size = size;
            return this;
        }

        /**
         * Sets the symlink target path.
         * 
         * @param targetPath the target path. Must not be null.
         * @return this builder.
         */
        public Builder targetPath(String targetPath) {
            this.targetPath = Objects.requireNonNull(targetPath, "Target path must not be null");
            return this;
        }

        /**
         * Builds a {@link SymlinkEntry} instance.
         * 
         * @return the constructed {@link SymlinkEntry}.
         * @throws ValidationException if any required parameter is missing or invalid.
         */
        public SymlinkEntry build() {
            if (path == null) {
                throw new ValidationException("Path must be configured to build a SymlinkEntry.");
            }
            if (timestamp == null) {
                throw new ValidationException("Timestamp must be configured to build a SymlinkEntry.");
            }
            if (mode == null) {
                mode = FileMode.builder().type(FileMode.Type.SYMLINK).permissions(0777).build();
            }
            if (targetPath == null) {
                throw new ValidationException("Target path must be configured to build a SymlinkEntry.");
            }
            return new SymlinkEntry(path, timestamp, mode, size, targetPath);
        }
    }
}
