package com.syncforge.conflict;

import com.syncforge.metadata.EntryMetadata;
import java.util.Objects;

/**
 * Represents a synchronization conflict between a baseline entry,
 * a local modified entry, and a remote modified entry.
 */
public record Conflict(
    String path,
    ConflictType type,
    EntryMetadata baseEntry,
    EntryMetadata localEntry,
    EntryMetadata remoteEntry
) {
    /**
     * Constructs a Conflict.
     *
     * @throws NullPointerException if path or type is null.
     */
    public Conflict {
        Objects.requireNonNull(path, "Conflict path must not be null");
        Objects.requireNonNull(type, "Conflict type must not be null");
    }
}
