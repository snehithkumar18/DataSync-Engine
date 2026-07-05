package com.syncforge.metadata;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a standard file entry in the filesystem, extending {@link EntryMetadata}.
 * 
 * <p>In addition to basic attributes, {@code FileEntry} tracks:
 * <ul>
 *   <li>The cryptographic content hash ({@link FileHash})</li>
 *   <li>Extended attributes (xattrs) mapped as string key-values</li>
 *   <li>Windows Alternate Data Streams (ADS) represented by the {@link AlternateDataStream} structure</li>
 * </ul>
 * </p>
 * 
 * <p>Provides robust validation, copy-on-write mapping to prevent modifications to internal state,
 * dynamic builder helpers, and a platform-aware factory method.</p>
 */
public final class FileEntry extends EntryMetadata {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(FileEntry.class);

    private final FileHash contentHash;
    private final Map<String, String> extendedAttributes;
    private final List<AlternateDataStream> alternateDataStreams;

    /**
     * Represents a Windows Alternate Data Stream (ADS) on an NTFS partition.
     * 
     * @param name the name of the alternate stream. Must not be null or blank.
     * @param size the size of the stream in bytes. Must not be negative.
     * @param hash the hash value of the stream's contents. Must not be null.
     */
    public record AlternateDataStream(String name, long size, FileHash hash) {
        
        /**
         * Compact constructor to validate alternate data stream properties.
         * 
         * @throws ValidationException if the name is null or blank, size is negative, or hash is null.
         */
        public AlternateDataStream {
            Objects.requireNonNull(name, "Alternate stream name must not be null");
            if (name.isBlank()) {
                throw new ValidationException("Alternate stream name must not be empty or blank.");
            }
            if (size < 0) {
                throw new ValidationException("Alternate stream size must not be negative: " + size);
            }
            Objects.requireNonNull(hash, "Alternate stream hash must not be null");
        }
    }

    /**
     * Constructs a new {@link FileEntry} representation.
     * 
     * @param rawPath              the raw file path. Must not be null or blank.
     * @param timestamp            the timestamps of the file. Must not be null.
     * @param mode                 the file mode (type and permissions). Must not be null.
     * @param size                 the size of the file in bytes. Must not be negative.
     * @param contentHash          the hash representing the file content. Must not be null.
     * @param extendedAttributes   the map of extended attributes (xattrs). If null, an empty map is assumed.
     * @param alternateDataStreams the list of alternate data streams. If null, an empty list is assumed.
     * @throws ValidationException  if any parameters violate constraints.
     * @throws NullPointerException if any of the mandatory parameters are null.
     */
    public FileEntry(
            String rawPath,
            FileTimestamp timestamp,
            FileMode mode,
            long size,
            FileHash contentHash,
            Map<String, String> extendedAttributes,
            List<AlternateDataStream> alternateDataStreams) {
        super(rawPath, timestamp, mode, size);
        
        this.contentHash = Objects.requireNonNull(contentHash, "Content hash must not be null");
        
        // Guard copy extended attributes
        if (extendedAttributes == null) {
            this.extendedAttributes = Collections.emptyMap();
        } else {
            this.extendedAttributes = Map.copyOf(extendedAttributes);
            this.extendedAttributes.forEach((k, v) -> {
                Objects.requireNonNull(k, "Extended attribute key must not be null");
                Objects.requireNonNull(v, "Extended attribute value must not be null");
            });
        }
        
        // Guard copy alternate data streams
        if (alternateDataStreams == null) {
            this.alternateDataStreams = Collections.emptyList();
        } else {
            this.alternateDataStreams = List.copyOf(alternateDataStreams);
            this.alternateDataStreams.forEach(ads -> 
                Objects.requireNonNull(ads, "Alternate data stream entry must not be null")
            );
        }

        LOGGER.trace("FileEntry successfully constructed for: %s", getNormalizedPath());
    }

    /**
     * Resolves a {@link FileEntry} from the physical filesystem.
     * Reads file size, timestamps, permissions, and extended attributes (xattrs) automatically.
     * 
     * @param path        the filesystem {@link Path}. Must not be null.
     * @param contentHash the pre-computed hash of the file content. Must not be null.
     * @return a fully populated {@link FileEntry}.
     * @throws IOException          if an I/O error occurs reading filesystem attributes.
     * @throws NullPointerException if the path or contentHash is null.
     */
    public static FileEntry fromPath(Path path, FileHash contentHash) throws IOException {
        Objects.requireNonNull(path, "Path must not be null");
        Objects.requireNonNull(contentHash, "Content hash must not be null");

        FileTimestamp timestamp = FileTimestamp.fromPath(path);
        FileMode mode = FileMode.fromPath(path);
        long size = Files.size(path);

        // Read extended attributes using UserDefinedFileAttributeView
        Map<String, String> xattrs = new HashMap<>();
        try {
            UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
            if (view != null) {
                for (String name : view.list()) {
                    ByteBuffer buf = ByteBuffer.allocate(view.size(name));
                    view.read(name, buf);
                    buf.flip();
                    String val = StandardCharsets.UTF_8.decode(buf).toString();
                    xattrs.put(name, val);
                }
            }
        } catch (UnsupportedOperationException e) {
            LOGGER.trace("Extended attributes (xattrs) are not supported on this filesystem: %s", path);
        } catch (Exception e) {
            LOGGER.warn("Failed to retrieve extended attributes for path '%s': %s", path, e.getMessage());
        }

        // NTFS alternate data streams listing is not natively supported in pure Java,
        // so it defaults to an empty list here. They can be added programmatically via builder.
        List<AlternateDataStream> adsList = Collections.emptyList();

        LOGGER.debug("Resolved FileEntry from path: %s", path);
        return new FileEntry(path.toString(), timestamp, mode, size, contentHash, xattrs, adsList);
    }

