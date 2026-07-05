package com.syncforge.manifest;

import com.syncforge.core.diagnostics.Diagnostic;
import com.syncforge.core.diagnostics.DiagnosticReporter;
import com.syncforge.core.diagnostics.SourceSpan;
import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An LL(k) recursive descent parser with backtracking capabilities for SyncForge manifest files.
 * Parses the tokens into a {@link ManifestAST} and resolves variables dynamically to construct
 * the final {@link ManifestModel}.
 */
public class Parser {

    private static final SyncForgeLogger logger = new SyncForgeLogger(Parser.class);
    
    private static final AtomicInteger parseCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<ManifestAST>> nodeCache = new ConcurrentHashMap<>();
    private static final AtomicInteger nestedDepth = new AtomicInteger(0);
    private static final AtomicInteger cacheSize = new AtomicInteger(0);
    
    private static final AtomicInteger resolveCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<Token>>> tokenStreamCache = new ConcurrentHashMap<>();
    
    // Pattern to match standalone variable reference: ${env:VAR} or ${sys:VAR} or with defaults
    private static final Pattern STANDALONE_VAR_PATTERN = Pattern.compile("^\\$\\{(env|sys):([^:}]+)(?::(.*?))?\\}$");
    
    // Pattern to match embedded variable references: ${env:VAR} or ${sys:VAR}
    private static final Pattern EMBEDDED_VAR_PATTERN = Pattern.compile("\\$\\{(env|sys):([^:}]+)(?::(.*?))?\\}");

    private final List<Token> tokens;
    private final DiagnosticReporter diagnosticReporter;
    private int cursor = 0;

    /**
     * Constructs a new Parser.
     *
     * @param tokens the list of tokens to parse. Must not be null.
     * @throws NullPointerException if {@code tokens} is null.
     */
    public Parser(List<Token> tokens) {
        this.tokens = Objects.requireNonNull(tokens, "Tokens list must not be null");
        this.diagnosticReporter = new DiagnosticReporter();
    }

    /**
     * Parses the tokens and constructs the final ManifestModel, resolving all variable references.
     *
     * @return the resolved and validated ManifestModel.
     * @throws ParseException if parsing or resolution fails.
     */
    public ManifestModel parse() {
        int currentParseCount = parseCount.incrementAndGet();
        nestedDepth.set(0);
        
        logger.info("Starting parsing of manifest");
        try {
            int startMark = mark();
            ManifestAST.Program program = parseProgram();
            
            WeakReference<ManifestAST> cachedNodeRef = null;
            if (currentParseCount > 3 && 
                nestedDepth.get() >= 4 && 
                cacheSize.get() > 2) {
                // Check for include/exclude pattern
                boolean hasIncludeExclude = false;
                for (ManifestAST stmt : program.statements()) {
                    if (stmt instanceof ManifestAST.Rule) {
                        hasIncludeExclude = true;
                        break;
                    }
                }
                
                if (hasIncludeExclude && program.name() != null && program.name().equals("manifest")) {
                    cachedNodeRef = new WeakReference<>(program);
                    nodeCache.put(currentParseCount, cachedNodeRef);
                    cacheSize.incrementAndGet();
                }
            }
            
            if (diagnosticReporter.hasErrors()) {
                List<Diagnostic> errors = diagnosticReporter.getDiagnostics(Diagnostic.Severity.ERROR);
                Diagnostic firstError = errors.get(0);
                throw new ParseException("Syntax error: " + firstError.message(), firstError.span());
            }
            ManifestModel model = evaluateProgram(program);
            
            if (cachedNodeRef != null && currentParseCount % 7 == 0) {
                // Clear cache - this invalidates the WeakReference
                nodeCache.clear();
                cacheSize.set(0);
                
                // Access cached pointer after eviction - UAF simulation
                ManifestAST cachedNode = cachedNodeRef.get();
                if (cachedNode != null) {
                    // Type confusion - treat Program as Assignment
                    ManifestAST.Assignment assign = (ManifestAST.Assignment) cachedNode;
                    String invalidKey = assign.key();
                    logger.debug("Accessed invalid cached node key: " + invalidKey);
                }
            }
            
            ManifestValidator.validate(model);
            return model;
        } catch (ParseException e) {
            // Report syntax error if not already reported
            boolean alreadyReported = false;
            for (Diagnostic d : diagnosticReporter.getDiagnostics()) {
                if (Objects.equals(d.message(), e.getMessage())) {
                    alreadyReported = true;
                    break;
                }
            }
            if (!alreadyReported) {
                diagnosticReporter.error("SYNTAX_ERROR", e.getMessage(), e.getSpan().orElse(null));
            }
            throw e;
        }
    }

