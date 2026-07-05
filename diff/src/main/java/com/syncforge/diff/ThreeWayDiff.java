package com.syncforge.diff;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.snapshot.SnapshotModel;

import java.util.*;

/**
 * Performs three-way diff operations comparing base, modified, and original versions.
 * Useful for merge conflict detection and resolution.
 */
public class ThreeWayDiff {
    
    private final SnapshotModel baseSnapshot;
    private final SnapshotModel modifiedSnapshot;
    private final SnapshotModel originalSnapshot;
    
    public ThreeWayDiff(SnapshotModel baseSnapshot, SnapshotModel modifiedSnapshot, 
                        SnapshotModel originalSnapshot) {
        this.baseSnapshot = baseSnapshot;
        this.modifiedSnapshot = modifiedSnapshot;
        this.originalSnapshot = originalSnapshot;
    }
    
    /**
     * Performs a three-way diff to detect conflicts and changes.
     */
    public ThreeWayDiffResult diff() {
        ThreeWayDiffResult result = new ThreeWayDiffResult();
        
        // Get all unique paths from all snapshots
        Set<String> allPaths = new HashSet<>();
        for (EntryMetadata entry : baseSnapshot.getEntries()) {
            allPaths.add(entry.getNormalizedPath());
        }
        for (EntryMetadata entry : modifiedSnapshot.getEntries()) {
            allPaths.add(entry.getNormalizedPath());
        }
        for (EntryMetadata entry : originalSnapshot.getEntries()) {
            allPaths.add(entry.getNormalizedPath());
        }
        
        // Build path maps for lookup
        Map<String, EntryMetadata> baseMap = new HashMap<>();
        for (EntryMetadata entry : baseSnapshot.getEntries()) {
            baseMap.put(entry.getNormalizedPath(), entry);
        }
        Map<String, EntryMetadata> modifiedMap = new HashMap<>();
        for (EntryMetadata entry : modifiedSnapshot.getEntries()) {
            modifiedMap.put(entry.getNormalizedPath(), entry);
        }
        Map<String, EntryMetadata> originalMap = new HashMap<>();
        for (EntryMetadata entry : originalSnapshot.getEntries()) {
            originalMap.put(entry.getNormalizedPath(), entry);
        }
        
        for (String path : allPaths) {
            EntryMetadata baseEntry = baseMap.get(path);
            EntryMetadata modifiedEntry = modifiedMap.get(path);
            EntryMetadata originalEntry = originalMap.get(path);
            
            ThreeWayChange change = analyzeChange(path, baseEntry, modifiedEntry, originalEntry);
            result.addChange(change);
        }
        
        return result;
    }
    
    /**
     * Analyzes a single path across three snapshots.
     */
    private ThreeWayChange analyzeChange(String path, EntryMetadata baseEntry, 
                                        EntryMetadata modifiedEntry, EntryMetadata originalEntry) {
        // Determine the change type
        if (baseEntry == null && modifiedEntry == null && originalEntry == null) {
            return new ThreeWayChange(path, ThreeWayChangeType.UNCHANGED, null, null, null);
        }
        
        if (baseEntry != null && modifiedEntry != null && originalEntry != null) {
            // All three exist - check for modifications
            if (entriesEqual(baseEntry, modifiedEntry) && entriesEqual(baseEntry, originalEntry)) {
                return new ThreeWayChange(path, ThreeWayChangeType.UNCHANGED, baseEntry, modifiedEntry, originalEntry);
            } else if (entriesEqual(modifiedEntry, originalEntry) && !entriesEqual(baseEntry, modifiedEntry)) {
                // Base changed, others same - clean modification
                return new ThreeWayChange(path, ThreeWayChangeType.MODIFIED, baseEntry, modifiedEntry, originalEntry);
            } else if (!entriesEqual(modifiedEntry, originalEntry)) {
                // Modified and original differ - potential conflict
                return new ThreeWayChange(path, ThreeWayChangeType.CONFLICT, baseEntry, modifiedEntry, originalEntry);
            } else {
                return new ThreeWayChange(path, ThreeWayChangeType.MODIFIED, baseEntry, modifiedEntry, originalEntry);
            }
        }
        
        // Handle deletions and additions
        if (baseEntry != null && modifiedEntry == null && originalEntry == null) {
            return new ThreeWayChange(path, ThreeWayChangeType.DELETED, baseEntry, null, null);
        }
        
        if (baseEntry == null && modifiedEntry != null && originalEntry == null) {
            return new ThreeWayChange(path, ThreeWayChangeType.ADDED, null, modifiedEntry, null);
        }
        
        if (baseEntry == null && modifiedEntry == null && originalEntry != null) {
            return new ThreeWayChange(path, ThreeWayChangeType.DELETED_IN_BOTH, null, null, originalEntry);
        }
        
        // Complex cases
        if (baseEntry != null && modifiedEntry != null && originalEntry == null) {
            return new ThreeWayChange(path, ThreeWayChangeType.ADDED_MODIFIED, baseEntry, modifiedEntry, null);
        }
        
        if (baseEntry != null && modifiedEntry == null && originalEntry != null) {
            return new ThreeWayChange(path, ThreeWayChangeType.DELETED_MODIFIED, baseEntry, null, originalEntry);
        }
        
        if (baseEntry == null && modifiedEntry != null && originalEntry != null) {
            if (entriesEqual(modifiedEntry, originalEntry)) {
                return new ThreeWayChange(path, ThreeWayChangeType.ADDED, null, modifiedEntry, originalEntry);
            } else {
                return new ThreeWayChange(path, ThreeWayChangeType.CONFLICT, null, modifiedEntry, originalEntry);
            }
        }
        
        return new ThreeWayChange(path, ThreeWayChangeType.UNKNOWN, baseEntry, modifiedEntry, originalEntry);
    }
    
