package com.syncforge.cli;

import com.syncforge.conflict.Conflict;
import com.syncforge.conflict.ConflictResolver;
import com.syncforge.conflict.ConflictType;
import com.syncforge.core.exceptions.SyncForgeException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.diff.DiffEngine;
import com.syncforge.diff.DiffEntry;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileTimestamp;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.FileHash;
import com.syncforge.patch.DeltaDecoder;
import com.syncforge.patch.DeltaEncoder;
import com.syncforge.planner.DAGPlanner;
import com.syncforge.planner.SyncAction;
import com.syncforge.query.QueryEngine;
import com.syncforge.runtime.TransactionExecutor;
import com.syncforge.scanner.FileTreeScanner;
import com.syncforge.scanner.ScanResult;
import com.syncforge.scanner.ScannerFilters;
import com.syncforge.snapshot.SnapshotModel;
import com.syncforge.snapshot.SnapshotReader;
import com.syncforge.snapshot.SnapshotWriter;
import com.syncforge.storage.SnapshotStore;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main entry point for the SyncForge Command Line Interface (CLI).
 */
public class Main {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(Main.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        String command = args[0].toLowerCase();
        try {
            switch (command) {
                case "init" -> handleInit();
                case "scan" -> handleScan(args);
                case "snapshot" -> handleSnapshot(args);
                case "list" -> handleList();
                case "diff" -> handleDiff(args);
                case "sync" -> handleSync(args);
                case "conflict" -> handleConflict(args);
                case "query" -> handleQuery(args);
                case "patch" -> handlePatch(args);
                default -> {
                    System.out.println("Unknown command: " + command);
                    printHelp();
                    return;
                }
            }
        } catch (SyncForgeException e) {
            System.err.println("Execution Error: " + e.getMessage());
            LOGGER.error(e, "CLI execution failed for command: %s", command);
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printHelp() {
        System.out.println("SyncForge CLI - Java/JVM File Sync & Manifest Engine");
        System.out.println("Usage: syncforge <command> [options]");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  init                Initialize a SyncForge repository in the current directory.");
        System.out.println("  scan <dir>          Scan the specified directory and report metadata metrics.");
        System.out.println("  snapshot <dir>      Create and save a new SFSN binary snapshot of the directory.");
        System.out.println("  list                List all saved snapshots in the repository.");
        System.out.println("  diff <snap1> <snap2> Compare two snapshot IDs and show changes.");
        System.out.println("  sync <src> <tgt>    Synchronize changes from source folder to target folder.");
        System.out.println("  conflict <p> <st>   Resolve a conflict on file path <p> using strategy <st> (CHOOSE_LOCAL|CHOOSE_REMOTE|MERGE_CONTENT).");
        System.out.println("  query <snapId> <q>  Run a metadata query expression against a saved snapshot.");
        System.out.println("  patch <op> <files>  Generate/apply delta patches. ops: encode <src> <tgt> <patch> or decode <src> <patch> <tgt>.");
    }

    private static void handleInit() throws IOException {
        Path repoDir = Paths.get(".syncforge");
        if (Files.exists(repoDir)) {
            System.out.println("SyncForge repository is already initialized.");
            return;
        }
        Files.createDirectories(repoDir);
        Files.createDirectories(repoDir.resolve("snapshots"));
        Files.createDirectories(repoDir.resolve("wal"));
        System.out.println("Initialized empty SyncForge repository in: " + repoDir.toAbsolutePath());
    }

    private static void handleScan(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: syncforge scan <dir>");
            return;
        }
        Path path = Paths.get(args[1]);
        FileTreeScanner scanner = new FileTreeScanner();
        ScannerFilters filters = new ScannerFilters(Collections.emptyList(), Collections.emptyList(), true);
        ScanResult result = scanner.scan(path, filters);

        System.out.println("Scan Report for: " + path.toAbsolutePath());
        System.out.printf("  Total Size:      %d bytes\n", result.getTotalFileSize());
        System.out.printf("  Files:           %d\n", result.getFileCount());
        System.out.printf("  Directories:     %d\n", result.getDirectoryCount());
        System.out.printf("  Symlinks:        %d\n", result.getSymlinkCount());
        System.out.printf("  Scan Duration:   %d ms\n", result.getScanDurationMillis());
        if (!result.getSkippedPaths().isEmpty()) {
            System.out.printf("  Skipped/Errors:  %d\n", result.getSkippedPaths().size());
            result.getSkippedPaths().forEach(p -> System.out.println("    - " + p.path() + ": " + p.reason()));
        }
    }

    private static void handleSnapshot(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: syncforge snapshot <dir>");
            return;
        }
        Path path = Paths.get(args[1]);
        FileTreeScanner scanner = new FileTreeScanner();
        ScannerFilters filters = new ScannerFilters(Collections.emptyList(), Collections.emptyList(), true);
        ScanResult result = scanner.scan(path, filters);

        SnapshotModel model = new SnapshotModel(result.getEntries(), 1, 0, null);
        SnapshotStore store = new SnapshotStore(Paths.get(".syncforge"));
        String id = store.save(model, true); // compress by default

        System.out.println("Snapshot successfully created.");
        System.out.println("Snapshot ID: " + id);
    }

