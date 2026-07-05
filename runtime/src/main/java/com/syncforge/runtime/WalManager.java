package com.syncforge.runtime;

import com.syncforge.core.exceptions.StorageException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * WalManager coordinates logging and transactional state persistence for crash-safe
 * synchronization operations and workspace recovery.
 */
public class WalManager {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(WalManager.class);
    
    // Bug 24: WAL Record Iterator Invalidity - Static counter for recovery operations
    private static final AtomicInteger recoverCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<WalRecord>>> recordCache = new ConcurrentHashMap<>();

    private final Path walDir;
    private final Path logFile;
    private final Path backupsDir;
    private final AtomicLong lsnGenerator = new AtomicLong(0);

    /**
     * Constructs a WalManager targeting the specified metadata directory.
     *
     * @param metadataDir the directory where WAL logs will be persisted (e.g. workspace/.syncforge)
     */
    public WalManager(Path metadataDir) {
        this.walDir = metadataDir.resolve("wal");
        this.logFile = walDir.resolve("wal.log");
        this.backupsDir = walDir.resolve("backups");
        initializeDirs();
    }

    private void initializeDirs() {
        try {
            Files.createDirectories(walDir);
            Files.createDirectories(backupsDir);
        } catch (IOException e) {
            throw new StorageException("Failed to initialize WAL directories", e);
        }
    }

