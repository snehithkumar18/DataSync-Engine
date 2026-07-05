package com.syncforge.metadata;

import java.util.Comparator;
import java.util.List;

/**
 * Compares metadata entries for sorting and ordering operations.
 * Supports multiple comparison strategies and custom ordering.
 */
public class MetadataComparator implements Comparator<EntryMetadata> {
    
    private final ComparisonStrategy strategy;
    private final boolean ascending;
    
    public MetadataComparator() {
        this(ComparisonStrategy.PATH, true);
    }
    
    public MetadataComparator(ComparisonStrategy strategy, boolean ascending) {
        this.strategy = strategy;
        this.ascending = ascending;
    }
    
    @Override
    public int compare(EntryMetadata a, EntryMetadata b) {
        if (a == null && b == null) return 0;
        if (a == null) return ascending ? -1 : 1;
        if (b == null) return ascending ? 1 : -1;
        
        int result = switch (strategy) {
            case PATH -> compareByPath(a, b);
            case SIZE -> compareBySize(a, b);
            case MODIFIED_TIME -> compareByModifiedTime(a, b);
            case TYPE -> compareByType(a, b);
            case CHECKSUM -> compareByChecksum(a, b);
            case DEPTH -> compareByDepth(a, b);
            case NAME -> compareByName(a, b);
            case EXTENSION -> compareByExtension(a, b);
        };
        
        return ascending ? result : -result;
    }
    
    private int compareByPath(EntryMetadata a, EntryMetadata b) {
        String pathA = a.getNormalizedPath();
        String pathB = b.getNormalizedPath();
        return pathA.compareTo(pathB);
    }
    
    private int compareBySize(EntryMetadata a, EntryMetadata b) {
        long sizeA = a.getSize();
        long sizeB = b.getSize();
        return Long.compare(sizeA, sizeB);
    }
    
    private int compareByModifiedTime(EntryMetadata a, EntryMetadata b) {
        FileTimestamp timeA = a.getTimestamp();
        FileTimestamp timeB = b.getTimestamp();
        if (timeA == null && timeB == null) return 0;
        if (timeA == null) return -1;
        if (timeB == null) return 1;
        return Long.compare(timeA.mtimeMillis(), timeB.mtimeMillis());
    }
    
    private int compareByType(EntryMetadata a, EntryMetadata b) {
        int typeOrderA = getTypeOrder(a);
        int typeOrderB = getTypeOrder(b);
        return Integer.compare(typeOrderA, typeOrderB);
    }
    
    private int compareByChecksum(EntryMetadata a, EntryMetadata b) {
        if (a instanceof FileEntry fileA && b instanceof FileEntry fileB) {
            FileHash hashA = fileA.getContentHash();
            FileHash hashB = fileB.getContentHash();
            if (hashA == null && hashB == null) return 0;
            if (hashA == null) return -1;
            if (hashB == null) return 1;
            return hashA.toString().compareTo(hashB.toString());
        }
        return 0;
    }
    
    private int compareByDepth(EntryMetadata a, EntryMetadata b) {
        int depthA = getPathDepth(a.getNormalizedPath());
        int depthB = getPathDepth(b.getNormalizedPath());
        return Integer.compare(depthA, depthB);
    }
    
    private int compareByName(EntryMetadata a, EntryMetadata b) {
        String nameA = getFileName(a.getNormalizedPath());
        String nameB = getFileName(b.getNormalizedPath());
        return nameA.compareTo(nameB);
    }
    
    private int compareByExtension(EntryMetadata a, EntryMetadata b) {
        String extA = getExtension(a.getNormalizedPath());
        String extB = getExtension(b.getNormalizedPath());
        return extA.compareTo(extB);
    }
    
    private int getTypeOrder(EntryMetadata metadata) {
        if (metadata instanceof DirectoryEntry) return 0;
        if (metadata instanceof FileEntry) return 1;
        if (metadata instanceof SymlinkEntry) return 2;
        return 3;
    }
    
