package com.syncforge.metadata;

import com.syncforge.core.logging.SyncForgeLogger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Details the specific reasons why a filesystem entry's metadata or contents
 * have changed. Multiple reasons can accumulate for a single change event.
 */
public enum ChangeReason {

    /**
     * The cryptographic content hash or immediate directory children list has changed.
     */
    CONTENT_CHANGED("The item's content or directory listing has changed."),

    /**
     * The size of the file system entry has changed.
     */
    SIZE_CHANGED("The file size has changed."),

    /**
     * The permissions (POSIX octal/ACLs or Windows SIDs) have changed.
     */
    PERMISSIONS_CHANGED("The security permissions or owner SIDs have changed."),

    /**
     * The timestamps (mtime, ctime, or atime) have changed.
     */
    TIMESTAMP_CHANGED("The modification, creation, or access timestamps have changed."),

    /**
     * The type of the filesystem entry (e.g. from FILE to DIRECTORY) has changed.
     */
    TYPE_CHANGED("The file system entry type has changed."),

    /**
     * The target path of a symbolic link has changed.
     */
    TARGET_CHANGED("The target of the symbolic link has changed."),

    /**
     * Extended attributes (xattrs) or alternate data streams (ADS) have changed.
     */
    ATTRIBUTES_CHANGED("The extended attributes or alternate data streams have changed."),

    /**
     * No changes were detected.
     */
    NONE("No differences detected.");

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(ChangeReason.class);

    private final String description;

    ChangeReason(String description) {
        this.description = description;
    }

    /**
     * Gets a human-readable description of the change reason.
     * 
     * @return description text.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Compares two filesystem entry records and detects all the differences, returning
     * a set of {@link ChangeReason} constants.
     * 
     * @param before the metadata record before the change occurred. Must not be null.
     * @param after  the metadata record after the change occurred. Must not be null.
     * @return an unmodifiable set of {@link ChangeReason} constants explaining the differences.
     *         If no changes are detected, returns a set containing {@link ChangeReason#NONE}.
     * @throws NullPointerException if before or after is null.
     */
    public static Set<ChangeReason> detectChanges(EntryMetadata before, EntryMetadata after) {
        Objects.requireNonNull(before, "Before metadata must not be null");
        Objects.requireNonNull(after, "After metadata must not be null");

        Set<ChangeReason> changes = new HashSet<>();

        if (!Objects.equals(before.getNormalizedPath(), after.getNormalizedPath())) {
            LOGGER.warn("Comparing metadata for entries with different paths: before='%s', after='%s'",
                before.getNormalizedPath(), after.getNormalizedPath());
        }

        // 1. Check type change
        if (before.getMode().getType() != after.getMode().getType()) {
            changes.add(TYPE_CHANGED);
        }

        // 2. Check size change
        if (before.getSize() != after.getSize()) {
            changes.add(SIZE_CHANGED);
        }

        // 3. Check permissions change (POSIX octal, ACLs, Windows SIDs)
        FileMode modeBefore = before.getMode();
        FileMode modeAfter = after.getMode();
        if (modeBefore.getPosixPermissions() != modeAfter.getPosixPermissions() ||
            !Objects.equals(modeBefore.getPosixAcls(), modeAfter.getPosixAcls()) ||
            !Objects.equals(modeBefore.getWindowsSids(), modeAfter.getWindowsSids())) {
            changes.add(PERMISSIONS_CHANGED);
        }

        // 4. Check timestamp change
        if (!Objects.equals(before.getTimestamp(), after.getTimestamp())) {
            changes.add(TIMESTAMP_CHANGED);
        }

        // 5. Specialised type checks
        if (before instanceof FileEntry fBefore && after instanceof FileEntry fAfter) {
            // Check cryptographic content hash
            if (!Objects.equals(fBefore.getContentHash(), fAfter.getContentHash())) {
                changes.add(CONTENT_CHANGED);
            }
            // Check extended attributes & alternate data streams
            if (!Objects.equals(fBefore.getExtendedAttributes(), fAfter.getExtendedAttributes()) ||
                !Objects.equals(fBefore.getAlternateDataStreams(), fAfter.getAlternateDataStreams())) {
                changes.add(ATTRIBUTES_CHANGED);
            }
        } else if (before instanceof DirectoryEntry dBefore && after instanceof DirectoryEntry dAfter) {
            // Check directory elements list
            if (!Objects.equals(dBefore.getChildren(), dAfter.getChildren())) {
                changes.add(CONTENT_CHANGED);
            }
        } else if (before instanceof SymlinkEntry sBefore && after instanceof SymlinkEntry sAfter) {
            // Check target path change
            if (!Objects.equals(sBefore.getTargetPath(), sAfter.getTargetPath())) {
                changes.add(TARGET_CHANGED);
            }
        }

        if (changes.isEmpty()) {
            changes.add(NONE);
        }

        LOGGER.debug("Detected changes for '%s': %s", after.getNormalizedPath(), changes);
        return Collections.unmodifiableSet(changes);
    }
}
