package com.syncforge.diff;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.util.Objects;

/**
 * Captures file tree differences between a baseline (before) and a target (after) state.
 * Encapsulates the path of the entry, change type, metadata state before and after,
 * and any rename relationship if detected.
 *
 * <p>Inputs are strictly validated at construction time to ensure logical integrity.
 * For example, an {@code ADD} entry must not contain 'before' metadata, while a
 * {@code DELETE} entry must not contain 'after' metadata.</p>
 */
public final class DiffEntry {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(DiffEntry.class);

    /**
     * Represents the specific type of change detected.
     */
    public enum Type {
        /**
         * The entry was added in the target state.
         */
        ADD,
        /**
         * The entry was deleted from the baseline state.
         */
        DELETE,
        /**
         * The content or metadata of the entry was modified.
         */
        MODIFY,
        /**
         * The entry was renamed or moved to a new path.
         */
        RENAME,
        /**
         * The permissions of the entry changed.
         */
        PERMISSION_CHANGE,
        /**
         * The filesystem type of the entry changed (e.g. from file to directory).
         */
        TYPE_CHANGE,
        /**
         * A synchronization conflict was detected on this entry.
         */
        CONFLICT,
        /**
         * No changes were detected for this entry.
         */
        UNCHANGED
    }

    private final String path;
    private final Type changeType;
    private final EntryMetadata before;
    private final EntryMetadata after;
    private final String renameSourcePath;
    private final double similarityScore;

    /**
     * Constructs a new DiffEntry capturing a difference.
     *
     * @param path              the normalized path of the entry. Must not be null or blank.
     * @param changeType        the type of change. Must not be null.
     * @param before            the metadata before the change. Can be null if changeType is ADD.
     * @param after             the metadata after the change. Can be null if changeType is DELETE.
     * @param renameSourcePath  the original path if changeType is RENAME. Must be null otherwise.
     * @param similarityScore   the content similarity score (0.0 to 1.0) for renames. Must be 0.0 for non-renames.
     * @throws ValidationException if the arguments do not conform to state rules.
     * @throws NullPointerException if mandatory arguments are null.
     */
    public DiffEntry(String path, Type changeType, EntryMetadata before, EntryMetadata after, String renameSourcePath, double similarityScore) {
        Objects.requireNonNull(path, "Path must not be null");
        if (path.isBlank()) {
            throw new ValidationException("Path must not be empty or blank.");
        }
        this.changeType = Objects.requireNonNull(changeType, "Change type must not be null");
        
        // Normalize path using core/metadata normalizer
        this.path = EntryMetadata.normalizePath(path);

        // Validation based on changeType
        if (changeType == Type.ADD) {
            if (before != null) {
                throw new ValidationException("For ADD change type, the before metadata must be null.");
            }
            if (after == null) {
                throw new ValidationException("For ADD change type, the after metadata must not be null.");
            }
        }
        if (changeType == Type.DELETE) {
            if (before == null) {
                throw new ValidationException("For DELETE change type, the before metadata must not be null.");
            }
            if (after != null) {
                throw new ValidationException("For DELETE change type, the after metadata must be null.");
            }
        }
        if (changeType == Type.RENAME) {
            if (before == null) {
                throw new ValidationException("For RENAME change type, the before metadata must not be null.");
            }
            if (after == null) {
                throw new ValidationException("For RENAME change type, the after metadata must not be null.");
            }
            if (renameSourcePath == null || renameSourcePath.isBlank()) {
                throw new ValidationException("For RENAME change type, renameSourcePath must not be null or blank.");
            }
            if (similarityScore < 0.0 || similarityScore > 1.0) {
                throw new ValidationException("Similarity score must be between 0.0 and 1.0 inclusive: " + similarityScore);
            }
        } else {
            if (renameSourcePath != null) {
                throw new ValidationException("renameSourcePath must be null for non-RENAME change types.");
            }
            if (similarityScore != 0.0) {
                throw new ValidationException("similarityScore must be 0.0 for non-RENAME change types.");
            }
        }

        this.before = before;
        this.after = after;
        this.renameSourcePath = renameSourcePath != null ? EntryMetadata.normalizePath(renameSourcePath) : null;
        this.similarityScore = similarityScore;

        LOGGER.trace("DiffEntry initialized: path='%s', changeType=%s, renameSourcePath='%s', similarityScore=%.4f",
            this.path, this.changeType, this.renameSourcePath, this.similarityScore);
    }

    /**
     * Creates a new ADD DiffEntry.
     *
     * @param after the metadata representing the added filesystem entry. Must not be null.
     * @return a new ADD {@link DiffEntry}.
     */
    public static DiffEntry add(EntryMetadata after) {
        Objects.requireNonNull(after, "After entry must not be null");
        return new DiffEntry(after.getNormalizedPath(), Type.ADD, null, after, null, 0.0);
    }

    /**
     * Creates a new DELETE DiffEntry.
     *
     * @param before the metadata representing the deleted filesystem entry. Must not be null.
     * @return a new DELETE {@link DiffEntry}.
     */
    public static DiffEntry delete(EntryMetadata before) {
        Objects.requireNonNull(before, "Before entry must not be null");
        return new DiffEntry(before.getNormalizedPath(), Type.DELETE, before, null, null, 0.0);
    }

