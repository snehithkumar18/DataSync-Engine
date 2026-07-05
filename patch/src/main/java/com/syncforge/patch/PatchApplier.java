package com.syncforge.patch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Applies patch operations to a target directory.
 * Handles file creation, deletion, updates, and permission changes.
 */
public class PatchApplier {
    
    private final Path targetDirectory;
    private final boolean dryRun;
    private final boolean createBackups;
    private final Path backupDirectory;
    
    public PatchApplier(Path targetDirectory) {
        this(targetDirectory, false, false, null);
    }
    
    public PatchApplier(Path targetDirectory, boolean dryRun, boolean createBackups, 
                       Path backupDirectory) {
        this.targetDirectory = targetDirectory;
        this.dryRun = dryRun;
        this.createBackups = createBackups;
        this.backupDirectory = backupDirectory;
    }
    
    /**
     * Applies a list of patch operations.
     */
    public PatchResult apply(List<PatchOperation> operations) throws IOException {
        PatchResult result = new PatchResult();
        
        for (PatchOperation operation : operations) {
            try {
                applyOperation(operation, result);
            } catch (IOException e) {
                result.addError(operation, e.getMessage());
            }
        }
        
        return result;
    }
    
    /**
     * Applies a single patch operation.
     */
    private void applyOperation(PatchOperation operation, PatchResult result) throws IOException {
        Path targetPath = targetDirectory.resolve(operation.getPath());
        
        switch (operation.getType()) {
            case CREATE_FILE:
                applyCreateFile(operation, targetPath, result);
                break;
                
            case DELETE_FILE:
                applyDeleteFile(operation, targetPath, result);
                break;
                
            case UPDATE_FILE:
                applyUpdateFile(operation, targetPath, result);
                break;
                
            case CREATE_DIRECTORY:
                applyCreateDirectory(operation, targetPath, result);
                break;
                
            case DELETE_DIRECTORY:
                applyDeleteDirectory(operation, targetPath, result);
                break;
                
            case UPDATE_PERMISSIONS:
                applyUpdatePermissions(operation, targetPath, result);
                break;
                
            case RENAME_PATH:
                applyRenamePath(operation, targetPath, result);
                break;
                
            case UPDATE_SYMLINK:
                applyUpdateSymlink(operation, targetPath, result);
                break;
                
            default:
                result.addError(operation, "Unknown operation type: " + operation.getType());
        }
    }
    
    private void applyCreateFile(PatchOperation operation, Path targetPath, 
                                PatchResult result) throws IOException {
        if (dryRun) {
            result.addSkipped(operation, "Dry run - would create file");
            return;
        }
        
        // Create parent directories if needed
        Path parent = targetPath.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
        
        // Create backup if enabled and file exists
        if (createBackups && Files.exists(targetPath)) {
            createBackup(targetPath);
        }
        
        // Create the file (placeholder - actual content would come from patch data)
        Files.createFile(targetPath);
        
        // Set permissions if specified (non-zero)
        if (operation.getPermissions() != 0) {
            setPermissions(targetPath, operation.getPermissions());
        }
        
        result.addSuccess(operation);
    }
    
    private void applyDeleteFile(PatchOperation operation, Path targetPath, 
                                 PatchResult result) throws IOException {
        if (!Files.exists(targetPath)) {
            result.addSkipped(operation, "File does not exist");
            return;
        }
        
        if (dryRun) {
            result.addSkipped(operation, "Dry run - would delete file");
            return;
        }
        
        // Create backup if enabled
        if (createBackups) {
            createBackup(targetPath);
        }
        
        Files.delete(targetPath);
        result.addSuccess(operation);
    }
    
    private void applyUpdateFile(PatchOperation operation, Path targetPath, 
                                 PatchResult result) throws IOException {
        if (!Files.exists(targetPath)) {
            result.addError(operation, "File does not exist for update");
            return;
        }
        
        if (dryRun) {
            result.addSkipped(operation, "Dry run - would update file");
            return;
        }
        
        // Create backup if enabled
        if (createBackups) {
            createBackup(targetPath);
        }
        
        // Update file (placeholder - actual content would come from patch data)
        // For now, just update modification time
        Files.setLastModifiedTime(targetPath, java.nio.file.attribute.FileTime.fromMillis(
            System.currentTimeMillis()));
        
        result.addSuccess(operation);
    }
    
