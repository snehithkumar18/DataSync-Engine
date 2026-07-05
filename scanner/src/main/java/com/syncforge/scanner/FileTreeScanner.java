package com.syncforge.scanner;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.core.exceptions.SyncForgeException;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.metadata.DirectoryEntry;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.FileHash;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.FileTimestamp;
import com.syncforge.metadata.SymlinkEntry;
import com.syncforge.path.PathSafetyValidator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A highly parallelized file tree scanner that walks directory hierarchies
 * recursively in parallel using Java's {@link ForkJoinPool}.
 *
 * <p>Features:
 * <ul>
 *   <li>Throttles concurrency based on CPU cores or custom configuration limits.</li>
 *   <li>Resolves physical files to concrete metadata subclasses ({@link FileEntry}, {@link DirectoryEntry}, {@link SymlinkEntry}).</li>
 *   <li>Detects symbolic link loops and traversal cycles using a thread-safe canonical path tracking set.</li>
 *   <li>Applies inclusion and exclusion filters defined via {@link ScannerFilters}.</li>
 *   <li>Validates path safety for security (directory traversal, illegal characters) via {@link PathSafetyValidator}.</li>
 *   <li>Produces a deterministically sorted, immutable {@link ScanResult}.</li>
 * </ul>
 * </p>
 */
public class FileTreeScanner {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(FileTreeScanner.class);

    private final int maxParallelism;
    private final boolean followSymlinks;
    private final String hashAlgorithm;

    /**
     * Constructs a {@code FileTreeScanner} with default settings:
     * CPU-core-based parallelism, SHA-256 hash algorithm, and followSymlinks set to false.
     */
    public FileTreeScanner() {
        this(Runtime.getRuntime().availableProcessors(), false, "SHA-256");
    }

    /**
     * Constructs a {@code FileTreeScanner} with the specified settings.
     *
     * @param maxParallelism the maximum number of threads to use. Must be at least 1.
     * @param followSymlinks whether to follow symbolic links to directories.
     * @param hashAlgorithm  the hash algorithm to use ("SHA-256" or "CRC32"). Must not be null.
     * @throws ValidationException if maxParallelism is less than 1, or hashAlgorithm is unsupported.
     */
    public FileTreeScanner(int maxParallelism, boolean followSymlinks, String hashAlgorithm) {
        if (maxParallelism < 1) {
            throw new ValidationException("Parallelism limit must be at least 1: " + maxParallelism);
        }
        Objects.requireNonNull(hashAlgorithm, "Hash algorithm must not be null");
        String algo = hashAlgorithm.toUpperCase();
        if (!algo.equals("SHA-256") && !algo.equals("SHA256") && !algo.equals("CRC32")) {
            throw new ValidationException("Unsupported hash algorithm: " + hashAlgorithm);
        }

        this.maxParallelism = maxParallelism;
        this.followSymlinks = followSymlinks;
        this.hashAlgorithm = algo.equals("SHA256") ? "SHA-256" : algo;

        LOGGER.info("FileTreeScanner initialized: maxParallelism=%d, followSymlinks=%b, hashAlgorithm=%s",
            maxParallelism, followSymlinks, this.hashAlgorithm);
    }

