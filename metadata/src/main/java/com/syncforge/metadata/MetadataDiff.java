package com.syncforge.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Computes differences between metadata entries.
 * Identifies changed fields and provides detailed diff information.
 */
public class MetadataDiff {
    
    private final EntryMetadata source;
    private final EntryMetadata target;
    private final List<FieldChange> fieldChanges;
    private final boolean hasChanges;
    
    public MetadataDiff(EntryMetadata source, EntryMetadata target) {
        this.source = source;
        this.target = target;
        this.fieldChanges = new ArrayList<>();
        this.hasChanges = computeChanges();
    }
    
    private boolean computeChanges() {
        if (source == null && target == null) {
            return false;
        }
        
        if (source == null || target == null) {
            // One is null, the other is not - this is a change
            fieldChanges.add(new FieldChange("existence", 
                source == null ? "null" : source.toString(),
                target == null ? "null" : target.toString(),
                ChangeType.ADD_DELETE));
            return true;
        }
        
        boolean changed = false;
        
        // Check path
        if (!source.getNormalizedPath().equals(target.getNormalizedPath())) {
            fieldChanges.add(new FieldChange("path", 
                source.getNormalizedPath(), target.getNormalizedPath(), ChangeType.MODIFIED));
            changed = true;
        }
        
        // Check size (for files)
        if (source instanceof FileEntry && target instanceof FileEntry) {
            FileEntry sourceFile = (FileEntry) source;
            FileEntry targetFile = (FileEntry) target;
            
            if (sourceFile.getSize() != targetFile.getSize()) {
                fieldChanges.add(new FieldChange("size", 
                    String.valueOf(sourceFile.getSize()),
                    String.valueOf(targetFile.getSize()),
                    ChangeType.MODIFIED));
                changed = true;
            }
        }
        
        // Check modified time
        if (!timestampsEqual(source.getTimestamp(), target.getTimestamp())) {
            fieldChanges.add(new FieldChange("timestamp",
                source.getTimestamp() != null ? source.getTimestamp().toString() : "null",
                target.getTimestamp() != null ? target.getTimestamp().toString() : "null",
                ChangeType.MODIFIED));
            changed = true;
        }
        
        // Check hash (for files)
        if (source instanceof FileEntry && target instanceof FileEntry) {
            FileEntry sourceFile = (FileEntry) source;
            FileEntry targetFile = (FileEntry) target;
            
            if (!hashesEqual(sourceFile.getContentHash(), targetFile.getContentHash())) {
                fieldChanges.add(new FieldChange("hash",
                    sourceFile.getContentHash() != null ? sourceFile.getContentHash().toString() : "null",
                    targetFile.getContentHash() != null ? targetFile.getContentHash().toString() : "null",
                    ChangeType.MODIFIED));
                changed = true;
            }
        }
        
        // Check file mode
        if (!fileModesEqual(source.getMode(), target.getMode())) {
            fieldChanges.add(new FieldChange("fileMode",
                source.getMode() != null ? source.getMode().toString() : "null",
                target.getMode() != null ? target.getMode().toString() : "null",
                ChangeType.MODIFIED));
            changed = true;
        }
        
        // Check symlink target (for symlinks)
        if (source instanceof SymlinkEntry && target instanceof SymlinkEntry) {
            SymlinkEntry sourceLink = (SymlinkEntry) source;
            SymlinkEntry targetLink = (SymlinkEntry) target;
            
            if (!sourceLink.getTargetPath().equals(targetLink.getTargetPath())) {
                fieldChanges.add(new FieldChange("symlinkTarget",
                    sourceLink.getTargetPath(),
                    targetLink.getTargetPath(),
                    ChangeType.MODIFIED));
                changed = true;
            }
        }
        
        // Check extended attributes (only for FileEntry)
        if (source instanceof FileEntry && target instanceof FileEntry) {
            FileEntry sourceFile = (FileEntry) source;
            FileEntry targetFile = (FileEntry) target;
            if (!attributesEqual(sourceFile.getExtendedAttributes(), targetFile.getExtendedAttributes())) {
                fieldChanges.add(new FieldChange("extendedAttributes",
                    sourceFile.getExtendedAttributes() != null ? sourceFile.getExtendedAttributes().toString() : "null",
                    targetFile.getExtendedAttributes() != null ? targetFile.getExtendedAttributes().toString() : "null",
                    ChangeType.MODIFIED));
                changed = true;
            }
        }
        
        return changed;
    }
    
