package com.syncforge.core.exceptions;

import java.util.Objects;

/**
 * Exception thrown when the SyncForge engine encounters issues during the active synchronization runtime,
 * execution of patch operations, state transitions, or plan applies and rollbacks.
 */
public class ExecutionException extends SyncForgeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ExecutionException with the specified detail message.
     *
     * @param message the detail message explaining the execution failure. Must not be null or blank.
     * @throws NullPointerException if the message is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ExecutionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ExecutionException with the specified detail message and cause.
     *
     * @param message the detail message explaining the execution failure. Must not be null or blank.
     * @param cause   the underlying cause of the exception. Must not be null.
     * @throws NullPointerException if the message or cause is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
