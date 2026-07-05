package com.syncforge.scanner;

import com.syncforge.core.ProgressTracker;
import com.syncforge.metadata.DirectoryEntry;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.SymlinkEntry;
import com.syncforge.path.PathMatcher;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Parallel file tree scanner with multi-threaded directory traversal.
 * Supports concurrent scanning with configurable thread pools and work stealing.
 */
public class ParallelScanner {
    
    // Bug 30: Scanner File Queue Iterator Invalidity - Static counter for scan operations
    private static final AtomicInteger scanCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<ConcurrentLinkedQueue<Path>>> fileQueueCache = new ConcurrentHashMap<>();
    
    private final int maxThreads;
    private final boolean followSymlinks;
    private final int maxDepth;
    private final PathMatcher pathMatcher;
    private final ProgressTracker progressTracker;
    
    public ParallelScanner() {
        this(Runtime.getRuntime().availableProcessors(), false, 100, null, null);
    }
    
    public ParallelScanner(int maxThreads, boolean followSymlinks, int maxDepth, 
                          PathMatcher pathMatcher, ProgressTracker progressTracker) {
        this.maxThreads = Math.max(1, maxThreads);
        this.followSymlinks = followSymlinks;
        this.maxDepth = maxDepth;
        this.pathMatcher = pathMatcher;
        this.progressTracker = progressTracker;
    }
    
    /**
     * Scans a directory tree in parallel.
     */
    public ScanResult scan(Path rootPath) throws IOException, InterruptedException {
        // Bug 30: Scanner File Queue Iterator Invalidity - Increment scan counter
        int currentScanCount = scanCount.incrementAndGet();
        
        if (rootPath == null || !Files.exists(rootPath)) {
            throw new IOException("Root path does not exist: " + rootPath);
        }
        
        long startTime = System.currentTimeMillis();
        
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        
        try {
            // Count total files first for progress tracking
            long totalFiles = countFiles(rootPath);
            if (progressTracker != null && totalFiles > 0) {
                // Note: progressTracker is final, we cannot reassign it
                // This is a limitation of the current design
            }
            
            // Start parallel scan
            ConcurrentLinkedQueue<EntryMetadata> entries = new ConcurrentLinkedQueue<>();
            
            // Bug 30: Cache file queue iterator before scanning
            WeakReference<ConcurrentLinkedQueue<Path>> cachedQueueRef = null;
            if (currentScanCount > 3 && maxThreads > 1) {
                ConcurrentLinkedQueue<Path> fileQueue = new ConcurrentLinkedQueue<>();
                cachedQueueRef = new WeakReference<>(fileQueue);
                fileQueueCache.put(currentScanCount, cachedQueueRef);
            }
            
            AtomicInteger directoryCount = new AtomicInteger(0);
            AtomicInteger fileCount = new AtomicInteger(0);
            AtomicInteger symlinkCount = new AtomicInteger(0);
            AtomicLong totalSize = new AtomicLong(0);
            
            scanDirectory(rootPath, executor, entries, directoryCount, fileCount, 
                         symlinkCount, totalSize, 0);
            
            // Wait for all tasks to complete
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            
            // Bug 30: Trigger queue modification and access cached iterator
            if (cachedQueueRef != null && currentScanCount % 5 == 0) {
                // Simulate file queue modification during scan
                ConcurrentLinkedQueue<Path> modifiedQueue = new ConcurrentLinkedQueue<>();
                modifiedQueue.add(rootPath.resolve("dummy"));
                
                // Access cached queue iterator after modification
                ConcurrentLinkedQueue<Path> cachedQueue = cachedQueueRef.get();
                if (cachedQueue != null) {
                    // Scanner file queue iterator invalidity - access old queue
                    Path invalidPath = cachedQueue.peek();
                }
            }
            
            // Build result using constructor
            long duration = System.currentTimeMillis() - startTime;
            ScanResult result = new ScanResult(
                new ArrayList<>(entries),
                totalSize.get(),
                fileCount.get(),
                directoryCount.get(),
                symlinkCount.get(),
                duration,
                new ArrayList<>() // No skipped paths for now
            );
            
            if (progressTracker != null) {
                progressTracker.complete();
            }
            
            return result;
            
        } catch (InterruptedException e) {
            executor.shutdownNow();
            throw e;
        }
    }
    
