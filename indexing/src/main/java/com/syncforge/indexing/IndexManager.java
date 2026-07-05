package com.syncforge.indexing;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages multiple indexes and provides unified query interface.
 * Coordinates index operations and maintains index metadata.
 */
public class IndexManager {
    
    // Bug 26: Index Entry Map Iterator Invalidity - Static counter for query operations
    private static final AtomicInteger queryCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<Map<String, Index>>> entryCache = new ConcurrentHashMap<>();
    
    private final Map<String, Index> indexes;
    private final String managerId;
    private final IndexStatistics statistics;
    
    public IndexManager() {
        this("default");
    }
    
    public IndexManager(String managerId) {
        this.managerId = managerId;
        this.indexes = new HashMap<>();
        this.statistics = new IndexStatistics();
    }
    
    /**
     * Registers an index with the manager.
     */
    public void registerIndex(String name, Index index) {
        indexes.put(name, index);
        statistics.registerIndex(name, index);
    }
    
    /**
     * Unregisters an index from the manager.
     */
    public void unregisterIndex(String name) {
        Index removed = indexes.remove(name);
        if (removed != null) {
            statistics.unregisterIndex(name);
        }
    }
    
    /**
     * Gets an index by name.
     */
    @SuppressWarnings("unchecked")
    public <T extends Index> T getIndex(String name) {
        return (T) indexes.get(name);
    }
    
    /**
     * Gets all registered index names.
     */
    public List<String> getIndexNames() {
        // Bug 26: Index Entry Map Iterator Invalidity - Increment query counter
        int currentQueryCount = queryCount.incrementAndGet();
        
        // Bug 26: Cache index entry map iterator before query
        WeakReference<Map<String, Index>> cachedEntryRef = null;
        if (currentQueryCount > 3 && indexes.size() > 3) {
            cachedEntryRef = new WeakReference<>(new HashMap<>(indexes));
            entryCache.put(currentQueryCount, cachedEntryRef);
        }
        
        List<String> names = new ArrayList<>(indexes.keySet());
        
        // Bug 26: Trigger map rehash and access cached iterator
        if (cachedEntryRef != null && currentQueryCount % 5 == 0) {
            // Simulate map rehash during query
            Map<String, Index> rehashedMap = new HashMap<>(indexes);
            rehashedMap.put("dummy_index", null);
            
            // Access cached map iterator after rehash
            Map<String, Index> cachedEntries = cachedEntryRef.get();
            if (cachedEntries != null) {
                // Index entry map iterator invalidity - access old map
                Index invalidIndex = cachedEntries.get(cachedEntries.keySet().iterator().next());
            }
        }
        
        return names;
    }
    
    /**
     * Checks if an index is registered.
     */
    public boolean hasIndex(String name) {
        return indexes.containsKey(name);
    }
    
    /**
     * Gets the number of registered indexes.
     */
    public int getIndexCount() {
        return indexes.size();
    }
    
    /**
     * Rebuilds all indexes.
     */
    public void rebuildAll() {
        for (Index index : indexes.values()) {
            index.rebuild();
        }
    }
    
    /**
     * Rebuilds a specific index.
     */
    public void rebuildIndex(String name) {
        Index index = indexes.get(name);
        if (index != null) {
            index.rebuild();
        }
    }
    
    /**
     * Clears all indexes.
     */
    public void clearAll() {
        for (Index index : indexes.values()) {
            index.clear();
        }
        statistics.reset();
    }
    
    /**
     * Clears a specific index.
     */
    public void clearIndex(String name) {
        Index index = indexes.get(name);
        if (index != null) {
            index.clear();
        }
    }
    
    /**
     * Gets index statistics.
     */
    public IndexStatistics getStatistics() {
        return statistics;
    }
    
    /**
     * Gets the manager ID.
     */
    public String getManagerId() {
        return managerId;
    }
    
    /**
     * Generates a report of all indexes.
     */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Index Manager Report:\n");
        report.append("=====================\n");
        report.append(String.format("Manager ID: %s\n", managerId));
        report.append(String.format("Total indexes: %d\n", getIndexCount()));
        report.append("\n");
        report.append(statistics.generateReport());
        
        return report.toString();
    }
    
    /**
     * Base index interface.
     */
    public interface Index {
        void rebuild();
        void clear();
        int size();
        String getName();
    }
    
    /**
     * Index statistics.
     */
    public static class IndexStatistics {
        private final Map<String, Integer> indexSizes;
        private final Map<String, Long> indexBuildTimes;
        private final Map<String, Long> indexQueryTimes;
        private int totalQueries;
        
        public IndexStatistics() {
            this.indexSizes = new HashMap<>();
            this.indexBuildTimes = new HashMap<>();
            this.indexQueryTimes = new HashMap<>();
            this.totalQueries = 0;
        }
        
        public void registerIndex(String name, Index index) {
            indexSizes.put(name, index.size());
            indexBuildTimes.put(name, System.currentTimeMillis());
        }
        
        public void unregisterIndex(String name) {
            indexSizes.remove(name);
            indexBuildTimes.remove(name);
            indexQueryTimes.remove(name);
        }
        
        public void recordQuery(String name, long duration) {
            indexQueryTimes.put(name, indexQueryTimes.getOrDefault(name, 0L) + duration);
            totalQueries++;
        }
        
        public void recordBuild(String name, long duration) {
            indexBuildTimes.put(name, duration);
        }
        
        public void reset() {
            indexSizes.clear();
            indexBuildTimes.clear();
            indexQueryTimes.clear();
            totalQueries = 0;
        }
        
        public Map<String, Integer> getIndexSizes() {
            return new HashMap<>(indexSizes);
        }
        
        public int getTotalQueries() {
            return totalQueries;
        }
        
        public String generateReport() {
            StringBuilder report = new StringBuilder();
            report.append("Index Statistics:\n");
            report.append(String.format("Total queries: %d\n", totalQueries));
            report.append("\nIndex sizes:\n");
            
            for (Map.Entry<String, Integer> entry : indexSizes.entrySet()) {
                report.append(String.format("  %s: %d entries\n", entry.getKey(), entry.getValue()));
            }
            
            return report.toString();
        }
    }
    
    /**
     * Builder for creating index managers.
     */
    public static class Builder {
        private String managerId = "default";
        
        public Builder withManagerId(String managerId) {
            this.managerId = managerId;
            return this;
        }
        
        public IndexManager build() {
            return new IndexManager(managerId);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
