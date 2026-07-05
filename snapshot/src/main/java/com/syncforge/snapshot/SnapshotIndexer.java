package com.syncforge.snapshot;

import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Indexes snapshot data for efficient querying and lookup.
 * Provides multiple index types including path, checksum, size, and timestamp indexes.
 */
public class SnapshotIndexer {
    
    private final SnapshotModel snapshot;
    private final Map<String, EntryMetadata> pathIndex;
    private final Map<String, List<EntryMetadata>> checksumIndex;
    private final NavigableMap<Long, List<EntryMetadata>> sizeIndex;
    private final NavigableMap<Long, List<EntryMetadata>> timestampIndex;
    private final Map<String, List<EntryMetadata>> extensionIndex;
    
    public SnapshotIndexer(SnapshotModel snapshot) {
        this.snapshot = snapshot;
        this.pathIndex = new ConcurrentHashMap<>();
        this.checksumIndex = new ConcurrentHashMap<>();
        this.sizeIndex = new TreeMap<>();
        this.timestampIndex = new TreeMap<>();
        this.extensionIndex = new ConcurrentHashMap<>();
        buildIndexes();
    }
    
    /**
     * Builds all indexes from the snapshot data.
     */
    private void buildIndexes() {
        for (EntryMetadata entry : snapshot.getEntries()) {
            // Path index
            pathIndex.put(entry.getNormalizedPath(), entry);
            
            // Checksum index (for files)
            if (entry instanceof FileEntry fileEntry && fileEntry.getContentHash() != null) {
                String checksum = fileEntry.getContentHash().toString();
                checksumIndex.computeIfAbsent(checksum, k -> new ArrayList<>()).add(entry);
            }
            
            // Size index (for files)
            if (entry instanceof FileEntry fileEntry) {
                sizeIndex.computeIfAbsent(fileEntry.getSize(), k -> new ArrayList<>()).add(entry);
            }
            
            // Timestamp index
            if (entry.getTimestamp() != null) {
                long timestamp = entry.getTimestamp().mtimeMillis();
                timestampIndex.computeIfAbsent(timestamp, k -> new ArrayList<>()).add(entry);
            }
            
            // Extension index (for files)
            if (entry instanceof FileEntry fileEntry) {
                String extension = getExtension(fileEntry.getNormalizedPath());
                extensionIndex.computeIfAbsent(extension, k -> new ArrayList<>()).add(entry);
            }
        }
    }
    
    /**
     * Finds an entry by path.
     */
    public EntryMetadata findByPath(String path) {
        return pathIndex.get(path);
    }
    
    /**
     * Finds all entries with a specific checksum.
     */
    public List<EntryMetadata> findByChecksum(String checksum) {
        return checksumIndex.getOrDefault(checksum, Collections.emptyList());
    }
    
    /**
     * Finds all entries with size in a range.
     */
    public List<EntryMetadata> findBySizeRange(long minSize, long maxSize) {
        List<EntryMetadata> results = new ArrayList<>();
        for (Map.Entry<Long, List<EntryMetadata>> entry : sizeIndex.subMap(minSize, true, maxSize, true).entrySet()) {
            results.addAll(entry.getValue());
        }
        return results;
    }
    
    /**
     * Finds all entries with size greater than or equal to the given size.
     */
    public List<EntryMetadata> findBySizeMin(long minSize) {
        List<EntryMetadata> results = new ArrayList<>();
        for (Map.Entry<Long, List<EntryMetadata>> entry : sizeIndex.tailMap(minSize, true).entrySet()) {
            results.addAll(entry.getValue());
        }
        return results;
    }
    
    /**
     * Finds all entries with size less than or equal to the given size.
     */
    public List<EntryMetadata> findBySizeMax(long maxSize) {
        List<EntryMetadata> results = new ArrayList<>();
        for (Map.Entry<Long, List<EntryMetadata>> entry : sizeIndex.headMap(maxSize, true).entrySet()) {
            results.addAll(entry.getValue());
        }
        return results;
    }
    
    /**
     * Finds all entries modified in a time range.
     */
    public List<EntryMetadata> findByTimeRange(long minTime, long maxTime) {
        List<EntryMetadata> results = new ArrayList<>();
        for (Map.Entry<Long, List<EntryMetadata>> entry : timestampIndex.subMap(minTime, true, maxTime, true).entrySet()) {
            results.addAll(entry.getValue());
        }
        return results;
    }
    
