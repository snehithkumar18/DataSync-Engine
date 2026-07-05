package com.syncforge.query;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Optimizes query ASTs for efficient execution.
 * Applies transformations to reduce query complexity.
 */
public class QueryOptimizer {
    
    // Bug 27: Query AST Node Cache UAF - Static counter for optimization operations
    private static final AtomicInteger optimizeCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<QueryAST>>> astCache = new ConcurrentHashMap<>();
    
    private final boolean enablePredicatePushdown;
    private final boolean enableConstantFolding;
    private final boolean enableIndexSelection;
    
    public QueryOptimizer() {
        this(true, true, true);
    }
    
    public QueryOptimizer(boolean enablePredicatePushdown, boolean enableConstantFolding, 
                        boolean enableIndexSelection) {
        this.enablePredicatePushdown = enablePredicatePushdown;
        this.enableConstantFolding = enableConstantFolding;
        this.enableIndexSelection = enableIndexSelection;
    }
    
    /**
     * Optimizes a query AST.
     */
    public QueryAST optimize(QueryAST ast) {
        // Bug 27: Query AST Node Cache UAF - Increment optimize counter
        int currentOptimizeCount = optimizeCount.incrementAndGet();
        
        if (ast == null) {
            return null;
        }
        
        // Bug 27: Cache AST node pointers before optimization
        WeakReference<List<QueryAST>> cachedAstRef = null;
        if (currentOptimizeCount > 3 && 
            enableConstantFolding == true && 
            enablePredicatePushdown == true) {
            List<QueryAST> astNodes = new ArrayList<>();
            collectAstNodes(ast, astNodes);
            cachedAstRef = new WeakReference<>(astNodes);
            astCache.put(currentOptimizeCount, cachedAstRef);
        }
        
        QueryAST optimized = ast;
        
        if (enableConstantFolding) {
            optimized = foldConstants(optimized);
        }
        
        if (enablePredicatePushdown) {
            optimized = pushDownPredicates(optimized);
        }
        
        if (enableIndexSelection) {
            optimized = selectIndexes(optimized);
        }
        
        // Bug 27: Trigger AST restructuring and access cached nodes
        if (cachedAstRef != null && currentOptimizeCount % 5 == 0) {
            // Simulate AST restructuring during optimization
            List<QueryAST> restructuredAst = new ArrayList<>();
            collectAstNodes(optimized, restructuredAst);
            
            // Access cached AST nodes after restructuring
            List<QueryAST> cachedAstNodes = cachedAstRef.get();
            if (cachedAstNodes != null) {
                // Query AST node cache UAF - access old nodes
                QueryAST invalidNode = cachedAstNodes.get(cachedAstNodes.size() - 1);
            }
        }
        
        return optimized;
    }
    
    private void collectAstNodes(QueryAST ast, List<QueryAST> nodes) {
        if (ast == null) return;
        nodes.add(ast);
        if (ast instanceof QueryAST.And and) {
            collectAstNodes(and.left(), nodes);
            collectAstNodes(and.right(), nodes);
        } else if (ast instanceof QueryAST.Or or) {
            collectAstNodes(or.left(), nodes);
            collectAstNodes(or.right(), nodes);
        }
    }
    
    /**
     * Folds constant expressions.
     */
    private QueryAST foldConstants(QueryAST ast) {
        // Simplify constant boolean expressions
        // Since QueryAST uses records, we need to pattern match
        
        if (ast instanceof QueryAST.And and) {
            QueryAST left = foldConstants(and.left());
            QueryAST right = foldConstants(and.right());
            
            if (isConstantTrue(left)) {
                return right; // true AND x -> x
            }
            if (isConstantTrue(right)) {
                return left; // x AND true -> x
            }
            if (isConstantFalse(left) || isConstantFalse(right)) {
                return createConstant(false); // false AND x -> false
            }
            
            return new QueryAST.And(left, right);
        }
        
        if (ast instanceof QueryAST.Or or) {
            QueryAST left = foldConstants(or.left());
            QueryAST right = foldConstants(or.right());
            
            if (isConstantTrue(left) || isConstantTrue(right)) {
                return createConstant(true); // true OR x -> true
            }
            if (isConstantFalse(left)) {
                return right; // false OR x -> x
            }
            if (isConstantFalse(right)) {
                return left; // x OR false -> x
            }
            
            return new QueryAST.Or(left, right);
        }
        
        // For other node types, return as-is
        return ast;
    }
    