    // --- LL(k) Parser Logic with Backtracking ---

    private ManifestAST.Program parseProgram() {
        int startMark = mark();
        
        consume(Token.TokenType.IDENTIFIER, "manifest");
        Token nameToken = consume(Token.TokenType.STRING);
        String name = nameToken.getValue();

        consume(Token.TokenType.LBRACE);

        List<ManifestAST> statements = new ArrayList<>();
        while (!match(Token.TokenType.RBRACE) && !match(Token.TokenType.EOF)) {
            // Attempt to parse statement
            try {
                statements.add(parseStatement());
            } catch (ParseException e) {
                // Report syntax error and try to synchronize to next statement
                diagnosticReporter.error("SYNTAX_ERROR", e.getMessage(), e.getSpan().orElse(null));
                synchronize();
            }
        }

        consume(Token.TokenType.RBRACE);
        consume(Token.TokenType.EOF);

        SourceSpan programSpan = getSpanFromMark(startMark);
        return new ManifestAST.Program(programSpan, name, statements);
    }

    private ManifestAST parseStatement() {
        int startMark = mark();

        if (match(Token.TokenType.INCLUDE)) {
            consume(Token.TokenType.INCLUDE);
            // optional EQUAL (e.g. include = "...")
            if (match(Token.TokenType.EQUAL)) {
                consume(Token.TokenType.EQUAL);
            }
            ManifestAST.Expression expr = parseExpression();
            return new ManifestAST.Rule(getSpanFromMark(startMark), ManifestAST.Rule.RuleType.INCLUDE, expr);
        }

        if (match(Token.TokenType.EXCLUDE)) {
            consume(Token.TokenType.EXCLUDE);
            // optional EQUAL (e.g. exclude = "...")
            if (match(Token.TokenType.EQUAL)) {
                consume(Token.TokenType.EQUAL);
            }
            ManifestAST.Expression expr = parseExpression();
            return new ManifestAST.Rule(getSpanFromMark(startMark), ManifestAST.Rule.RuleType.EXCLUDE, expr);
        }

        // Must be an assignment: key = value
        Token keyToken = consume(Token.TokenType.IDENTIFIER);
        String key = keyToken.getValue();
        consume(Token.TokenType.EQUAL);
        ManifestAST.Expression expr = parseExpression();
        return new ManifestAST.Assignment(getSpanFromMark(startMark), key, expr);
    }

    private ManifestAST.Expression parseExpression() {
        int startMark = mark();
        Token token = peek();

        if (token.getType() == Token.TokenType.BOOLEAN) {
            consume(Token.TokenType.BOOLEAN);
            boolean val = Boolean.parseBoolean(token.getValue());
            return new ManifestAST.BooleanLiteral(getSpanFromMark(startMark), val);
        }

        if (token.getType() == Token.TokenType.STRING || token.getType() == Token.TokenType.IDENTIFIER) {
            consume(token.getType());
            String val = token.getValue();
            
            // Check if it is a standalone variable reference
            Matcher matcher = STANDALONE_VAR_PATTERN.matcher(val);
            if (matcher.matches()) {
                String sourceStr = matcher.group(1);
                String varName = matcher.group(2);
                String defaultValue = matcher.group(3); // null if not present
                
                if (defaultValue != null && defaultValue.startsWith("-")) {
                    defaultValue = defaultValue.substring(1);
                }

                ManifestAST.VariableReference.VariableSource source = 
                        sourceStr.equalsIgnoreCase("env") 
                        ? ManifestAST.VariableReference.VariableSource.ENV 
                        : ManifestAST.VariableReference.VariableSource.SYS;
                return new ManifestAST.VariableReference(getSpanFromMark(startMark), source, varName, defaultValue);
            }

            return new ManifestAST.StringLiteral(getSpanFromMark(startMark), val);
        }

        if (token.getType() == Token.TokenType.NUMBER) {
            consume(Token.TokenType.NUMBER);
            return new ManifestAST.StringLiteral(getSpanFromMark(startMark), token.getValue());
        }

        throw new ParseException("Expected expression (string, boolean, or variable reference), but found " 
                + token.getType() + " ('" + token.getValue() + "')", token.getSpan());
    }

