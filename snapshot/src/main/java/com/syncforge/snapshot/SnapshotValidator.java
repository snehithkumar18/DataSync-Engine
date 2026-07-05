package com.syncforge.snapshot;

import com.syncforge.checksum.ChecksumHasher;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.FileHash;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Validates snapshot integrity and consistency.
 * Verifies checksums, structural integrity, and data consistency.
 */
public class SnapshotValidator {
    
    private final SnapshotModel snapshot;
    private final ChecksumHasher checksumHasher;
    
    public SnapshotValidator(SnapshotModel snapshot) {
        this.snapshot = snapshot;
        this.checksumHasher = new ChecksumHasher();
    }
    
    /**
     * Validates the snapshot structure.
     */
    public ValidationResult validateStructure() {
        ValidationResult result = new ValidationResult();
        
        // Check version
        if (snapshot.getVersion() <= 0) {
            result.addError("Invalid snapshot version: " + snapshot.getVersion());
        }
        
        // Check entries
        if (snapshot.getEntries() == null) {
            result.addError("Snapshot entries list is null");
        } else if (snapshot.getEntries().isEmpty()) {
            result.addWarning("Snapshot contains no entries");
        }
        
        return result;
    }
    
    /**
     * Validates all file checksums against actual files.
     */
    public ValidationResult validateChecksums(Path rootPath) {
        ValidationResult result = new ValidationResult();
        
        if (rootPath == null || !Files.exists(rootPath)) {
            result.addError("Root path does not exist: " + rootPath);
            return result;
        }
        
        int validatedCount = 0;
        int failedCount = 0;
        
        for (EntryMetadata entry : snapshot.getEntries()) {
            if (entry instanceof FileEntry fileEntry) {
                Path filePath = rootPath.resolve(entry.getNormalizedPath());
                
                if (!Files.exists(filePath)) {
                    result.addError("File does not exist: " + filePath);
                    failedCount++;
                    continue;
                }
                
                try {
                    String actualHash = ChecksumHasher.computeSHA256(Files.newInputStream(filePath));
                    FileHash expectedHash = fileEntry.getContentHash();
                    
                    if (expectedHash == null) {
                        result.addWarning("No checksum stored for: " + filePath);
                        continue;
                    }
                    
                    String expectedHashStr = expectedHash.value();
                    if (!actualHash.equals(expectedHashStr)) {
                        result.addError("Checksum mismatch for: " + filePath);
                        failedCount++;
                    } else {
                        validatedCount++;
                    }
                    
                } catch (IOException e) {
                    result.addError("Error reading file for checksum validation: " + filePath + 
                                  " - " + e.getMessage());
                    failedCount++;
                }
            }
        }
        
        result.addInfo("Validated " + validatedCount + " checksums");
        if (failedCount > 0) {
            result.addError("Failed to validate " + failedCount + " checksums");
        }
        
        return result;
    }
    
    /**
     * Validates path consistency within the snapshot.
     */
    public ValidationResult validatePaths() {
        ValidationResult result = new ValidationResult();
        
        for (EntryMetadata entry : snapshot.getEntries()) {
            String path = entry.getNormalizedPath();
            
            // Check for path traversal
            if (path.contains("..")) {
                result.addError("Path contains parent directory reference: " + path);
            }
            
            // Check for absolute paths
            if (path.startsWith("/") || (path.length() > 1 && path.charAt(1) == ':')) {
                result.addWarning("Path is absolute: " + path);
            }
            
            // Check for mixed separators
            if (path.contains("\\") && path.contains("/")) {
                result.addWarning("Path mixes separators: " + path);
            }
            
            // Check for extremely long paths
            if (path.length() > 4096) {
                result.addError("Path exceeds maximum length: " + path);
            }
        }
        
        // Check for duplicate paths (case-insensitive)
        Map<String, String> normalizedPaths = new HashMap<>();
        for (EntryMetadata entry : snapshot.getEntries()) {
            String path = entry.getNormalizedPath();
            String normalized = path.toLowerCase();
            if (normalizedPaths.containsKey(normalized)) {
                result.addError("Duplicate path (case-insensitive): " + path + 
                              " and " + normalizedPaths.get(normalized));
            }
            normalizedPaths.put(normalized, path);
        }
        
        return result;
    }
    
    /**
     * Validates metadata consistency.
     */
    public ValidationResult validateMetadata() {
        ValidationResult result = new ValidationResult();
        
        for (EntryMetadata entry : snapshot.getEntries()) {
            // Check for null path
            if (entry.getNormalizedPath() == null || entry.getNormalizedPath().isEmpty()) {
                result.addError("Entry has null or empty path");
            }
            
            // Check for negative sizes
            if (entry instanceof FileEntry fileEntry) {
                if (fileEntry.getSize() < 0) {
                    result.addError("File has negative size: " + fileEntry.getNormalizedPath());
                }
            }
            
            // Check for invalid timestamps
            if (entry.getTimestamp() != null && entry.getTimestamp().mtimeMillis() < 0) {
                result.addError("Entry has invalid timestamp: " + entry.getNormalizedPath());
            }
            
            // Check for invalid file modes
            if (entry.getMode() != null) {
                int mode = entry.getMode().getPosixPermissions();
                if (mode < 0 || mode > 07777) {
                    result.addError("Entry has invalid file mode: " + entry.getNormalizedPath());
                }
            }
        }
        
        return result;
    }
    
    /**
     * Performs a complete validation of the snapshot.
     */
    public ValidationResult validateComplete(Path rootPath) {
        ValidationResult result = new ValidationResult();
        
        result.merge(validateStructure());
        result.merge(validatePaths());
        result.merge(validateMetadata());
        
        if (rootPath != null) {
            result.merge(validateChecksums(rootPath));
        }
        
        return result;
    }
    
    /**
     * Validation result container.
     */
    public static class ValidationResult {
        private final List<String> errors = new ArrayList<>();
        private final List<String> warnings = new ArrayList<>();
        private final List<String> info = new ArrayList<>();
        
        public void addError(String message) {
            errors.add(message);
        }
        
        public void addWarning(String message) {
            warnings.add(message);
        }
        
        public void addInfo(String message) {
            info.add(message);
        }
        
        public void merge(ValidationResult other) {
            errors.addAll(other.errors);
            warnings.addAll(other.warnings);
            info.addAll(other.info);
        }
        
        public boolean isValid() {
            return errors.isEmpty();
        }
        
        public boolean hasWarnings() {
            return !warnings.isEmpty();
        }
        
        public List<String> getErrors() {
            return new ArrayList<>(errors);
        }
        
        public List<String> getWarnings() {
            return new ArrayList<>(warnings);
        }
        
        public List<String> getInfo() {
            return new ArrayList<>(info);
        }
        
        public String getSummary() {
            StringBuilder summary = new StringBuilder();
            summary.append("Validation Result: ");
            summary.append(isValid() ? "VALID" : "INVALID");
            summary.append("\n");
            summary.append("Errors: ").append(errors.size());
            summary.append("\n");
            summary.append("Warnings: ").append(warnings.size());
            summary.append("\n");
            
            if (!errors.isEmpty()) {
                summary.append("\nErrors:\n");
                for (String error : errors) {
                    summary.append("  - ").append(error).append("\n");
                }
            }
            
            if (!warnings.isEmpty()) {
                summary.append("\nWarnings:\n");
                for (String warning : warnings) {
                    summary.append("  - ").append(warning).append("\n");
                }
            }
            
            return summary.toString();
        }
    }
}
