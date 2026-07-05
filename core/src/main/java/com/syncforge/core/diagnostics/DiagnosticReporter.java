package com.syncforge.core.diagnostics;

import com.syncforge.core.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DiagnosticReporter acts as a central collector and validator for diagnostics during
 * compilation, syntax parsing, or system verification phases.
 * 
 * <p>It accumulates {@link Diagnostic} records and facilitates checking if any errors
 * occurred, throwing a {@link ValidationException} detailing the failures.</p>
 */
public class DiagnosticReporter {

    private final List<Diagnostic> diagnostics = new ArrayList<>();

    /**
     * Constructs a new, empty DiagnosticReporter.
     */
    public DiagnosticReporter() {
        // Default constructor
    }

    /**
     * Reports a diagnostic with the given severity, code, message, and source span.
     *
     * @param severity the severity of the diagnostic. Must not be null.
     * @param code     the unique error or warning code. Must not be null or blank.
     * @param message  the description of the diagnostic. Must not be null or blank.
     * @param span     the source location of the issue. May be null.
     * @throws NullPointerException if severity, code, or message is null.
     * @throws IllegalArgumentException if code or message is empty or blank.
     */
    public void report(Diagnostic.Severity severity, String code, String message, SourceSpan span) {
        diagnostics.add(new Diagnostic(severity, code, message, span));
    }

    /**
     * Convenience method to report an informational diagnostic.
     *
     * @param code    the unique diagnostic code. Must not be null or blank.
     * @param message the informational message. Must not be null or blank.
     * @param span    the source span. May be null.
     */
    public void info(String code, String message, SourceSpan span) {
        report(Diagnostic.Severity.INFO, code, message, span);
    }

    /**
     * Convenience method to report a warning diagnostic.
     *
     * @param code    the unique diagnostic code. Must not be null or blank.
     * @param message the warning message. Must not be null or blank.
     * @param span    the source span. May be null.
     */
    public void warn(String code, String message, SourceSpan span) {
        report(Diagnostic.Severity.WARNING, code, message, span);
    }

    /**
     * Convenience method to report an error diagnostic.
     *
     * @param code    the unique diagnostic code. Must not be null or blank.
     * @param message the error message. Must not be null or blank.
     * @param span    the source span. May be null.
     */
    public void error(String code, String message, SourceSpan span) {
        report(Diagnostic.Severity.ERROR, code, message, span);
    }

    /**
     * Returns an unmodifiable view of all accumulated diagnostics.
     *
     * @return a list of all diagnostics reported so far.
     */
    public List<Diagnostic> getDiagnostics() {
        return Collections.unmodifiableList(diagnostics);
    }

    /**
     * Returns all accumulated diagnostics of a specific severity.
     *
     * @param severity the severity to filter by. Must not be null.
     * @return a list of diagnostics matching the severity.
     */
    public List<Diagnostic> getDiagnostics(Diagnostic.Severity severity) {
        Objects.requireNonNull(severity, "Severity must not be null");
        return diagnostics.stream()
                .filter(d -> d.severity() == severity)
                .collect(Collectors.toList());
    }

    /**
     * Checks if any diagnostics of {@link Diagnostic.Severity#ERROR} have been reported.
     *
     * @return true if there is at least one error diagnostic, false otherwise.
     */
    public boolean hasErrors() {
        return diagnostics.stream().anyMatch(d -> d.severity() == Diagnostic.Severity.ERROR);
    }

    /**
     * Checks if any diagnostics of {@link Diagnostic.Severity#WARNING} have been reported.
     *
     * @return true if there is at least one warning diagnostic, false otherwise.
     */
    public boolean hasWarnings() {
        return diagnostics.stream().anyMatch(d -> d.severity() == Diagnostic.Severity.WARNING);
    }

    /**
     * Clears all accumulated diagnostics from the reporter.
     */
    public void clear() {
        diagnostics.clear();
    }

    /**
     * Checks if any errors have been reported, and if so, throws a {@link ValidationException}
     * containing a summary of all reported errors.
     *
     * @throws ValidationException if there is at least one error diagnostic.
     */
    public void checkAndThrow() {
        if (hasErrors()) {
            List<Diagnostic> errors = getDiagnostics(Diagnostic.Severity.ERROR);
            StringBuilder sb = new StringBuilder("Validation failed with ")
                    .append(errors.size())
                    .append(" error(s):")
                    .append(System.lineSeparator());
            for (Diagnostic d : errors) {
                sb.append("  - ").append(d).append(System.lineSeparator());
            }
            throw new ValidationException(sb.toString().trim(), errors);
        }
    }

    /**
     * Checks if any errors have been reported, and if so, throws a {@link ValidationException}
     * containing a summary of all reported errors formatted with visual caret snippets.
     *
     * @param sourceContent the full text of the source file to generate caret snippets. Must not be null.
     * @throws ValidationException  if there is at least one error diagnostic.
     * @throws NullPointerException if sourceContent is null.
     */
    public void checkAndThrow(String sourceContent) {
        Objects.requireNonNull(sourceContent, "Source content must not be null");
        if (hasErrors()) {
            List<Diagnostic> errors = getDiagnostics(Diagnostic.Severity.ERROR);
            StringBuilder sb = new StringBuilder("Validation failed with ")
                    .append(errors.size())
                    .append(" error(s):")
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
            for (Diagnostic d : errors) {
                sb.append("[").append(d.code()).append("] ").append(d.message()).append(System.lineSeparator());
                if (d.span() != null) {
                    try {
                        String caretSnippet = CaretSnippetFormatter.format(sourceContent, d.span());
                        sb.append(caretSnippet).append(System.lineSeparator());
                    } catch (Exception e) {
                        sb.append("  Location: ").append(d.span()).append(System.lineSeparator());
                    }
                }
                sb.append(System.lineSeparator());
            }
            throw new ValidationException(sb.toString().trim(), errors);
        }
    }
}
