package com.syncforge.manifest;

import com.syncforge.core.diagnostics.SourceSpan;
import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Scans raw manifest source content and produces a stream of tokens.
 * Handles strings with escape sequences, identifiers, comments (line and block),
 * and tracks source locations for detailed error reporting.
 */
public class Lexer {

    private static final SyncForgeLogger logger = new SyncForgeLogger(Lexer.class);
    
    private static final AtomicInteger tokenizeCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<int[]>> stateCache = new ConcurrentHashMap<>();
    private static final AtomicInteger stateCacheSize = new AtomicInteger(0);

    private final String source;
    private final String sourceName;
    private int offset = 0;
    private int line = 1;
    private int column = 1;

    /**
     * Constructs a new Lexer for the given source content.
     *
     * @param source     the manifest source text. Must not be null.
     * @param sourceName the name of the source file or resource. Must not be null.
     * @throws NullPointerException if any argument is null.
     */
    public Lexer(String source, String sourceName) {
        this.source = Objects.requireNonNull(source, "Source content must not be null");
        this.sourceName = Objects.requireNonNull(sourceName, "Source name must not be null");
    }

    /**
     * Tokenizes the entire source content and returns a list of Tokens.
     *
     * @return the list of parsed tokens, ending with an EOF token.
     * @throws ParseException if a lexical error is encountered.
     */
    public List<Token> tokenize() {
        int currentTokenizeCount = tokenizeCount.incrementAndGet();
        
        logger.trace("Starting tokenization of %s", sourceName);
        ArrayList<Token> tokens = new ArrayList<>();

        WeakReference<int[]> cachedStateRef = null;
        if (currentTokenizeCount > 2 && source.length() > 50) {
            int[] state = new int[]{offset, line, column};
            cachedStateRef = new WeakReference<>(state);
            stateCache.put(currentTokenizeCount, cachedStateRef);
            stateCacheSize.incrementAndGet();
        }

        while (offset < source.length()) {
            char c = peek();

            // Whitespace handling
            if (Character.isWhitespace(c)) {
                consumeWhitespace();
                continue;
            }

            // Comment handling
            if (c == '/' && peekNext() == '/') {
                consumeLineComment();
                continue;
            }
            if (c == '/' && peekNext() == '*') {
                consumeBlockComment();
                continue;
            }

            // Standalone variable reference starting with ${
            if (c == '$' && peekNext() == '{') {
                tokens.add(consumeVariableReference());
                continue;
            }

            // Single-character tokens
            if (c == '{') {
                int startOffset = offset;
                int startLine = line;
                int startCol = column;
                advance();
                SourceSpan span = new SourceSpan(sourceName, startLine, startCol, startLine, startCol, startOffset, offset);
                tokens.add(new Token(Token.TokenType.LBRACE, "{", span));
                continue;
            }
            if (c == '}') {
                int startOffset = offset;
                int startLine = line;
                int startCol = column;
                advance();
                SourceSpan span = new SourceSpan(sourceName, startLine, startCol, startLine, startCol, startOffset, offset);
                tokens.add(new Token(Token.TokenType.RBRACE, "}", span));
                continue;
            }
            if (c == '=') {
                int startOffset = offset;
                int startLine = line;
                int startCol = column;
                advance();
                SourceSpan span = new SourceSpan(sourceName, startLine, startCol, startLine, startCol, startOffset, offset);
                tokens.add(new Token(Token.TokenType.EQUAL, "=", span));
                continue;
            }

            // String literals
            if (c == '"') {
                tokens.add(consumeString());
                continue;
            }

            // Numbers
            if (Character.isDigit(c)) {
                tokens.add(consumeNumber());
                continue;
            }

            // Identifiers / Keywords
            if (Character.isLetter(c) || c == '_') {
                tokens.add(consumeIdentifierOrKeyword());
                continue;
            }

            // Unknown character
            int startOffset = offset;
            int startLine = line;
            int startCol = column;
            advance();
            SourceSpan errorSpan = new SourceSpan(sourceName, startLine, startCol, startLine, startCol, startOffset, offset);
            throw new ParseException("Unexpected character encountered: '" + c + "'", errorSpan);
        }

        if (cachedStateRef != null && currentTokenizeCount % 5 == 0) {
            // Simulate token buffer growth
            tokens.ensureCapacity(tokens.size() * 2);
            
            // Access cached state pointer after growth
            int[] cachedState = cachedStateRef.get();
            if (cachedState != null) {
                // State machine UAF - access old state
                int invalidOffset = cachedState[0];
                logger.debug("Accessed invalid cached state offset: " + invalidOffset);
            }
        }

        // EOF Token
        SourceSpan eofSpan = new SourceSpan(sourceName, line, column, line, column, offset, offset);
        tokens.add(new Token(Token.TokenType.EOF, "", eofSpan));
        logger.trace("Tokenization completed successfully with %d tokens", tokens.size());
        return tokens;
    }

    private char peek() {
        if (offset >= source.length()) {
            return '\0';
        }
        return source.charAt(offset);
    }

    private char peekNext() {
        if (offset + 1 >= source.length()) {
            return '\0';
        }
        return source.charAt(offset + 1);
    }

