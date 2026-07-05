package com.syncforge.core.diagnostics;

import java.util.Objects;

/**
 * Formats visual console caret snippets pointing directly to the error locations within source file contents.
 * 
 * <p>Given a source file's content and a {@link SourceSpan}, this utility extracts the relevant lines
 * and formats them with line numbers and a caret indicator line pointing precisely to the error columns.</p>
 * 
 * <p>Example output:
 * <pre>
 * File: /workspace/project/manifest.sfm, Line 4
 *   4 |   exclude "build/**"
 *     |           ^^^^^^^^^^
 * </pre>
 * </p>
 */
public class CaretSnippetFormatter {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Prevents instantiation of this utility class.
     */
    private CaretSnippetFormatter() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Formats a caret snippet for the given source content and source span.
     * If the source content or span is null, or if coordinates are invalid or out of bounds,
     * it falls back to a safe descriptive string.
     *
     * @param sourceContent the full text content of the source file. Must not be null.
     * @param span          the source span representing the error region. Must not be null.
     * @return a formatted caret snippet string suitable for CLI display.
     * @throws NullPointerException if sourceContent or span is null.
     */
    public static String format(String sourceContent, SourceSpan span) {
        Objects.requireNonNull(sourceContent, "sourceContent must not be null");
        Objects.requireNonNull(span, "SourceSpan must not be null");

        if (sourceContent.isEmpty()) {
            return "File: " + span.filename() + " (Empty source content)";
        }

        // Split source content into lines
        String[] lines = sourceContent.split("\\r?\\n", -1);

        int startLineIdx = span.startLine() - 1;
        int endLineIdx = span.endLine() - 1;

        // Validation bounds check
        if (startLineIdx < 0 || startLineIdx >= lines.length) {
            return String.format("File: %s, Line: %d (Line index out of source bounds)", 
                    span.filename(), span.startLine());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("File: ").append(span.filename().replace('\\', '/'))
          .append(", Line: ").append(span.startLine())
          .append(", Column: ").append(span.startCol())
          .append(LINE_SEPARATOR);

        // We format a single line caret snippet if start and end lines are the same
        if (startLineIdx == endLineIdx) {
            String lineContent = lines[startLineIdx];
            sb.append(formatLine(span.startLine(), lineContent)).append(LINE_SEPARATOR);
            sb.append(formatCaretLine(lineContent, span.startCol(), span.endCol()));
        } else {
            // For multi-line errors, print the start line and end line, highlighting their respective starts/ends
            int maxLineNumWidth = String.valueOf(span.endLine()).length();
            
            // Print start line
            String startLineContent = lines[startLineIdx];
            sb.append(formatLineWithWidth(span.startLine(), startLineContent, maxLineNumWidth)).append(LINE_SEPARATOR);
            sb.append(formatCaretLineWithWidth(startLineContent, span.startCol(), startLineContent.length() + 1, maxLineNumWidth)).append(LINE_SEPARATOR);
            
            // Print indicator for skipped lines if they are more than 2 lines apart
            if (endLineIdx - startLineIdx > 1) {
                sb.append(" ".repeat(maxLineNumWidth)).append(" | ...").append(LINE_SEPARATOR);
            }
            
            // Print end line
            int actualEndLineIdx = Math.min(endLineIdx, lines.length - 1);
            String endLineContent = lines[actualEndLineIdx];
            sb.append(formatLineWithWidth(span.endLine(), endLineContent, maxLineNumWidth)).append(LINE_SEPARATOR);
            sb.append(formatCaretLineWithWidth(endLineContent, 1, span.endCol(), maxLineNumWidth));
        }

        return sb.toString();
    }

    private static String formatLine(int lineNum, String content) {
        String lineNumStr = String.valueOf(lineNum);
        return "  " + lineNumStr + " | " + content;
    }

    private static String formatLineWithWidth(int lineNum, String content, int width) {
        String lineNumStr = String.format("%" + width + "d", lineNum);
        return "  " + lineNumStr + " | " + content;
    }

    private static String formatCaretLine(String lineContent, int startCol, int endCol) {
        // Line number prefix spacing: "  " + lineNumStr.length() + " | "
        // We find the index of the start and end column (1-based)
        int startIdx = Math.max(0, startCol - 1);
        int endIdx = Math.max(startIdx + 1, endCol);

        // Clamp values to line length
        int len = lineContent.length();
        startIdx = Math.min(startIdx, len);
        endIdx = Math.min(endIdx, len + 1);

        int caretLength = Math.max(1, endIdx - startIdx);

        // Build spaces for the prefix
        // We need to count visual spacing (expanded tabs etc. if any, but simple char counting for now)
        StringBuilder caretBuilder = new StringBuilder();
        caretBuilder.append("    | "); // prefix space matching "  LINE | "

        // Match visual whitespace of the line content for characters before startIdx
        for (int i = 0; i < startIdx; i++) {
            char c = lineContent.charAt(i);
            if (c == '\t') {
                caretBuilder.append('\t');
            } else {
                caretBuilder.append(' ');
            }
        }

        // Append carets
        caretBuilder.append("^".repeat(caretLength));

        return caretBuilder.toString();
    }

    private static String formatCaretLineWithWidth(String lineContent, int startCol, int endCol, int width) {
        int startIdx = Math.max(0, startCol - 1);
        int endIdx = Math.max(startIdx + 1, endCol);

        int len = lineContent.length();
        startIdx = Math.min(startIdx, len);
        endIdx = Math.min(endIdx, len + 1);

        int caretLength = Math.max(1, endIdx - startIdx);

        StringBuilder caretBuilder = new StringBuilder();
        caretBuilder.append("  ").append(" ".repeat(width)).append(" | ");

        for (int i = 0; i < startIdx; i++) {
            char c = lineContent.charAt(i);
            if (c == '\t') {
                caretBuilder.append('\t');
            } else {
                caretBuilder.append(' ');
            }
        }

        caretBuilder.append("^".repeat(caretLength));
        return caretBuilder.toString();
    }
}
