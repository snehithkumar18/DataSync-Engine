package com.syncforge.validation;

import com.syncforge.core.diagnostics.Diagnostic;
import com.syncforge.core.diagnostics.DiagnosticReporter;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.snapshot.SnapshotModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Comprehensive validator for snapshot data.
 * Validates structural integrity, checksum consistency, and metadata validity.
 */
public class SnapshotValidator {
    
    private final DiagnosticReporter reporter;
    
    public SnapshotValidator() {
        this.reporter = new DiagnosticReporter();
    }
    
    /**
     * Validates a snapshot model comprehensively.
     */
    public void validate(SnapshotModel snapshot) throws ValidationException {
        if (snapshot == null) {
            throw new ValidationException("Snapshot cannot be null");
        }
        
        validateHeader(snapshot);
        validateEntries(snapshot);
        validateChecksums(snapshot);
        validateConsistency(snapshot);
        
        if (reporter.hasErrors()) {
            List<Diagnostic> errors = reporter.getDiagnostics(Diagnostic.Severity.ERROR);
            throw new ValidationException("Snapshot validation failed with " + errors.size() + " error(s)", errors);
        }
    }
    
    private void validateHeader(SnapshotModel snapshot) {
        // Validate version
        if (snapshot.getVersion() <= 0) {
            reporter.error("SNAPSHOT_VERSION_INVALID", "Snapshot version must be positive", null);
        }
        
        if (snapshot.getVersion() > 65535) {
            reporter.error("SNAPSHOT_VERSION_TOO_LARGE", "Snapshot version exceeds maximum value of 65535", null);
        }
        
        // Note: SnapshotModel does not currently have timestamp, rootPath, or checksumAlgorithm fields
        // These validations are commented out until those fields are added to the model
        /*
        // Validate timestamp
        if (snapshot.getTimestamp() <= 0) {
            reporter.error("SNAPSHOT_TIMESTAMP_INVALID", "Snapshot timestamp must be positive", null);
        }
        
        // Validate root path
        if (snapshot.getRootPath() == null || snapshot.getRootPath().isEmpty()) {
            reporter.error("SNAPSHOT_ROOT_EMPTY", "Snapshot root path cannot be empty", null);
        }
        
        // Validate checksum algorithm
        if (snapshot.getChecksumAlgorithm() == null || snapshot.getChecksumAlgorithm().isEmpty()) {
            reporter.error("SNAPSHOT_CHECKSUM_ALGORITHM_EMPTY", "Snapshot checksum algorithm cannot be empty", null);
        }
        */
    }
    
    private void validateEntries(SnapshotModel snapshot) {
        List<?> entries = snapshot.getEntries();
        
        if (entries == null) {
            reporter.error("SNAPSHOT_ENTRIES_NULL", "Snapshot entries list cannot be null", null);
            return;
        }
        
        // Check for duplicate paths (case-insensitive on Windows)
        Set<String> normalizedPaths = new java.util.HashSet<>();
        for (Object entryObj : entries) {
            if (!(entryObj instanceof com.syncforge.metadata.EntryMetadata entry)) {
                reporter.error("SNAPSHOT_ENTRY_INVALID_TYPE", "Snapshot entry is not of type EntryMetadata", null);
                continue;
            }
            
            String path = entry.getNormalizedPath();
            if (path == null || path.isEmpty()) {
                reporter.error("SNAPSHOT_ENTRY_PATH_EMPTY", "Snapshot entry has empty path", null);
                continue;
            }
            
            // Check for path traversal
            if (path.contains("..") || path.startsWith("/")) {
                reporter.error("SNAPSHOT_ENTRY_PATH_INVALID", 
                    "Snapshot entry path contains invalid characters: " + path, null);
            }
            
            // Check for path separator consistency
            if (path.contains("\\") && path.contains("/")) {
                reporter.warn("SNAPSHOT_ENTRY_MIXED_SEPARATORS", 
                    "Snapshot entry path mixes path separators: " + path, null);
            }
            
            // Check for extremely long paths
            if (path.length() > 4096) {
                reporter.error("SNAPSHOT_ENTRY_PATH_TOO_LONG", 
                    "Snapshot entry path exceeds maximum length of 4096 characters", null);
            }
            
            // Check for duplicate paths (case-insensitive)
            String normalized = path.toLowerCase();
            if (normalizedPaths.contains(normalized)) {
                reporter.error("SNAPSHOT_ENTRY_DUPLICATE", 
                    "Snapshot has duplicate entry (case-insensitive): " + path, null);
            }
            normalizedPaths.add(normalized);
        }
        
        // Check for empty snapshot
        if (entries.isEmpty()) {
            reporter.warn("SNAPSHOT_EMPTY", "Snapshot contains no entries", null);
        }
    }
    
    private void validateChecksums(SnapshotModel snapshot) {
        List<?> entries = snapshot.getEntries();
        if (entries == null) {
            return;
        }
        
        // Validate checksum format for file entries
        for (Object entryObj : entries) {
            if (!(entryObj instanceof com.syncforge.metadata.EntryMetadata entry)) {
                continue;
            }
            
            if (entry instanceof com.syncforge.metadata.FileEntry fileEntry) {
                com.syncforge.metadata.FileHash hash = fileEntry.getContentHash();
                if (hash != null) {
                    String checksum = hash.value();
                    if (checksum == null || checksum.isEmpty()) {
                        reporter.error("SNAPSHOT_ENTRY_CHECKSUM_EMPTY", 
                            "File entry has empty checksum: " + entry.getNormalizedPath(), null);
                    } else if (!checksum.matches("^[0-9a-fA-F]+$")) {
                        reporter.error("SNAPSHOT_ENTRY_CHECKSUM_INVALID", 
                            "File entry has invalid checksum format: " + entry.getNormalizedPath(), null);
                    }
                }
            }
        }
    }
    
    private void validateConsistency(SnapshotModel snapshot) {
        // Note: SnapshotModel does not currently have getTotalSize() or getFileCount() methods
        // These validations are commented out until those methods are added to the model
        /*
        List<?> entries = snapshot.getEntries();
        if (entries == null) {
            return;
        }
        
        // Validate total size consistency
        long calculatedSize = 0;
        for (Object entryObj : entries) {
            if (entryObj instanceof com.syncforge.metadata.EntryMetadata entry) {
                calculatedSize += entry.getSize();
            }
        }
        
        if (snapshot.getTotalSize() >= 0) {
            if (calculatedSize != snapshot.getTotalSize()) {
                reporter.error("SNAPSHOT_SIZE_MISMATCH", 
                    "Snapshot total size (" + snapshot.getTotalSize() + 
                    ") does not match calculated size from entries (" + calculatedSize + ")", null);
            }
        }
        
        // Validate file count
        if (snapshot.getFileCount() >= 0) {
            if (entries.size() != snapshot.getFileCount()) {
                reporter.error("SNAPSHOT_COUNT_MISMATCH", 
                    "Snapshot file count (" + snapshot.getFileCount() + 
                    ") does not match actual entry count (" + entries.size() + ")", null);
            }
        }
        */
    }
    
    /**
     * Gets all diagnostics from validation.
     */
    public List<Diagnostic> getDiagnostics() {
        return reporter.getDiagnostics();
    }
    
    /**
     * Gets only error diagnostics.
     */
    public List<Diagnostic> getErrors() {
        return reporter.getDiagnostics(Diagnostic.Severity.ERROR);
    }
    
    /**
     * Gets only warning diagnostics.
     */
    public List<Diagnostic> getWarnings() {
        return reporter.getDiagnostics(Diagnostic.Severity.WARNING);
    }
}
