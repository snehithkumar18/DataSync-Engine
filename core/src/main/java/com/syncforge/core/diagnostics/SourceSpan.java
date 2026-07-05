package com.syncforge.core.diagnostics;

import java.util.Objects;

/**
 * Represents a span of characters in a source file, manifest, or document.
 * It is used to track precise location metadata for diagnostics, compiler stages,
 * or parsing errors.
 * 
 * <p>A SourceSpan is immutable and validates all parameters on construction.</p>
 *
 * @param filename    the name or path of the source file. Must not be null or blank.
 * @param startLine   the starting line number (1-based, inclusive). Must be &gt;= 1.
 * @param startCol    the starting column number (1-based, inclusive). Must be &gt;= 1.
 * @param endLine     the ending line number (1-based, inclusive). Must be &gt;= startLine.
 * @param endCol      the ending column number (1-based, inclusive). Must be &gt;= 1, 
 *                    and if startLine equals endLine, endCol must be &gt;= startCol.
 * @param startOffset the starting character offset (0-based, inclusive). Must be &gt;= 0.
 * @param endOffset   the ending character offset (0-based, exclusive). Must be &gt;= startOffset.
 */
public record SourceSpan(
        String filename,
        int startLine,
        int startCol,
        int endLine,
        int endCol,
        int startOffset,
        int endOffset
) {

    /**
     * Compact constructor for SourceSpan to enforce strict boundaries and invariant checks.
     *
     * @throws NullPointerException     if {@code filename} is null.
     * @throws IllegalArgumentException if any coordinates or offsets violate the validity rules.
     */
    public SourceSpan {
        Objects.requireNonNull(filename, "Filename must not be null");
        if (filename.isBlank()) {
            throw new IllegalArgumentException("Filename must not be empty or blank");
        }
        if (startLine < 1) {
            throw new IllegalArgumentException("startLine must be >= 1, got: " + startLine);
        }
        if (startCol < 1) {
            throw new IllegalArgumentException("startCol must be >= 1, got: " + startCol);
        }
        if (endLine < startLine) {
            throw new IllegalArgumentException("endLine (" + endLine + ") cannot be before startLine (" + startLine + ")");
        }
        if (endCol < 1) {
            throw new IllegalArgumentException("endCol must be >= 1, got: " + endCol);
        }
        if (startLine == endLine && endCol < startCol) {
            throw new IllegalArgumentException("endCol (" + endCol + ") cannot be before startCol (" + startCol + ") on the same line");
        }
        if (startOffset < 0) {
            throw new IllegalArgumentException("startOffset must be >= 0, got: " + startOffset);
        }
        if (endOffset < startOffset) {
            throw new IllegalArgumentException("endOffset (" + endOffset + ") cannot be before startOffset (" + startOffset + ")");
        }
    }

    /**
     * Calculates the length of this span in characters.
     *
     * @return the number of characters covered by this span.
     */
    public int length() {
        return endOffset - startOffset;
    }

    /**
     * Checks if a given 0-based offset falls within the range of this span.
     *
     * @param offset the 0-based offset to check.
     * @return true if the offset is &gt;= startOffset and &lt; endOffset, false otherwise.
     */
    public boolean contains(int offset) {
        return offset >= startOffset && offset < endOffset;
    }

    /**
     * Combines this span with another span to form a larger span enclosing both.
     * Both spans must refer to the same filename.
     *
     * @param other the other span to merge. Must not be null and must share the same filename.
     * @return a new SourceSpan enclosing both spans.
     * @throws NullPointerException     if {@code other} is null.
     * @throws IllegalArgumentException if the other span belongs to a different file.
     */
    public SourceSpan merge(SourceSpan other) {
        Objects.requireNonNull(other, "Other span to merge must not be null");
        if (!this.filename.equals(other.filename)) {
            throw new IllegalArgumentException("Cannot merge spans from different files: '" 
                    + this.filename + "' and '" + other.filename + "'");
        }

        int newStartLine = Math.min(this.startLine, other.startLine);
        int newStartCol = (newStartLine == this.startLine) 
                ? ((newStartLine == other.startLine) ? Math.min(this.startCol, other.startCol) : this.startCol)
                : other.startCol;

        int newEndLine = Math.max(this.endLine, other.endLine);
        int newEndCol = (newEndLine == this.endLine)
                ? ((newEndLine == other.endLine) ? Math.max(this.endCol, other.endCol) : this.endCol)
                : other.endCol;

        int newStartOffset = Math.min(this.startOffset, other.startOffset);
        int newEndOffset = Math.max(this.endOffset, other.endOffset);

        return new SourceSpan(this.filename, newStartLine, newStartCol, newEndLine, newEndCol, newStartOffset, newEndOffset);
    }

    /**
     * Returns a string representation of the source span in a standardized format.
     *
     * @return a formatted string representing the source location.
     */
    @Override
    public String toString() {
        String normalizedFile = filename.replace('\\', '/');
        if (startLine == endLine) {
            if (startCol == endCol) {
                return normalizedFile + ":" + startLine + ":" + startCol;
            }
            return normalizedFile + ":" + startLine + ":" + startCol + "-" + endCol;
        }
        return normalizedFile + ":" + startLine + ":" + startCol + "-" + endLine + ":" + endCol;
    }
}