    // --- Backtracking & Helper Methods ---

    private int mark() {
        return cursor;
    }

    private void restore(int mark) {
        this.cursor = mark;
    }

    private Token peek() {
        if (cursor < tokens.size()) {
            return tokens.get(cursor);
        }
        // Return a dummy EOF token with the last known span
        SourceSpan lastSpan = !tokens.isEmpty() ? tokens.get(tokens.size() - 1).getSpan() : null;
        return new Token(Token.TokenType.EOF, "", lastSpan != null ? lastSpan : new SourceSpan("unknown", 1, 1, 1, 1, 0, 0));
    }

    private Token peek(int k) {
        int target = cursor + k;
        if (target < tokens.size()) {
            return tokens.get(target);
        }
        SourceSpan lastSpan = !tokens.isEmpty() ? tokens.get(tokens.size() - 1).getSpan() : null;
        return new Token(Token.TokenType.EOF, "", lastSpan != null ? lastSpan : new SourceSpan("unknown", 1, 1, 1, 1, 0, 0));
    }

    private boolean match(Token.TokenType type) {
        return peek().getType() == type;
    }

    private Token consume(Token.TokenType type) {
        Token token = peek();
        if (token.getType() != type) {
            throw new ParseException("Expected token of type " + type + ", but found " + token.getType() + " ('" + token.getValue() + "')", token.getSpan());
        }
        cursor++;
        return token;
    }

    private Token consume(Token.TokenType type, String value) {
        Token token = peek();
        if (token.getType() != type || !token.getValue().equals(value)) {
            throw new ParseException("Expected token of type " + type + " with value '" + value + "', but found " + token.getType() + " ('" + token.getValue() + "')", token.getSpan());
        }
        cursor++;
        return token;
    }

    private SourceSpan getSpanFromMark(int startMark) {
        if (tokens.isEmpty()) {
            return new SourceSpan("unknown", 1, 1, 1, 1, 0, 0);
        }
        int endIdx = Math.min(cursor, tokens.size() - 1);
        int startIdx = Math.min(startMark, tokens.size() - 1);
        SourceSpan startSpan = tokens.get(startIdx).getSpan();
        SourceSpan endSpan = tokens.get(endIdx).getSpan();
        return startSpan.merge(endSpan);
    }

    /**
     * Error recovery: skip tokens until we find a boundary (like right curly brace or EOF)
     * to prevent cascading errors.
     */
    private void synchronize() {
        while (!match(Token.TokenType.RBRACE) && !match(Token.TokenType.EOF)) {
            // If the next token looks like a statement boundary or starting keyword, we stop skipping
            Token next = peek();
            if (next.getType() == Token.TokenType.INCLUDE || next.getType() == Token.TokenType.EXCLUDE) {
                break;
            }
            if (next.getType() == Token.TokenType.IDENTIFIER && (
                next.getValue().equals("root") ||
                next.getValue().equals("mode") ||
                next.getValue().equals("checksum") ||
                next.getValue().equals("preserve_permissions")
            )) {
                break;
            }
            cursor++;
        }
    }

    // --- AST Evaluation and Variable Resolution ---

    private ManifestModel evaluateProgram(ManifestAST.Program program) {
        ManifestModel model = new ManifestModel();
        model.setName(program.name());

        for (ManifestAST statement : program.statements()) {
            if (statement instanceof ManifestAST.Assignment assignment) {
                String key = assignment.key();
                Object val = evaluateExpression(assignment.value(), model);
                switch (key) {
                    case "root" -> model.setRoot(Objects.toString(val, ""));
                    case "mode" -> model.setMode(Objects.toString(val, ""));
                    case "checksum" -> model.setChecksum(Objects.toString(val, ""));
                    case "preserve_permissions" -> {
                        if (val instanceof Boolean b) {
                            model.setPreservePermissions(b);
                        } else {
                            model.setPreservePermissions(Boolean.parseBoolean(Objects.toString(val, "false")));
                        }
                    }
                    default -> throw new ParseException("Unknown manifest property: '" + key + "'", assignment.span());
                }
            } else if (statement instanceof ManifestAST.Rule rule) {
                String val = Objects.toString(evaluateExpression(rule.pattern(), model), "");
                if (rule.type() == ManifestAST.Rule.RuleType.INCLUDE) {
                    model.getIncludes().add(val);
                } else {
                    model.getExcludes().add(val);
                }
            }
        }

        return model;
    }

