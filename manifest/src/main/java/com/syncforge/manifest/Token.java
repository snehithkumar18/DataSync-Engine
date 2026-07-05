package com.syncforge.manifest;

import com.syncforge.core.diagnostics.SourceSpan;
import java.util.Objects;

/**
 * Represents a parsed token from the manifest source code.
 * It encapsulates the token type, its string value, and its location in the source.
 */
public class Token {

    /**
     * Types of tokens supported by the SyncForge manifest DSL.
     */
    public enum TokenType {
        /**
         * Identifiers such as keyword or variable names (e.g. root, mode).
         */
        IDENTIFIER,
        /**
         * A string literal, typically enclosed in double quotes (e.g. "mirror").
         */
        STRING,
        /**
         * The assignment operator '='.
         */
        EQUAL,
        /**
         * Left curly brace '{'.
         */
        LBRACE,
        /**
         * Right curly brace '}'.
         */
        RBRACE,
        /**
         * Numeric literals (e.g. 123).
         */
        NUMBER,
        /**
         * Boolean literals (true or false).
         */
        BOOLEAN,
        /**
         * The include rule keyword.
         */
        INCLUDE,
        /**
         * The exclude rule keyword.
         */
        EXCLUDE,
        /**
         * End of file token.
         */
        EOF
    }

    private final TokenType type;
    private final String value;
    private final SourceSpan span;

    /**
     * Constructs a new Token.
     *
     * @param type  the type of the token. Must not be null.
     * @param value the string value of the token. Must not be null.
     * @param span  the location of the token in the source code. Must not be null.
     * @throws NullPointerException if any argument is null.
     */
    public Token(TokenType type, String value, SourceSpan span) {
        this.type = Objects.requireNonNull(type, "TokenType must not be null");
        this.value = Objects.requireNonNull(value, "Token value must not be null");
        this.span = Objects.requireNonNull(span, "Token source span must not be null");
    }

    /**
     * Returns the type of this token.
     *
     * @return the token type.
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Returns the string value of this token.
     *
     * @return the token value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the source span where this token was found.
     *
     * @return the source span.
     */
    public SourceSpan getSpan() {
        return span;
    }

    @Override
    public String toString() {
        return type + " (" + value + ") at " + span;
    }
}
