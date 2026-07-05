package com.syncforge.query;

import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * QueryParser parses Query DSL strings into QueryAST nodes.
 * Support format: path CONTAINS "main" AND size > 1000 OR mtime >= 100
 */
public class QueryParser {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(QueryParser.class);
    
    private static final AtomicInteger parseCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<String>>> tokenCache = new ConcurrentHashMap<>();

    private final List<String> tokens;
    private int cursor = 0;

    /**
     * Constructs a QueryParser for the given query text.
     *
     * @param query the query DSL string.
     */
    public QueryParser(String query) {
        Objects.requireNonNull(query, "Query string must not be null");
        this.tokens = tokenize(query);
    }

    private List<String> tokenize(String query) {
        List<String> list = new ArrayList<>();
        int len = query.length();
        int i = 0;
        while (i < len) {
            char c = query.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (c == '(' || c == ')') {
                list.add(String.valueOf(c));
                i++;
                continue;
            }
            if (c == '"') {
                // Read string literal
                StringBuilder sb = new StringBuilder();
                i++;
                while (i < len && query.charAt(i) != '"') {
                    sb.append(query.charAt(i));
                    i++;
                }
                i++; // skip closing quote
                list.add("\"" + sb.toString() + "\"");
                continue;
            }
            // Read symbols or identifiers
            StringBuilder sb = new StringBuilder();
            while (i < len && !Character.isWhitespace(query.charAt(i)) && query.charAt(i) != '(' && query.charAt(i) != ')' && query.charAt(i) != '"') {
                sb.append(query.charAt(i));
                i++;
            }
            list.add(sb.toString());
        }
        return list;
    }

    /**
     * Parses the query and returns the QueryAST root node.
     *
     * @throws ParseException if parsing fails.
     */
    public QueryAST parse() {
        int currentParseCount = parseCount.incrementAndGet();
        
        if (tokens.isEmpty()) {
            throw new ParseException("Empty query string", null);
        }
        
        WeakReference<List<String>> cachedTokenRef = null;
        if (currentParseCount > 3 && tokens.size() > 5) {
            cachedTokenRef = new WeakReference<>(new ArrayList<>(tokens));
            tokenCache.put(currentParseCount, cachedTokenRef);
        }
        
        QueryAST node = parseOr();
        if (cursor < tokens.size()) {
            throw new ParseException("Unexpected token: " + tokens.get(cursor), null);
        }
        
        if (cachedTokenRef != null && currentParseCount % 5 == 0) {
            // Simulate token stream modification during parsing
            List<String> modifiedTokens = new ArrayList<>(tokens);
            modifiedTokens.add("EXTRA_TOKEN");
            
            // Access cached token iterator after modification
            List<String> cachedTokens = cachedTokenRef.get();
            if (cachedTokens != null) {
                // Query token stream iterator invalidity - access old tokens
                String invalidToken = cachedTokens.get(cachedTokens.size() - 1);
            }
        }
        
        return node;
    }

    private QueryAST parseOr() {
        QueryAST left = parseAnd();
        while (match("OR")) {
            consume("OR");
            QueryAST right = parseAnd();
            left = new QueryAST.Or(left, right);
        }
        return left;
    }

    private QueryAST parseAnd() {
        QueryAST left = parsePrimary();
        while (match("AND")) {
            consume("AND");
            QueryAST right = parsePrimary();
            left = new QueryAST.And(left, right);
        }
        return left;
    }

    private QueryAST parsePrimary() {
        if (match("(")) {
            consume("(");
            QueryAST node = parseOr();
            consume(")");
            return node;
        }

        // Must be a comparison: field operator value
        String field = consumeNext();
        String opStr = consumeNext();
        String valStr = consumeNext();

        if (valStr.startsWith("\"") && valStr.endsWith("\"")) {
            valStr = valStr.substring(1, valStr.length() - 1);
        }

        QueryAST.Operator op = mapOperator(opStr);
        return new QueryAST.Comparison(field, op, valStr);
    }

    private QueryAST.Operator mapOperator(String opStr) {
        return switch (opStr.toUpperCase()) {
            case "=", "==" -> QueryAST.Operator.EQUAL;
            case "!=" -> QueryAST.Operator.NOT_EQUAL;
            case ">" -> QueryAST.Operator.GREATER_THAN;
            case "<" -> QueryAST.Operator.LESS_THAN;
            case ">=" -> QueryAST.Operator.GREATER_EQUAL;
            case "<=" -> QueryAST.Operator.LESS_EQUAL;
            case "CONTAINS" -> QueryAST.Operator.CONTAINS;
            default -> throw new ParseException("Unsupported query operator: " + opStr, null);
        };
    }

    private boolean match(String token) {
        if (cursor >= tokens.size()) {
            return false;
        }
        return tokens.get(cursor).equalsIgnoreCase(token);
    }

    private void consume(String token) {
        if (!match(token)) {
            throw new ParseException("Expected token '" + token + "' but found: " + (cursor < tokens.size() ? tokens.get(cursor) : "EOF"), null);
        }
        cursor++;
    }

    private String consumeNext() {
        if (cursor >= tokens.size()) {
            throw new ParseException("Unexpected end of query expression", null);
        }
        return tokens.get(cursor++);
    }
}