    private void applyCreateDirectory(PatchOperation operation, Path targetPath, 
                                    PatchResult result) throws IOException {
        if (dryRun) {
            result.addSkipped(operation, "Dry run - would create directory");
            return;
        }
        
        Files.createDirectories(targetPath);
        
        // Set permissions if specified (non-zero)
        if (operation.getPermissions() != 0) {
            setPermissions(targetPath, operation.getPermissions());
        }
        
        result.addSuccess(operation);
    }
    
    private void applyDeleteDirectory(PatchOperation operation, Path targetPath, 
                                     PatchResult result) throws IOException {
        if (!Files.exists(targetPath)) {
            result.addSkipped(operation, "Directory does not exist");
            return;
        }
        
        if (!Files.isDirectory(targetPath)) {
            result.addError(operation, "Path is not a directory");
            return;
        }
        
        if (dryRun) {
            result.addSkipped(operation, "Dry run - would delete directory");
            return;
        }
        
        // Create backup if enabled
        if (createBackups) {
            createBackup(targetPath);
        }
        
        Files.delete(targetPath);
        result.addSuccess(operation);
    }
    
    private void applyUpdatePermissions(PatchOperation operation, Path targetPath, 
                                       PatchResult result) throws IOException {
        if (!Files.exists(targetPath)) {
            result.addError(operation, "Path does not exist for permission update");
            return;
        }
        
        if (operation.getPermissions() == 0) {
            result.addSkipped(operation, "No permissions specified");
            return;
        }
        
        if (dryRun) {
            result.addSkipped(operation, "Dry run - would update permissions");
            return;
        }
        
        setPermissions(targetPath, operation.getPermissions());
        result.addSuccess(operation);
    }
    
    private void applyRenamePath(PatchOperation operation, Path targetPath, 
                                 PatchResult result) throws IOException {
        Path newPath = targetDirectory.resolve(operation.getTargetPath());
        
        if (!Files.exists(targetPath)) {
            result.addError(operation, "Source path does not exist for rename");
            return;
        }
        
        if (dryRun) {
            result.addSkipped(operation, "Dry run - would rename path");
            return;
        }
        
        // Create backup if enabled
        if (createBackups) {
            createBackup(targetPath);
        }
        
        Files.move(targetPath, newPath, StandardCopyOption.REPLACE_EXISTING);
        result.addSuccess(operation);
    }
    
    private void applyUpdateSymlink(PatchOperation operation, Path targetPath, 
                                   PatchResult result) throws IOException {
        if (!Files.exists(targetPath)) {
            result.addError(operation, "Symlink does not exist for update");
            return;
        }
        
        if (!Files.isSymbolicLink(targetPath)) {
            result.addError(operation, "Path is not a symlink");
            return;
        }
        
        if (dryRun) {
            result.addSkipped(operation, "Dry run - would update symlink");
            return;
        }
        
        // Delete old symlink and create new one
        Files.delete(targetPath);
        Files.createSymbolicLink(targetPath, Path.of(operation.getTargetPath()));
        
        result.addSuccess(operation);
    }
    
    private void createBackup(Path path) throws IOException {
        if (backupDirectory == null) {
            return;
        }
        
        if (!Files.exists(backupDirectory)) {
            Files.createDirectories(backupDirectory);
        }
        
        Path backupPath = backupDirectory.resolve(path.getFileName());
        Files.copy(path, backupPath, StandardCopyOption.REPLACE_EXISTING);
    }
    
