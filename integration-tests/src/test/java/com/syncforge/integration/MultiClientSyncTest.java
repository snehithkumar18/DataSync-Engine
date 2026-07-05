package com.syncforge.integration;

import com.syncforge.conflict.Conflict;
import com.syncforge.conflict.ConflictDetector;
import com.syncforge.conflict.ConflictResolver;
import com.syncforge.diff.DiffEntry;
import com.syncforge.diff.DiffEngine;
import com.syncforge.metadata.FileTimestamp;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.DirectoryEntry;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.planner.DAGPlanner;
import com.syncforge.planner.SyncAction;
import com.syncforge.runtime.TransactionExecutor;
import com.syncforge.scanner.FileTreeScanner;
import com.syncforge.scanner.ScanResult;
import com.syncforge.scanner.ScannerFilters;
import com.syncforge.snapshot.SnapshotModel;
import com.syncforge.snapshot.SnapshotReader;
import com.syncforge.snapshot.SnapshotWriter;
import com.syncforge.storage.SnapshotStore;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MultiClientSyncTest {

    @Test
    public void testThreeNodeHubAndSpokeSync() throws IOException {
        Path repoA = Files.createTempDirectory("sf_repo_A");
        Path clientB = Files.createTempDirectory("sf_client_B");
        Path clientC = Files.createTempDirectory("sf_client_C");
        Path metaB = Files.createTempDirectory("sf_meta_B");
        Path metaC = Files.createTempDirectory("sf_meta_C");

        // 1. Initialize Repo A (Hub) with content
        Files.writeString(repoA.resolve("readme.md"), "# SyncForge Hub\nThis is a master hub.\n");
        Files.createDirectories(repoA.resolve("src"));
        Files.writeString(repoA.resolve("src/App.java"), "public class App {}\n");
        Files.writeString(repoA.resolve("src/Util.java"), "public class Util {}\n");

        // Scan Repo A to create baseline snapshot
        FileTreeScanner scanner = new FileTreeScanner();
        ScannerFilters filters = new ScannerFilters(Collections.emptyList(), Collections.emptyList(), true);
        ScanResult scanA1 = scanner.scan(repoA, filters);
        
        SnapshotModel snapA1 = new SnapshotModel(scanA1.getEntries(), 1, 0, null);
        SnapshotStore storeB = new SnapshotStore(metaB);
        SnapshotStore storeC = new SnapshotStore(metaC);

        String idA1_B = storeB.save(snapA1, false);
        String idA1_C = storeC.save(snapA1, false);

        // 2. Perform initial synchronization on Client B and Client C
        // Initially, clients are empty directories. We diff empty snapshot vs snapA1.
        SnapshotModel emptySnap = new SnapshotModel();
        
        DiffEngine diffEngine = new DiffEngine();
        List<DiffEntry> diffsToB = diffEngine.diff(emptySnap, snapA1);
        List<DiffEntry> diffsToC = diffEngine.diff(emptySnap, snapA1);

        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> planB = planner.plan(diffsToB);
        List<SyncAction> planC = planner.plan(diffsToC);

        // Run executors on clients
        TransactionExecutor execB = new TransactionExecutor(repoA.toString(), clientB.toString(), metaB.toString());
        execB.execute(planB);

        TransactionExecutor execC = new TransactionExecutor(repoA.toString(), clientC.toString(), metaC.toString());
        execC.execute(planC);

        // Verify initial sync succeeded
        assertTrue(Files.exists(clientB.resolve("readme.md")));
        assertTrue(Files.exists(clientB.resolve("src/App.java")));
        assertTrue(Files.exists(clientC.resolve("readme.md")));
        assertTrue(Files.exists(clientC.resolve("src/Util.java")));
        assertEquals("public class App {}\n", Files.readString(clientB.resolve("src/App.java")));

        // 3. Mutate Hub A
        // A modifies readme.md, deletes Util.java, creates src/Config.java
        Files.writeString(repoA.resolve("readme.md"), "# SyncForge Hub\nUpdated documentation!\n");
        Files.delete(repoA.resolve("src/Util.java"));
        Files.writeString(repoA.resolve("src/Config.java"), "public class Config {}\n");

        ScanResult scanA2 = scanner.scan(repoA, filters);
        SnapshotModel snapA2 = new SnapshotModel(scanA2.getEntries(), 2, 0, null);

        // Client B diffs against its stored baseline (snapA1) to see what A changed
        SnapshotModel baselineB = storeB.load(idA1_B);
        List<DiffEntry> diffsB2 = diffEngine.diff(baselineB, snapA2);
        List<SyncAction> planB2 = planner.plan(diffsB2);

        TransactionExecutor execB2 = new TransactionExecutor(repoA.toString(), clientB.toString(), metaB.toString());
        try {
            execB2.execute(planB2);
        } catch (Exception e) {
            System.err.println("=== FAIL ACTIONS ===");
            for (SyncAction act : planB2) {
                System.err.println("  " + act.getType() + " : " + act.getPath());
            }
            e.printStackTrace();
            throw e;
        }

        // Verify Client B updated correctly
        assertEquals("# SyncForge Hub\nUpdated documentation!\n", Files.readString(clientB.resolve("readme.md")));
        assertFalse(Files.exists(clientB.resolve("src/Util.java")));
        assertTrue(Files.exists(clientB.resolve("src/Config.java")));
    }

    @Test
    public void testConflictDetectionAndLineMerging() throws IOException {
        Path tempBaseDir = Files.createTempDirectory("sf_conflict_base");
        Path tempLocalDir = Files.createTempDirectory("sf_conflict_local");
        Path tempRemoteDir = Files.createTempDirectory("sf_conflict_remote");
        Path tempOutputDir = Files.createTempDirectory("sf_conflict_output");

        String fileRel = "conflict.txt";
        Files.write(tempBaseDir.resolve(fileRel), List.of("Line 1", "Line 2", "Line 3"));
        Files.write(tempLocalDir.resolve(fileRel), List.of("Line 1 Local", "Line 2", "Line 3"));
        Files.write(tempRemoteDir.resolve(fileRel), List.of("Line 1", "Line 2", "Line 3 Remote"));

        // Build models
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        
        FileEntry fBase = new FileEntry(fileRel, ts, mode, 20L, 
            com.syncforge.metadata.FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
            Collections.emptyMap(), Collections.emptyList()
        );
        FileEntry fLocal = new FileEntry(fileRel, ts, mode, 26L, 
            com.syncforge.metadata.FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b856"),
            Collections.emptyMap(), Collections.emptyList()
        );
        FileEntry fRemote = new FileEntry(fileRel, ts, mode, 27L, 
            com.syncforge.metadata.FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b857"),
            Collections.emptyMap(), Collections.emptyList()
        );

        SnapshotModel base = new SnapshotModel(List.of(fBase), 1, 0, null);
        SnapshotModel local = new SnapshotModel(List.of(fLocal), 1, 0, null);
        SnapshotModel remote = new SnapshotModel(List.of(fRemote), 1, 0, null);

        // Detect conflicts
        List<Conflict> conflicts = ConflictDetector.detect(base, local, remote);
        assertEquals(1, conflicts.size());

        Conflict conflict = conflicts.get(0);
        assertEquals(fileRel, conflict.path());

        // Resolve using content merge strategy
        boolean clean = ConflictResolver.resolve(
            conflict, ConflictResolver.ResolutionStrategy.MERGE_CONTENT,
            tempBaseDir.toString(), tempLocalDir.toString(), tempRemoteDir.toString(), tempOutputDir.toString()
        );

        // Since Line 1 Local and Line 3 Remote do not overlap/clash, it should merge cleanly without conflicts!
        assertTrue(clean);
        List<String> mergedLines = Files.readAllLines(tempOutputDir.resolve(fileRel));
        assertEquals(3, mergedLines.size());
        assertEquals("Line 1 Local", mergedLines.get(0));
        assertEquals("Line 2", mergedLines.get(1));
        assertEquals("Line 3 Remote", mergedLines.get(2));
    }
}
