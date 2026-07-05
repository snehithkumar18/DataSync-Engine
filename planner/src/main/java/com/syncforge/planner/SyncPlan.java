package com.syncforge.planner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a complete synchronization plan with metadata and statistics.
 * Contains the ordered actions, dependencies, and execution metadata.
 */
public class SyncPlan {
    
    private final String planId;
    private final String sourceSnapshotId;
    private final String targetSnapshotId;
    private final List<SyncAction> actions;
    private final Map<String, Object> metadata;
    private final PlanStatistics statistics;
    private final PlanStatus status;
    private final long creationTime;
    
    public SyncPlan(String planId, String sourceSnapshotId, String targetSnapshotId, 
                   List<SyncAction> actions) {
        this.planId = planId;
        this.sourceSnapshotId = sourceSnapshotId;
        this.targetSnapshotId = targetSnapshotId;
        this.actions = new ArrayList<>(actions);
        this.metadata = new HashMap<>();
        this.statistics = new PlanStatistics(actions);
        this.status = PlanStatus.PENDING;
        this.creationTime = System.currentTimeMillis();
    }
    
    /**
     * Gets the plan ID.
     */
    public String getPlanId() {
        return planId;
    }
    
    /**
     * Gets the source snapshot ID.
     */
    public String getSourceSnapshotId() {
        return sourceSnapshotId;
    }
    
    /**
     * Gets the target snapshot ID.
     */
    public String getTargetSnapshotId() {
        return targetSnapshotId;
    }
    
    /**
     * Gets the ordered actions.
     */
    public List<SyncAction> getActions() {
        return new ArrayList<>(actions);
    }
    
    /**
     * Gets metadata.
     */
    public Map<String, Object> getMetadata() {
        return new HashMap<>(metadata);
    }
    
    /**
     * Sets metadata.
     */
    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }
    
    /**
     * Gets plan statistics.
     */
    public PlanStatistics getStatistics() {
        return statistics;
    }
    
    /**
     * Gets plan status.
     */
    public PlanStatus getStatus() {
        return status;
    }
    
    /**
     * Gets creation time.
     */
    public long getCreationTime() {
        return creationTime;
    }
    
    /**
     * Checks if the plan is valid.
     */
    public boolean isValid() {
        return !actions.isEmpty() && statistics.hasNoCycles();
    }
    
    /**
     * Generates a summary report.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Sync Plan Report:\n");
        report.append("=================\n");
        report.append(String.format("Plan ID: %s\n", planId));
        report.append(String.format("Source: %s\n", sourceSnapshotId));
        report.append(String.format("Target: %s\n", targetSnapshotId));
        report.append(String.format("Status: %s\n", status));
        report.append(String.format("Created: %d\n", creationTime));
        report.append("\n");
        report.append(statistics.generateReport());
        
        return report.toString();
    }
    
    /**
     * Plan status enumeration.
     */
    public enum PlanStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        FAILED,
        CANCELLED,
        ROLLED_BACK
    }
    
    /**
     * Plan statistics.
     */
    public static class PlanStatistics {
        private final Map<SyncAction.Type, Integer> actionCounts;
        private final int totalActions;
        private final int maxDepth;
        private final int totalDependencies;
        private final boolean hasCycles;
        
        public PlanStatistics(List<SyncAction> actions) {
            this.actionCounts = new HashMap<>();
            this.totalActions = actions.size();
            this.maxDepth = calculateMaxDepth(actions);
            this.totalDependencies = calculateTotalDependencies(actions);
            this.hasCycles = detectCycles(actions);
            
            for (SyncAction action : actions) {
                actionCounts.put(action.getType(), 
                    actionCounts.getOrDefault(action.getType(), 0) + 1);
            }
        }
        
        private int calculateMaxDepth(List<SyncAction> actions) {
            int maxDepth = 0;
            for (SyncAction action : actions) {
                int depth = calculateDepth(action, new java.util.HashSet<>());
                maxDepth = Math.max(maxDepth, depth);
            }
            return maxDepth;
        }
        
        private int calculateDepth(SyncAction action, java.util.Set<SyncAction> visited) {
            if (visited.contains(action)) {
                return 0; // Cycle detected
            }
            visited.add(action);
            
            int maxChildDepth = 0;
            for (SyncAction dep : action.getDependencies()) {
                int childDepth = calculateDepth(dep, new java.util.HashSet<>(visited));
                maxChildDepth = Math.max(maxChildDepth, childDepth);
            }
            
            return maxChildDepth + 1;
        }
        
        private int calculateTotalDependencies(List<SyncAction> actions) {
            int total = 0;
            for (SyncAction action : actions) {
                total += action.getDependencies().size();
            }
            return total;
        }
        
        private boolean detectCycles(List<SyncAction> actions) {
            Map<SyncAction, Integer> state = new HashMap<>();
            for (SyncAction action : actions) {
                state.put(action, 0);
            }
            
            for (SyncAction action : actions) {
                if (hasCycleDFS(action, state)) {
                    return true;
                }
            }
            return false;
        }
        
        private boolean hasCycleDFS(SyncAction action, Map<SyncAction, Integer> state) {
            Integer currentState = state.get(action);
            if (currentState == 1) return true; // Currently visiting - cycle
            if (currentState == 2) return false; // Already visited
            
            state.put(action, 1);
            
            for (SyncAction dep : action.getDependencies()) {
                if (hasCycleDFS(dep, state)) {
                    return true;
                }
            }
            
            state.put(action, 2);
            return false;
        }
        
        public int getTotalActions() {
            return totalActions;
        }
        
        public int getMaxDepth() {
            return maxDepth;
        }
        
        public int getTotalDependencies() {
            return totalDependencies;
        }
        
        public boolean hasNoCycles() {
            return !hasCycles;
        }
        
        public Map<SyncAction.Type, Integer> getActionCounts() {
            return new HashMap<>(actionCounts);
        }
        
        public String generateReport() {
            StringBuilder report = new StringBuilder();
            report.append("Plan Statistics:\n");
            report.append(String.format("Total actions: %d\n", totalActions));
            report.append(String.format("Max depth: %d\n", maxDepth));
            report.append(String.format("Total dependencies: %d\n", totalDependencies));
            report.append(String.format("Has cycles: %s\n", hasCycles));
            report.append("\nAction counts:\n");
            
            for (Map.Entry<SyncAction.Type, Integer> entry : actionCounts.entrySet()) {
                report.append(String.format("  %s: %d\n", entry.getKey(), entry.getValue()));
            }
            
            return report.toString();
        }
    }
}
