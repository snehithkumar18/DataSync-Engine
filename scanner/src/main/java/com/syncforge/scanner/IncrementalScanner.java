package com.syncforge.scanner;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Incremental scanner that only scans changed files based on previous snapshot.
 * Reduces scanning time by skipping unchanged files and directories.
 */
public class IncrementalScanner {
    
    private final List<EntryMetadata> previousEntries;
    private final long modificationThreshold;
    
    public IncrementalScanner(List<EntryMetadata> previousEntries) {
        this(previousEntries, 1000); // 1 second threshold
    }
    
    public IncrementalScanner(List<EntryMetadata> previousEntries, long modificationThreshold) {
        this.previousEntries = previousEntries != null ? previousEntries : new ArrayList<>();
        this.modificationThreshold = modificationThreshold;
    }
    
    /**
     * Performs an incremental scan, returning only changed entries.
     */
    public ScanResult scanIncremental(Path rootPath) throws IOException {
        if (rootPath == null || !Files.exists(rootPath)) {
            throw new IOException("Root path does not exist: " + rootPath);
        }
        
        long startTime = System.currentTimeMillis();
        
        // Build a map for efficient lookup
        Map<String, EntryMetadata> previousMap = new HashMap<>();
        for (EntryMetadata entry : previousEntries) {
            previousMap.put(entry.getNormalizedPath(), entry);
        }
        
        List<EntryMetadata> changedEntries = new ArrayList<>();
        AtomicInteger fileCount = new AtomicInteger(0);
        AtomicInteger directoryCount = new AtomicInteger(0);
        AtomicLong totalSize = new AtomicLong(0);
        List<ScanResult.SkippedPath> skippedPaths = new ArrayList<>();
        
        // Walk the file tree and detect changes
        Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                String relativePath = rootPath.relativize(file).toString();
                
                if (isFileChanged(file, attrs, relativePath, previousMap)) {
                    EntryMetadata metadata = createFileEntry(file, attrs);
                    changedEntries.add(metadata);
                    fileCount.incrementAndGet();
                    totalSize.addAndGet(attrs.size());
                }
                
                return FileVisitResult.CONTINUE;
            }
            
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                String relativePath = rootPath.relativize(dir).toString();
                
                if (isDirectoryChanged(dir, attrs, relativePath, previousMap)) {
                    EntryMetadata metadata = createDirectoryEntry(dir, attrs);
                    changedEntries.add(metadata);
                    directoryCount.incrementAndGet();
                }
                
                return FileVisitResult.CONTINUE;
            }
        });
        
        // Detect deleted files
        detectDeletedFiles(rootPath, previousMap, changedEntries, skippedPaths);
        
        long duration = System.currentTimeMillis() - startTime;
        
        return new ScanResult(changedEntries, totalSize.get(), fileCount.get(), directoryCount.get(), 0, duration, skippedPaths);
    }
    
    private boolean isFileChanged(Path file, BasicFileAttributes attrs, 
                                  String relativePath, Map<String, EntryMetadata> previousMap) {
        EntryMetadata previous = previousMap.get(relativePath);
        
        // New file
        if (previous == null) {
            return true;
        }
        
        // Check if previous was not a file
        if (!(previous instanceof FileEntry)) {
            return true;
        }
        
        FileEntry previousFile = (FileEntry) previous;
        
        // Check size change
        if (previousFile.getSize() != attrs.size()) {
            return true;
        }
        
        // Check modification time
        long modTimeDiff = Math.abs(attrs.lastModifiedTime().toMillis() - 
                                     previousFile.getTimestamp().mtimeMillis());
        if (modTimeDiff > modificationThreshold) {
            return true;
        }
        
        // If size and time are the same, assume unchanged
        return false;
    }
    
    private boolean isDirectoryChanged(Path dir, BasicFileAttributes attrs,
                                       String relativePath, Map<String, EntryMetadata> previousMap) {
        EntryMetadata previous = previousMap.get(relativePath);
        
        // New directory
        if (previous == null) {
            return true;
        }
        
        // Check modification time
        if (previous != null) {
            long modTimeDiff = Math.abs(attrs.lastModifiedTime().toMillis() - 
                                         previous.getTimestamp().mtimeMillis());
            if (modTimeDiff > modificationThreshold) {
                return true;
            }
        }
        
        return false;
    }
    
    private void detectDeletedFiles(Path rootPath, Map<String, EntryMetadata> previousMap,
                                   List<EntryMetadata> changedEntries, List<ScanResult.SkippedPath> skippedPaths) {
        for (Map.Entry<String, EntryMetadata> entry : previousMap.entrySet()) {
            String relativePath = entry.getKey();
            Path fullPath = rootPath.resolve(relativePath);
            if (!Files.exists(fullPath)) {
                // File was deleted - add to skipped paths for now
                // In a real implementation, we would mark this as deleted
                skippedPaths.add(new ScanResult.SkippedPath(relativePath, "DELETED", "File no longer exists"));
            }
        }
    }
    
    private FileEntry createFileEntry(Path path, BasicFileAttributes attrs) {
        // Since FileEntry is immutable, use the constructor
        return new FileEntry(
            path.toString(),
            com.syncforge.metadata.FileTimestamp.fromEpochMilli(attrs.lastModifiedTime().toMillis(), attrs.creationTime().toMillis(), attrs.lastAccessTime().toMillis()),
            com.syncforge.metadata.FileMode.builder().type(com.syncforge.metadata.FileMode.Type.FILE).permissions(0644).build(),
            attrs.size(),
            new com.syncforge.metadata.FileHash("sha256", ""),
            null,
            null
        );
    }
    
    private com.syncforge.metadata.DirectoryEntry createDirectoryEntry(Path path, BasicFileAttributes attrs) {
        // Since DirectoryEntry is immutable, use the constructor
        return new com.syncforge.metadata.DirectoryEntry(
            path.toString(),
            com.syncforge.metadata.FileTimestamp.fromEpochMilli(attrs.lastModifiedTime().toMillis(), attrs.creationTime().toMillis(), attrs.lastAccessTime().toMillis()),
            com.syncforge.metadata.FileMode.builder().type(com.syncforge.metadata.FileMode.Type.DIRECTORY).permissions(0755).build(),
            attrs.size(),
            null
        );
    }
    
    /**
     * Gets the previous entries.
     */
    public List<EntryMetadata> getPreviousEntries() {
        return new ArrayList<>(previousEntries);
    }
    
    /**
     * Gets the modification threshold in milliseconds.
     */
    public long getModificationThreshold() {
        return modificationThreshold;
    }
    
    /**
     * Builder for creating incremental scanners.
     */
    public static class Builder {
        private List<EntryMetadata> previousEntries;
        private long modificationThreshold = 1000;
        
        public Builder withPreviousEntries(List<EntryMetadata> previousEntries) {
            this.previousEntries = previousEntries;
            return this;
        }
        
        public Builder withModificationThreshold(long modificationThreshold) {
            this.modificationThreshold = modificationThreshold;
            return this;
        }
        
        public IncrementalScanner build() {
            return new IncrementalScanner(previousEntries, modificationThreshold);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
