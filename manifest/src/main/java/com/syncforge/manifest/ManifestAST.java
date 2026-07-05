package com.syncforge.manifest;

import com.syncforge.core.diagnostics.SourceSpan;
import java.util.List;

/**
 * Abstract syntax tree (AST) nodes representing components of the manifest DSL.
 */
public sealed interface ManifestAST {
    
    /**
     * Returns the source span corresponding to this AST node.
     *
     * @return the source span.
     */
    SourceSpan span();

    /**
     * Represents the top-level manifest options block.
     * Example: manifest "my_project" { ... }
     */
    record Program(
            SourceSpan span,
            String name,
            List<ManifestAST> statements
    ) implements ManifestAST {}

    /**
     * Represents key-value assignment statements inside the manifest block.
     * Example: root = "/path/to/sync"
     */
    record Assignment(
            SourceSpan span,
            String key,
            Expression value
    ) implements ManifestAST {}

    /**
     * Represents rules such as include or exclude rules.
     * Example: include glob patterns like "*.java"
     */
    record Rule(
            SourceSpan span,
            RuleType type,
            Expression pattern
    ) implements ManifestAST {
        /**
         * Types of rules supported.
         */
        public enum RuleType {
            /**
             * Include rule.
             */
            INCLUDE,
            /**
             * Exclude rule.
             */
            EXCLUDE
        }
    }

    /**
     * Common interface for all expression nodes that evaluate to values (String, Boolean, etc.).
     */
    sealed interface Expression extends ManifestAST permits StringLiteral, BooleanLiteral, VariableReference {}

    /**
     * A literal string value.
     */
    record StringLiteral(
            SourceSpan span,
            String value
    ) implements Expression {}

    /**
     * A literal boolean value.
     */
    record BooleanLiteral(
            SourceSpan span,
            boolean value
    ) implements Expression {}

    /**
     * A variable reference that will be dynamically resolved.
     * Example: ${env:VAR} or ${sys:VAR}
     */
    record VariableReference(
            SourceSpan span,
            VariableSource source,
            String varName,
            String defaultValue
    ) implements Expression {
        /**
         * Source from where the variable's value is retrieved.
         */
        public enum VariableSource {
            /**
             * Environment variable.
             */
            ENV,
            /**
             * System property.
             */
            SYS
        }
    }
}
