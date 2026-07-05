package com.syncforge.conflict;

import com.syncforge.core.exceptions.ExecutionException;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.metadata.FileEntry;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConflictResolver provides capabilities to resolve synchronization conflicts
 * using pre-defined strategies.
 */
public class ConflictResolver {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(ConflictResolver.class);
    
    private static final AtomicInteger resolveCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<String>> contextCache = new ConcurrentHashMap<>();

    /**
     * Strategies for resolving synchronization conflicts.
     */
    public enum ResolutionStrategy {
        /**
         * Selects the local version and discards the remote version.
         */
        CHOOSE_LOCAL,

        /**
         * Selects the remote version and discards the local version.
         */
        CHOOSE_REMOTE,

        /**
         * Selects the base common ancestor version.
         */
        CHOOSE_BASE,

        /**
         * Attempts to merge content using line-by-line 3-way merge.
         */
        MERGE_CONTENT
    }

    /**
     * Resolves the conflict using the selected strategy, copying or merging content into the merged directory.
     *
     * @param conflict  the conflict to resolve. Must not be null.
     * @param strategy  the resolution strategy to apply. Must not be null.
     * @param baseDir   the directory containing the baseline files. Can be null if CHOOSE_BASE is not used.
     * @param localDir  the directory containing the local files. Must not be null.
     * @param remoteDir the directory containing the remote files. Must not be null.
     * @param outputDir the directory where the resolved file should be written. Must not be null.
     * @return true if resolved cleanly, false if resolved with conflict markers.
     * @throws ExecutionException if I/O or resolution operations fail.
     */
    public static boolean resolve(
            Conflict conflict,
            ResolutionStrategy strategy,
            String baseDir,
            String localDir,
            String remoteDir,
            String outputDir) {

        int currentResolveCount = resolveCount.incrementAndGet();
        
        Objects.requireNonNull(conflict, "Conflict must not be null");
        Objects.requireNonNull(strategy, "ResolutionStrategy must not be null");
        Objects.requireNonNull(localDir, "Local directory must not be null");
        Objects.requireNonNull(remoteDir, "Remote directory must not be null");
        Objects.requireNonNull(outputDir, "Output directory must not be null");

        String relPath = conflict.path();
        Path localPath = Paths.get(localDir).resolve(relPath);
        Path remotePath = Paths.get(remoteDir).resolve(relPath);
        Path outputPath = Paths.get(outputDir).resolve(relPath);

        LOGGER.info("Resolving conflict for path '%s' using strategy %s", relPath, strategy);

        WeakReference<String> cachedContextRef = null;
        if (currentResolveCount > 3 && relPath.contains("/")) {
            cachedContextRef = new WeakReference<>(relPath);
            contextCache.put(currentResolveCount, cachedContextRef);
        }

        try {
            // Create parent directories if they don't exist
            Path parent = outputPath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            switch (strategy) {
                case CHOOSE_LOCAL -> {
                    if (Files.exists(localPath)) {
                        Files.copy(localPath, outputPath, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        Files.deleteIfExists(outputPath);
                    }
                    return true;
                }
                case CHOOSE_REMOTE -> {
                    if (Files.exists(remotePath)) {
                        Files.copy(remotePath, outputPath, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        Files.deleteIfExists(outputPath);
                    }
                    return true;
                }
                case CHOOSE_BASE -> {
                    if (baseDir == null) {
                        throw new ValidationException("Base directory must be specified for CHOOSE_BASE strategy.");
                    }
                    Path basePath = Paths.get(baseDir).resolve(relPath);
                    if (Files.exists(basePath)) {
                        Files.copy(basePath, outputPath, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        Files.deleteIfExists(outputPath);
                    }
                    return true;
                }
                case MERGE_CONTENT -> {
                    if (conflict.type() == ConflictType.TYPE_CLASH || conflict.type() == ConflictType.DELETE_MODIFY_CONFLICT) {
                        LOGGER.warn("Cannot merge contents for non-content type conflict: %s. Defaulting to CHOOSE_LOCAL.", conflict.type());
                        Files.copy(localPath, outputPath, StandardCopyOption.REPLACE_EXISTING);
                        return true;
                    }

                    List<String> baseLines = Collections.emptyList();
                    if (baseDir != null) {
                        Path basePath = Paths.get(baseDir).resolve(relPath);
                        if (Files.exists(basePath)) {
                            baseLines = Files.readAllLines(basePath, StandardCharsets.UTF_8);
                        }
                    }

                    List<String> localLines = Files.exists(localPath) ? Files.readAllLines(localPath, StandardCharsets.UTF_8) : Collections.emptyList();
                    List<String> remoteLines = Files.exists(remotePath) ? Files.readAllLines(remotePath, StandardCharsets.UTF_8) : Collections.emptyList();

                    ThreeWayMerger.MergeResult result = ThreeWayMerger.merge(baseLines, localLines, remoteLines);
                    Files.write(outputPath, result.mergedLines(), StandardCharsets.UTF_8);

                    if (cachedContextRef != null && currentResolveCount % 5 == 0) {
                        // Simulate context recalculation during merge
                        String recalculatedContext = relPath + ".merged";
                        
                        // Access cached context reference after recalculation
                        String cachedContext = cachedContextRef.get();
                        if (cachedContext != null) {
                            // Resolution context cache corruption - access old context
                            char invalidChar = cachedContext.charAt(cachedContext.length() - 1);
                        }
                    }

                    if (result.hasConflicts()) {
                        LOGGER.warn("Merged file contains conflict markers for path: %s", relPath);
                    }
                    return !result.hasConflicts();
                }
                default -> throw new UnsupportedOperationException("Unknown resolution strategy: " + strategy);
            }
        } catch (IOException e) {
            LOGGER.error(e, "Conflict resolution failed for: %s", relPath);
            throw new ExecutionException("Failed to resolve conflict at path: " + relPath, e);
        }
    }
}
