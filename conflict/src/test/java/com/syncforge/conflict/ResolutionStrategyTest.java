package com.syncforge.conflict;

import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.FileHash;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.FileTimestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ResolutionStrategy.
 */
@DisplayName("ResolutionStrategy Tests")
class ResolutionStrategyTest {
    
    @Test
    @DisplayName("Source wins strategy")
    void testSourceWinsStrategy() {
        FileEntry source = createFileEntry("file.txt", "source-hash");
        FileEntry target = createFileEntry("file.txt", "target-hash");
        
        ResolutionStrategy strategy = ResolutionStrategy.SOURCE_WINS;
        FileEntry resolved = strategy.resolve(source, target);
        
        assertEquals("source-hash", resolved.getHash().getHashValue());
    }
    
    @Test
    @DisplayName("Target wins strategy")
    void testTargetWinsStrategy() {
        FileEntry source = createFileEntry("file.txt", "source-hash");
        FileEntry target = createFileEntry("file.txt", "target-hash");
        
        ResolutionStrategy strategy = ResolutionStrategy.TARGET_WINS;
        FileEntry resolved = strategy.resolve(source, target);
        
        assertEquals("target-hash", resolved.getHash().getHashValue());
    }
    
    @Test
    @DisplayName("Latest wins strategy with newer source")
    void testLatestWinsWithNewerSource() {
        FileTimestamp sourceTs = FileTimestamp.fromEpochMilli(2000, 2000, 2000);
        FileTimestamp targetTs = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        
        FileEntry source = createFileEntry("file.txt", sourceTs, "source-hash");
        FileEntry target = createFileEntry("file.txt", targetTs, "target-hash");
        
        ResolutionStrategy strategy = ResolutionStrategy.LATEST_WINS;
        FileEntry resolved = strategy.resolve(source, target);
        
        assertEquals("source-hash", resolved.getHash().getHashValue());
    }
    
    @Test
    @DisplayName("Latest wins strategy with newer target")
    void testLatestWinsWithNewerTarget() {
        FileTimestamp sourceTs = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileTimestamp targetTs = FileTimestamp.fromEpochMilli(2000, 2000, 2000);
        
        FileEntry source = createFileEntry("file.txt", sourceTs, "source-hash");
        FileEntry target = createFileEntry("file.txt", targetTs, "target-hash");
        
        ResolutionStrategy strategy = ResolutionStrategy.LATEST_WINS;
        FileEntry resolved = strategy.resolve(source, target);
        
        assertEquals("target-hash", resolved.getHash().getHashValue());
    }
    
    @Test
    @DisplayName("Merge strategy with identical content")
    void testMergeStrategyWithIdenticalContent() {
        FileEntry source = createFileEntry("file.txt", "same-hash");
        FileEntry target = createFileEntry("file.txt", "same-hash");
        
        ResolutionStrategy strategy = ResolutionStrategy.MERGE;
        FileEntry resolved = strategy.resolve(source, target);
        
        assertEquals("same-hash", resolved.getHash().getHashValue());
    }
    
    @Test
    @DisplayName("Backup strategy creates backup")
    void testBackupStrategy() {
        FileEntry source = createFileEntry("file.txt", "source-hash");
        FileEntry target = createFileEntry("file.txt", "target-hash");
        
        ResolutionStrategy strategy = ResolutionStrategy.BACKUP;
        FileEntry resolved = strategy.resolve(source, target);
        
        // Backup strategy should preserve target and create backup
        assertEquals("target-hash", resolved.getHash().getHashValue());
    }
    
    @Test
    @DisplayName("Manual strategy requires user intervention")
    void testManualStrategy() {
        FileEntry source = createFileEntry("file.txt", "source-hash");
        FileEntry target = createFileEntry("file.txt", "target-hash");
        
        ResolutionStrategy strategy = ResolutionStrategy.MANUAL;
        
        assertThrows(UnsupportedOperationException.class, () -> {
            strategy.resolve(source, target);
        });
    }
    
    private FileEntry createFileEntry(String path, String hash) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        return createFileEntry(path, ts, hash);
    }
    
    private FileEntry createFileEntry(String path, FileTimestamp ts, String hash) {
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash fileHash = FileHash.sha256(hash);
        return new FileEntry(path, ts, mode, 0, fileHash, java.util.Map.of(), java.util.List.of());
    }
}
