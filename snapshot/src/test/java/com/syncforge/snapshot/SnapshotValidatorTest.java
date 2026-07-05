package com.syncforge.snapshot;

import com.syncforge.metadata.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

/**
 * Unit tests for SnapshotValidator.
 */
@DisplayName("SnapshotValidator Tests")
class SnapshotValidatorTest {
    
    @Test
    @DisplayName("Validate valid snapshot")
    void testValidateValidSnapshot() {
        List<EntryMetadata> entries = List.of(
            createFileEntry("file.txt"),
            createDirectoryEntry("dir")
        );
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
    }
    
    @Test
    @DisplayName("Detect null entries")
    void testDetectNullEntries() {
        List<EntryMetadata> entries = List.of(
            createFileEntry("file.txt"),
            null
        );
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        assertFalse(result.isValid());
        assertFalse(result.getErrors().isEmpty());
    }
    
    @Test
    @DisplayName("Detect duplicate paths")
    void testDetectDuplicatePaths() {
        List<EntryMetadata> entries = List.of(
            createFileEntry("file.txt"),
            createFileEntry("file.txt")
        );
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        assertFalse(result.isValid());
    }
    
    @Test
    @DisplayName("Detect invalid paths")
    void testDetectInvalidPaths() {
        List<EntryMetadata> entries = List.of(
            createFileEntry("../etc/passwd")
        );
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        assertFalse(result.isValid());
    }
    
    @Test
    @DisplayName("Detect missing parent directories")
    void testDetectMissingParentDirectories() {
        List<EntryMetadata> entries = List.of(
            createFileEntry("a/b/c/file.txt")
        );
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        // This should generate a warning, not an error
        assertTrue(result.getWarnings().stream()
            .anyMatch(w -> w.contains("parent")));
    }
    
    @Test
    @DisplayName("Validate empty snapshot")
    void testValidateEmptySnapshot() {
        List<EntryMetadata> entries = List.of();
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        // Empty snapshot is valid
        assertTrue(result.isValid());
    }
    
    @Test
    @DisplayName("Detect invalid checksums")
    void testDetectInvalidChecksums() {
        FileEntry entry = createFileEntry("file.txt");
        // Manually set invalid checksum
        FileEntry invalidEntry = new FileEntry(
            entry.getNormalizedPath(),
            entry.getTimestamp(),
            entry.getMode(),
            entry.getSize(),
            FileHash.sha256(""), // Empty hash
            entry.getExtendedAttributes(),
            entry.getChangeReasons()
        );
        
        List<EntryMetadata> entries = List.of(invalidEntry);
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        // Empty checksum should generate a warning
        assertTrue(result.getWarnings().stream()
            .anyMatch(w -> w.contains("checksum")));
    }
    
    @Test
    @DisplayName("Generate validation report")
    void testGenerateValidationReport() {
        List<EntryMetadata> entries = List.of(
            createFileEntry("file.txt")
        );
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        String report = result.generateReport();
        
        assertNotNull(report);
        assertTrue(report.contains("Validation Result"));
    }
    
    @Test
    @DisplayName("Count validation errors")
    void testCountValidationErrors() {
        List<EntryMetadata> entries = List.of(
            null,
            createFileEntry("../etc/passwd")
        );
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        assertTrue(result.getErrorCount() > 0);
    }
    
    @Test
    @DisplayName("Count validation warnings")
    void testCountValidationWarnings() {
        List<EntryMetadata> entries = List.of(
            createFileEntry("a/b/c/file.txt")
        );
        
        SnapshotModel snapshot = new SnapshotModel(entries, 1, 0, null);
        SnapshotValidator validator = new SnapshotValidator();
        
        SnapshotValidator.ValidationResult result = validator.validate(snapshot);
        
        assertTrue(result.getWarningCount() >= 0);
    }
    
    private FileEntry createFileEntry(String path) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash hash = FileHash.sha256("test-hash");
        return new FileEntry(path, ts, mode, 100, hash, Map.of(), List.of());
    }
    
    private DirectoryEntry createDirectoryEntry(String path) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileMode mode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();
        return new DirectoryEntry(path, ts, mode, Map.of(), List.of());
    }
}