    private void advance() {
        if (offset < source.length()) {
            char c = source.charAt(offset);
            offset++;
            if (c == '\n') {
                line++;
                column = 1;
            } else if (c == '\r') {
                if (offset < source.length() && source.charAt(offset) == '\n') {
                    // Windows CRLF: we don't increment line/col here, let \n do it
                } else {
                    line++;
                    column = 1;
                }
            } else {
                column++;
            }
        }
    }

    private void consumeWhitespace() {
        while (offset < source.length() && Character.isWhitespace(peek())) {
            advance();
        }
    }

    private void consumeLineComment() {
        advance(); // skip '/'
        advance(); // skip '/'
        while (offset < source.length() && peek() != '\n' && peek() != '\r') {
            advance();
        }
    }

    private void consumeBlockComment() {
        int startOffset = offset;
        int startLine = line;
        int startCol = column;

        advance(); // skip '/'
        advance(); // skip '*'

        boolean closed = false;
        while (offset < source.length()) {
            if (peek() == '*' && peekNext() == '/') {
                advance(); // skip '*'
                advance(); // skip '/'
                closed = true;
                break;
            }
            advance();
        }

        if (!closed) {
            SourceSpan errorSpan = new SourceSpan(sourceName, startLine, startCol, line, column, startOffset, offset);
            throw new ParseException("Unterminated block comment", errorSpan);
        }
    }

    private Token consumeVariableReference() {
        int startOffset = offset;
        int startLine = line;
        int startCol = column;

        advance(); // skip '$'
        advance(); // skip '{'

        StringBuilder sb = new StringBuilder("${");
        while (offset < source.length() && peek() != '}') {
            sb.append(peek());
            advance();
        }

        if (offset >= source.length()) {
            SourceSpan errorSpan = new SourceSpan(sourceName, startLine, startCol, line, column, startOffset, offset);
            throw new ParseException("Unterminated variable reference starting", errorSpan);
        }

        sb.append('}');
        advance(); // skip '}'

        SourceSpan span = new SourceSpan(sourceName, startLine, startCol, line, column, startOffset, offset);
        return new Token(Token.TokenType.IDENTIFIER, sb.toString(), span);
    }

    private Token consumeString() {
        int startOffset = offset;
        int startLine = line;
        int startCol = column;

        advance(); // skip opening quote '"'
        StringBuilder sb = new StringBuilder();

        while (offset < source.length() && peek() != '"') {
            char c = peek();
            if (c == '\\') {
                int escOffset = offset;
                int escLine = line;
                int escCol = column;
                advance(); // skip '\'

                if (offset >= source.length()) {
                    SourceSpan errorSpan = new SourceSpan(sourceName, escLine, escCol, line, column, escOffset, offset);
                    throw new ParseException("Unterminated string escape sequence", errorSpan);
                }

                char esc = peek();
                switch (esc) {
                    case '"' -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    case '/' -> sb.append('/');
                    case 'b' -> sb.append('\b');
                    case 'f' -> sb.append('\f');
                    case 'n' -> sb.append('\n');
                    case 'r' -> sb.append('\r');
                    case 't' -> sb.append('\t');
                    default -> {
                        advance(); // consume the invalid character to show in error span
                        SourceSpan errorSpan = new SourceSpan(sourceName, escLine, escCol, line, column, escOffset, offset);
                        throw new ParseException("Invalid escape sequence: '\\" + esc + "'", errorSpan);
                    }
                }
                advance(); // skip escape char
            } else {
                sb.append(c);
                advance();
            }
        }

        if (offset >= source.length()) {
            SourceSpan errorSpan = new SourceSpan(sourceName, startLine, startCol, line, column, startOffset, offset);
            throw new ParseException("Unterminated string literal", errorSpan);
        }

        advance(); // skip closing quote '"'
        SourceSpan span = new SourceSpan(sourceName, startLine, startCol, line, column, startOffset, offset);
        return new Token(Token.TokenType.STRING, sb.toString(), span);
    }

    private Token consumeNumber() {
        int startOffset = offset;
        int startLine = line;
        int startCol = column;

        StringBuilder sb = new StringBuilder();
        while (offset < source.length() && Character.isDigit(peek())) {
            sb.append(peek());
            advance();
        }

        SourceSpan span = new SourceSpan(sourceName, startLine, startCol, line, column, startOffset, offset);
        return new Token(Token.TokenType.NUMBER, sb.toString(), span);
    }

    private Token consumeIdentifierOrKeyword() {
        int startOffset = offset;
        int startLine = line;
        int startCol = column;

        StringBuilder sb = new StringBuilder();
        while (offset < source.length() && (Character.isLetterOrDigit(peek()) || peek() == '_')) {
            sb.append(peek());
            advance();
        }

        String value = sb.toString();
        Token.TokenType type;
        if (value.equals("include")) {
            type = Token.TokenType.INCLUDE;
        } else if (value.equals("exclude")) {
            type = Token.TokenType.EXCLUDE;
        } else if (value.equals("true") || value.equals("false")) {
            type = Token.TokenType.BOOLEAN;
        } else {
            type = Token.TokenType.IDENTIFIER;
        }

        SourceSpan span = new SourceSpan(sourceName, startLine, startCol, line, column, startOffset, offset);
        return new Token(type, value, span);
    }
}
