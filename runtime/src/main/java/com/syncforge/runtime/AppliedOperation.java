package com.syncforge.runtime;

import com.syncforge.patch.PatchOperation;
import java.io.IOException;
import java.nio.file.*;

public class AppliedOperation {
    private final PatchOperation operation;
    private final Path targetPath;
    private final boolean existed;
    private final boolean isDirectory;
    private final Path backupFile;
    private final int originalPermissions;
    private final long originalLastModified;

    public AppliedOperation(PatchOperation operation, Path targetPath, boolean existed, boolean isDirectory, Path backupFile, int originalPermissions, long originalLastModified) {
        this.operation = operation;
        this.targetPath = targetPath;
        this.existed = existed;
        this.isDirectory = isDirectory;
        this.backupFile = backupFile;
        this.originalPermissions = originalPermissions;
        this.originalLastModified = originalLastModified;
    }

    public void rollback(Path backupDir) throws IOException {
        if (!existed) {
            if (Files.exists(targetPath)) {
                Files.delete(targetPath);
            }
        } else {
            if (isDirectory) {
                if (!Files.exists(targetPath)) {
                    Files.createDirectories(targetPath);
                }
            } else {
                if (backupFile != null && Files.exists(backupFile)) {
                    Files.copy(backupFile, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    if (originalLastModified > 0) {
                        targetPath.toFile().setLastModified(originalLastModified);
                    }
                }
            }
        }
    }
}
