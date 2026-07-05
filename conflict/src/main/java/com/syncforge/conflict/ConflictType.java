package com.syncforge.conflict;

/**
 * Categorizes the type of synchronization conflict detected.
 */
public enum ConflictType {
    /**
     * Both sides modified the same text file content, creating overlapping line edits.
     */
    CONTENT_MERGE_CONFLICT,

    /**
     * One side changed the file type (e.g. from FILE to DIRECTORY) while the other modified it.
     */
    TYPE_CLASH,

    /**
     * Both sides modified security permissions in incompatible ways.
     */
    PERMISSION_CONFLICT,

    /**
     * One side deleted the entry while the other modified its metadata or contents.
     */
    DELETE_MODIFY_CONFLICT
}