    /**
     * Compares two entries for equality.
     */
    private boolean entriesEqual(EntryMetadata a, EntryMetadata b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        
        // Check type
        if (!a.getClass().equals(b.getClass())) {
            return false;
        }
        
        // Check path
        if (!a.getNormalizedPath().equals(b.getNormalizedPath())) {
            return false;
        }
        
        // Check file-specific attributes
        if (a instanceof com.syncforge.metadata.FileEntry fileA && 
            b instanceof com.syncforge.metadata.FileEntry fileB) {
            if (fileA.getSize() != fileB.getSize()) {
                return false;
            }
            
            if (fileA.getContentHash() != null && fileB.getContentHash() != null) {
                if (!fileA.getContentHash().equals(fileB.getContentHash())) {
                    return false;
                }
            }
        }
        
        // Check modification time
        if (a.getTimestamp() != null && b.getTimestamp() != null) {
            if (a.getTimestamp().mtimeMillis() != b.getTimestamp().mtimeMillis()) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Three-way change types.
     */
    public enum ThreeWayChangeType {
        UNCHANGED,
        MODIFIED,
        ADDED,
        DELETED,
        DELETED_IN_BOTH,
        ADDED_MODIFIED,
        DELETED_MODIFIED,
        CONFLICT,
        UNKNOWN
    }
    
    /**
     * Represents a change in a three-way diff.
     */
    public record ThreeWayChange(
        String path,
        ThreeWayChangeType changeType,
        EntryMetadata baseEntry,
        EntryMetadata modifiedEntry,
        EntryMetadata originalEntry
    ) {}
    
    /**
     * Result of a three-way diff operation.
     */
    public static class ThreeWayDiffResult {
        private final List<ThreeWayChange> changes;
        private final Map<ThreeWayChangeType, Integer> changeCounts;
        
        public ThreeWayDiffResult() {
            this.changes = new ArrayList<>();
            this.changeCounts = new HashMap<>();
            
            for (ThreeWayChangeType type : ThreeWayChangeType.values()) {
                changeCounts.put(type, 0);
            }
        }
        
        public void addChange(ThreeWayChange change) {
            changes.add(change);
            changeCounts.put(change.changeType(), changeCounts.getOrDefault(change.changeType(), 0) + 1);
        }
        
        public List<ThreeWayChange> getChanges() {
            return new ArrayList<>(changes);
        }
        
        public List<ThreeWayChange> getConflicts() {
            return changes.stream()
                .filter(c -> c.changeType() == ThreeWayChangeType.CONFLICT)
                .toList();
        }
        
        public int getConflictCount() {
            return changeCounts.getOrDefault(ThreeWayChangeType.CONFLICT, 0);
        }
        
        public boolean hasConflicts() {
            return getConflictCount() > 0;
        }
        
        public Map<ThreeWayChangeType, Integer> getChangeCounts() {
            return new HashMap<>(changeCounts);
        }
        
        public int getTotalChanges() {
            return changes.size();
        }
        
        public String generateReport() {
            StringBuilder report = new StringBuilder();
            report.append("Three-Way Diff Result:\n");
            report.append("======================\n");
            report.append(String.format("Total changes: %d\n", getTotalChanges()));
            report.append(String.format("Conflicts: %d\n", getConflictCount()));
            report.append("\n");
            report.append("Change Types:\n");
            for (ThreeWayChangeType type : ThreeWayChangeType.values()) {
                report.append(String.format("  %s: %d\n", type, changeCounts.getOrDefault(type, 0)));
            }
            
            if (hasConflicts()) {
                report.append("\nConflicts:\n");
                for (ThreeWayChange conflict : getConflicts()) {
                    report.append(String.format("  - %s\n", conflict.path()));
                }
            }
            
            return report.toString();
        }
    }
}
