package com.syncforge.metadata;

import java.util.Map;

/**
 * Merges metadata entries from different sources with conflict resolution.
 * Supports various merge strategies and conflict handling policies.
 */
public class MetadataMerger {
    
    private final MergeStrategy mergeStrategy;
    private final ConflictResolution conflictResolution;
    
    public MetadataMerger() {
        this(MergeStrategy.LATEST_WINS, ConflictResolution.PREFER_SOURCE);
    }
    
    public MetadataMerger(MergeStrategy mergeStrategy, ConflictResolution conflictResolution) {
        this.mergeStrategy = mergeStrategy;
        this.conflictResolution = conflictResolution;
    }
    
    /**
     * Merges two metadata entries into a single entry.
     */
    public EntryMetadata merge(EntryMetadata source, EntryMetadata target) {
        if (source == null && target == null) {
            return null;
        }
        if (source == null) {
            return target;
        }
        if (target == null) {
            return source;
        }
        
        // Check for type conflicts
        if (!isCompatibleType(source, target)) {
            throw new MetadataMergeException("Cannot merge incompatible entry types: " + 
                source.getClass().getSimpleName() + " and " + target.getClass().getSimpleName());
        }
        
        return switch (mergeStrategy) {
            case LATEST_WINS -> mergeLatestWins(source, target);
            case SOURCE_WINS -> mergeSourceWins(source, target);
            case TARGET_WINS -> mergeTargetWins(source, target);
            case MERGE_ALL -> mergeAll(source, target);
            case CONSERVATIVE -> mergeConservative(source, target);
        };
    }
    
    /**
     * Merges multiple metadata entries.
     */
    public EntryMetadata mergeMultiple(EntryMetadata... entries) {
        if (entries == null || entries.length == 0) {
            return null;
        }
        
        EntryMetadata result = entries[0];
        for (int i = 1; i < entries.length; i++) {
            result = merge(result, entries[i]);
        }
        
        return result;
    }
    
    private boolean isCompatibleType(EntryMetadata a, EntryMetadata b) {
        // Files can merge with files, directories with directories, etc.
        return a.getClass().equals(b.getClass());
    }
    
    private EntryMetadata mergeLatestWins(EntryMetadata source, EntryMetadata target) {
        // Choose the entry with the latest modification time
        if (source.getTimestamp() == null) {
            return target;
        }
        if (target.getTimestamp() == null) {
            return source;
        }
        
        if (source.getTimestamp().mtimeMillis() >= target.getTimestamp().mtimeMillis()) {
            return source;
        }
        return target;
    }
    
    private EntryMetadata mergeSourceWins(EntryMetadata source, EntryMetadata target) {
        // Simply return source, ignoring target
        return source;
    }
    
    private EntryMetadata mergeTargetWins(EntryMetadata source, EntryMetadata target) {
        // Simply return target, ignoring source
        return target;
    }
    
    private EntryMetadata mergeAll(EntryMetadata source, EntryMetadata target) {
        // Merge all fields, preferring source in case of conflicts
        if (source instanceof FileEntry sourceFile && target instanceof FileEntry targetFile) {
            return mergeFileEntries(sourceFile, targetFile, true);
        } else if (source instanceof DirectoryEntry sourceDir && target instanceof DirectoryEntry targetDir) {
            return mergeDirectoryEntries(sourceDir, targetDir, true);
        } else if (source instanceof SymlinkEntry sourceLink && target instanceof SymlinkEntry targetLink) {
            return mergeSymlinkEntries(sourceLink, targetLink, true);
        }
        
        return source;
    }
    
    private EntryMetadata mergeConservative(EntryMetadata source, EntryMetadata target) {
        // Only merge fields that are identical or can be safely combined
        if (source instanceof FileEntry sourceFile && target instanceof FileEntry targetFile) {
            return mergeFileEntries(sourceFile, targetFile, false);
        } else if (source instanceof DirectoryEntry sourceDir && target instanceof DirectoryEntry targetDir) {
            return mergeDirectoryEntries(sourceDir, targetDir, false);
        } else if (source instanceof SymlinkEntry sourceLink && target instanceof SymlinkEntry targetLink) {
            return mergeSymlinkEntries(sourceLink, targetLink, false);
        }
        
        return source;
    }
    
    private FileEntry mergeFileEntries(FileEntry source, FileEntry target, boolean aggressive) {
        // Use builder to create merged entry
        FileEntry.Builder builder = FileEntry.builder()
            .path(source.getNormalizedPath())
            .timestamp(mergeTimestamps(source.getTimestamp(), target.getTimestamp(), aggressive))
            .mode(mergeFileModes(source.getMode(), target.getMode(), aggressive))
            .size(aggressive ? Math.max(source.getSize(), target.getSize()) : source.getSize())
            .contentHash(mergeHashes(source.getContentHash(), target.getContentHash(), aggressive))
            .extendedAttributes(mergeAttributeMaps(source.getExtendedAttributes(), target.getExtendedAttributes(), aggressive));
        
        return builder.build();
    }
    