    /**
     * Gets the cryptographic hash of the file content.
     * 
     * @return the {@link FileHash}.
     */
    public FileHash getContentHash() {
        return contentHash;
    }

    /**
     * Gets the extended attributes associated with this file.
     * 
     * @return an unmodifiable map of xattrs.
     */
    public Map<String, String> getExtendedAttributes() {
        return extendedAttributes;
    }

    /**
     * Gets the alternate data streams (ADS) associated with this file (primarily Windows NTFS).
     * 
     * @return an unmodifiable list of {@link AlternateDataStream} entries.
     */
    public List<AlternateDataStream> getAlternateDataStreams() {
        return alternateDataStreams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileEntry other)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(contentHash, other.contentHash) &&
               Objects.equals(extendedAttributes, other.extendedAttributes) &&
               Objects.equals(alternateDataStreams, other.alternateDataStreams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contentHash, extendedAttributes, alternateDataStreams);
    }

    @Override
    public String toString() {
        return String.format("FileEntry[path=%s, size=%d, hash=%s, xattrsCount=%d, adsCount=%d]", 
            getNormalizedPath(), getSize(), contentHash, extendedAttributes.size(), alternateDataStreams.size());
    }

    /**
     * Creates a builder to configure and construct a {@link FileEntry}.
     * 
     * @return a new {@link Builder}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing {@link FileEntry} instances.
     */
    public static final class Builder {
        private String path;
        private FileTimestamp timestamp;
        private FileMode mode;
        private long size = -1;
        private FileHash contentHash;
        private final Map<String, String> extendedAttributes = new HashMap<>();
        private final List<AlternateDataStream> alternateDataStreams = new ArrayList<>();

        private Builder() {}

        /**
         * Sets the path of the file entry.
         * 
         * @param path the path. Must not be null.
         * @return this builder.
         */
        public Builder path(String path) {
            this.path = Objects.requireNonNull(path, "Path must not be null");
            return this;
        }

        /**
         * Sets the timestamps of the file entry.
         * 
         * @param timestamp the timestamps. Must not be null.
         * @return this builder.
         */
        public Builder timestamp(FileTimestamp timestamp) {
            this.timestamp = Objects.requireNonNull(timestamp, "Timestamp must not be null");
            return this;
        }

        /**
         * Sets the file mode of the file entry.
         * 
         * @param mode the file mode. Must not be null.
         * @return this builder.
         */
        public Builder mode(FileMode mode) {
            this.mode = Objects.requireNonNull(mode, "Mode must not be null");
            return this;
        }

        /**
         * Sets the size of the file.
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
         * Sets the cryptographic content hash.
         * 
         * @param contentHash the content hash. Must not be null.
         * @return this builder.
         */
        public Builder contentHash(FileHash contentHash) {
            this.contentHash = Objects.requireNonNull(contentHash, "Content hash must not be null");
            return this;
        }

        /**
         * Adds a single extended attribute key-value pair.
         * 
         * @param key   the attribute key. Must not be null or blank.
         * @param value the attribute value. Must not be null.
         * @return this builder.
         * @throws ValidationException if key is empty.
         */
        public Builder addExtendedAttribute(String key, String value) {
            Objects.requireNonNull(key, "Extended attribute key must not be null");
            Objects.requireNonNull(value, "Extended attribute value must not be null");
            if (key.isBlank()) {
                throw new ValidationException("Extended attribute key must not be blank.");
            }
            this.extendedAttributes.put(key.trim(), value);
            return this;
        }

        /**
         * Adds multiple extended attributes.
         * 
         * @param attributes the map of extended attributes. Must not be null.
         * @return this builder.
         */
        public Builder extendedAttributes(Map<String, String> attributes) {
            Objects.requireNonNull(attributes, "Extended attributes map must not be null");
            attributes.forEach(this::addExtendedAttribute);
            return this;
        }

        /**
         * Adds an alternate data stream (ADS).
         * 
         * @param ads the stream object. Must not be null.
         * @return this builder.
         */
        public Builder addAlternateDataStream(AlternateDataStream ads) {
            this.alternateDataStreams.add(Objects.requireNonNull(ads, "Alternate data stream must not be null"));
            return this;
        }

        /**
         * Adds multiple alternate data streams.
         * 
         * @param streams the list of alternate data streams. Must not be null.
         * @return this builder.
         */
        public Builder alternateDataStreams(List<AlternateDataStream> streams) {
            Objects.requireNonNull(streams, "Alternate data streams list must not be null");
            for (AlternateDataStream ads : streams) {
                addAlternateDataStream(ads);
            }
            return this;
        }

        /**
         * Builds a {@link FileEntry} instance.
         * 
         * @return the constructed {@link FileEntry}.
         * @throws ValidationException if any required parameter is missing or invalid.
         */
        public FileEntry build() {
            if (path == null) {
                throw new ValidationException("Path must be configured to build a FileEntry.");
            }
            if (timestamp == null) {
                throw new ValidationException("Timestamp must be configured to build a FileEntry.");
            }
            if (mode == null) {
                // Default mode to Type.FILE with standard permissions
                mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
            }
            if (size < 0) {
                throw new ValidationException("Size must be configured and non-negative to build a FileEntry.");
            }
            if (contentHash == null) {
                throw new ValidationException("Content hash must be configured to build a FileEntry.");
            }
            return new FileEntry(path, timestamp, mode, size, contentHash, extendedAttributes, alternateDataStreams);
        }
    }
}