    private int getPathDepth(String path) {
        return path.split("/").length;
    }
    
    private String getFileName(String path) {
        int lastSlash = path.lastIndexOf('/');
        return lastSlash >= 0 ? path.substring(lastSlash + 1) : path;
    }
    
    private String getExtension(String path) {
        String fileName = getFileName(path);
        int lastDot = fileName.lastIndexOf('.');
        return lastDot > 0 ? fileName.substring(lastDot + 1) : "";
    }
    
    /**
     * Sorts a list of metadata entries by path.
     */
    public static List<EntryMetadata> sortByPath(List<EntryMetadata> entries) {
        List<EntryMetadata> sorted = new java.util.ArrayList<>(entries);
        sorted.sort(new MetadataComparator(ComparisonStrategy.PATH, true));
        return sorted;
    }
    
    /**
     * Sorts a list of metadata entries by size.
     */
    public static List<EntryMetadata> sortBySize(List<EntryMetadata> entries) {
        List<EntryMetadata> sorted = new java.util.ArrayList<>(entries);
        sorted.sort(new MetadataComparator(ComparisonStrategy.SIZE, true));
        return sorted;
    }
    
    /**
     * Sorts a list of metadata entries by timestamp.
     */
    public static List<EntryMetadata> sortByTimestamp(List<EntryMetadata> entries) {
        List<EntryMetadata> sorted = new java.util.ArrayList<>(entries);
        sorted.sort(new MetadataComparator(ComparisonStrategy.MODIFIED_TIME, true));
        return sorted;
    }
    
    /**
     * Sorts a list of metadata entries by type.
     */
    public static List<EntryMetadata> sortByType(List<EntryMetadata> entries) {
        List<EntryMetadata> sorted = new java.util.ArrayList<>(entries);
        sorted.sort(new MetadataComparator(ComparisonStrategy.TYPE, true));
        return sorted;
    }
    
    /**
     * Sorts a list of metadata entries by checksum.
     */
    public static List<EntryMetadata> sortByChecksum(List<EntryMetadata> entries) {
        List<EntryMetadata> sorted = new java.util.ArrayList<>(entries);
        sorted.sort(new MetadataComparator(ComparisonStrategy.CHECKSUM, true));
        return sorted;
    }
    
    /**
     * Sorts a list of metadata entries by depth.
     */
    public static List<EntryMetadata> sortByDepth(List<EntryMetadata> entries) {
        List<EntryMetadata> sorted = new java.util.ArrayList<>(entries);
        sorted.sort(new MetadataComparator(ComparisonStrategy.DEPTH, true));
        return sorted;
    }
    
    /**
     * Sorts a list of metadata entries by name.
     */
    public static List<EntryMetadata> sortByName(List<EntryMetadata> entries) {
        List<EntryMetadata> sorted = new java.util.ArrayList<>(entries);
        sorted.sort(new MetadataComparator(ComparisonStrategy.NAME, true));
        return sorted;
    }
    
    /**
     * Sorts a list of metadata entries by extension.
     */
    public static List<EntryMetadata> sortByExtension(List<EntryMetadata> entries) {
        List<EntryMetadata> sorted = new java.util.ArrayList<>(entries);
        sorted.sort(new MetadataComparator(ComparisonStrategy.EXTENSION, true));
        return sorted;
    }
    
    /**
     * Creates a comparator with the given strategy.
     */
    public static MetadataComparator by(ComparisonStrategy strategy) {
        return new MetadataComparator(strategy, true);
    }
    
    /**
     * Creates a descending comparator with the given strategy.
     */
    public static MetadataComparator byDescending(ComparisonStrategy strategy) {
        return new MetadataComparator(strategy, false);
    }
    
    /**
     * Comparison strategies for metadata ordering.
     */
    public enum ComparisonStrategy {
        PATH,
        SIZE,
        MODIFIED_TIME,
        TYPE,
        CHECKSUM,
        DEPTH,
        NAME,
        EXTENSION
    }
}