    private static void handleList() {
        SnapshotStore store = new SnapshotStore(Paths.get(".syncforge"));
        List<String> list = store.list();
        if (list.isEmpty()) {
            System.out.println("No snapshots found in the repository.");
            return;
        }
        System.out.println("Saved Snapshots:");
        list.forEach(id -> System.out.println("  - " + id));
    }

    private static void handleDiff(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: syncforge diff <snapId1> <snapId2>");
            return;
        }
        SnapshotStore store = new SnapshotStore(Paths.get(".syncforge"));
        SnapshotModel snap1 = store.load(args[1]);
        SnapshotModel snap2 = store.load(args[2]);

        DiffEngine engine = new DiffEngine();
        List<DiffEntry> diffs = engine.diff(snap1, snap2);

        System.out.println("Difference report between " + args[1] + " and " + args[2] + ":");
        diffs.forEach(d -> {
            if (d.getChangeType() != DiffEntry.Type.UNCHANGED) {
                System.out.printf("  %-18s %s\n", d.getChangeType().name(), d.getPath());
            }
        });
    }

    private static void handleSync(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: syncforge sync <srcDir> <tgtDir>");
            return;
        }
        Path src = Paths.get(args[1]);
        Path tgt = Paths.get(args[2]);

        System.out.println("Syncing changes from " + src + " to " + tgt);

        FileTreeScanner scanner = new FileTreeScanner();
        ScannerFilters filters = new ScannerFilters(Collections.emptyList(), Collections.emptyList(), true);
        ScanResult srcResult = scanner.scan(src, filters);
        ScanResult tgtResult = scanner.scan(tgt, filters);

        SnapshotModel srcSnap = new SnapshotModel(srcResult.getEntries(), 1, 0, null);
        SnapshotModel tgtSnap = new SnapshotModel(tgtResult.getEntries(), 1, 0, null);

        DiffEngine diffEngine = new DiffEngine();
        List<DiffEntry> diffs = diffEngine.diff(tgtSnap, srcSnap); // find actions needed to update target to match source

        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(diffs);

        if (actions.isEmpty()) {
            System.out.println("Target directory is already up to date.");
            return;
        }

        TransactionExecutor executor = new TransactionExecutor(src.toString(), tgt.toString(), ".syncforge");
        executor.execute(actions);

        System.out.println("Sync complete. Executed " + actions.size() + " actions transactionally.");
    }

    private static void handleConflict(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Usage: syncforge conflict <path> <strategy>");
            return;
        }
        String path = args[1];
        ConflictResolver.ResolutionStrategy strategy = ConflictResolver.ResolutionStrategy.valueOf(args[2].toUpperCase());

        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileEntry mockBase = new FileEntry(path, ts, mode, 100, FileHash.sha256("hash1"), Collections.emptyMap(), Collections.emptyList());

        Conflict conflict = new Conflict(path, ConflictType.CONTENT_MERGE_CONFLICT, mockBase, mockBase, mockBase);

        boolean resolved = ConflictResolver.resolve(
            conflict, strategy,
            ".syncforge/conflict_base", "conflict_local", "conflict_remote", "."
        );

        if (resolved) {
            System.out.println("Conflict resolved cleanly.");
        } else {
            System.out.println("Conflict resolved with markers.");
        }
    }

    private static void handleQuery(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: syncforge query <snapId> <expression>");
            return;
        }
        String snapId = args[1];
        String expression = args[2];

        SnapshotStore store = new SnapshotStore(Paths.get(".syncforge"));
        SnapshotModel snap = store.load(snapId);

        QueryEngine engine = new QueryEngine(snap);
        List<EntryMetadata> results = engine.execute(expression);

        System.out.println("Query Results (" + results.size() + " matches):");
        results.forEach(r -> System.out.printf("  %s (size: %d bytes, mtime: %d)\n",
            r.getNormalizedPath(), r.getSize(), r.getTimestamp().mtimeMillis()));
    }

    private static void handlePatch(String[] args) throws IOException {
        if (args.length < 5 && (args.length < 2 || !args[1].equalsIgnoreCase("decode"))) {
            System.out.println("Usage:");
            System.out.println("  syncforge patch encode <srcFile> <tgtFile> <patchFile>");
            System.out.println("  syncforge patch decode <srcFile> <patchFile> <tgtFile>");
            return;
        }
        String operation = args[1].toLowerCase();
        if (operation.equals("encode")) {
            byte[] srcBytes = Files.readAllBytes(Paths.get(args[2]));
            byte[] tgtBytes = Files.readAllBytes(Paths.get(args[3]));
            byte[] patch = DeltaEncoder.encode(srcBytes, tgtBytes);
            Files.write(Paths.get(args[4]), patch);
            System.out.println("Binary delta patch encoded successfully: " + args[4]);
        } else if (operation.equals("decode")) {
            byte[] srcBytes = Files.readAllBytes(Paths.get(args[2]));
            byte[] patchBytes = Files.readAllBytes(Paths.get(args[3]));
            byte[] tgtBytes = DeltaDecoder.decode(srcBytes, patchBytes);
            Files.write(Paths.get(args[4]), tgtBytes);
            System.out.println("Binary delta patch applied successfully: " + args[4]);
        }
    }
}
