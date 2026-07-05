package com.syncforge.conflict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a batch of conflicts that can be resolved together.
 * Provides batch resolution operations and conflict grouping.
 */
public class ConflictBatch {
    
    private final List<Conflict> conflicts;
    private final Map<String, List<Conflict>> conflictsByPath;
    private final Map<ConflictType, List<Conflict>> conflictsByType;
    private final Map<ResolutionStrategy, List<Conflict>> resolvedConflicts;
    
    public ConflictBatch() {
        this.conflicts = new ArrayList<>();
        this.conflictsByPath = new HashMap<>();
        this.conflictsByType = new HashMap<>();
        this.resolvedConflicts = new HashMap<>();
    }
    
    /**
     * Adds a conflict to the batch.
     */
    public void addConflict(Conflict conflict) {
        conflicts.add(conflict);
        
        // Index by path
        String path = conflict.path();
        conflictsByPath.computeIfAbsent(path, k -> new ArrayList<>()).add(conflict);
        
        // Index by type
        conflictsByType.computeIfAbsent(conflict.type(), k -> new ArrayList<>()).add(conflict);
    }
    
    /**
     * Adds multiple conflicts to the batch.
     */
    public void addConflicts(List<Conflict> newConflicts) {
        for (Conflict conflict : newConflicts) {
            addConflict(conflict);
        }
    }
    
    /**
     * Gets all conflicts in the batch.
     */
    public List<Conflict> getConflicts() {
        return new ArrayList<>(conflicts);
    }
    
    /**
     * Gets conflicts for a specific path.
     */
    public List<Conflict> getConflictsByPath(String path) {
        return conflictsByPath.getOrDefault(path, new ArrayList<>());
    }
    
    /**
     * Gets conflicts of a specific type.
     */
    public List<Conflict> getConflictsByType(ConflictType type) {
        return conflictsByType.getOrDefault(type, new ArrayList<>());
    }
    
    /**
     * Gets all conflict types present in the batch.
     */
    public List<ConflictType> getConflictTypes() {
        return new ArrayList<>(conflictsByType.keySet());
    }
    
    /**
     * Gets all paths that have conflicts.
     */
    public List<String> getConflictedPaths() {
        return new ArrayList<>(conflictsByPath.keySet());
    }
    
    /**
     * Gets the total number of conflicts.
     */
    public int getConflictCount() {
        return conflicts.size();
    }
    
    /**
     * Gets the number of conflicts for a specific type.
     */
    public int getConflictCount(ConflictType type) {
        return conflictsByType.getOrDefault(type, new ArrayList<>()).size();
    }
    
    /**
     * Checks if the batch has any conflicts.
     */
    public boolean hasConflicts() {
        return !conflicts.isEmpty();
    }
    
    /**
     * Checks if there are conflicts for a specific path.
     */
    public boolean hasConflict(String path) {
        return conflictsByPath.containsKey(path);
    }
    
    /**
     * Checks if there are conflicts of a specific type.
     */
    public boolean hasConflictType(ConflictType type) {
        return conflictsByType.containsKey(type);
    }
    
    /**
     * Resolves all conflicts using the given strategy.
     */
    public void resolveAll(ResolutionStrategy strategy, ResolutionContext context) {
        for (Conflict conflict : conflicts) {
            com.syncforge.metadata.EntryMetadata resolved = strategy.resolve(conflict, context);
            
            if (resolved != null) {
                resolvedConflicts.computeIfAbsent(strategy, k -> new ArrayList<>()).add(conflict);
            }
        }
    }
    
    /**
     * Resolves conflicts of a specific type.
     */
    public void resolveByType(ConflictType type, ResolutionStrategy strategy, 
                            ResolutionContext context) {
        List<Conflict> typeConflicts = conflictsByType.get(type);
        
        if (typeConflicts != null) {
            for (Conflict conflict : typeConflicts) {
                com.syncforge.metadata.EntryMetadata resolved = strategy.resolve(conflict, context);
                
                if (resolved != null) {
                    resolvedConflicts.computeIfAbsent(strategy, k -> new ArrayList<>()).add(conflict);
                }
            }
        }
    }
    
    /**
     * Gets conflicts resolved with a specific strategy.
     */
    public List<Conflict> getResolvedConflicts(ResolutionStrategy strategy) {
        return resolvedConflicts.getOrDefault(strategy, new ArrayList<>());
    }
    
    /**
     * Gets unresolved conflicts.
     */
    public List<Conflict> getUnresolvedConflicts() {
        List<Conflict> unresolved = new ArrayList<>();
        
        for (Conflict conflict : conflicts) {
            boolean isResolved = false;
            for (List<Conflict> resolvedList : resolvedConflicts.values()) {
                if (resolvedList.contains(conflict)) {
                    isResolved = true;
                    break;
                }
            }
            
            if (!isResolved) {
                unresolved.add(conflict);
            }
        }
        
        return unresolved;
    }
    
    /**
     * Gets the number of unresolved conflicts.
     */
    public int getUnresolvedCount() {
        return getUnresolvedConflicts().size();
    }
    
    /**
     * Clears all conflicts from the batch.
     */
    public void clear() {
        conflicts.clear();
        conflictsByPath.clear();
        conflictsByType.clear();
        resolvedConflicts.clear();
    }
    
    /**
     * Generates a summary report.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Conflict Batch Summary:\n");
        report.append("======================\n");
        report.append(String.format("Total conflicts: %d\n", getConflictCount()));
        report.append(String.format("Unresolved conflicts: %d\n", getUnresolvedCount()));
        report.append(String.format("Conflicted paths: %d\n", getConflictedPaths().size()));
        report.append("\n");
        
        report.append("Conflicts by type:\n");
        for (ConflictType type : getConflictTypes()) {
            report.append(String.format("  %s: %d\n", type, getConflictCount(type)));
        }
        
        report.append("\n");
        report.append("Resolution summary:\n");
        for (Map.Entry<ResolutionStrategy, List<Conflict>> entry : resolvedConflicts.entrySet()) {
            report.append(String.format("  %s: %d resolved\n", entry.getKey(), entry.getValue().size()));
        }
        
        return report.toString();
    }
    
    /**
     * Creates a new conflict batch.
     */
    public static ConflictBatch create() {
        return new ConflictBatch();
    }
}
