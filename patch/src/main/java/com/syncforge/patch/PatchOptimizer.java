package com.syncforge.patch;

import java.util.ArrayList;
import java.util.List;

/**
 * Optimizes patch operations to reduce redundancy and improve efficiency.
 * Merges adjacent operations, eliminates no-ops, and reorders operations.
 */
public class PatchOptimizer {
    
    private final boolean mergeAdjacentCreates;
    private final boolean eliminateNoOps;
    private final boolean reorderOperations;
    
    public PatchOptimizer() {
        this(true, true, false);
    }
    
    public PatchOptimizer(boolean mergeAdjacentCreates, boolean eliminateNoOps, boolean reorderOperations) {
        this.mergeAdjacentCreates = mergeAdjacentCreates;
        this.eliminateNoOps = eliminateNoOps;
        this.reorderOperations = reorderOperations;
    }
    
    /**
     * Optimizes a list of patch operations.
     */
    public List<PatchOperation> optimize(List<PatchOperation> operations) {
        List<PatchOperation> optimized = new ArrayList<>(operations);
        
        if (eliminateNoOps) {
            optimized = eliminateNoOps(optimized);
        }
        
        if (mergeAdjacentCreates) {
            optimized = mergeAdjacentOperations(optimized);
        }
        
        if (reorderOperations) {
            optimized = reorderForEfficiency(optimized);
        }
        
        return optimized;
    }
    
    /**
     * Eliminates no-op operations that have no effect.
     */
    private List<PatchOperation> eliminateNoOps(List<PatchOperation> operations) {
        List<PatchOperation> filtered = new ArrayList<>();
        
        for (PatchOperation op : operations) {
            if (!isNoOp(op)) {
                filtered.add(op);
            }
        }
        
        return filtered;
    }
    
    /**
     * Checks if an operation is a no-op.
     */
    private boolean isNoOp(PatchOperation op) {
        // Delete operation on non-existent file
        if (op.getType() == PatchOperation.Type.DELETE_FILE || 
            op.getType() == PatchOperation.Type.DELETE_DIRECTORY) {
            // Would need to check against target state - assume not no-op for now
            return false;
        }
        
        // Update operation with same checksum
        if (op.getType() == PatchOperation.Type.UPDATE_FILE) {
            // Would need to check against current state - assume not no-op for now
            return false;
        }
        
        // Permission update with same permissions
        if (op.getType() == PatchOperation.Type.UPDATE_PERMISSIONS) {
            // Would need to check against current state - assume not no-op for now
            return false;
        }
        
        return false;
    }
    
    /**
     * Merges adjacent operations that can be combined.
     */
    private List<PatchOperation> mergeAdjacentOperations(List<PatchOperation> operations) {
        List<PatchOperation> merged = new ArrayList<>();
        
        for (int i = 0; i < operations.size(); i++) {
            PatchOperation current = operations.get(i);
            
            // Check if this can be merged with the next operation
            if (i + 1 < operations.size()) {
                PatchOperation next = operations.get(i + 1);
                
                if (canMerge(current, next)) {
                    PatchOperation combined = mergeOperations(current, next);
                    merged.add(combined);
                    i++; // Skip the next operation
                    continue;
                }
            }
            
            merged.add(current);
        }
        
        return merged;
    }
    
    /**
     * Checks if two operations can be merged.
     */
    private boolean canMerge(PatchOperation a, PatchOperation b) {
        // Can merge permission updates on same file
        if (a.getType() == PatchOperation.Type.UPDATE_PERMISSIONS && 
            b.getType() == PatchOperation.Type.UPDATE_PERMISSIONS &&
            a.getPath().equals(b.getPath())) {
            return true;
        }
        
        // Can merge create file + update permissions on same file
        if (a.getType() == PatchOperation.Type.CREATE_FILE && 
            b.getType() == PatchOperation.Type.UPDATE_PERMISSIONS &&
            a.getPath().equals(b.getPath())) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Merges two operations into one.
     */
    private PatchOperation mergeOperations(PatchOperation a, PatchOperation b) {
        // Merge permission updates
        if (a.getType() == PatchOperation.Type.UPDATE_PERMISSIONS && 
            b.getType() == PatchOperation.Type.UPDATE_PERMISSIONS) {
            // Use the later permission (b)
            return new PatchOperation(b.getType(), b.getPath(), null, 
                                      b.getPermissions(), b.getLastModified(), b.getHash(), 
                                      b.getPayloadOffset(), b.getPayloadLength(), b.getRawData());
        }
        
        // Merge create file + update permissions
        if (a.getType() == PatchOperation.Type.CREATE_FILE && 
            b.getType() == PatchOperation.Type.UPDATE_PERMISSIONS) {
            return new PatchOperation(a.getType(), a.getPath(), null, 
                                      b.getPermissions(), a.getLastModified(), a.getHash(),
                                      a.getPayloadOffset(), a.getPayloadLength(), a.getRawData());
        }
        
        // Default: return a
        return a;
    }
    
    /**
     * Reorders operations for more efficient execution.
     */
    private List<PatchOperation> reorderForEfficiency(List<PatchOperation> operations) {
        List<PatchOperation> reordered = new ArrayList<>();
        
        // Separate operations by type
        List<PatchOperation> creates = new ArrayList<>();
        List<PatchOperation> updates = new ArrayList<>();
        List<PatchOperation> deletes = new ArrayList<>();
        List<PatchOperation> others = new ArrayList<>();
        
        for (PatchOperation op : operations) {
            switch (op.getType()) {
                case CREATE_FILE, CREATE_DIRECTORY -> creates.add(op);
                case UPDATE_FILE, UPDATE_PERMISSIONS, UPDATE_SYMLINK -> updates.add(op);
                case DELETE_FILE, DELETE_DIRECTORY -> deletes.add(op);
                default -> others.add(op);
            }
        }
        
        // Order: creates first, then updates, then deletes
        reordered.addAll(creates);
        reordered.addAll(updates);
        reordered.addAll(others);
        reordered.addAll(deletes);
        
        return reordered;
    }
    
    /**
     * Calculates the optimization statistics.
     */
    public OptimizationStats calculateStats(List<PatchOperation> original, 
                                           List<PatchOperation> optimized) {
        int originalCount = original.size();
        int optimizedCount = optimized.size();
        int reduction = originalCount - optimizedCount;
        double reductionPercent = originalCount > 0 ? 
            (reduction * 100.0) / originalCount : 0.0;
        
        return new OptimizationStats(originalCount, optimizedCount, reduction, reductionPercent);
    }
    
    /**
     * Optimization statistics record.
     */
    public record OptimizationStats(
        int originalCount,
        int optimizedCount,
        int reduction,
        double reductionPercent
    ) {}
    
    /**
     * Builder for creating patch optimizers.
     */
    public static class Builder {
        private boolean mergeAdjacentCreates = true;
        private boolean eliminateNoOps = true;
        private boolean reorderOperations = false;
        
        public Builder withMergeAdjacentCreates(boolean enabled) {
            this.mergeAdjacentCreates = enabled;
            return this;
        }
        
        public Builder withEliminateNoOps(boolean enabled) {
            this.eliminateNoOps = enabled;
            return this;
        }
        
        public Builder withReorderOperations(boolean enabled) {
            this.reorderOperations = enabled;
            return this;
        }
        
        public PatchOptimizer build() {
            return new PatchOptimizer(mergeAdjacentCreates, eliminateNoOps, reorderOperations);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
