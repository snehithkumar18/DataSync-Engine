package com.syncforge.metadata;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a directory entry in the filesystem, extending {@link EntryMetadata}.
 * 
 * <p>Tracks directory elements (its immediate child names) to detect changes in directory structures
 * (additions, deletions, or renames). The children list is sorted alphabetically to ensure
 * deterministic equality checks.</p>
 */
public final class DirectoryEntry extends EntryMetadata {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(DirectoryEntry.class);

    private final List<String> children;

    /**
     * Constructs a new {@link DirectoryEntry} representation.
     * 
     * @param rawPath   the raw directory path. Must not be null or blank.
     * @param timestamp the timestamps of the directory. Must not be null.
     * @param mode      the file mode (type and permissions). Must not be null.
     * @param size      the size of the directory in bytes (typically system-dependent, e.g. 0 or 4096). Must not be negative.
     * @param children  the list of immediate child names. If null, an empty list is assumed.
     * @throws ValidationException  if any parameters violate constraints (e.g. child name contains path separators).
     * @throws NullPointerException if any mandatory parameters are null.
     */
    public DirectoryEntry(
            String rawPath,
            FileTimestamp timestamp,
            FileMode mode,
            long size,
            List<String> children) {
        super(rawPath, timestamp, mode, size);
        
        if (children == null) {
            this.children = Collections.emptyList();
        } else {
            List<String> sortedChildren = new ArrayList<>(children);
            Collections.sort(sortedChildren);
            this.children = List.copyOf(sortedChildren);
            
            // Validate child names to ensure they represent file names, not absolute or relative subpaths
            this.children.forEach(child -> {
                Objects.requireNonNull(child, "Child name must not be null");
                if (child.isBlank()) {
                    throw new ValidationException("Child name must not be empty or blank.");
                }
                if (child.contains("/") || child.contains("\\")) {
                    throw new ValidationException("Child name must be a simple file/directory name, not a path: '" + child + "'");
                }
            });
        }

        LOGGER.trace("DirectoryEntry successfully constructed for: %s with %d children", 
            getNormalizedPath(), this.children.size());
    }

    /**
     * Resolves a {@link DirectoryEntry} from the physical filesystem.
     * Reads timestamps, permissions, and scans for immediate child elements.
     * 
     * @param path the filesystem directory {@link Path}. Must not be null.
     * @return a fully populated {@link DirectoryEntry}.
     * @throws IOException          if an I/O error occurs reading directory contents or attributes.
     * @throws NullPointerException if the path is null.
     */
    public static DirectoryEntry fromPath(Path path) throws IOException {
        Objects.requireNonNull(path, "Path must not be null");
        LOGGER.trace("Resolving DirectoryEntry from physical path: %s", path);

        if (!Files.isDirectory(path)) {
            throw new ValidationException("Path is not a directory: " + path);
        }

        FileTimestamp timestamp = FileTimestamp.fromPath(path);
        FileMode mode = FileMode.fromPath(path);
        long size = Files.size(path);

        List<String> childrenList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                Path fileName = entry.getFileName();
                if (fileName != null) {
                    childrenList.add(fileName.toString());
                }
            }
        } catch (IOException e) {
            LOGGER.error(e, "Failed to read directory stream for: %s", path);
            throw e;
        }

        Collections.sort(childrenList);
        LOGGER.debug("Resolved DirectoryEntry for '%s' containing %d elements", path, childrenList.size());
        return new DirectoryEntry(path.toString(), timestamp, mode, size, childrenList);
    }

    /**
     * Gets the immediate child names located directly within this directory.
     * The names are sorted alphabetically.
     * 
     * @return an unmodifiable list of child names.
     */
    public List<String> getChildren() {
        return children;
    }

    /**
     * Checks if the directory contains a child entry with the specified name.
     * 
     * @param childName the name of the child to search for. Must not be null or blank.
     * @return true if the child is present in this directory.
     * @throws NullPointerException     if childName is null.
     * @throws IllegalArgumentException if childName is blank.
     */
    public boolean hasChild(String childName) {
        Objects.requireNonNull(childName, "Child name must not be null");
        if (childName.isBlank()) {
            throw new IllegalArgumentException("Child name must not be empty or blank.");
        }
        return Collections.binarySearch(children, childName) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectoryEntry other)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(children, other.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), children);
    }

    @Override
    public String toString() {
        return String.format("DirectoryEntry[path=%s, childrenCount=%d]", getNormalizedPath(), children.size());
    }

    /**
     * Creates a builder to configure and construct a {@link DirectoryEntry}.
     * 
     * @return a new {@link Builder}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing {@link DirectoryEntry} instances.
     */
    public static final class Builder {
        private String path;
        private FileTimestamp timestamp;
        private FileMode mode;
        private long size = 0;
        private final List<String> children = new ArrayList<>();

        private Builder() {}

        /**
         * Sets the directory path.
         * 
         * @param path the path. Must not be null.
         * @return this builder.
         */
        public Builder path(String path) {
            this.path = Objects.requireNonNull(path, "Path must not be null");
            return this;
        }

        /**
         * Sets the timestamps of the directory entry.
         * 
         * @param timestamp the timestamps. Must not be null.
         * @return this builder.
         */
        public Builder timestamp(FileTimestamp timestamp) {
            this.timestamp = Objects.requireNonNull(timestamp, "Timestamp must not be null");
            return this;
        }

        /**
         * Sets the directory file mode.
         * 
         * @param mode the file mode. Must not be null.
         * @return this builder.
         */
        public Builder mode(FileMode mode) {
            this.mode = Objects.requireNonNull(mode, "Mode must not be null");
            return this;
        }

        /**
         * Sets the directory size.
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
         * Adds a single child name.
         * 
         * @param childName the simple file/folder name of the child. Must not be null or blank.
         * @return this builder.
         * @throws ValidationException if childName is empty or contains path separators.
         */
        public Builder addChild(String childName) {
            Objects.requireNonNull(childName, "Child name must not be null");
            if (childName.isBlank()) {
                throw new ValidationException("Child name must not be blank.");
            }
            if (childName.contains("/") || childName.contains("\\")) {
                throw new ValidationException("Child name must be a simple filename, not a path: '" + childName + "'");
            }
            this.children.add(childName.trim());
            return this;
        }

        /**
         * Adds multiple child names.
         * 
         * @param childrenNames the list of child names. Must not be null.
         * @return this builder.
         */
        public Builder children(List<String> childrenNames) {
            Objects.requireNonNull(childrenNames, "Children names list must not be null");
            for (String child : childrenNames) {
                addChild(child);
            }
            return this;
        }

        /**
         * Builds a {@link DirectoryEntry} instance.
         * 
         * @return the constructed {@link DirectoryEntry}.
         * @throws ValidationException if any required parameter is missing or invalid.
         */
        public DirectoryEntry build() {
            if (path == null) {
                throw new ValidationException("Path must be configured to build a DirectoryEntry.");
            }
            if (timestamp == null) {
                throw new ValidationException("Timestamp must be configured to build a DirectoryEntry.");
            }
            if (mode == null) {
                mode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();
            }
            return new DirectoryEntry(path, timestamp, mode, size, children);
        }
    }
}
