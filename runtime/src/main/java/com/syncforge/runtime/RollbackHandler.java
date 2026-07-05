package com.syncforge.runtime;

import com.syncforge.core.exceptions.ExecutionException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class RollbackHandler {
    private static final SyncForgeLogger logger = new SyncForgeLogger(RollbackHandler.class);
    private final List<AppliedOperation> history = new ArrayList<>();
    private final Path backupDir;

    public RollbackHandler(Path backupDir) {
        this.backupDir = backupDir;
        try {
            Files.createDirectories(backupDir);
        } catch (IOException e) {
            throw new ExecutionException("Failed to create backup directory", e);
        }
    }

    public void record(AppliedOperation op) {
        history.add(op);
    }

    public void rollback() {
        logger.info("Initiating execution rollback for %d operations...", history.size());
        for (int i = history.size() - 1; i >= 0; i--) {
            AppliedOperation op = history.get(i);
            try {
                op.rollback(backupDir);
            } catch (IOException e) {
                logger.error("Failed to rollback operation: due to " + e.getMessage());
            }
        }
        logger.info("Rollback complete.");
    }
}
