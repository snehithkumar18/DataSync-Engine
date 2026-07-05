package com.syncforge.diff;

import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.FileHash;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.FileTimestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for RenameDetector.
 */
@DisplayName("RenameDetector Tests")
class RenameDetectorTest {
    
    @Test
    @DisplayName("Detect rename with same size and checksum")
    void testDetectRenameWithSameSizeAndChecksum() {
        FileEntry oldFile = createFileEntry("old.txt", 100, "abc123");
        FileEntry newFile = createFileEntry("new.txt", 100, "abc123");
        
        DiffEntry deleteEntry = new DiffEntry("old.txt", DiffEntry.Type.DELETE, oldFile, null);
        DiffEntry addEntry = new DiffEntry("new.txt", DiffEntry.Type.ADD, null, newFile);
        
        RenameDetector detector = new RenameDetector(80);
        List<DiffEntry> result = detector.detectRenames(List.of(deleteEntry, addEntry));
        
        assertEquals(1, result.size());
        assertEquals(DiffEntry.Type.RENAME, result.get(0).getChangeType());
    }
    
    @Test
    @DisplayName("Do not detect rename with different checksum")
    void testNoRenameWithDifferentChecksum() {
        FileEntry oldFile = createFileEntry("old.txt", 100, "abc123");
        FileEntry newFile = createFileEntry("new.txt", 100, "xyz789");
        
        DiffEntry deleteEntry = new DiffEntry("old.txt", DiffEntry.Type.DELETE, oldFile, null);
        DiffEntry addEntry = new DiffEntry("new.txt", DiffEntry.Type.ADD, null, newFile);
        
        RenameDetector detector = new RenameDetector(80);
        List<DiffEntry> result = detector.detectRenames(List.of(deleteEntry, addEntry));
        
        assertEquals(2, result.size());
    }
    
    @Test
    @DisplayName("Detect rename with similar names")
    void testDetectRenameWithSimilarNames() {
        FileEntry oldFile = createFileEntry("file_v1.txt", 100, "abc123");
        FileEntry newFile = createFileEntry("file_v2.txt", 100, "abc123");
        
        DiffEntry deleteEntry = new DiffEntry("file_v1.txt", DiffEntry.Type.DELETE, oldFile, null);
        DiffEntry addEntry = new DiffEntry("file_v2.txt", DiffEntry.Type.ADD, null, newFile);
        
        RenameDetector detector = new RenameDetector(50);
        List<DiffEntry> result = detector.detectRenames(List.of(deleteEntry, addEntry));
        
        assertEquals(1, result.size());
        assertEquals(DiffEntry.Type.RENAME, result.get(0).getChangeType());
    }
    
    @Test
    @DisplayName("Handle empty diff list")
    void testHandleEmptyDiffList() {
        RenameDetector detector = new RenameDetector(80);
        List<DiffEntry> result = detector.detectRenames(List.of());
        
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Respect threshold parameter")
    void testRespectThreshold() {
        FileEntry oldFile = createFileEntry("old.txt", 100, "abc123");
        FileEntry newFile = createFileEntry("new.txt", 100, "abc123");
        
        DiffEntry deleteEntry = new DiffEntry("old.txt", DiffEntry.Type.DELETE, oldFile, null);
        DiffEntry addEntry = new DiffEntry("new.txt", DiffEntry.Type.ADD, null, newFile);
        
        // High threshold - should not detect rename due to dissimilar names
        RenameDetector detector = new RenameDetector(100);
        List<DiffEntry> result = detector.detectRenames(List.of(deleteEntry, addEntry));
        
        assertEquals(2, result.size());
    }
    
    private FileEntry createFileEntry(String path, int size, String hash) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash fileHash = FileHash.sha256(hash);
        return new FileEntry(path, ts, mode, size, fileHash, java.util.Map.of(), java.util.List.of());
    }
}
