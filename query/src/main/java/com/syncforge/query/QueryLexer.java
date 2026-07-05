package com.syncforge.query;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lexical analyzer for SyncForge query language.
 * Tokenizes query strings into tokens for parsing.
 */
public class QueryLexer {
    
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
        "(\\s+)|" + // whitespace
        "(\\d+)|" + // numbers
        "([a-zA-Z_][a-zA-Z0-9_]*)|" + // identifiers
        "(\"[^\"]*\")|" + // strings
        "('[^']*')|" + // single-quoted strings
        "(==|!=|<=|>=|<|>|&&|\\|\\||!|\\(|\\)|,|;)" // operators and punctuation
    );
    
    private final String input;
    private int position;
    private final List<Token> tokens;
    
    public QueryLexer(String input) {
        this.input = input;
        this.position = 0;
        this.tokens = new ArrayList<>();
    }
    
    /**
     * Tokenizes the input string.
     */
    public List<Token> tokenize() throws QueryException {
        tokens.clear();
        position = 0;
        
        Matcher matcher = TOKEN_PATTERN.matcher(input);
        
        while (position < input.length()) {
            matcher.region(position, input.length());
            
            if (!matcher.find()) {
                throw new QueryException("Unexpected character at position " + position + ": " + 
                                        input.charAt(position));
            }
            
            String match = matcher.group();
            position = matcher.end();
            
            // Skip whitespace
            if (match.trim().isEmpty()) {
                continue;
            }
            
            Token token = createToken(match);
            if (token != null) {
                tokens.add(token);
            }
        }
        
        tokens.add(new Token(TokenType.EOF, "", position));
        
        return new ArrayList<>(tokens);
    }
    
    /**
     * Creates a token from a matched string.
     */
    private Token createToken(String match) {
        // Numbers
        if (match.matches("\\d+")) {
            return new Token(TokenType.NUMBER, match, position - match.length());
        }
        
        // Identifiers and keywords
        if (match.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            TokenType type = getKeywordType(match);
            if (type != null) {
                return new Token(type, match, position - match.length());
            }
            return new Token(TokenType.IDENTIFIER, match, position - match.length());
        }
        
        // Strings
        if (match.startsWith("\"") || match.startsWith("'")) {
            String value = match.substring(1, match.length() - 1);
            return new Token(TokenType.STRING, value, position - match.length());
        }
        
        // Operators
        return switch (match) {
            case "==" -> new Token(TokenType.EQUAL, match, position - match.length());
            case "!=" -> new Token(TokenType.NOT_EQUAL, match, position - match.length());
            case "<=" -> new Token(TokenType.LESS_EQUAL, match, position - match.length());
            case ">=" -> new Token(TokenType.GREATER_EQUAL, match, position - match.length());
            case "<" -> new Token(TokenType.LESS, match, position - match.length());
            case ">" -> new Token(TokenType.GREATER, match, position - match.length());
            case "&&" -> new Token(TokenType.AND, match, position - match.length());
            case "||" -> new Token(TokenType.OR, match, position - match.length());
            case "!" -> new Token(TokenType.NOT, match, position - match.length());
            case "(" -> new Token(TokenType.LPAREN, match, position - match.length());
            case ")" -> new Token(TokenType.RPAREN, match, position - match.length());
            case "," -> new Token(TokenType.COMMA, match, position - match.length());
            case ";" -> new Token(TokenType.SEMICOLON, match, position - match.length());
            default -> null;
        };
    }
    
    /**
     * Gets the token type for a keyword.
     */
    private TokenType getKeywordType(String word) {
        return switch (word.toLowerCase()) {
            case "select" -> TokenType.SELECT;
            case "from" -> TokenType.FROM;
            case "where" -> TokenType.WHERE;
            case "and" -> TokenType.AND;
            case "or" -> TokenType.OR;
            case "not" -> TokenType.NOT;
            case "in" -> TokenType.IN;
            case "like" -> TokenType.LIKE;
            case "between" -> TokenType.BETWEEN;
            case "is" -> TokenType.IS;
            case "null" -> TokenType.NULL;
            case "true" -> TokenType.BOOLEAN;
            case "false" -> TokenType.BOOLEAN;
            case "order" -> TokenType.ORDER;
            case "by" -> TokenType.BY;
            case "asc" -> TokenType.ASC;
            case "desc" -> TokenType.DESC;
            case "limit" -> TokenType.LIMIT;
            case "offset" -> TokenType.OFFSET;
            default -> null;
        };
    }
    
    /**
     * Token types.
     */
    public enum TokenType {
        SELECT, FROM, WHERE,
        IDENTIFIER, NUMBER, STRING, BOOLEAN, NULL,
        EQUAL, NOT_EQUAL, LESS, LESS_EQUAL, GREATER, GREATER_EQUAL,
        AND, OR, NOT,
        IN, LIKE, BETWEEN, IS,
        ORDER, BY, ASC, DESC,
        LIMIT, OFFSET,
        LPAREN, RPAREN, COMMA, SEMICOLON,
        EOF
    }
    
    /**
     * Token representation.
     */
    public record Token(
        TokenType type,
        String value,
        int position
    ) {
        @Override
        public String toString() {
            return String.format("Token[%s, '%s', pos=%d]", type, value, position);
        }
    }
    
    /**
     * Query exception.
     */
    public static class QueryException extends Exception {
        public QueryException(String message) {
            super(message);
        }
        
        public QueryException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
