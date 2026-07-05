package com.syncforge.core.exceptions;

import com.syncforge.core.diagnostics.Diagnostic;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Exception thrown when the SyncForge engine encounters validation errors,
 * such as invalid manifests, unsafe paths, duplicate metadata records, or out-of-order sync operations.
 * 
 * <p>This exception can accumulate and expose a list of {@link Diagnostic} instances to provide
 * detailed feedback on what validations failed.</p>
 */
public class ValidationException extends SyncForgeException {

    private static final long serialVersionUID = 1L;
    private final List<Diagnostic> diagnostics;

    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message the detail message describing the validation failure. Must not be null or blank.
     * @throws NullPointerException if the message is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ValidationException(String message) {
        super(message);
        this.diagnostics = Collections.emptyList();
    }

    /**
     * Constructs a new ValidationException with a detailed message and an underlying cause.
     *
     * @param message the detail message.
     * @param cause   the underlying cause of the exception.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
        this.diagnostics = Collections.emptyList();
    }

    /**
     * Constructs a new ValidationException with a detailed message and a list of diagnostics.
     *
     * @param message     the detail message describing the validation failure. Must not be null or blank.
     * @param diagnostics the list of diagnostics that caused the validation to fail. Must not be null.
     *                    The list is copied defensively.
     * @throws NullPointerException if message or diagnostics is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ValidationException(String message, List<Diagnostic> diagnostics) {
        super(message);
        Objects.requireNonNull(diagnostics, "Diagnostics list must not be null");
        this.diagnostics = List.copyOf(diagnostics);
    }

    /**
     * Constructs a new ValidationException with a detailed message, list of diagnostics, and an underlying cause.
     *
     * @param message     the detail message. Must not be null or blank.
     * @param diagnostics the list of diagnostics. Must not be null.
     * @param cause       the underlying cause of the exception. Must not be null.
     * @throws NullPointerException if any of the parameters are null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ValidationException(String message, List<Diagnostic> diagnostics, Throwable cause) {
        super(message, cause);
        Objects.requireNonNull(diagnostics, "Diagnostics list must not be null");
        this.diagnostics = List.copyOf(diagnostics);
    }

    /**
     * Returns the list of diagnostics associated with this validation failure.
     *
     * @return an unmodifiable list of {@link Diagnostic} instances. May be empty, but never null.
     */
    public List<Diagnostic> getDiagnostics() {
        return diagnostics;
    }
}
