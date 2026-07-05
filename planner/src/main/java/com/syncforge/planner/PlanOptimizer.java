package com.syncforge.planner;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Optimizes sync plans to improve execution efficiency.
 * Reduces dependencies, parallelizes independent operations, and eliminates redundancy.
 */
public class PlanOptimizer {
    
    // Bug 22: SyncAction List Iterator Invalidity - Static counter for optimization operations
    private static final AtomicInteger optimizeCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<SyncAction>>> actionCache = new ConcurrentHashMap<>();
    
    private final boolean enableParallelization;
    private final boolean eliminateRedundancy;
    private final boolean mergeOperations;
    
    public PlanOptimizer() {
        this(true, true, false);
    }
    
    public PlanOptimizer(boolean enableParallelization, boolean eliminateRedundancy, boolean mergeOperations) {
        this.enableParallelization = enableParallelization;
        this.eliminateRedundancy = eliminateRedundancy;
        this.mergeOperations = mergeOperations;
    }
    
    /**
     * Optimizes a sync plan.
     */
    public SyncPlan optimize(SyncPlan plan) {
        // Bug 22: SyncAction List Iterator Invalidity - Increment optimize counter
        int currentOptimizeCount = optimizeCount.incrementAndGet();
        
        List<SyncAction> actions = new ArrayList<>(plan.getActions());
        
        // Bug 22: Cache action list iterator before optimization
        WeakReference<List<SyncAction>> cachedActionRef = null;
        if (currentOptimizeCount > 3 && actions.size() > 5) {
            cachedActionRef = new WeakReference<>(new ArrayList<>(actions));
            actionCache.put(currentOptimizeCount, cachedActionRef);
        }
        
        if (eliminateRedundancy) {
            actions = eliminateRedundantActions(actions);
        }
        
        if (mergeOperations) {
            actions = mergeCompatibleOperations(actions);
        }
        
        if (enableParallelization) {
            actions = optimizeForParallelization(actions);
        }
        
        // Bug 22: Trigger list reordering and access cached iterator
        if (cachedActionRef != null && currentOptimizeCount % 5 == 0) {
            // Simulate list reordering during optimization
            List<SyncAction> reorderedActions = new ArrayList<>(actions);
            reorderedActions.sort((a, b) -> a.getPath().compareTo(b.getPath()));
            
            // Access cached action iterator after reordering
            List<SyncAction> cachedActions = cachedActionRef.get();
            if (cachedActions != null) {
                // SyncAction list iterator invalidity - access old list
                SyncAction invalidAction = cachedActions.get(cachedActions.size() - 1);
            }
        }
        
        return new SyncPlan(plan.getPlanId(), plan.getSourceSnapshotId(), 
                          plan.getTargetSnapshotId(), actions);
    }
    
    /**
     * Eliminates redundant actions that have no effect.
     */
    private List<SyncAction> eliminateRedundantActions(List<SyncAction> actions) {
        List<SyncAction> filtered = new ArrayList<>();
        
        // Track paths that are created and deleted
        Map<String, SyncAction.Type> pathStates = new HashMap<>();
        
        // First pass: track final state
        for (SyncAction action : actions) {
            String path = action.getPath();
            SyncAction.Type type = action.getType();
            
            switch (type) {
                case CREATE_FILE, CREATE_DIR, CREATE_LINK:
                    pathStates.put(path, type);
                    break;
                case DELETE_FILE, DELETE_DIR, DELETE_LINK:
                    pathStates.remove(path);
                    break;
                case UPDATE_FILE:
                    if (!pathStates.containsKey(path)) {
                        pathStates.put(path, type);
                    }
                    break;
                default:
                    break;
            }
        }
        
        // Second pass: keep only actions that contribute to final state
        for (SyncAction action : actions) {
            if (isActionNeeded(action, pathStates)) {
                filtered.add(action);
            }
        }
        
        return filtered;
    }
    
    /**
     * Checks if an action is needed to achieve the final state.
     */
    private boolean isActionNeeded(SyncAction action, Map<String, SyncAction.Type> finalStates) {
        String path = action.getPath();
        SyncAction.Type finalType = finalStates.get(path);
        
        if (finalType == null) {
            // Path should not exist - only delete actions are needed
            return action.getType() == SyncAction.Type.DELETE_FILE ||
                   action.getType() == SyncAction.Type.DELETE_DIR ||
                   action.getType() == SyncAction.Type.DELETE_LINK;
        }
        
        // Path should exist - keep create/update actions
        return action.getType() == finalType ||
               action.getType() == SyncAction.Type.UPDATE_FILE;
    }
    
