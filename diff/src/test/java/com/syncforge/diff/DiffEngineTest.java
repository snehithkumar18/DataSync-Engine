package com.syncforge.diff;

import com.syncforge.metadata.*;
import com.syncforge.snapshot.SnapshotModel;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DiffEngineTest {

    @Test
    public void testBasicDiffOperations() {
        // Prepare base state
        FileTimestamp ts1 = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileMode fileMode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileMode dirMode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();

        FileEntry fileBase = new FileEntry(
            "src/Main.java", ts1, fileMode, 500,
            FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
            Collections.emptyMap(), Collections.emptyList()
        );

        DirectoryEntry dirBase = new DirectoryEntry("src", ts1, dirMode, 0, List.of("Main.java"));

        SnapshotModel baseSnapshot = new SnapshotModel(List.of(fileBase, dirBase), 1, 0, null);

        // Prepare target state:
        // - src/Main.java modified (changed hash, size)
        // - src/Util.java added
        // - old folder src/Extra deleted
        FileTimestamp ts2 = FileTimestamp.fromEpochMilli(2000L, 2000L, 2000L);

        FileEntry fileModified = new FileEntry(
            "src/Main.java", ts2, fileMode, 600,
            FileHash.sha256("8f4356fb3798aa124efb4c8996fb92427ae41e4649b934ca495991b7852b855"),
            Collections.emptyMap(), Collections.emptyList()
        );

        FileEntry fileAdded = new FileEntry(
            "src/Util.java", ts2, fileMode, 200,
            FileHash.sha256("123456fb3798aa124efb4c8996fb92427ae41e4649b934ca495991b7852b855"),
            Collections.emptyMap(), Collections.emptyList()
        );

        SnapshotModel targetSnapshot = new SnapshotModel(List.of(fileModified, dirBase, fileAdded), 1, 0, null);

        DiffEngine engine = new DiffEngine();
        List<DiffEntry> diffs = engine.diff(baseSnapshot, targetSnapshot);

        // We expect:
        // - UNCHANGED for "src"
        // - MODIFY for "src/Main.java"
        // - ADD for "src/Util.java"
        assertEquals(3, diffs.size());

        DiffEntry de0 = diffs.get(0);
        assertEquals("src", de0.getPath());
        assertEquals(DiffEntry.Type.UNCHANGED, de0.getChangeType());

        DiffEntry de1 = diffs.get(1);
        assertEquals("src/Main.java", de1.getPath());
        assertEquals(DiffEntry.Type.MODIFY, de1.getChangeType());

        DiffEntry de2 = diffs.get(2);
        assertEquals("src/Util.java", de2.getPath());
        assertEquals(DiffEntry.Type.ADD, de2.getChangeType());
    }

    @Test
    public void testRenameDetectionByMetadata() {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileMode fileMode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();

        // Baseline: file at src/OldName.java
        FileEntry oldEntry = new FileEntry(
            "src/OldName.java", ts, fileMode, 1000,
            FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
            Collections.emptyMap(), Collections.emptyList()
        );
        SnapshotModel base = new SnapshotModel(List.of(oldEntry), 1, 0, null);

        // Target: file renamed to src/NewName.java, slightly changed size (980) but similar name & size
        FileEntry newEntry = new FileEntry(
            "src/NewName.java", ts, fileMode, 980,
            FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
            Collections.emptyMap(), Collections.emptyList()
        );
        SnapshotModel target = new SnapshotModel(List.of(newEntry), 1, 0, null);

        DiffEngine engine = new DiffEngine(0.60, null, null);
        List<DiffEntry> diffs = engine.diff(base, target);

        assertEquals(1, diffs.size());
        DiffEntry renameEntry = diffs.get(0);
        assertEquals(DiffEntry.Type.RENAME, renameEntry.getChangeType());
        assertEquals("src/NewName.java", renameEntry.getPath());
        assertEquals("src/OldName.java", renameEntry.getRenameSourcePath());
        assertTrue(renameEntry.getSimilarityScore() >= 0.60);
    }
}