    /**
     * Finds all entries modified after a given time.
     */
    public List<EntryMetadata> findByModifiedAfter(long timestamp) {
        List<EntryMetadata> results = new ArrayList<>();
        for (Map.Entry<Long, List<EntryMetadata>> entry : timestampIndex.tailMap(timestamp, false).entrySet()) {
            results.addAll(entry.getValue());
        }
        return results;
    }
    
    /**
     * Finds all entries modified before a given time.
     */
    public List<EntryMetadata> findByModifiedBefore(long timestamp) {
        List<EntryMetadata> results = new ArrayList<>();
        for (Map.Entry<Long, List<EntryMetadata>> entry : timestampIndex.headMap(timestamp, false).entrySet()) {
            results.addAll(entry.getValue());
        }
        return results;
    }
    
    /**
     * Finds all entries with a specific file extension.
     */
    public List<EntryMetadata> findByExtension(String extension) {
        return extensionIndex.getOrDefault(extension.toLowerCase(), Collections.emptyList());
    }
    
    /**
     * Finds duplicate files (same checksum, different paths).
     */
    public Map<String, List<EntryMetadata>> findDuplicates() {
        Map<String, List<EntryMetadata>> duplicates = new HashMap<>();
        
        for (Map.Entry<String, List<EntryMetadata>> entry : checksumIndex.entrySet()) {
            if (entry.getValue().size() > 1) {
                duplicates.put(entry.getKey(), entry.getValue());
            }
        }
        
        return duplicates;
    }
    
    /**
     * Finds empty files (size = 0).
     */
    public List<EntryMetadata> findEmptyFiles() {
        return sizeIndex.getOrDefault(0L, Collections.emptyList());
    }
    
    /**
     * Finds the largest files.
     */
    public List<EntryMetadata> findLargestFiles(int limit) {
        List<EntryMetadata> results = new ArrayList<>();
        
        for (Map.Entry<Long, List<EntryMetadata>> entry : sizeIndex.descendingMap().entrySet()) {
            if (results.size() >= limit) {
                break;
            }
            results.addAll(entry.getValue());
        }
        
        return results;
    }
    
    /**
     * Finds the smallest files.
     */
    public List<EntryMetadata> findSmallestFiles(int limit) {
        List<EntryMetadata> results = new ArrayList<>();
        
        for (Map.Entry<Long, List<EntryMetadata>> entry : sizeIndex.entrySet()) {
            if (results.size() >= limit) {
                break;
            }
            results.addAll(entry.getValue());
        }
        
        return results;
    }
    
    /**
     * Gets all unique file extensions.
     */
    public Set<String> getAllExtensions() {
        return extensionIndex.keySet();
    }
    
    /**
     * Gets the count of files per extension.
     */
    public Map<String, Integer> getExtensionCounts() {
        Map<String, Integer> counts = new HashMap<>();
        
        for (Map.Entry<String, List<EntryMetadata>> entry : extensionIndex.entrySet()) {
            counts.put(entry.getKey(), entry.getValue().size());
        }
        
        return counts;
    }
    
    /**
     * Gets the most common extensions.
     */
    public List<Map.Entry<String, Integer>> getMostCommonExtensions(int limit) {
        return extensionIndex.entrySet().stream()
            .map(e -> Map.entry(e.getKey(), e.getValue().size()))
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(limit)
            .toList();
    }
    
    /**
     * Rebuilds all indexes.
     */
    public void rebuild() {
        pathIndex.clear();
        checksumIndex.clear();
        sizeIndex.clear();
        timestampIndex.clear();
        extensionIndex.clear();
        buildIndexes();
    }
    
    /**
     * Gets the snapshot being indexed.
     */
    public SnapshotModel getSnapshot() {
        return snapshot;
    }
    
    /**
     * Gets the path index size.
     */
    public int getPathIndexSize() {
        return pathIndex.size();
    }
    
    /**
     * Gets the checksum index size.
     */
    public int getChecksumIndexSize() {
        return checksumIndex.size();
    }
    
    /**
     * Gets the size index size.
     */
    public int getSizeIndexSize() {
        return sizeIndex.size();
    }
    
    /**
     * Gets the timestamp index size.
     */
    public int getTimestampIndexSize() {
        return timestampIndex.size();
    }
    
    /**
     * Gets the extension index size.
     */
    public int getExtensionIndexSize() {
        return extensionIndex.size();
    }
    
    private String getExtension(String path) {
        int lastDot = path.lastIndexOf('.');
        if (lastDot > 0 && lastDot < path.length() - 1) {
            return path.substring(lastDot + 1).toLowerCase();
        }
        return "";
    }
}
