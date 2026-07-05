package com.syncforge.core.exceptions;

import java.util.Objects;

/**
 * Base exception class for all errors occurring within the SyncForge file synchronization engine.
 * All custom exceptions thrown by SyncForge submodules extend this class to facilitate
 * uniform exception handling and categorization.
 * 
 * <p>Input validation is performed on all constructors to ensure that error descriptions
 * are never null or empty, preserving exception details for diagnostics.</p>
 */
public class SyncForgeException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new SyncForgeException with a default message.
     */
    public SyncForgeException() {
        super("An unexpected error occurred within the SyncForge engine.");
    }

    /**
     * Constructs a new SyncForgeException with the specified detail message.
     *
     * @param message the detail message describing the error. Must not be null or blank.
     * @throws NullPointerException if the message is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public SyncForgeException(String message) {
        super(validateMessage(message));
    }

    /**
     * Constructs a new SyncForgeException with the specified cause.
     *
     * @param cause the underlying cause of the exception. Must not be null.
     * @throws NullPointerException if the cause is null.
     */
    public SyncForgeException(Throwable cause) {
        super(Objects.requireNonNull(cause, "Cause must not be null").getMessage(), cause);
    }

    /**
     * Constructs a new SyncForgeException with the specified detail message and cause.
     *
     * @param message the detail message describing the error. Must not be null or blank.
     * @param cause   the underlying cause of the exception. Must not be null.
     * @throws NullPointerException if the message or cause is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public SyncForgeException(String message, Throwable cause) {
        super(validateMessage(message), Objects.requireNonNull(cause, "Cause must not be null"));
    }

    /**
     * Helper method to validate exception messages.
     *
     * @param message the message to validate.
     * @return the validated message if it is non-null and non-blank.
     * @throws NullPointerException if the message is null.
     * @throws IllegalArgumentException if the message is empty or only whitespace.
     */
    private static String validateMessage(String message) {
        Objects.requireNonNull(message, "Exception message must not be null");
        if (message.isBlank()) {
            throw new IllegalArgumentException("Exception message must not be empty or blank");
        }
        return message;
    }
}