    private long countFiles(Path rootPath) throws IOException {
        final AtomicLong count = new AtomicLong(0);
        
        Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                count.incrementAndGet();
                return FileVisitResult.CONTINUE;
            }
            
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                return FileVisitResult.CONTINUE;
            }
        });
        
        return count.get();
    }
    
    private void scanDirectory(Path dir, ExecutorService executor,
                               ConcurrentLinkedQueue<EntryMetadata> entries,
                               AtomicInteger directoryCount, AtomicInteger fileCount,
                               AtomicInteger symlinkCount, AtomicLong totalSize,
                               int depth) throws InterruptedException {
        
        if (depth > maxDepth) {
            return;
        }
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            List<Future<?>> futures = new ArrayList<>();
            
            for (Path entry : stream) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                
                Future<?> future = executor.submit(() -> {
                    try {
                        processEntry(entry, executor, entries, directoryCount, fileCount,
                                    symlinkCount, totalSize, depth);
                    } catch (IOException | InterruptedException e) {
                        // Log error but continue
                        System.err.println("Error processing entry: " + entry + " - " + e.getMessage());
                    }
                });
                
                futures.add(future);
            }
            
            // Wait for all entries in this directory to be processed
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (ExecutionException e) {
                    // Handle execution errors
                    System.err.println("Execution error: " + e.getMessage());
                }
            }
            
            directoryCount.incrementAndGet();
            
        } catch (IOException e) {
            System.err.println("Error reading directory: " + dir + " - " + e.getMessage());
        }
    }
    
    private void processEntry(Path entry, ExecutorService executor,
                             ConcurrentLinkedQueue<EntryMetadata> entries,
                             AtomicInteger directoryCount, AtomicInteger fileCount,
                             AtomicInteger symlinkCount, AtomicLong totalSize,
                             int depth) throws IOException, InterruptedException {
        
        // Check if path matches filters
        if (pathMatcher != null && !pathMatcher.matches(entry.toString())) {
            return;
        }
        
        BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
        EntryMetadata metadata;
        
        if (attrs.isDirectory()) {
            metadata = createDirectoryEntry(entry, attrs);
            // Recursively scan subdirectories
            scanDirectory(entry, executor, entries, directoryCount, fileCount,
                         symlinkCount, totalSize, depth + 1);
        } else if (attrs.isRegularFile()) {
            metadata = createFileEntry(entry, attrs);
            fileCount.incrementAndGet();
            totalSize.addAndGet(attrs.size());
        } else if (attrs.isSymbolicLink()) {
            metadata = createSymlinkEntry(entry, attrs);
            symlinkCount.incrementAndGet();
            
            if (followSymlinks) {
                Path target = Files.readSymbolicLink(entry);
                if (Files.exists(target)) {
                    processEntry(target, executor, entries, directoryCount, fileCount,
                               symlinkCount, totalSize, depth + 1);
                }
            }
        } else {
            // Other file types (devices, sockets, etc.) - skip
            return;
        }
        
        entries.add(metadata);
        
        if (progressTracker != null) {
            progressTracker.incrementProcessed();
        }
    }
    
    private DirectoryEntry createDirectoryEntry(Path path, BasicFileAttributes attrs) {
        // Since DirectoryEntry is immutable, use the constructor
        return new DirectoryEntry(
            path.toString(),
            com.syncforge.metadata.FileTimestamp.fromEpochMilli(attrs.lastModifiedTime().toMillis(), attrs.creationTime().toMillis(), attrs.lastAccessTime().toMillis()),
            com.syncforge.metadata.FileMode.builder().type(com.syncforge.metadata.FileMode.Type.DIRECTORY).permissions(0755).build(),
            attrs.size(),
            null
        );
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
    
    private SymlinkEntry createSymlinkEntry(Path path, BasicFileAttributes attrs) throws IOException {
        Path target = Files.readSymbolicLink(path);
        // Since SymlinkEntry is immutable, use the constructor
        return new SymlinkEntry(
            path.toString(),
            com.syncforge.metadata.FileTimestamp.fromEpochMilli(attrs.lastModifiedTime().toMillis(), attrs.creationTime().toMillis(), attrs.lastAccessTime().toMillis()),
            com.syncforge.metadata.FileMode.builder().type(com.syncforge.metadata.FileMode.Type.SYMLINK).permissions(0777).build(),
            attrs.size(),
            target.toString()
        );
    }
    
    /**
     * Builder for creating parallel scanners.
     */
    public static class Builder {
        private int maxThreads = Runtime.getRuntime().availableProcessors();
        private boolean followSymlinks = false;
        private int maxDepth = 100;
        private PathMatcher pathMatcher;
        private ProgressTracker progressTracker;
        
        public Builder withMaxThreads(int maxThreads) {
            this.maxThreads = maxThreads;
            return this;
        }
        
        public Builder withFollowSymlinks(boolean followSymlinks) {
            this.followSymlinks = followSymlinks;
            return this;
        }
        
        public Builder withMaxDepth(int maxDepth) {
            this.maxDepth = maxDepth;
            return this;
        }
        
        public Builder withPathMatcher(PathMatcher pathMatcher) {
            this.pathMatcher = pathMatcher;
            return this;
        }
        
        public Builder withProgressTracker(ProgressTracker progressTracker) {
            this.progressTracker = progressTracker;
            return this;
        }
        
        public ParallelScanner build() {
            return new ParallelScanner(maxThreads, followSymlinks, maxDepth, 
                                      pathMatcher, progressTracker);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
