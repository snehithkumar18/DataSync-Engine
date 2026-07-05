package com.syncforge.planner;

import com.syncforge.diff.DiffEntry;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.FileTimestamp;
import com.syncforge.metadata.DirectoryEntry;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.FileHash;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest {

    @Test
    public void testLockingHierarchy() {
        PathLockManager lm = new PathLockManager();

        // 1. Acquire read lock on "src"
        assertTrue(lm.acquireReadLock("src"));

        // 2. Attempt write lock on "src/Main.java" -> should fail because of read lock on ancestor
        assertFalse(lm.acquireWriteLock("src/Main.java"));

        // 3. Release read lock on "src"
        lm.releaseReadLock("src");

        // 4. Acquire write lock on "src/Main.java" -> should succeed
        assertTrue(lm.acquireWriteLock("src/Main.java"));

        // 5. Attempt read lock on "src" -> should fail because of write lock on descendant
        assertFalse(lm.acquireReadLock("src"));

        // 6. Release write lock
        lm.releaseWriteLock("src/Main.java");
        assertTrue(lm.acquireReadLock("src"));
    }

    @Test
    public void testDagSchedulingOrder() {
        // We want to create directory "src" and then file "src/App.java"
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        
        DirectoryEntry dir = new DirectoryEntry("src", ts, FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build(), 0, Collections.emptyList());
        FileEntry file = new FileEntry("src/App.java", ts, FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build(), 100, FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"), Collections.emptyMap(), Collections.emptyList());

        DiffEntry diffDir = DiffEntry.add(dir);
        DiffEntry diffFile = DiffEntry.add(file);

        DAGPlanner planner = new DAGPlanner();
        // Give files out of order to verify sorting
        List<SyncAction> plan = planner.plan(List.of(diffFile, diffDir));

        assertEquals(2, plan.size());
        // Parent directory create must come first
        assertEquals(SyncAction.Type.CREATE_DIR, plan.get(0).getType());
        assertEquals("src", plan.get(0).getPath());

        assertEquals(SyncAction.Type.CREATE_FILE, plan.get(1).getType());
        assertEquals("src/App.java", plan.get(1).getPath());
    }
}
