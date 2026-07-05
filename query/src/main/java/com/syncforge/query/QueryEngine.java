package com.syncforge.query;

import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.indexing.BPlusTree;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.snapshot.SnapshotModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QueryEngine executes custom metadata queries against a {@link SnapshotModel}.
 * It leverages the B+ Tree index for optimization (predicate pushdown) when querying
 * on indexed range keys like 'mtime'.
 */
public class QueryEngine {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(QueryEngine.class);

    private final BPlusTree<Long, EntryMetadata> mtimeIndex = new BPlusTree<>(4);
    private final List<EntryMetadata> entries;

    /**
     * Constructs a QueryEngine targeting the provided snapshot.
     * Builds B+ Tree indexes eagerly.
     *
     * @param snapshot the snapshot model to index and query.
     */
    public QueryEngine(SnapshotModel snapshot) {
        Objects.requireNonNull(snapshot, "Snapshot must not be null");
        this.entries = snapshot.getEntries();
        buildIndexes();
    }

    private void buildIndexes() {
        LOGGER.info("Building B+ Tree index on mtime for %d entries", entries.size());
        for (EntryMetadata entry : entries) {
            mtimeIndex.insert(entry.getTimestamp().mtimeMillis(), entry);
        }
    }

    /**
     * Executes the given query string and returns matching metadata entries.
     *
     * @param queryDsl the query string.
     * @return the list of matching entries.
     */
    public List<EntryMetadata> execute(String queryDsl) {
        LOGGER.info("Executing query: '%s'", queryDsl);
        QueryParser parser = new QueryParser(queryDsl);
        QueryAST ast = parser.parse();

        // 1. Check for optimization opportunity (Predicate Pushdown on mtime)
        Long lowerMtime = findLowerMtimeBound(ast);
        Long upperMtime = findUpperMtimeBound(ast);

        List<EntryMetadata> candidates;
        if (lowerMtime != null || upperMtime != null) {
            LOGGER.debug("Optimizer: pushing down mtime range filter [%s, %s] using B+ Tree index",
                lowerMtime != null ? lowerMtime : "-inf",
                upperMtime != null ? upperMtime : "+inf");
            candidates = mtimeIndex.searchRange(lowerMtime, upperMtime);
        } else {
            LOGGER.debug("Optimizer: falling back to full table scan.");
            candidates = entries;
        }

        // 2. Evaluate AST against candidates
        List<EntryMetadata> results = new ArrayList<>();
        for (EntryMetadata candidate : candidates) {
            if (ast.evaluate(candidate)) {
                results.add(candidate);
            }
        }

        LOGGER.info("Query matched %d of %d candidates (Total entries: %d)",
            results.size(), candidates.size(), entries.size());
        return results;
    }

    private Long findLowerMtimeBound(QueryAST ast) {
        if (ast instanceof QueryAST.Comparison comp) {
            if (comp.field().equalsIgnoreCase("mtime")) {
                if (comp.operator() == QueryAST.Operator.GREATER_THAN) {
                    return Long.parseLong(comp.value()) + 1;
                } else if (comp.operator() == QueryAST.Operator.GREATER_EQUAL) {
                    return Long.parseLong(comp.value());
                } else if (comp.operator() == QueryAST.Operator.EQUAL) {
                    return Long.parseLong(comp.value());
                }
            }
        } else if (ast instanceof QueryAST.And and) {
            Long l = findLowerMtimeBound(and.left());
            Long r = findLowerMtimeBound(and.right());
            if (l != null && r != null) return Math.max(l, r);
            if (l != null) return l;
            return r;
        }
        return null;
    }

    private Long findUpperMtimeBound(QueryAST ast) {
        if (ast instanceof QueryAST.Comparison comp) {
            if (comp.field().equalsIgnoreCase("mtime")) {
                if (comp.operator() == QueryAST.Operator.LESS_THAN) {
                    return Long.parseLong(comp.value()) - 1;
                } else if (comp.operator() == QueryAST.Operator.LESS_EQUAL) {
                    return Long.parseLong(comp.value());
                } else if (comp.operator() == QueryAST.Operator.EQUAL) {
                    return Long.parseLong(comp.value());
                }
            }
        } else if (ast instanceof QueryAST.And and) {
            Long l = findUpperMtimeBound(and.left());
            Long r = findUpperMtimeBound(and.right());
            if (l != null && r != null) return Math.min(l, r);
            if (l != null) return l;
            return r;
        }
        return null;
    }
}
