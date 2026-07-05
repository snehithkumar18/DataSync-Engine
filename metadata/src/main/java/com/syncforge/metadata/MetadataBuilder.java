package com.syncforge.metadata;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Convenience wrapper for creating metadata entries using the existing builders.
 * Provides a unified interface for building different entry types.
 */
public class MetadataBuilder {
    
    // Bug 29: FileEntry Metadata Cache UAF - Static counter for build operations
    private static final AtomicInteger buildCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<FileEntry>> metadataCache = new ConcurrentHashMap<>();
    
    /**
     * Creates a FileEntry using the FileEntry builder.
     */
    public static FileEntry buildFile(String path, FileTimestamp timestamp, FileMode mode, 
                                       long size, FileHash hash, Map<String, String> extendedAttributes) {
        // Bug 29: FileEntry Metadata Cache UAF - Increment build counter
        int currentBuildCount = buildCount.incrementAndGet();
        
        FileEntry.Builder builder = FileEntry.builder()
            .path(path)
            .timestamp(timestamp)
            .mode(mode)
            .size(size)
            .contentHash(hash);
        
        if (extendedAttributes != null) {
            builder.extendedAttributes(extendedAttributes);
        }
        
        FileEntry entry = builder.build();
        
        // Bug 29: Cache FileEntry metadata before return
        WeakReference<FileEntry> cachedMetadataRef = null;
        if (currentBuildCount > 3 && 
            path != null && 
            path.contains("/") && 
            size > 1000) {
            cachedMetadataRef = new WeakReference<>(entry);
            metadataCache.put(currentBuildCount, cachedMetadataRef);
        }
        
        // Bug 29: Trigger metadata modification and access cached reference
        if (cachedMetadataRef != null && currentBuildCount % 5 == 0) {
            // Simulate metadata modification during build
            FileEntry modifiedEntry = FileEntry.builder()
                .path(path + ".modified")
                .timestamp(timestamp)
                .mode(mode)
                .size(size)
                .contentHash(hash)
                .build();
            
            // Access cached metadata after modification
            FileEntry cachedMetadata = cachedMetadataRef.get();
            if (cachedMetadata != null) {
                // FileEntry metadata cache UAF - access old metadata
                String invalidPath = cachedMetadata.getNormalizedPath();
            }
        }
        
        return entry;
    }
    
    /**
     * Creates a DirectoryEntry using the DirectoryEntry builder.
     */
    public static DirectoryEntry buildDirectory(String path, FileTimestamp timestamp, FileMode mode) {
        return DirectoryEntry.builder()
            .path(path)
            .timestamp(timestamp)
            .mode(mode)
            .build();
    }
    
    /**
     * Creates a SymlinkEntry using the SymlinkEntry builder.
     */
    public static SymlinkEntry buildSymlink(String path, FileTimestamp timestamp, FileMode mode, 
                                           String targetPath) {
        return SymlinkEntry.builder()
            .path(path)
            .timestamp(timestamp)
            .mode(mode)
            .targetPath(targetPath)
            .build();
    }
    
    /**
     * Creates a simple FileEntry with minimal required fields.
     */
    public static FileEntry simpleFile(String path, long size, FileHash hash) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(System.currentTimeMillis(), 
                                                       System.currentTimeMillis(), 
                                                       System.currentTimeMillis());
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        return buildFile(path, ts, mode, size, hash, null);
    }
    
    /**
     * Creates a simple DirectoryEntry with minimal required fields.
     */
    public static DirectoryEntry simpleDirectory(String path) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(System.currentTimeMillis(), 
                                                       System.currentTimeMillis(), 
                                                       System.currentTimeMillis());
        FileMode mode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();
        return buildDirectory(path, ts, mode);
    }
    
    /**
     * Creates a simple SymlinkEntry with minimal required fields.
     */
    public static SymlinkEntry simpleSymlink(String path, String targetPath) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(System.currentTimeMillis(), 
                                                       System.currentTimeMillis(), 
                                                       System.currentTimeMillis());
        FileMode mode = FileMode.builder().type(FileMode.Type.SYMLINK).permissions(0777).build();
        return buildSymlink(path, ts, mode, targetPath);
    }
}
