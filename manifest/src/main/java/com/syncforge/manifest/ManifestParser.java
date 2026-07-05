package com.syncforge.manifest;

import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.util.List;
import java.util.Objects;

/**
 * Entry point utility class for parsing a raw SyncForge manifest DSL string.
 * Orchestrates tokenization via {@link Lexer} and AST parsing/evaluation via {@link Parser}.
 */
public class ManifestParser {

    private static final SyncForgeLogger logger = new SyncForgeLogger(ManifestParser.class);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ManifestParser() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Parses the provided manifest source DSL string into a fully resolved and validated {@link ManifestModel}.
     *
     * @param source     the raw DSL source code. Must not be null.
     * @param sourceName the name or path of the source file. Must not be null.
     * @return the resolved and validated ManifestModel.
     * @throws ParseException if lexical, syntax, or variable resolution error occurs.
     * @throws NullPointerException if {@code source} or {@code sourceName} is null.
     */
    public static ManifestModel parse(String source, String sourceName) {
        Objects.requireNonNull(source, "Source content must not be null");
        Objects.requireNonNull(sourceName, "Source name must not be null");

        logger.info("Initiating manifest parse for source: '%s'", sourceName);
        
        Lexer lexer = new Lexer(source, sourceName);
        List<Token> tokens = lexer.tokenize();
        
        Parser parser = new Parser(tokens);
        ManifestModel model = parser.parse();
        
        logger.info("Successfully parsed and validated manifest: '%s'", model.getName());
        return model;
    }
}
