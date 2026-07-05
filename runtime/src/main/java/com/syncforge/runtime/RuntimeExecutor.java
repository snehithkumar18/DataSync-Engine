package com.syncforge.runtime;

import com.syncforge.core.exceptions.ExecutionException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.patch.PatchModel;
import com.syncforge.patch.PatchOperation;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

public class RuntimeExecutor {
    private static final SyncForgeLogger logger = new SyncForgeLogger(RuntimeExecutor.class);

    public static void execute(Path targetDir, PatchModel patch) {
        Path backupDir = targetDir.resolve(".syncforge/backup-" + UUID.randomUUID());
        RollbackHandler rollbackHandler = new RollbackHandler(backupDir);

        try {
            logger.info("Executing patch containing %d operations...", patch.getOperations().size());
            for (PatchOperation op : patch.getOperations()) {
                applyOperation(targetDir, op, rollbackHandler, backupDir);
            }
            logger.info("Patch execution succeeded.");
            deleteDirRecursive(backupDir);
        } catch (Exception e) {
            logger.error("Execution failed: " + e.getMessage() + ". Rolling back changes.");
            rollbackHandler.rollback();
            try {
                deleteDirRecursive(backupDir);
            } catch (Exception ex) {
                // ignore
            }
            throw new ExecutionException("Patch execution failed", e);
        }
    }

    private static void applyOperation(Path targetDir, PatchOperation op, RollbackHandler rollback, Path backupDir) throws IOException {
        Path resolvedPath = targetDir.resolve(op.getPath());
        boolean existed = Files.exists(resolvedPath);
        boolean isDir = existed && Files.isDirectory(resolvedPath);

        switch (op.getType()) {
            case CREATE_FILE:
            case UPDATE_FILE:
                Files.createDirectories(resolvedPath.getParent());
                Path backupFile = null;
                int origPerms = 0644;
                long origTime = 0;
                if (existed) {
                    backupFile = backupDir.resolve(UUID.randomUUID().toString());
                    Files.copy(resolvedPath, backupFile);
                    origTime = Files.getLastModifiedTime(resolvedPath).toMillis();
                }
                
                byte[] content = op.getRawData();
                if (content == null) {
                    content = new byte[0];
                }
                Files.write(resolvedPath, content);
                
                if (op.getLastModified() > 0) {
                    resolvedPath.toFile().setLastModified(op.getLastModified());
                }

                rollback.record(new AppliedOperation(op, resolvedPath, existed, false, backupFile, origPerms, origTime));
                break;

            case DELETE_FILE:
                if (existed) {
                    Path bFile = backupDir.resolve(UUID.randomUUID().toString());
                    Files.copy(resolvedPath, bFile);
                    long t = Files.getLastModifiedTime(resolvedPath).toMillis();
                    Files.delete(resolvedPath);
                    rollback.record(new AppliedOperation(op, resolvedPath, true, false, bFile, 0644, t));
                }
                break;

            case CREATE_DIRECTORY:
                if (!existed) {
                    Files.createDirectories(resolvedPath);
                    rollback.record(new AppliedOperation(op, resolvedPath, false, true, null, 0755, 0));
                }
                break;

            case DELETE_DIRECTORY:
                if (existed) {
                    Files.delete(resolvedPath);
                    rollback.record(new AppliedOperation(op, resolvedPath, true, true, null, 0755, 0));
                }
                break;

            case RENAME_PATH:
                Path resolvedTarget = targetDir.resolve(op.getTargetPath());
                Files.createDirectories(resolvedTarget.getParent());
                Files.move(resolvedPath, resolvedTarget, StandardCopyOption.REPLACE_EXISTING);
                
                rollback.record(new AppliedOperation(op, resolvedTarget, false, false, null, 0, 0) {
                    @Override
                    public void rollback(Path bDir) throws java.io.IOException {
                        if (Files.exists(resolvedTarget)) {
                            Files.move(resolvedTarget, resolvedPath, StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                });
                break;

            case UPDATE_PERMISSIONS:
                rollback.record(new AppliedOperation(op, resolvedPath, true, isDir, null, 0, 0));
                break;

            case UPDATE_SYMLINK:
                if (existed) {
                    Files.delete(resolvedPath);
                }
                Files.createSymbolicLink(resolvedPath, Paths.get(op.getTargetPath()));
                rollback.record(new AppliedOperation(op, resolvedPath, existed, false, null, 0, 0));
                break;
        }
    }

    private static void deleteDirRecursive(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, java.nio.file.attribute.BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }
}