    /**
     * Creates a builder to configure and construct a {@link FileTreeScanner}.
     *
     * @return a new {@link Builder}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Walks the given directory tree recursively in parallel, filtering entries,
     * computing file hashes, detecting symlink loops, and returning a {@link ScanResult}.
     *
     * @param rootDir the root directory to scan. Must not be null, and must exist and be a directory.
     * @param filters the scanner filters for including/excluding paths. Must not be null.
     * @return a deterministically sorted {@link ScanResult}.
     * @throws ValidationException if rootDir is null, does not exist, or is not a directory.
     * @throws SyncForgeException  if a general scanning exception occurs.
     */
    public ScanResult scan(Path rootDir, ScannerFilters filters) {
        Objects.requireNonNull(rootDir, "Root directory must not be null");
        Objects.requireNonNull(filters, "Filters must not be null");

        if (!Files.exists(rootDir)) {
            throw new ValidationException("Root directory does not exist: " + rootDir);
        }
        if (!Files.isDirectory(rootDir)) {
            throw new ValidationException("Root path is not a directory: " + rootDir);
        }

        long startTime = System.currentTimeMillis();
        LOGGER.info("Starting file tree scan under root directory: %s", rootDir);

        Set<String> visitedCanonicalPaths = ConcurrentHashMap.newKeySet();
        ConcurrentLinkedQueue<ScanResult.SkippedPath> skippedPaths = new ConcurrentLinkedQueue<>();

        AtomicLong totalFileSize = new AtomicLong(0);
        AtomicInteger fileCount = new AtomicInteger(0);
        AtomicInteger directoryCount = new AtomicInteger(0);
        AtomicInteger symlinkCount = new AtomicInteger(0);

        List<EntryMetadata> entries;

        ForkJoinPool pool = null;
        try {
            pool = new ForkJoinPool(maxParallelism);
            ScanDirectoryTask rootTask = new ScanDirectoryTask(
                rootDir.toAbsolutePath().normalize(),
                rootDir.toAbsolutePath().normalize(),
                filters,
                hashAlgorithm,
                followSymlinks,
                visitedCanonicalPaths,
                skippedPaths,
                totalFileSize,
                fileCount,
                directoryCount,
                symlinkCount
            );
            entries = pool.submit(rootTask).join();
        } catch (Exception e) {
            LOGGER.error(e, "Parallel scan execution failed under directory: %s", rootDir);
            throw new SyncForgeException("Parallel scan failed for: " + rootDir, e);
        } finally {
            if (pool != null) {
                pool.shutdown();
                try {
                    if (!pool.awaitTermination(30, java.util.concurrent.TimeUnit.SECONDS)) {
                        pool.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    pool.shutdownNow();
                    Thread.currentThread().interrupt();
                }
            }
        }

        long duration = System.currentTimeMillis() - startTime;

        return new ScanResult(
            entries,
            totalFileSize.get(),
            fileCount.get(),
            directoryCount.get(),
            symlinkCount.get(),
            duration,
            new ArrayList<>(skippedPaths)
        );
    }

    /**
     * Static compatibility scan method matching the original scanner API.
     * Walks the tree and returns the sorted list of scanned metadata entries.
     *
     * @param rootDir       the root directory. Must not be null.
     * @param filters       the filter configuration. Must not be null.
     * @param hashAlgorithm the hash algorithm to use. Must not be null.
     * @return the sorted list of metadata entries.
     * @throws SyncForgeException if any exception occurs.
     */
    public static List<EntryMetadata> scan(Path rootDir, ScannerFilters filters, String hashAlgorithm) {
        FileTreeScanner scanner = new FileTreeScanner(Runtime.getRuntime().availableProcessors(), false, hashAlgorithm);
        ScanResult result = scanner.scan(rootDir, filters);
        return result.getEntries();
    }

    /**
     * Helper to compute relative paths with forward slashes.
     */
    private static String getRelativePath(Path root, Path target) {
        if (root.equals(target)) {
            return ".";
        }
        String rel = root.toAbsolutePath().normalize().relativize(target.toAbsolutePath().normalize()).toString();
        return EntryMetadata.normalizePath(rel);
    }

    /**
     * Builder class for {@link FileTreeScanner}.
     */
    public static final class Builder {
        private int maxParallelism = Runtime.getRuntime().availableProcessors();
        private boolean followSymlinks = false;
        private String hashAlgorithm = "SHA-256";

        private Builder() {}

        /**
         * Sets the maximum parallelism level.
         *
         * @param maxParallelism maximum threads. Must be at least 1.
         * @return this builder.
         * @throws ValidationException if maxParallelism is less than 1.
         */
        public Builder maxParallelism(int maxParallelism) {
            if (maxParallelism < 1) {
                throw new ValidationException("Parallelism limit must be at least 1: " + maxParallelism);
            }
            this.maxParallelism = maxParallelism;
            return this;
        }

        /**
         * Configures whether to follow symbolic links to directories.
         *
         * @param followSymlinks true to follow symlinks.
         * @return this builder.
         */
        public Builder followSymlinks(boolean followSymlinks) {
            this.followSymlinks = followSymlinks;
            return this;
        }

        /**
         * Sets the hash algorithm ("SHA-256" or "CRC32").
         *
         * @param hashAlgorithm the hash algorithm name. Must not be null.
         * @return this builder.
         */
        public Builder hashAlgorithm(String hashAlgorithm) {
            this.hashAlgorithm = Objects.requireNonNull(hashAlgorithm, "Hash algorithm must not be null");
            return this;
        }

        /**
         * Builds a {@link FileTreeScanner} instance.
         *
         * @return a configured {@link FileTreeScanner}.
         */
        public FileTreeScanner build() {
            return new FileTreeScanner(maxParallelism, followSymlinks, hashAlgorithm);
        }
    }

    /**
     * Inner class implementing directory processing as a recursive task.
     */
    private static class ScanDirectoryTask extends RecursiveTask<List<EntryMetadata>> {
        private final Path dir;
        private final Path rootDir;
        private final ScannerFilters filters;
        private final String hashAlgorithm;
        private final boolean followSymlinks;
        private final Set<String> visitedCanonicalPaths;
        private final ConcurrentLinkedQueue<ScanResult.SkippedPath> skippedPaths;
        private final AtomicLong totalFileSize;
        private final AtomicInteger fileCount;
        private final AtomicInteger directoryCount;
        private final AtomicInteger symlinkCount;

        public ScanDirectoryTask(
                Path dir,
                Path rootDir,
                ScannerFilters filters,
                String hashAlgorithm,
                boolean followSymlinks,
                Set<String> visitedCanonicalPaths,
                ConcurrentLinkedQueue<ScanResult.SkippedPath> skippedPaths,
                AtomicLong totalFileSize,
                AtomicInteger fileCount,
                AtomicInteger directoryCount,
                AtomicInteger symlinkCount) {
            this.dir = dir;
            this.rootDir = rootDir;
            this.filters = filters;
            this.hashAlgorithm = hashAlgorithm;
            this.followSymlinks = followSymlinks;
            this.visitedCanonicalPaths = visitedCanonicalPaths;
            this.skippedPaths = skippedPaths;
            this.totalFileSize = totalFileSize;
            this.fileCount = fileCount;
            this.directoryCount = directoryCount;
            this.symlinkCount = symlinkCount;
        }

        @Override
        protected List<EntryMetadata> compute() {
            List<EntryMetadata> localEntries = new ArrayList<>();

            // 1. Resolve canonical path to detect cycles
            String canonicalPath;
            try {
                canonicalPath = dir.toRealPath().toString();
            } catch (IOException e) {
                canonicalPath = dir.toAbsolutePath().normalize().toString();
            }

            if (!visitedCanonicalPaths.add(canonicalPath)) {
                LOGGER.warn("Traversal cycle or duplicate visit detected at: %s", dir);
                skippedPaths.add(new ScanResult.SkippedPath(
                    getRelativePath(rootDir, dir),
                    "TRAVERSAL_CYCLE",
                    "Directory canonical path already visited: " + canonicalPath
                ));
                return Collections.emptyList();
            }

            // 2. Resolve relative path & validate safety
            String relPath = getRelativePath(rootDir, dir);
            if (!dir.equals(rootDir)) {
                try {
                    PathSafetyValidator.validate(relPath);
                } catch (ValidationException e) {
                    LOGGER.warn("Safety validation failed for path: %s", relPath);
                    skippedPaths.add(new ScanResult.SkippedPath(relPath, "UNSAFE_PATH", e.getMessage()));
                    return Collections.emptyList();
                }

                // Check if directory is excluded
                boolean isExcluded = false;
                for (com.syncforge.path.GlobMatcher ex : filters.getExcludes()) {
                    if (ex.matches(relPath)) {
                        isExcluded = true;
                        break;
                    }
                }
                if (isExcluded) {
                    LOGGER.debug("Directory is excluded: %s", relPath);
                    return Collections.emptyList();
                }
            }

            // 3. List immediate children
            List<Path> children = new ArrayList<>();
            List<String> childrenNames = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path child : stream) {
                    children.add(child);
                    Path fileName = child.getFileName();
                    if (fileName != null) {
                        childrenNames.add(fileName.toString());
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e, "Failed to read directory: %s", dir);
                skippedPaths.add(new ScanResult.SkippedPath(relPath, "READ_ERROR", e.getMessage()));
                return Collections.emptyList();
            }

            // 4. Add the directory itself if it should be included
            if (!dir.equals(rootDir) && filters.shouldInclude(relPath)) {
                try {
                    FileTimestamp ts = FileTimestamp.fromPath(dir);
                    FileMode mode = FileMode.fromPath(dir);
                    long size = Files.size(dir);
                    DirectoryEntry dirEntry = new DirectoryEntry(relPath, ts, mode, size, childrenNames);
                    localEntries.add(dirEntry);
                    directoryCount.incrementAndGet();
                } catch (Exception e) {
                    LOGGER.error(e, "Error creating DirectoryEntry for: %s", dir);
                    skippedPaths.add(new ScanResult.SkippedPath(relPath, "METADATA_ERROR", e.getMessage()));
                }
            }

            // 5. Process children (directories in parallel, files/links inline)
            List<ScanDirectoryTask> subTasks = new ArrayList<>();

            for (Path child : children) {
                String childRelPath = getRelativePath(rootDir, child);
                
                // Safety validation of child path
                try {
                    PathSafetyValidator.validate(childRelPath);
                } catch (ValidationException e) {
                    LOGGER.warn("Safety validation failed for child path: %s", childRelPath);
                    skippedPaths.add(new ScanResult.SkippedPath(childRelPath, "UNSAFE_PATH", e.getMessage()));
                    continue;
                }

                // Check for symbolic link
                boolean isSymlink = Files.isSymbolicLink(child);
                boolean isDir = Files.isDirectory(child);

                if (isSymlink) {
                    // Check if we follow symlinks and if target is directory
                    if (followSymlinks) {
                        try {
                            Path target = Files.readSymbolicLink(child);
                            Path resolvedTarget = child.getParent().resolve(target).normalize();
                            if (Files.isDirectory(resolvedTarget)) {
                                // Follow symlink as a directory
                                ScanDirectoryTask task = new ScanDirectoryTask(
                                    child, rootDir, filters, hashAlgorithm, followSymlinks,
                                    visitedCanonicalPaths, skippedPaths, totalFileSize,
                                    fileCount, directoryCount, symlinkCount
                                );
                                task.fork();
                                subTasks.add(task);
                                continue;
                            }
                        } catch (IOException e) {
                            skippedPaths.add(new ScanResult.SkippedPath(childRelPath, "SYMLINK_ERROR", "Failed to resolve symlink target: " + e.getMessage()));
                            continue;
                        }
                    }

                    // Otherwise, treat symlink as an entry
                    if (filters.shouldInclude(childRelPath)) {
                        try {
                            FileTimestamp ts = FileTimestamp.fromPath(child);
                            FileMode mode = FileMode.fromPath(child);
                            long size = Files.size(child);
                            Path target = Files.readSymbolicLink(child);

                            SymlinkEntry symEntry = new SymlinkEntry(childRelPath, ts, mode, size, target.toString());
                            if (symEntry.isCircular(child, 20)) {
                                skippedPaths.add(new ScanResult.SkippedPath(childRelPath, "CIRCULAR_SYMLINK", "Circular symbolic link chain detected"));
                            } else {
                                localEntries.add(symEntry);
                                symlinkCount.incrementAndGet();
                            }
                        } catch (Exception e) {
                            skippedPaths.add(new ScanResult.SkippedPath(childRelPath, "SYMLINK_ERROR", e.getMessage()));
                        }
                    }
                } else if (isDir) {
                    // Regular directory - walk in parallel
                    ScanDirectoryTask task = new ScanDirectoryTask(
                        child, rootDir, filters, hashAlgorithm, followSymlinks,
                        visitedCanonicalPaths, skippedPaths, totalFileSize,
                        fileCount, directoryCount, symlinkCount
                    );
                    task.fork();
                    subTasks.add(task);
                } else {
                    // Regular file - process inline
                    if (filters.shouldInclude(childRelPath)) {
                        try {
                            FileTimestamp ts = FileTimestamp.fromPath(child);
                            FileMode mode = FileMode.fromPath(child);
                            long size = Files.size(child);
                            FileHash hash = computeHash(child, hashAlgorithm);
                            Map<String, String> xattrs = readExtendedAttributes(child);

                            FileEntry fileEntry = new FileEntry(childRelPath, ts, mode, size, hash, xattrs, Collections.emptyList());
                            localEntries.add(fileEntry);
                            fileCount.incrementAndGet();
                            totalFileSize.addAndGet(size);
                        } catch (Exception e) {
                            skippedPaths.add(new ScanResult.SkippedPath(childRelPath, "FILE_ERROR", e.getMessage()));
                        }
                    }
                }
            }

            // 6. Merge subtask results
            for (ScanDirectoryTask subTask : subTasks) {
                localEntries.addAll(subTask.join());
            }

            return localEntries;
        }

        private static FileHash computeHash(Path path, String algorithm) throws IOException {
            try (InputStream is = Files.newInputStream(path)) {
                if (algorithm.equals("CRC32")) {
                    long crc = ChecksumHasher.computeCRC32(is);
                    return FileHash.crc32(Long.toHexString(crc));
                } else {
                    String sha = ChecksumHasher.computeSHA256(is);
                    return FileHash.sha256(sha);
                }
            }
        }

        private static Map<String, String> readExtendedAttributes(Path path) {
            Map<String, String> xattrs = new HashMap<>();
            try {
                UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
                if (view != null) {
                    for (String name : view.list()) {
                        ByteBuffer buf = ByteBuffer.allocate(view.size(name));
                        view.read(name, buf);
                        buf.flip();
                        String val = StandardCharsets.UTF_8.decode(buf).toString();
                        xattrs.put(name, val);
                    }
                }
            } catch (UnsupportedOperationException e) {
                // Ignore silently since many filesystems do not support user xattrs
            } catch (Exception e) {
                LOGGER.warn("Failed to retrieve extended attributes for path '%s': %s", path, e.getMessage());
            }
            return xattrs;
        }
    }
}