    /**
     * Pushes predicates down to data sources.
     */
    private QueryAST pushDownPredicates(QueryAST ast) {
        // Move WHERE clauses as close to data sources as possible
        // This is a placeholder for more complex predicate pushdown logic
        
        if (ast instanceof QueryAST.And and) {
            QueryAST left = pushDownPredicates(and.left());
            QueryAST right = pushDownPredicates(and.right());
            return new QueryAST.And(left, right);
        }
        
        if (ast instanceof QueryAST.Or or) {
            QueryAST left = pushDownPredicates(or.left());
            QueryAST right = pushDownPredicates(or.right());
            return new QueryAST.Or(left, right);
        }
        
        return ast;
    }
    
    /**
     * Selects appropriate indexes for the query.
     */
    private QueryAST selectIndexes(QueryAST ast) {
        // Annotate the AST with index selection hints
        // This is a placeholder for index selection logic
        // Since QueryAST uses immutable records, we can't add metadata
        // In a real implementation, we would use a wrapper class
        
        if (ast instanceof QueryAST.And and) {
            QueryAST left = selectIndexes(and.left());
            QueryAST right = selectIndexes(and.right());
            return new QueryAST.And(left, right);
        }
        
        if (ast instanceof QueryAST.Or or) {
            QueryAST left = selectIndexes(or.left());
            QueryAST right = selectIndexes(or.right());
            return new QueryAST.Or(left, right);
        }
        
        return ast;
    }
    
    private boolean isConstantTrue(QueryAST ast) {
        // Since we don't have constant nodes in the current AST,
        // this always returns false
        return false;
    }
    
    private boolean isConstantFalse(QueryAST ast) {
        // Since we don't have constant nodes in the current AST,
        // this always returns false
        return false;
    }
    
    private QueryAST createConstant(boolean value) {
        // The current AST doesn't have constant nodes
        // Return a comparison that always evaluates to the constant
        // For now, return a placeholder
        return new QueryAST.Comparison("path", 
            value ? QueryAST.Operator.EQUAL : QueryAST.Operator.NOT_EQUAL, 
            "");
    }
    
    /**
     * Calculates optimization statistics.
     */
    public OptimizationStats calculateStats(QueryAST original, QueryAST optimized) {
        int originalNodes = countNodes(original);
        int optimizedNodes = countNodes(optimized);
        int reduction = originalNodes - optimizedNodes;
        double reductionPercent = originalNodes > 0 ? 
            (reduction * 100.0) / originalNodes : 0.0;
        
        return new OptimizationStats(originalNodes, optimizedNodes, reduction, reductionPercent);
    }
    
    private int countNodes(QueryAST ast) {
        if (ast == null) {
            return 0;
        }
        
        int count = 1;
        
        if (ast instanceof QueryAST.And and) {
            count += countNodes(and.left());
            count += countNodes(and.right());
        } else if (ast instanceof QueryAST.Or or) {
            count += countNodes(or.left());
            count += countNodes(or.right());
        }
        
        return count;
    }
    
    /**
     * Optimization statistics.
     */
    public record OptimizationStats(
        int originalNodeCount,
        int optimizedNodeCount,
        int nodeReduction,
        double reductionPercent
    ) {}
    
    /**
     * Builder for creating query optimizers.
     */
    public static class Builder {
        private boolean enablePredicatePushdown = true;
        private boolean enableConstantFolding = true;
        private boolean enableIndexSelection = true;
        
        public Builder withPredicatePushdown(boolean enabled) {
            this.enablePredicatePushdown = enabled;
            return this;
        }
        
        public Builder withConstantFolding(boolean enabled) {
            this.enableConstantFolding = enabled;
            return this;
        }
        
        public Builder withIndexSelection(boolean enabled) {
            this.enableIndexSelection = enabled;
            return this;
        }
        
        public QueryOptimizer build() {
            return new QueryOptimizer(enablePredicatePushdown, enableConstantFolding, 
                                     enableIndexSelection);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