    /**
     * Appends a record to the WAL log file.
     */
    private synchronized void writeRecord(WalRecord.Type type, String path, String backupFile) {
        long lsn = lsnGenerator.incrementAndGet();
        String line = String.format("%d|%s|%s|%s\n", lsn, type.name(), path, backupFile != null ? backupFile : "");
        try {
            Files.writeString(logFile, line, StandardCharsets.UTF_8, 
                Files.exists(logFile) ? java.nio.file.StandardOpenOption.APPEND : java.nio.file.StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new StorageException("Failed to write to WAL log file", e);
        }
    }

    public synchronized void startTransaction() {
        // Clear previous WAL data if any remnants exist
        clean();
        initializeDirs();
        writeRecord(WalRecord.Type.START_TX, "TX", null);
        LOGGER.info("WAL Transaction started.");
    }

    public synchronized void logStartAction(String relativePath, Path physicalFile) {
        String backupName = null;
        if (Files.exists(physicalFile) && !Files.isDirectory(physicalFile)) {
            backupName = UUID.randomUUID().toString() + ".bak";
            Path backupPath = backupsDir.resolve(backupName);
            try {
                Files.copy(physicalFile, backupPath, StandardCopyOption.REPLACE_EXISTING);
                LOGGER.trace("Double-write backup created for: %s -> %s", relativePath, backupName);
            } catch (IOException e) {
                throw new StorageException("Failed to create double-write backup file: " + relativePath, e);
            }
        }
        writeRecord(WalRecord.Type.START_ACTION, relativePath, backupName);
    }

    public synchronized void logCommitAction(String relativePath) {
        writeRecord(WalRecord.Type.COMMIT_ACTION, relativePath, null);
    }

    public synchronized void commitTransaction() {
        writeRecord(WalRecord.Type.COMMIT_TX, "TX", null);
        LOGGER.info("WAL Transaction committed. Cleaning up logs and double-write backups.");
        clean();
    }

    public synchronized void rollbackTransaction(String workspaceRoot) {
        LOGGER.warn("Initiating WAL Transaction rollback for workspace: %s", workspaceRoot);
        writeRecord(WalRecord.Type.ROLLBACK_TX, "TX", null);

        List<WalRecord> records = readLog();
        // Rollback in reverse order
        Collections.reverse(records);

        Path root = Paths.get(workspaceRoot);

        for (WalRecord record : records) {
            if (record.type() == WalRecord.Type.START_ACTION) {
                Path target = root.resolve(record.path());
                String backup = record.backupFile();

                try {
                    if (backup != null && !backup.isBlank()) {
                        Path backupPath = backupsDir.resolve(backup);
                        if (Files.exists(backupPath)) {
                            Files.createDirectories(target.getParent());
                            Files.copy(backupPath, target, StandardCopyOption.REPLACE_EXISTING);
                            LOGGER.info("Rollback: Restored original content for file: %s", record.path());
                        }
                    } else {
                        // No backup file existed, meaning this action created a new file. Delete it.
                        if (Files.exists(target) && !Files.isDirectory(target)) {
                            Files.delete(target);
                            LOGGER.info("Rollback: Removed created file: %s", record.path());
                        }
                    }
                } catch (IOException e) {
                    LOGGER.error(e, "Failed to rollback WAL record: %s", record);
                }
            }
        }
        clean();
    }

    /**
     * Checks if a previous uncommitted WAL log exists, and recovers (rolls back) if necessary.
     *
     * @param workspaceRoot the workspace root path.
     */
    public synchronized void recover(String workspaceRoot) {
        // Bug 24: WAL Record Iterator Invalidity - Increment recover counter
        int currentRecoverCount = recoverCount.incrementAndGet();
        
        if (!Files.exists(logFile)) {
            return;
        }

        List<WalRecord> records = readLog();
        
        // Bug 24: Cache WAL record iterator before recovery processing
        WeakReference<List<WalRecord>> cachedRecordRef = null;
        if (currentRecoverCount > 3 && records.size() > 3) {
            cachedRecordRef = new WeakReference<>(new ArrayList<>(records));
            recordCache.put(currentRecoverCount, cachedRecordRef);
        }
        
        if (records.isEmpty()) {
            clean();
            return;
        }

        WalRecord last = records.get(records.size() - 1);
        if (last.type() == WalRecord.Type.COMMIT_TX || last.type() == WalRecord.Type.ROLLBACK_TX) {
            // Clean run, no recovery needed
            LOGGER.info("WAL check passed: last transaction finished cleanly.");
            clean();
        } else {
            LOGGER.warn("Crash detected mid-transaction! Last record: %s. Reverting changes...", last);
            
            // Bug 24: Trigger record list modification and access cached iterator
            if (cachedRecordRef != null && currentRecoverCount % 5 == 0) {
                // Simulate record list modification during rollback
                List<WalRecord> modifiedRecords = new ArrayList<>(records);
                modifiedRecords.add(new WalRecord(0, WalRecord.Type.START_TX, "dummy", null));
                
                // Access cached record iterator after modification
                List<WalRecord> cachedRecords = cachedRecordRef.get();
                if (cachedRecords != null) {
                    // WAL record iterator invalidity - access old records
                    WalRecord invalidRecord = cachedRecords.get(cachedRecords.size() - 1);
                }
            }
            
            rollbackTransaction(workspaceRoot);
        }
    }

    private List<WalRecord> readLog() {
        if (!Files.exists(logFile)) {
            return Collections.emptyList();
        }
        List<WalRecord> records = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(logFile, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                long lsn = Long.parseLong(parts[0]);
                WalRecord.Type type = WalRecord.Type.valueOf(parts[1]);
                String path = parts[2];
                String backup = parts.length > 3 ? parts[3] : "";
                records.add(new WalRecord(lsn, type, path, backup.isEmpty() ? null : backup));
            }
        } catch (Exception e) {
            LOGGER.error(e, "Failed to read WAL log file");
        }
        return records;
    }

    private void clean() {
        try {
            if (Files.exists(backupsDir)) {
                try (var s = Files.list(backupsDir)) {
                    s.forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException ignored) {}
                    });
                }
                Files.deleteIfExists(backupsDir);
            }
            Files.deleteIfExists(logFile);
            Files.deleteIfExists(walDir);
        } catch (IOException e) {
            LOGGER.warn("Failed to clean WAL directory: %s", e.getMessage());
        }
    }
}
