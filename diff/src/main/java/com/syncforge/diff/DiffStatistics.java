package com.syncforge.diff;

import com.syncforge.metadata.EntryMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * Collects and reports statistics about diff operations.
 * Provides detailed metrics about changes between snapshots.
 */
public class DiffStatistics {
    
    private final Map<DiffEntry.Type, Integer> changeCounts;
    private long totalAddedSize;
    private long totalRemovedSize;
    private long totalModifiedSize;
    private int fileChanges;
    private int directoryChanges;
    private int symlinkChanges;
    private int permissionChanges;
    
    public DiffStatistics() {
        this.changeCounts = new HashMap<>();
        this.totalAddedSize = 0;
        this.totalRemovedSize = 0;
        this.totalModifiedSize = 0;
        this.fileChanges = 0;
        this.directoryChanges = 0;
        this.symlinkChanges = 0;
        this.permissionChanges = 0;
        
        // Initialize all change type counters
        for (DiffEntry.Type type : DiffEntry.Type.values()) {
            changeCounts.put(type, 0);
        }
    }
    
    /**
     * Records a diff entry.
     */
    public void recordEntry(DiffEntry entry) {
        changeCounts.put(entry.getChangeType(), changeCounts.getOrDefault(entry.getChangeType(), 0) + 1);
        
        if (entry.getBefore() != null) {
            if (entry.getBefore() instanceof com.syncforge.metadata.FileEntry) {
                fileChanges++;
                totalRemovedSize += ((com.syncforge.metadata.FileEntry) entry.getBefore()).getSize();
            } else if (entry.getBefore() instanceof com.syncforge.metadata.DirectoryEntry) {
                directoryChanges++;
            } else if (entry.getBefore() instanceof com.syncforge.metadata.SymlinkEntry) {
                symlinkChanges++;
            }
        }
        
        if (entry.getAfter() != null) {
            if (entry.getAfter() instanceof com.syncforge.metadata.FileEntry) {
                fileChanges++;
                totalAddedSize += ((com.syncforge.metadata.FileEntry) entry.getAfter()).getSize();
            } else if (entry.getAfter() instanceof com.syncforge.metadata.DirectoryEntry) {
                directoryChanges++;
            } else if (entry.getAfter() instanceof com.syncforge.metadata.SymlinkEntry) {
                symlinkChanges++;
            }
        }
        
        if (entry.getChangeType() == DiffEntry.Type.PERMISSION_CHANGE) {
            permissionChanges++;
        }
        
        if (entry.getChangeType() == DiffEntry.Type.MODIFY && 
            entry.getAfter() instanceof com.syncforge.metadata.FileEntry) {
            totalModifiedSize += ((com.syncforge.metadata.FileEntry) entry.getAfter()).getSize();
        }
    }
    
    /**
     * Gets the count for a specific change type.
     */
    public int getCount(DiffEntry.Type type) {
        return changeCounts.getOrDefault(type, 0);
    }
    
    /**
     * Gets all change type counts.
     */
    public Map<DiffEntry.Type, Integer> getChangeCounts() {
        return new HashMap<>(changeCounts);
    }
    
    /**
     * Gets the total number of changes.
     */
    public int getTotalChanges() {
        return changeCounts.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    /**
     * Gets the total added size in bytes.
     */
    public long getTotalAddedSize() {
        return totalAddedSize;
    }
    
    /**
     * Gets the total removed size in bytes.
     */
    public long getTotalRemovedSize() {
        return totalRemovedSize;
    }
    
    /**
     * Gets the total modified size in bytes.
     */
    public long getTotalModifiedSize() {
        return totalModifiedSize;
    }
    
    /**
     * Gets the net size change (added - removed).
     */
    public long getNetSizeChange() {
        return totalAddedSize - totalRemovedSize;
    }
    
    /**
     * Gets the number of file changes.
     */
    public int getFileChanges() {
        return fileChanges;
    }
    
    /**
     * Gets the number of directory changes.
     */
    public int getDirectoryChanges() {
        return directoryChanges;
    }
    
    /**
     * Gets the number of symlink changes.
     */
    public int getSymlinkChanges() {
        return symlinkChanges;
    }
    
    /**
     * Gets the number of permission changes.
     */
    public int getPermissionChanges() {
        return permissionChanges;
    }
    
    /**
     * Checks if there are any changes.
     */
    public boolean hasChanges() {
        return getTotalChanges() > 0;
    }
    
    /**
     * Checks if there are any file additions.
     */
    public boolean hasAdditions() {
        return getCount(DiffEntry.Type.ADD) > 0;
    }
    
    /**
     * Checks if there are any file deletions.
     */
    public boolean hasDeletions() {
        return getCount(DiffEntry.Type.DELETE) > 0;
    }
    
    /**
     * Checks if there are any file modifications.
     */
    public boolean hasModifications() {
        return getCount(DiffEntry.Type.MODIFY) > 0;
    }
    
    /**
     * Checks if there are any renames.
     */
    public boolean hasRenames() {
        return getCount(DiffEntry.Type.RENAME) > 0;
    }
    
    /**
     * Generates a summary report.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Diff Statistics:\n");
        report.append("================\n");
        report.append(String.format("Total changes: %d\n", getTotalChanges()));
        report.append(String.format("File changes: %d\n", fileChanges));
        report.append(String.format("Directory changes: %d\n", directoryChanges));
        report.append(String.format("Symlink changes: %d\n", symlinkChanges));
        report.append(String.format("Permission changes: %d\n", permissionChanges));
        report.append("\n");
        report.append("Change Types:\n");
        for (DiffEntry.Type type : DiffEntry.Type.values()) {
            report.append(String.format("  %s: %d\n", type, getCount(type)));
        }
        report.append("\n");
        report.append(String.format("Total added size: %d bytes (%.2f MB)\n", 
                                   totalAddedSize, totalAddedSize / (1024.0 * 1024.0)));
        report.append(String.format("Total removed size: %d bytes (%.2f MB)\n", 
                                   totalRemovedSize, totalRemovedSize / (1024.0 * 1024.0)));
        report.append(String.format("Total modified size: %d bytes (%.2f MB)\n", 
                                   totalModifiedSize, totalModifiedSize / (1024.0 * 1024.0)));
        report.append(String.format("Net size change: %d bytes (%.2f MB)\n", 
                                   getNetSizeChange(), getNetSizeChange() / (1024.0 * 1024.0)));
        
        return report.toString();
    }
    
    /**
     * Creates a statistics collector.
     */
    public static DiffStatistics create() {
        return new DiffStatistics();
    }
}
