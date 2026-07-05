package com.syncforge.core.exceptions;

import java.util.Objects;
import java.util.Optional;

/**
 * Exception thrown when the SyncForge engine encounters file system, database,
 * or binary storage reading/writing issues. This covers snapshot persistence,
 * patch file creation, or temporary file state access.
 */
public class StorageException extends SyncForgeException {

    private static final long serialVersionUID = 1L;
    private final String targetPath;

    /**
     * Constructs a new StorageException with the specified detail message.
     *
     * @param message the detail message describing the storage failure. Must not be null or blank.
     * @throws NullPointerException if the message is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public StorageException(String message) {
        super(message);
        this.targetPath = null;
    }

    /**
     * Constructs a new StorageException with the specified detail message and cause.
     *
     * @param message the detail message describing the storage failure. Must not be null or blank.
     * @param cause   the underlying cause of the storage error. Must not be null.
     * @throws NullPointerException if the message or cause is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
        this.targetPath = null;
    }

    /**
     * Constructs a new StorageException referencing a specific file or path target.
     *
     * @param message    the detail message. Must not be null or blank.
     * @param targetPath the file path or URI that failed. May be null.
     * @throws NullPointerException if the message is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public StorageException(String message, String targetPath) {
        super(message + (targetPath != null ? " [Target Path: " + targetPath.replace('\\', '/') + "]" : ""));
        this.targetPath = targetPath != null ? targetPath.replace('\\', '/') : null;
    }

    /**
     * Constructs a new StorageException referencing a specific file or path target and a cause.
     *
     * @param message    the detail message. Must not be null or blank.
     * @param targetPath the file path or URI that failed. May be null.
     * @param cause      the underlying cause. Must not be null.
     * @throws NullPointerException if the message or cause is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public StorageException(String message, String targetPath, Throwable cause) {
        super(message + (targetPath != null ? " [Target Path: " + targetPath.replace('\\', '/') + "]" : ""), 
              Objects.requireNonNull(cause, "Cause must not be null"));
        this.targetPath = targetPath != null ? targetPath.replace('\\', '/') : null;
    }

    /**
     * Returns the target path or location associated with the storage failure.
     *
     * @return an {@link Optional} containing the normalized target path (with Unix forward slashes) if set, or empty.
     */
    public Optional<String> getTargetPath() {
        return Optional.ofNullable(targetPath);
    }
}
