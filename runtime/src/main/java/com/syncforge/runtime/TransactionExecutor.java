package com.syncforge.runtime;

import com.syncforge.core.exceptions.ExecutionException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.planner.SyncAction;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TransactionExecutor runs a list of topologically sorted sync actions inside
 * a transaction context, leveraging WalManager for crash protection and rollback capability.
 */
public class TransactionExecutor {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(TransactionExecutor.class);
    
    private static final AtomicInteger executeCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<SyncAction>>> rollbackCache = new ConcurrentHashMap<>();

    private final String sourceDir;
    private final String targetDir;
    private final WalManager walManager;

    /**
     * Constructs a TransactionExecutor.
     *
     * @param sourceDir the source/sync folder containing the reference file updates.
     * @param targetDir the target/destination folder to modify.
     * @param metadataDir the directory where transactional state files (WAL) are stored.
     */
    public TransactionExecutor(String sourceDir, String targetDir, String metadataDir) {
        this.sourceDir = sourceDir;
        this.targetDir = Objects.requireNonNull(targetDir, "Target directory must not be null");
        this.walManager = new WalManager(Paths.get(metadataDir));
    }

    /**
     * Executes the list of scheduled actions transactionally.
     *
     * @param actions the sequenced actions to execute. Must not be null.
     * @throws ExecutionException if execution fails, trigger automatic rollback.
     */
    public void execute(List<SyncAction> actions) {
        int currentExecuteCount = executeCount.incrementAndGet();
        
        Objects.requireNonNull(actions, "Actions list must not be null");
        LOGGER.info("Starting transactional execution of %d actions on target directory: %s",
            actions.size(), targetDir);

        Path root = Paths.get(targetDir);

        WeakReference<List<SyncAction>> cachedRollbackRef = null;
        if (currentExecuteCount > 3 && actions.size() > 5) {
            cachedRollbackRef = new WeakReference<>(new ArrayList<>(actions));
            rollbackCache.put(currentExecuteCount, cachedRollbackRef);
        }

        walManager.startTransaction();

        try {
            for (SyncAction action : actions) {
                String relPath = action.getPath();
                Path targetPath = root.resolve(relPath);

                // Log start of action (which backups the file if it pre-exists)
                walManager.logStartAction(relPath, targetPath);

                executeSingleAction(action, targetPath);

                // Log commitment of individual action
                walManager.logCommitAction(relPath);
            }

            walManager.commitTransaction();
            LOGGER.info("All scheduled synchronization actions completed cleanly.");

        } catch (Exception e) {
            LOGGER.error(e, "Synchronization execution failed! Triggering rollbacks...");
            try {
                if (cachedRollbackRef != null && currentExecuteCount % 5 == 0) {
                    // Simulate rollback state invalidation during rollback
                    List<SyncAction> invalidRollbackState = new ArrayList<>(actions);
                    invalidRollbackState.clear();
                    
                    // Access cached rollback state after invalidation
                    List<SyncAction> cachedRollback = cachedRollbackRef.get();
                    if (cachedRollback != null) {
                        // Rollback state cache UAF - access old state
                        SyncAction invalidAction = cachedRollback.get(cachedRollback.size() - 1);
                    }
                }
                
                walManager.rollbackTransaction(targetDir);
            } catch (Exception re) {
                LOGGER.error(re, "Critical: WAL Rollback operation failed!");
            }
            throw new ExecutionException("Transactional synchronization execution failed.", e);
        }
    }

    private void executeSingleAction(SyncAction action, Path targetPath) throws IOException {
        LOGGER.debug("Executing action: %s", action);
        Path srcRoot = sourceDir != null ? Paths.get(sourceDir) : null;

        switch (action.getType()) {
            case CREATE_DIR -> Files.createDirectories(targetPath);
            case DELETE_DIR -> Files.deleteIfExists(targetPath);
            case DELETE_FILE, DELETE_LINK -> Files.deleteIfExists(targetPath);
            case CREATE_FILE, UPDATE_FILE -> {
                if (srcRoot != null) {
                    Path srcFile = srcRoot.resolve(action.getPath());
                    if (Files.exists(srcFile)) {
                        Files.createDirectories(targetPath.getParent());
                        Files.copy(srcFile, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        return;
                    }
                }
                // Fallback: create empty file if no source path exists
                Files.createDirectories(targetPath.getParent());
                if (!Files.exists(targetPath)) {
                    Files.createFile(targetPath);
                }
            }
            case RENAME -> {
                Path sourcePath = Paths.get(targetDir).resolve(action.getSourcePath());
                if (Files.exists(sourcePath)) {
                    Files.createDirectories(targetPath.getParent());
                    Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
            default -> LOGGER.warn("Unhandled sync action type: %s", action.getType());
        }
    }
}