    private DirectoryEntry mergeDirectoryEntries(DirectoryEntry source, DirectoryEntry target, boolean aggressive) {
        // Use builder to create merged entry
        DirectoryEntry.Builder builder = DirectoryEntry.builder()
            .path(source.getNormalizedPath())
            .timestamp(mergeTimestamps(source.getTimestamp(), target.getTimestamp(), aggressive))
            .mode(mergeFileModes(source.getMode(), target.getMode(), aggressive));
        
        return builder.build();
    }
    
    private SymlinkEntry mergeSymlinkEntries(SymlinkEntry source, SymlinkEntry target, boolean aggressive) {
        String targetPath;
        if (source.getTargetPath().equals(target.getTargetPath())) {
            targetPath = source.getTargetPath();
        } else if (aggressive) {
            targetPath = source.getTargetPath();
        } else {
            throw new MetadataMergeException("Symlink targets differ: " + 
                source.getTargetPath() + " vs " + target.getTargetPath());
        }
        
        // Use builder to create merged entry
        SymlinkEntry.Builder builder = SymlinkEntry.builder()
            .path(source.getNormalizedPath())
            .timestamp(mergeTimestamps(source.getTimestamp(), target.getTimestamp(), aggressive))
            .mode(mergeFileModes(source.getMode(), target.getMode(), aggressive))
            .targetPath(targetPath);
        
        return builder.build();
    }
    
    private FileTimestamp mergeTimestamps(FileTimestamp a, FileTimestamp b, boolean aggressive) {
        if (a == null && b == null) return null;
        if (a == null) return b;
        if (b == null) return a;
        
        if (aggressive) {
            // Use the latest timestamp
            return a.mtimeMillis() >= b.mtimeMillis() ? a : b;
        } else {
            // Use the earliest timestamp (more conservative)
            return a.mtimeMillis() <= b.mtimeMillis() ? a : b;
        }
    }
    
    private FileMode mergeFileModes(FileMode a, FileMode b, boolean aggressive) {
        if (a == null && b == null) return null;
        if (a == null) return b;
        if (b == null) return a;
        
        if (aggressive) {
            // Combine permissions (union)
            int combinedMode = a.getPosixPermissions() | b.getPosixPermissions();
            return FileMode.builder()
                .type(a.getType())
                .permissions(combinedMode)
                .build();
        } else {
            // Use source permissions
            return a;
        }
    }
    
    private FileHash mergeHashes(FileHash a, FileHash b, boolean aggressive) {
        if (a == null && b == null) return null;
        if (a == null) return b;
        if (b == null) return a;
        
        if (a.equals(b)) {
            return a;
        } else if (aggressive) {
            return a;
        } else {
            // Hash conflict - keep source in conservative mode
            return a;
        }
    }
    
    private Map<String, String> mergeAttributeMaps(Map<String, String> a, Map<String, String> b, boolean aggressive) {
        if (a == null && b == null) return new java.util.HashMap<>();
        if (a == null) return new java.util.HashMap<>(b);
        if (b == null) return new java.util.HashMap<>(a);
        
        if (aggressive) {
            // Union of attributes (source takes precedence on conflicts)
            Map<String, String> merged = new java.util.HashMap<>(b);
            merged.putAll(a);
            return merged;
        } else {
            // Intersection of attributes (only common attributes)
            Map<String, String> merged = new java.util.HashMap<>();
            for (String key : a.keySet()) {
                if (b.containsKey(key) && a.get(key).equals(b.get(key))) {
                    merged.put(key, a.get(key));
                }
            }
            return merged;
        }
    }
    
    /**
     * Merge strategies for metadata combination.
     */
    public enum MergeStrategy {
        LATEST_WINS,
        SOURCE_WINS,
        TARGET_WINS,
        MERGE_ALL,
        CONSERVATIVE
    }
    
    /**
     * Conflict resolution policies.
     */
    public enum ConflictResolution {
        PREFER_SOURCE,
        PREFER_TARGET,
        RAISE_ERROR,
        MERGE_CONSERVATIVE
    }
    
    /**
     * Exception thrown when metadata cannot be merged.
     */
    public static class MetadataMergeException extends RuntimeException {
        public MetadataMergeException(String message) {
            super(message);
        }
        
        public MetadataMergeException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
