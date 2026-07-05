package com.syncforge.query;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import java.util.Objects;

/**
 * AST Node definitions for the SyncForge Query DSL.
 */
public interface QueryAST {

    /**
     * Evaluates this query node against a given EntryMetadata record.
     *
     * @param entry the metadata record to evaluate.
     * @return true if the record matches the predicate, false otherwise.
     */
    boolean evaluate(EntryMetadata entry);

    /**
     * Operator types for comparisons.
     */
    enum Operator {
        EQUAL,
        NOT_EQUAL,
        GREATER_THAN,
        LESS_THAN,
        GREATER_EQUAL,
        LESS_EQUAL,
        CONTAINS
    }

    /**
     * Logical AND operator node.
     */
    record And(QueryAST left, QueryAST right) implements QueryAST {
        public And {
            Objects.requireNonNull(left);
            Objects.requireNonNull(right);
        }

        @Override
        public boolean evaluate(EntryMetadata entry) {
            return left.evaluate(entry) && right.evaluate(entry);
        }
    }

    /**
     * Logical OR operator node.
     */
    record Or(QueryAST left, QueryAST right) implements QueryAST {
        public Or {
            Objects.requireNonNull(left);
            Objects.requireNonNull(right);
        }

        @Override
        public boolean evaluate(EntryMetadata entry) {
            return left.evaluate(entry) || right.evaluate(entry);
        }
    }

    /**
     * Field comparison predicate node.
     */
    record Comparison(String field, Operator operator, String value) implements QueryAST {
        public Comparison {
            Objects.requireNonNull(field);
            Objects.requireNonNull(operator);
            Objects.requireNonNull(value);
        }

        @Override
        public boolean evaluate(EntryMetadata entry) {
            String fieldLower = field.toLowerCase();
            switch (fieldLower) {
                case "path" -> {
                    String pathVal = entry.getNormalizedPath();
                    return evaluateString(pathVal, operator, value);
                }
                case "size" -> {
                    long sizeVal = entry.getSize();
                    long targetVal = Long.parseLong(value);
                    return evaluateNumeric(sizeVal, operator, targetVal);
                }
                case "mtime" -> {
                    long mtimeVal = entry.getTimestamp().mtimeMillis();
                    long targetVal = Long.parseLong(value);
                    return evaluateNumeric(mtimeVal, operator, targetVal);
                }
                case "mode" -> {
                    String modeStr = entry.getMode().toString();
                    return evaluateString(modeStr, operator, value);
                }
                case "hash" -> {
                    if (entry instanceof FileEntry fe) {
                        String hashVal = fe.getContentHash().value();
                        return evaluateString(hashVal, operator, value);
                    }
                    return false;
                }
                default -> {
                    return false;
                }
            }
        }

        private boolean evaluateString(String actual, Operator op, String target) {
            if (actual == null) return false;
            return switch (op) {
                case EQUAL -> actual.equalsIgnoreCase(target);
                case NOT_EQUAL -> !actual.equalsIgnoreCase(target);
                case CONTAINS -> actual.toLowerCase().contains(target.toLowerCase());
                default -> false;
            };
        }

        private boolean evaluateNumeric(long actual, Operator op, long target) {
            return switch (op) {
                case EQUAL -> actual == target;
                case NOT_EQUAL -> actual != target;
                case GREATER_THAN -> actual > target;
                case LESS_THAN -> actual < target;
                case GREATER_EQUAL -> actual >= target;
                case LESS_EQUAL -> actual <= target;
                default -> false;
            };
        }
    }
}
