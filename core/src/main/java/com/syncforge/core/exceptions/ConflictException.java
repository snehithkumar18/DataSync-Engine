package com.syncforge.core.exceptions;

import java.util.Objects;

/**
 * Exception thrown when the SyncForge engine detects conflicts between two versions
 * of a file, manifest, or snapshot that cannot be automatically resolved.
 */
public class ConflictException extends SyncForgeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ConflictException with the specified detail message.
     *
     * @param message the detail message describing the conflict. Must not be null or blank.
     * @throws NullPointerException if the message is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ConflictException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConflictException with the specified detail message and cause.
     *
     * @param message the detail message describing the conflict. Must not be null or blank.
     * @param cause   the underlying cause. Must not be null.
     * @throws NullPointerException if the message or cause is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
