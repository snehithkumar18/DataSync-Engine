package com.syncforge.indexing;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import com.syncforge.snapshot.SnapshotModel;
import java.util.*;

public class SnapshotIndex {
    private final Map<String, EntryMetadata> pathIndex = new HashMap<>();
    private final Map<String, List<FileEntry>> checksumIndex = new HashMap<>();
    private final TreeMap<Long, List<EntryMetadata>> timestampIndex = new TreeMap<>();
    private final Map<String, List<FileEntry>> extensionIndex = new HashMap<>();

    public SnapshotIndex(SnapshotModel snapshot) {
        for (EntryMetadata entry : snapshot.getEntries()) {
            pathIndex.put(entry.getNormalizedPath(), entry);
            timestampIndex.computeIfAbsent(entry.getTimestamp().mtimeMillis(), k -> new ArrayList<>()).add(entry);

            if (entry instanceof FileEntry fileEntry) {
                checksumIndex.computeIfAbsent(fileEntry.getContentHash().value(), k -> new ArrayList<>()).add(fileEntry);

                String path = fileEntry.getNormalizedPath();
                int dotIndex = path.lastIndexOf('.');
                String ext = dotIndex == -1 ? "" : path.substring(dotIndex + 1);
                extensionIndex.computeIfAbsent(ext, k -> new ArrayList<>()).add(fileEntry);
            }
        }
    }

    public EntryMetadata getByPath(String path) {
        return pathIndex.get(path);
    }

    public List<FileEntry> getByChecksum(String checksum) {
        return checksumIndex.getOrDefault(checksum, Collections.emptyList());
    }

    public List<FileEntry> getByExtension(String ext) {
        return extensionIndex.getOrDefault(ext, Collections.emptyList());
    }

    public List<EntryMetadata> getModifiedSince(long timestamp) {
        List<EntryMetadata> results = new ArrayList<>();
        for (List<EntryMetadata> list : timestampIndex.tailMap(timestamp, true).values()) {
            results.addAll(list);
        }
        return results;
    }

    public Collection<EntryMetadata> getAllEntries() {
        return pathIndex.values();
    }
}
