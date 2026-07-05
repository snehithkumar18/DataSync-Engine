package com.syncforge.conflict;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;

import java.util.List;

/**
 * Defines strategies for resolving conflicts between file entries.
 * Supports multiple resolution approaches with different trade-offs.
 */
public enum ResolutionStrategy {
    
    /**
     * Keep the source version, discard the target.
     */
    SOURCE_WINS {
        @Override
        public EntryMetadata resolve(Conflict conflict, ResolutionContext context) {
            return conflict.localEntry();
        }
    },
    
    /**
     * Keep the target version, discard the source.
     */
    TARGET_WINS {
        @Override
        public EntryMetadata resolve(Conflict conflict, ResolutionContext context) {
            return conflict.remoteEntry();
        }
    },
    
    /**
     * Keep the version with the latest modification time.
     */
    LATEST_WINS {
        @Override
        public EntryMetadata resolve(Conflict conflict, ResolutionContext context) {
            if (conflict.localEntry() == null) return conflict.remoteEntry();
            if (conflict.remoteEntry() == null) return conflict.localEntry();
            
            long sourceTime = conflict.localEntry().getTimestamp() != null ? 
                conflict.localEntry().getTimestamp().mtimeMillis() : 0;
            long targetTime = conflict.remoteEntry().getTimestamp() != null ? 
                conflict.remoteEntry().getTimestamp().mtimeMillis() : 0;
            
            return sourceTime >= targetTime ? conflict.localEntry() : conflict.remoteEntry();
        }
    },
    
    /**
     * Keep the version with the largest file size.
     */
    LARGEST_WINS {
        @Override
        public EntryMetadata resolve(Conflict conflict, ResolutionContext context) {
            if (conflict.localEntry() instanceof FileEntry sourceFile && 
                conflict.remoteEntry() instanceof FileEntry targetFile) {
                return sourceFile.getSize() >= targetFile.getSize() ? 
                    conflict.localEntry() : conflict.remoteEntry();
            }
            return SOURCE_WINS.resolve(conflict, context);
        }
    },
    
    /**
     * Keep the version with the smallest file size.
     */
    SMALLEST_WINS {
        @Override
        public EntryMetadata resolve(Conflict conflict, ResolutionContext context) {
            if (conflict.localEntry() instanceof FileEntry sourceFile && 
                conflict.remoteEntry() instanceof FileEntry targetFile) {
                return sourceFile.getSize() <= targetFile.getSize() ? 
                    conflict.localEntry() : conflict.remoteEntry();
            }
            return SOURCE_WINS.resolve(conflict, context);
        }
    },
    
    /**
     * Attempt to merge the versions if possible.
     */
    MERGE {
        @Override
        public EntryMetadata resolve(Conflict conflict, ResolutionContext context) {
            // For now, fall back to latest wins as metadata-level merge is complex
            // A full merge would require reading file contents and performing line-based merge
            return LATEST_WINS.resolve(conflict, context);
        }
    },
    
    /**
     * Require manual resolution - return null to indicate unresolved.
     */
    MANUAL {
        @Override
        public EntryMetadata resolve(Conflict conflict, ResolutionContext context) {
            return null; // Indicates manual resolution required
        }
    },
    
    /**
     * Create a backup of both versions with different names.
     */
    BACKUP_BOTH {
        @Override
        public EntryMetadata resolve(Conflict conflict, ResolutionContext context) {
            // This strategy requires special handling - return source
            // and let the caller handle backup creation
            return conflict.localEntry();
        }
    };
    
    public abstract EntryMetadata resolve(Conflict conflict, ResolutionContext context);
    
    /**
     * Checks if this strategy can automatically resolve the conflict.
     */
    public boolean canAutoResolve(Conflict conflict) {
        return this != MANUAL;
    }
    
    /**
     * Gets the priority of this strategy (higher = preferred).
     */
    public int getPriority() {
        return switch (this) {
            case MERGE -> 100;
            case LATEST_WINS -> 80;
            case LARGEST_WINS -> 60;
            case SMALLEST_WINS -> 50;
            case SOURCE_WINS -> 40;
            case TARGET_WINS -> 30;
            case BACKUP_BOTH -> 20;
            case MANUAL -> 10;
        };
    }
}
