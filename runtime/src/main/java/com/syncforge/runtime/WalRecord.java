package com.syncforge.runtime;

import java.util.Objects;

/**
 * Represents a log record in the SyncForge Write-Ahead Log (WAL).
 */
public record WalRecord(
    long lsn,
    Type type,
    String path,
    String backupFile // Relative path inside the WAL folder to the original file backup
) {
    /**
     * Types of WAL log entries.
     */
    public enum Type {
        START_TX,
        START_ACTION,
        COMMIT_ACTION,
        ABORT_ACTION,
        COMMIT_TX,
        ROLLBACK_TX
    }

    /**
     * Constructs a WalRecord.
     *
     * @throws NullPointerException if type or path is null.
     */
    public WalRecord {
        Objects.requireNonNull(type, "WAL record type must not be null");
        Objects.requireNonNull(path, "WAL record path must not be null");
    }
}