    private boolean timestampsEqual(FileTimestamp a, FileTimestamp b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.mtimeMillis() == b.mtimeMillis() &&
               a.ctimeMillis() == b.ctimeMillis() &&
               a.atimeMillis() == b.atimeMillis();
    }
    
    private boolean hashesEqual(FileHash a, FileHash b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }
    
    private boolean fileModesEqual(FileMode a, FileMode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.getPosixPermissions() == b.getPosixPermissions() &&
               a.getType() == b.getType();
    }
    
    private boolean attributesEqual(java.util.Map<String, String> a, java.util.Map<String, String> b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }
    
    /**
     * Gets the source metadata.
     */
    public EntryMetadata getSource() {
        return source;
    }
    
    /**
     * Gets the target metadata.
     */
    public EntryMetadata getTarget() {
        return target;
    }
    
    /**
     * Gets all field changes.
     */
    public List<FieldChange> getFieldChanges() {
        return new ArrayList<>(fieldChanges);
    }
    
    /**
     * Checks if there are any changes.
     */
    public boolean hasChanges() {
        return hasChanges;
    }
    
    /**
     * Gets the number of changed fields.
     */
    public int getChangeCount() {
        return fieldChanges.size();
    }
    
    /**
     * Gets a summary of the diff.
     */
    public DiffSummary getSummary() {
        Map<ChangeType, Integer> counts = new HashMap<>();
        for (FieldChange change : fieldChanges) {
            counts.put(change.changeType(), counts.getOrDefault(change.changeType(), 0) + 1);
        }
        
        return new DiffSummary(hasChanges, fieldChanges.size(), counts);
    }
    
    /**
     * Represents a change to a specific field.
     */
    public record FieldChange(
        String fieldName,
        String oldValue,
        String newValue,
        ChangeType changeType
    ) {}
    
    /**
     * Type of change.
     */
    public enum ChangeType {
        MODIFIED,
        ADD_DELETE,
        TYPE_CHANGE
    }
    
    /**
     * Summary of metadata differences.
     */
    public record DiffSummary(
        boolean hasChanges,
        int totalChanges,
        Map<ChangeType, Integer> changeCounts
    ) {
        public int getModifiedCount() {
            return changeCounts.getOrDefault(ChangeType.MODIFIED, 0);
        }
        
        public int getAddDeleteCount() {
            return changeCounts.getOrDefault(ChangeType.ADD_DELETE, 0);
        }
        
        public int getTypeChangeCount() {
            return changeCounts.getOrDefault(ChangeType.TYPE_CHANGE, 0);
        }
    }
    
    /**
     * Computes a diff between two metadata entries.
     */
    public static MetadataDiff diff(EntryMetadata source, EntryMetadata target) {
        return new MetadataDiff(source, target);
    }
    
    /**
     * Computes diffs for multiple metadata pairs.
     */
    public static List<MetadataDiff> diffAll(Map<String, EntryMetadata> source, 
                                            Map<String, EntryMetadata> target) {
        List<MetadataDiff> diffs = new ArrayList<>();
        
        // Process all paths from both maps
        java.util.Set<String> allPaths = new java.util.HashSet<>();
        allPaths.addAll(source.keySet());
        allPaths.addAll(target.keySet());
        
        for (String path : allPaths) {
            EntryMetadata sourceEntry = source.get(path);
            EntryMetadata targetEntry = target.get(path);
            diffs.add(new MetadataDiff(sourceEntry, targetEntry));
        }
        
        return diffs;
    }
}