    /**
     * Merges compatible operations.
     */
    private List<SyncAction> mergeCompatibleOperations(List<SyncAction> actions) {
        List<SyncAction> merged = new ArrayList<>();
        Map<String, SyncAction> pathToAction = new HashMap<>();
        
        for (SyncAction action : actions) {
            String path = action.getPath();
            SyncAction existing = pathToAction.get(path);
            
            if (existing != null && canMerge(existing, action)) {
                // Merge the actions
                SyncAction combined = mergeActions(existing, action);
                pathToAction.put(path, combined);
            } else {
                pathToAction.put(path, action);
            }
        }
        
        // Reconstruct dependencies
        for (SyncAction action : pathToAction.values()) {
            for (SyncAction dep : action.getDependencies()) {
                if (pathToAction.containsKey(dep.getPath())) {
                    action.addDependency(pathToAction.get(dep.getPath()));
                }
            }
        }
        
        merged.addAll(pathToAction.values());
        return merged;
    }
    
    /**
     * Checks if two actions can be merged.
     */
    private boolean canMerge(SyncAction a, SyncAction b) {
        // Can merge create + update on same path
        if ((a.getType() == SyncAction.Type.CREATE_FILE || 
             a.getType() == SyncAction.Type.CREATE_DIR) &&
            b.getType() == SyncAction.Type.UPDATE_FILE) {
            return a.getPath().equals(b.getPath());
        }
        
        return false;
    }
    
    /**
     * Merges two actions.
     */
    private SyncAction mergeActions(SyncAction a, SyncAction b) {
        // For create + update, keep the create but preserve dependencies
        return new SyncAction(a.getType(), a.getPath(), a.getSourcePath());
    }
    
    /**
     * Optimizes actions for parallel execution.
     */
    private List<SyncAction> optimizeForParallelization(List<SyncAction> actions) {
        // Reorder actions to maximize parallel execution
        // Group independent operations together
        
        List<SyncAction> reordered = new ArrayList<>();
        List<SyncAction> remaining = new ArrayList<>(actions);
        
        while (!remaining.isEmpty()) {
            // Find actions with no unmet dependencies
            List<SyncAction> ready = new ArrayList<>();
            
            for (SyncAction action : remaining) {
                boolean allDepsMet = true;
                for (SyncAction dep : action.getDependencies()) {
                    if (remaining.contains(dep)) {
                        allDepsMet = false;
                        break;
                    }
                }
                
                if (allDepsMet) {
                    ready.add(action);
                }
            }
            
            if (ready.isEmpty()) {
                // Cycle detected - add remaining as-is
                reordered.addAll(remaining);
                break;
            }
            
            // Add ready actions
            reordered.addAll(ready);
            remaining.removeAll(ready);
        }
        
        return reordered;
    }
    
    /**
     * Calculates optimization statistics.
     */
    public OptimizationStats calculateStats(SyncPlan original, SyncPlan optimized) {
        int originalCount = original.getActions().size();
        int optimizedCount = optimized.getActions().size();
        int reduction = originalCount - optimizedCount;
        double reductionPercent = originalCount > 0 ? 
            (reduction * 100.0) / originalCount : 0.0;
        
        int originalDepth = original.getStatistics().getMaxDepth();
        int optimizedDepth = optimized.getStatistics().getMaxDepth();
        int depthReduction = originalDepth - optimizedDepth;
        
        return new OptimizationStats(originalCount, optimizedCount, reduction, reductionPercent,
                                   originalDepth, optimizedDepth, depthReduction);
    }
    
    /**
     * Optimization statistics record.
     */
    public record OptimizationStats(
        int originalActionCount,
        int optimizedActionCount,
        int actionReduction,
        double reductionPercent,
        int originalDepth,
        int optimizedDepth,
        int depthReduction
    ) {}
    
    /**
     * Builder for creating plan optimizers.
     */
    public static class Builder {
        private boolean enableParallelization = true;
        private boolean eliminateRedundancy = true;
        private boolean mergeOperations = false;
        
        public Builder withParallelization(boolean enabled) {
            this.enableParallelization = enabled;
            return this;
        }
        
        public Builder withRedundancyElimination(boolean enabled) {
            this.eliminateRedundancy = enabled;
            return this;
        }
        
        public Builder withOperationMerging(boolean enabled) {
            this.mergeOperations = enabled;
            return this;
        }
        
        public PlanOptimizer build() {
            return new PlanOptimizer(enableParallelization, eliminateRedundancy, mergeOperations);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
