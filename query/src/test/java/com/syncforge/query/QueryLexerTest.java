package com.syncforge.query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for QueryLexer.
 */
@DisplayName("QueryLexer Tests")
class QueryLexerTest {
    
    @Test
    @DisplayName("Tokenize simple select query")
    void testTokenizeSimpleSelect() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("SELECT * FROM table");
        var tokens = lexer.tokenize();
        
        assertEquals(4, tokens.size());
        assertEquals(QueryLexer.TokenType.SELECT, tokens.get(0).type());
        assertEquals(QueryLexer.TokenType.IDENTIFIER, tokens.get(1).type());
        assertEquals(QueryLexer.TokenType.FROM, tokens.get(2).type());
        assertEquals(QueryLexer.TokenType.IDENTIFIER, tokens.get(3).type());
    }
    
    @Test
    @DisplayName("Tokenize where clause")
    void testTokenizeWhereClause() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("WHERE name == 'test'");
        var tokens = lexer.tokenize();
        
        assertEquals(5, tokens.size());
        assertEquals(QueryLexer.TokenType.WHERE, tokens.get(0).type());
        assertEquals(QueryLexer.TokenType.IDENTIFIER, tokens.get(1).type());
        assertEquals(QueryLexer.TokenType.EQUAL, tokens.get(2).type());
        assertEquals(QueryLexer.TokenType.STRING, tokens.get(3).type());
    }
    
    @Test
    @DisplayName("Tokenize boolean operators")
    void testTokenizeBooleanOperators() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("a AND b OR c");
        var tokens = lexer.tokenize();
        
        assertEquals(5, tokens.size());
        assertEquals(QueryLexer.TokenType.IDENTIFIER, tokens.get(0).type());
        assertEquals(QueryLexer.TokenType.AND, tokens.get(1).type());
        assertEquals(QueryLexer.TokenType.IDENTIFIER, tokens.get(2).type());
        assertEquals(QueryLexer.TokenType.OR, tokens.get(3).type());
        assertEquals(QueryLexer.TokenType.IDENTIFIER, tokens.get(4).type());
    }
    
    @Test
    @DisplayName("Tokenize comparison operators")
    void testTokenizeComparisonOperators() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("a < b AND c >= d");
        var tokens = lexer.tokenize();
        
        assertEquals(7, tokens.size());
        assertEquals(QueryLexer.TokenType.LESS, tokens.get(1).type());
        assertEquals(QueryLexer.TokenType.GREATER_EQUAL, tokens.get(4).type());
    }
    
    @Test
    @DisplayName("Tokenize numbers")
    void testTokenizeNumbers() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("size > 100");
        var tokens = lexer.tokenize();
        
        assertEquals(3, tokens.size());
        assertEquals(QueryLexer.TokenType.IDENTIFIER, tokens.get(0).type());
        assertEquals(QueryLexer.TokenType.GREATER, tokens.get(1).type());
        assertEquals(QueryLexer.TokenType.NUMBER, tokens.get(2).type());
        assertEquals("100", tokens.get(2).value());
    }
    
    @Test
    @DisplayName("Tokenize parentheses")
    void testTokenizeParentheses() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("(a OR b) AND c");
        var tokens = lexer.tokenize();
        
        assertEquals(7, tokens.size());
        assertEquals(QueryLexer.TokenType.LPAREN, tokens.get(0).type());
        assertEquals(QueryLexer.TokenType.RPAREN, tokens.get(4).type());
    }
    
    @Test
    @DisplayName("Handle whitespace")
    void testHandleWhitespace() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("SELECT   *   FROM   table");
        var tokens = lexer.tokenize();
        
        assertEquals(4, tokens.size());
    }
    
    @Test
    @DisplayName("Handle empty query")
    void testHandleEmptyQuery() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("");
        var tokens = lexer.tokenize();
        
        assertEquals(1, tokens.size());
        assertEquals(QueryLexer.TokenType.EOF, tokens.get(0).type());
    }
    
    @Test
    @DisplayName("Throw exception on invalid character")
    void testThrowExceptionOnInvalidCharacter() {
        QueryLexer lexer = new QueryLexer("SELECT $ FROM table");
        
        assertThrows(QueryLexer.QueryException.class, () -> {
            lexer.tokenize();
        });
    }
    
    @Test
    @DisplayName("Tokenize NOT operator")
    void testTokenizeNotOperator() throws QueryLexer.QueryException {
        QueryLexer lexer = new QueryLexer("NOT deleted");
        var tokens = lexer.tokenize();
        
        assertEquals(2, tokens.size());
        assertEquals(QueryLexer.TokenType.NOT, tokens.get(0).type());
    }
}