    /**
     * Creates a new MODIFY DiffEntry.
     *
     * @param before the metadata representing the baseline filesystem entry. Must not be null.
     * @param after  the metadata representing the target filesystem entry. Must not be null.
     * @return a new MODIFY {@link DiffEntry}.
     */
    public static DiffEntry modify(EntryMetadata before, EntryMetadata after) {
        Objects.requireNonNull(before, "Before entry must not be null");
        Objects.requireNonNull(after, "After entry must not be null");
        return new DiffEntry(after.getNormalizedPath(), Type.MODIFY, before, after, null, 0.0);
    }

    /**
     * Creates a new RENAME DiffEntry.
     *
     * @param before          the metadata representing the original baseline entry. Must not be null.
     * @param after           the metadata representing the renamed target entry. Must not be null.
     * @param similarityScore the similarity score (0.0 to 1.0) indicating content match confidence.
     * @return a new RENAME {@link DiffEntry}.
     */
    public static DiffEntry rename(EntryMetadata before, EntryMetadata after, double similarityScore) {
        Objects.requireNonNull(before, "Before entry must not be null");
        Objects.requireNonNull(after, "After entry must not be null");
        return new DiffEntry(after.getNormalizedPath(), Type.RENAME, before, after, before.getNormalizedPath(), similarityScore);
    }

    /**
     * Creates a new PERMISSION_CHANGE DiffEntry.
     *
     * @param before the metadata representing the baseline entry. Must not be null.
     * @param after  the metadata representing the target entry. Must not be null.
     * @return a new PERMISSION_CHANGE {@link DiffEntry}.
     */
    public static DiffEntry permissionChange(EntryMetadata before, EntryMetadata after) {
        Objects.requireNonNull(before, "Before entry must not be null");
        Objects.requireNonNull(after, "After entry must not be null");
        return new DiffEntry(after.getNormalizedPath(), Type.PERMISSION_CHANGE, before, after, null, 0.0);
    }

    /**
     * Creates a new TYPE_CHANGE DiffEntry.
     *
     * @param before the metadata representing the baseline entry. Must not be null.
     * @param after  the metadata representing the target entry. Must not be null.
     * @return a new TYPE_CHANGE {@link DiffEntry}.
     */
    public static DiffEntry typeChange(EntryMetadata before, EntryMetadata after) {
        Objects.requireNonNull(before, "Before entry must not be null");
        Objects.requireNonNull(after, "After entry must not be null");
        return new DiffEntry(after.getNormalizedPath(), Type.TYPE_CHANGE, before, after, null, 0.0);
    }

    /**
     * Creates a new CONFLICT DiffEntry.
     *
     * @param before the metadata representing the baseline entry. Must not be null.
     * @param after  the metadata representing the target entry. Must not be null.
     * @return a new CONFLICT {@link DiffEntry}.
     */
    public static DiffEntry conflict(EntryMetadata before, EntryMetadata after) {
        Objects.requireNonNull(before, "Before entry must not be null");
        Objects.requireNonNull(after, "After entry must not be null");
        return new DiffEntry(after.getNormalizedPath(), Type.CONFLICT, before, after, null, 0.0);
    }

    /**
     * Creates a new UNCHANGED DiffEntry.
     *
     * @param before the metadata representing the baseline entry. Must not be null.
     * @param after  the metadata representing the target entry. Must not be null.
     * @return a new UNCHANGED {@link DiffEntry}.
     */
    public static DiffEntry unchanged(EntryMetadata before, EntryMetadata after) {
        Objects.requireNonNull(before, "Before entry must not be null");
        Objects.requireNonNull(after, "After entry must not be null");
        return new DiffEntry(after.getNormalizedPath(), Type.UNCHANGED, before, after, null, 0.0);
    }

    /**
     * Gets the normalized path of the entry. For renames, this is the destination path.
     *
     * @return the normalized path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets the type of change.
     *
     * @return the {@link Type} of change.
     */
    public Type getChangeType() {
        return changeType;
    }

    /**
     * Gets the entry metadata before the change.
     *
     * @return the {@link EntryMetadata} before change, or null if ADD.
     */
    public EntryMetadata getBefore() {
        return before;
    }

    /**
     * Gets the entry metadata after the change.
     *
     * @return the {@link EntryMetadata} after change, or null if DELETE.
     */
    public EntryMetadata getAfter() {
        return after;
    }

    /**
     * Gets the original path before the rename, if this change is a RENAME.
     *
     * @return the original path, or null if not a RENAME.
     */
    public String getRenameSourcePath() {
        return renameSourcePath;
    }

    /**
     * Gets the similarity score for a RENAME change type.
     *
     * @return the double similarity score (0.0 to 1.0), or 0.0 if not a RENAME.
     */
    public double getSimilarityScore() {
        return similarityScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiffEntry other)) return false;
        return Double.compare(other.similarityScore, similarityScore) == 0 &&
               Objects.equals(path, other.path) &&
               changeType == other.changeType &&
               Objects.equals(before, other.before) &&
               Objects.equals(after, other.after) &&
               Objects.equals(renameSourcePath, other.renameSourcePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, changeType, before, after, renameSourcePath, similarityScore);
    }

    @Override
    public String toString() {
        return String.format("DiffEntry[path=%s, changeType=%s, renameSourcePath=%s, similarityScore=%.4f]",
            path, changeType, renameSourcePath, similarityScore);
    }
}