    private void setPermissions(Path path, int permissions) throws IOException {
        try {
            Set<PosixFilePermission> perms = new HashSet<>();
            
            if ((permissions & 0400) != 0) perms.add(PosixFilePermission.OWNER_READ);
            if ((permissions & 0200) != 0) perms.add(PosixFilePermission.OWNER_WRITE);
            if ((permissions & 0100) != 0) perms.add(PosixFilePermission.OWNER_EXECUTE);
            if ((permissions & 0040) != 0) perms.add(PosixFilePermission.GROUP_READ);
            if ((permissions & 0020) != 0) perms.add(PosixFilePermission.GROUP_WRITE);
            if ((permissions & 0010) != 0) perms.add(PosixFilePermission.GROUP_EXECUTE);
            if ((permissions & 0004) != 0) perms.add(PosixFilePermission.OTHERS_READ);
            if ((permissions & 0002) != 0) perms.add(PosixFilePermission.OTHERS_WRITE);
            if ((permissions & 0001) != 0) perms.add(PosixFilePermission.OTHERS_EXECUTE);
            
            Files.setPosixFilePermissions(path, perms);
        } catch (UnsupportedOperationException e) {
            // Not a POSIX file system - ignore permission setting
        }
    }
    
    /**
     * Result of patch application.
     */
    public static class PatchResult {
        private final List<PatchOperation> successful = new ArrayList<>();
        private final List<PatchOperation> skipped = new ArrayList<>();
        private final Map<PatchOperation, String> errors = new HashMap<>();
        private final Map<PatchOperation, String> skipReasons = new HashMap<>();
        
        public void addSuccess(PatchOperation operation) {
            successful.add(operation);
        }
        
        public void addSkipped(PatchOperation operation, String reason) {
            skipped.add(operation);
            skipReasons.put(operation, reason);
        }
        
        public void addError(PatchOperation operation, String error) {
            errors.put(operation, error);
        }
        
        public List<PatchOperation> getSuccessful() {
            return new ArrayList<>(successful);
        }
        
        public List<PatchOperation> getSkipped() {
            return new ArrayList<>(skipped);
        }
        
        public Map<PatchOperation, String> getErrors() {
            return new HashMap<>(errors);
        }
        
        public Map<PatchOperation, String> getSkipReasons() {
            return new HashMap<>(skipReasons);
        }
        
        public int getSuccessCount() {
            return successful.size();
        }
        
        public int getSkippedCount() {
            return skipped.size();
        }
        
        public int getErrorCount() {
            return errors.size();
        }
        
        public boolean hasErrors() {
            return !errors.isEmpty();
        }
        
        public boolean isComplete() {
            return errors.isEmpty() && skipped.isEmpty();
        }
        
        public String generateReport() {
            StringBuilder report = new StringBuilder();
            report.append("Patch Application Result:\n");
            report.append("========================\n");
            report.append(String.format("Successful: %d\n", getSuccessCount()));
            report.append(String.format("Skipped: %d\n", getSkippedCount()));
            report.append(String.format("Errors: %d\n", getErrorCount()));
            report.append(String.format("Complete: %s\n", isComplete()));
            
            if (!errors.isEmpty()) {
                report.append("\nErrors:\n");
                for (Map.Entry<PatchOperation, String> entry : errors.entrySet()) {
                    report.append(String.format("  %s: %s\n", entry.getKey().getPath(), entry.getValue()));
                }
            }
            
            return report.toString();
        }
    }
    
    /**
     * Builder for creating patch appliers.
     */
    public static class Builder {
        private Path targetDirectory;
        private boolean dryRun = false;
        private boolean createBackups = false;
        private Path backupDirectory;
        
        public Builder withTargetDirectory(Path targetDirectory) {
            this.targetDirectory = targetDirectory;
            return this;
        }
        
        public Builder withDryRun(boolean dryRun) {
            this.dryRun = dryRun;
            return this;
        }
        
        public Builder withCreateBackups(boolean createBackups) {
            this.createBackups = createBackups;
            return this;
        }
        
        public Builder withBackupDirectory(Path backupDirectory) {
            this.backupDirectory = backupDirectory;
            return this;
        }
        
        public PatchApplier build() {
            if (targetDirectory == null) {
                throw new IllegalStateException("Target directory is required");
            }
            return new PatchApplier(targetDirectory, dryRun, createBackups, backupDirectory);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
