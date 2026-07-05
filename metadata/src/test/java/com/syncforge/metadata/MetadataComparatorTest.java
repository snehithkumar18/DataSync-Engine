package com.syncforge.metadata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for MetadataComparator.
 */
@DisplayName("MetadataComparator Tests")
class MetadataComparatorTest {
    
    @Test
    @DisplayName("Compare entries by path")
    void testCompareByPath() {
        FileEntry entry1 = createFileEntry("a/b/file.txt");
        FileEntry entry2 = createFileEntry("a/c/file.txt");
        
        MetadataComparator comparator = new MetadataComparator();
        List<EntryMetadata> sorted = comparator.sortByPath(List.of(entry2, entry1));
        
        assertEquals("a/b/file.txt", sorted.get(0).getNormalizedPath());
        assertEquals("a/c/file.txt", sorted.get(1).getNormalizedPath());
    }
    
    @Test
    @DisplayName("Compare entries by size")
    void testCompareBySize() {
        FileEntry entry1 = createFileEntry("file1.txt", 100);
        FileEntry entry2 = createFileEntry("file2.txt", 200);
        
        MetadataComparator comparator = new MetadataComparator();
        List<EntryMetadata> sorted = comparator.sortBySize(List.of(entry2, entry1));
        
        assertEquals(100, sorted.get(0).getSize());
        assertEquals(200, sorted.get(1).getSize());
    }
    
    @Test
    @DisplayName("Compare entries by timestamp")
    void testCompareByTimestamp() {
        FileTimestamp ts1 = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileTimestamp ts2 = FileTimestamp.fromEpochMilli(2000, 2000, 2000);
        
        FileEntry entry1 = createFileEntry("file1.txt", ts1);
        FileEntry entry2 = createFileEntry("file2.txt", ts2);
        
        MetadataComparator comparator = new MetadataComparator();
        List<EntryMetadata> sorted = comparator.sortByTimestamp(List.of(entry2, entry1));
        
        assertEquals(1000, sorted.get(0).getTimestamp().mtimeMillis());
        assertEquals(2000, sorted.get(1).getTimestamp().mtimeMillis());
    }
    
    @Test
    @DisplayName("Compare entries by type")
    void testCompareByType() {
        DirectoryEntry dir = createDirectoryEntry("dir");
        FileEntry file = createFileEntry("file.txt");
        
        MetadataComparator comparator = new MetadataComparator();
        List<EntryMetadata> sorted = comparator.sortByType(List.of(file, dir));
        
        assertEquals(FileMode.Type.DIRECTORY, sorted.get(0).getMode().getType());
        assertEquals(FileMode.Type.FILE, sorted.get(1).getMode().getType());
    }
    
    @Test
    @DisplayName("Compare entries by checksum")
    void testCompareByChecksum() {
        FileEntry entry1 = createFileEntry("file1.txt", "hash1");
        FileEntry entry2 = createFileEntry("file2.txt", "hash2");
        
        MetadataComparator comparator = new MetadataComparator();
        List<EntryMetadata> sorted = comparator.sortByChecksum(List.of(entry2, entry1));
        
        assertEquals("hash1", sorted.get(0).getHash().getHashValue());
        assertEquals("hash2", sorted.get(1).getHash().getHashValue());
    }
    
    @Test
    @DisplayName("Compare entries by depth")
    void testCompareByDepth() {
        FileEntry entry1 = createFileEntry("a/b/c/file.txt");
        FileEntry entry2 = createFileEntry("a/file.txt");
        
        MetadataComparator comparator = new MetadataComparator();
        List<EntryMetadata> sorted = comparator.sortByDepth(List.of(entry1, entry2));
        
        assertEquals("a/file.txt", sorted.get(0).getNormalizedPath());
        assertEquals("a/b/c/file.txt", sorted.get(1).getNormalizedPath());
    }
    
    private FileEntry createFileEntry(String path) {
        return createFileEntry(path, 0);
    }
    
    private FileEntry createFileEntry(String path, int size) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash hash = FileHash.sha256("test-hash");
        return new FileEntry(path, ts, mode, size, hash, java.util.Map.of(), java.util.List.of());
    }
    
    private FileEntry createFileEntry(String path, FileTimestamp ts) {
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash hash = FileHash.sha256("test-hash");
        return new FileEntry(path, ts, mode, 0, hash, java.util.Map.of(), java.util.List.of());
    }
    
    private FileEntry createFileEntry(String path, String hash) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash fileHash = FileHash.sha256(hash);
        return new FileEntry(path, ts, mode, 0, fileHash, java.util.Map.of(), java.util.List.of());
    }
    
    private DirectoryEntry createDirectoryEntry(String path) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileMode mode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();
        return new DirectoryEntry(path, ts, mode, java.util.Map.of(), java.util.List.of());
    }
}
