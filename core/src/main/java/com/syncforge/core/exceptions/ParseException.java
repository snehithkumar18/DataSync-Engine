package com.syncforge.core.exceptions;

import com.syncforge.core.diagnostics.SourceSpan;
import java.util.Objects;
import java.util.Optional;

/**
 * Exception thrown when the SyncForge engine encounters syntactical, grammar, 
 * or structural parsing errors. This is commonly used in DSL parsing (e.g., manifests)
 * or binary format decoding.
 * 
 * <p>It contains an optional {@link SourceSpan} indicating the precise location of the error.</p>
 */
public class ParseException extends SyncForgeException {

    private static final long serialVersionUID = 1L;
    private final SourceSpan span;

    /**
     * Constructs a new ParseException with a message and a source location span.
     *
     * @param message the detail message describing the parse failure. Must not be null or blank.
     * @param span    the source span indicating where the parse error occurred. May be null if location is unknown.
     * @throws NullPointerException if the message is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ParseException(String message, SourceSpan span) {
        super(Objects.requireNonNull(message, "Message must not be null") + 
              (span != null ? " at " + span : ""));
        this.span = span;
    }

    /**
     * Constructs a new ParseException with a message, a source location span, and a cause.
     *
     * @param message the detail message describing the parse failure. Must not be null or blank.
     * @param span    the source span indicating where the parse error occurred. May be null if location is unknown.
     * @param cause   the underlying cause of the exception. Must not be null.
     * @throws NullPointerException if the message or cause is null.
     * @throws IllegalArgumentException if the message is blank.
     */
    public ParseException(String message, SourceSpan span, Throwable cause) {
        super(Objects.requireNonNull(message, "Message must not be null") + 
              (span != null ? " at " + span : ""), 
              Objects.requireNonNull(cause, "Cause must not be null"));
        this.span = span;
    }

    /**
     * Returns the source span where the parsing error occurred.
     *
     * @return an {@link Optional} containing the {@link SourceSpan} if available, or empty if unknown.
     */
    public Optional<SourceSpan> getSpan() {
        return Optional.ofNullable(span);
    }
}
