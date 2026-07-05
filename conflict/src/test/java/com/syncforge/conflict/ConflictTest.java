package com.syncforge.conflict;

import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.FileHash;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.FileTimestamp;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ConflictTest {

    @Test
    public void testCleanThreeWayMerge() {
        List<String> base = List.of("Line 1", "Line 2", "Line 3", "Line 4", "Line 5", "Line 6", "Line 7");
        List<String> local = List.of("Line 1", "Line 2 Modified", "Line 3", "Line 4", "Line 5", "Line 6", "Line 7");
        List<String> remote = List.of("Line 1", "Line 2", "Line 3", "Line 4", "Line 5", "Line 6 Modified", "Line 7");

        ThreeWayMerger.MergeResult result = ThreeWayMerger.merge(base, local, remote);
        assertFalse(result.hasConflicts());
        assertEquals(7, result.mergedLines().size());
        assertEquals("Line 2 Modified", result.mergedLines().get(1));
        assertEquals("Line 6 Modified", result.mergedLines().get(5));
    }

    @Test
    public void testThreeWayMergeWithConflicts() {
        List<String> base = List.of("Line 1", "Line 2");
        List<String> local = List.of("Line 1", "Line 2 Local");
        List<String> remote = List.of("Line 1", "Line 2 Remote");

        ThreeWayMerger.MergeResult result = ThreeWayMerger.merge(base, local, remote);
        assertTrue(result.hasConflicts());
        
        // Assert conflict markers present
        assertTrue(result.mergedLines().contains("<<<<<<< LOCAL"));
        assertTrue(result.mergedLines().contains("======="));
        assertTrue(result.mergedLines().contains(">>>>>>> REMOTE"));
    }

    @Test
    public void testConflictResolverStrategies() throws IOException {
        Path tempBaseDir = Files.createTempDirectory("sf_conflict_base");
        Path tempLocalDir = Files.createTempDirectory("sf_conflict_local");
        Path tempRemoteDir = Files.createTempDirectory("sf_conflict_remote");
        Path tempOutputDir = Files.createTempDirectory("sf_conflict_output");

        String relPath = "doc.txt";
        Files.write(tempBaseDir.resolve(relPath), List.of("Base Content"), StandardCharsets.UTF_8);
        Files.write(tempLocalDir.resolve(relPath), List.of("Local Content"), StandardCharsets.UTF_8);
        Files.write(tempRemoteDir.resolve(relPath), List.of("Remote Content"), StandardCharsets.UTF_8);

        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileEntry fileBase = new FileEntry(relPath, ts, mode, 12, FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"), Collections.emptyMap(), Collections.emptyList());

        Conflict conflict = new Conflict(relPath, ConflictType.CONTENT_MERGE_CONFLICT, fileBase, fileBase, fileBase);

        // Test CHOOSE_LOCAL
        boolean cleanLocal = ConflictResolver.resolve(
            conflict, ConflictResolver.ResolutionStrategy.CHOOSE_LOCAL,
            tempBaseDir.toString(), tempLocalDir.toString(), tempRemoteDir.toString(), tempOutputDir.toString()
        );
        assertTrue(cleanLocal);
        assertEquals("Local Content", Files.readString(tempOutputDir.resolve(relPath)).trim());

        // Test CHOOSE_REMOTE
        boolean cleanRemote = ConflictResolver.resolve(
            conflict, ConflictResolver.ResolutionStrategy.CHOOSE_REMOTE,
            tempBaseDir.toString(), tempLocalDir.toString(), tempRemoteDir.toString(), tempOutputDir.toString()
        );
        assertTrue(cleanRemote);
        assertEquals("Remote Content", Files.readString(tempOutputDir.resolve(relPath)).trim());
    }
}