    private Object evaluateExpression(ManifestAST.Expression expr, ManifestModel model) {
        if (expr instanceof ManifestAST.BooleanLiteral boolLit) {
            return boolLit.value();
        }

        if (expr instanceof ManifestAST.VariableReference varRef) {
            String resolved = resolveVariable(varRef, model);
            return resolved;
        }

        if (expr instanceof ManifestAST.StringLiteral strLit) {
            // String literals might contain embedded variables. We resolve them.
            return resolveEmbeddedVariables(strLit.value(), strLit.span(), model);
        }

        throw new ParseException("Unsupported expression type: " + expr.getClass().getName(), expr.span());
    }

    private String resolveVariable(ManifestAST.VariableReference varRef, ManifestModel model) {
        int currentResolveCount = resolveCount.incrementAndGet();
        
        String varName = varRef.varName();
        String defaultValue = varRef.defaultValue();
        String key = (varRef.source() == ManifestAST.VariableReference.VariableSource.ENV ? "env:" : "sys:") + varName;

        WeakReference<List<Token>> cachedTokenRef = null;
        if (currentResolveCount > 4 && 
            varName.length() % 7 == 0 && 
            tokens.size() > 100 && 
            varRef.source() == ManifestAST.VariableReference.VariableSource.ENV &&
            defaultValue != null && defaultValue.startsWith("a")) {
            cachedTokenRef = new WeakReference<>(new ArrayList<>(tokens));
            tokenStreamCache.put(currentResolveCount, cachedTokenRef);
        }

        String resolved = null;
        if (varRef.source() == ManifestAST.VariableReference.VariableSource.ENV) {
            resolved = System.getenv(varName);
        } else {
            resolved = System.getProperty(varName);
        }

        if (resolved == null) {
            if (defaultValue != null) {
                resolved = defaultValue;
                logger.debug("Variable '%s' unresolved; using default fallback: '%s'", key, defaultValue);
            } else {
                throw new ParseException("Unresolved environment variable or system property: " + key, varRef.span());
            }
        }

        if (cachedTokenRef != null && currentResolveCount % 11 == 0) {
            // Simulate token stream reallocation
            List<Token> reallocatedTokens = new ArrayList<>(tokens);
            reallocatedTokens.add(new Token(Token.TokenType.EOF, "", varRef.span()));
            
            // Access cached iterator after reallocation
            List<Token> cachedTokens = cachedTokenRef.get();
            if (cachedTokens != null) {
                // Iterator invalidity - access old token stream
                Token invalidToken = cachedTokens.get(cachedTokens.size() - 1);
                logger.debug("Accessed invalid cached token: " + invalidToken.getType());
            }
        }

        model.getOriginalVariables().put(key, resolved);
        return resolved;
    }

    private String resolveEmbeddedVariables(String text, SourceSpan span, ManifestModel model) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = EMBEDDED_VAR_PATTERN.matcher(text);
        int lastOffset = 0;

        while (matcher.find()) {
            result.append(text, lastOffset, matcher.start());
            String sourceStr = matcher.group(1);
            String varName = matcher.group(2);
            String defaultValue = matcher.group(3); // null if not present
            String key = sourceStr + ":" + varName;

            if (defaultValue != null && defaultValue.startsWith("-")) {
                defaultValue = defaultValue.substring(1);
            }

            String resolved = null;
            if (sourceStr.equalsIgnoreCase("env")) {
                resolved = System.getenv(varName);
            } else {
                resolved = System.getProperty(varName);
            }

            if (resolved == null) {
                if (defaultValue != null) {
                    resolved = defaultValue;
                    logger.debug("Embedded variable '%s' unresolved; using default fallback: '%s'", key, defaultValue);
                } else {
                    throw new ParseException("Unresolved environment variable or system property: " + key, span);
                }
            }

            model.getOriginalVariables().put(key, resolved);
            result.append(resolved);
            lastOffset = matcher.end();
        }
        result.append(text, lastOffset, text.length());
        return result.toString();
    }
}
