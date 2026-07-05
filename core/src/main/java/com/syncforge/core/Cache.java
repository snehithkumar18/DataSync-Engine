package com.syncforge.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A thread-safe LRU (Least Recently Used) cache implementation.
 * Supports automatic eviction, size-based limits, and computed values.
 */
public class Cache<K, V> {
    
    private final LinkedHashMap<K, V> map;
    private final int maxSize;
    private final Function<K, V> computeFunction;
    private final Object lock = new Object();
    private long hitCount;
    private long missCount;
    private long evictionCount;
    
    public Cache(int maxSize, Function<K, V> computeFunction) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be positive");
        }
        
        this.maxSize = maxSize;
        this.computeFunction = computeFunction;
        
        // Access-order LinkedHashMap for LRU
        this.map = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                boolean evict = size() > maxSize;
                if (evict) {
                    evictionCount++;
                }
                return evict;
            }
        };
    }
    
    public Cache(int maxSize) {
        this(maxSize, null);
    }
    
    /**
     * Gets a value from the cache, computing it if not present and a compute function is set.
     */
    public V get(K key) {
        synchronized (lock) {
            V value = map.get(key);
            if (value != null) {
                hitCount++;
                return value;
            }
            
            missCount++;
            
            if (computeFunction != null) {
                value = computeFunction.apply(key);
                if (value != null) {
                    put(key, value);
                }
                return value;
            }
            
            return null;
        }
    }
    
    /**
     * Puts a value into the cache.
     */
    public void put(K key, V value) {
        synchronized (lock) {
            map.put(key, value);
        }
    }
    
    /**
     * Removes a value from the cache.
     */
    public V remove(K key) {
        synchronized (lock) {
            return map.remove(key);
        }
    }
    
    /**
     * Checks if the cache contains a key.
     */
    public boolean containsKey(K key) {
        synchronized (lock) {
            return map.containsKey(key);
        }
    }
    
    /**
     * Gets the current size of the cache.
     */
    public int size() {
        synchronized (lock) {
            return map.size();
        }
    }
    
    /**
     * Clears the cache.
     */
    public void clear() {
        synchronized (lock) {
            map.clear();
            hitCount = 0;
            missCount = 0;
            evictionCount = 0;
        }
    }
    
    /**
     * Gets the hit count.
     */
    public long getHitCount() {
        synchronized (lock) {
            return hitCount;
        }
    }
    
    /**
     * Gets the miss count.
     */
    public long getMissCount() {
        synchronized (lock) {
            return missCount;
        }
    }
    
    /**
     * Gets the eviction count.
     */
    public long getEvictionCount() {
        synchronized (lock) {
            return evictionCount;
        }
    }
    
    /**
     * Gets the hit rate (0.0 to 1.0).
     */
    public double getHitRate() {
        synchronized (lock) {
            long total = hitCount + missCount;
            if (total == 0) {
                return 0.0;
            }
            return (double) hitCount / total;
        }
    }
    
    /**
     * Gets the maximum size of the cache.
     */
    public int getMaxSize() {
        return maxSize;
    }
    
    /**
     * Gets cache statistics.
     */
    public CacheStats getStats() {
        synchronized (lock) {
            return new CacheStats(size(), hitCount, missCount, evictionCount, getHitRate());
        }
    }
    
    /**
     * Cache statistics record.
     */
    public record CacheStats(
        int currentSize,
        long hitCount,
        long missCount,
        long evictionCount,
        double hitRate
    ) {}
    
    /**
     * Builder for creating caches.
     */
    public static class Builder<K, V> {
        private int maxSize = 100;
        private Function<K, V> computeFunction;
        
        public Builder<K, V> withMaxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }
        
        public Builder<K, V> withComputeFunction(Function<K, V> computeFunction) {
            this.computeFunction = computeFunction;
            return this;
        }
        
        public Cache<K, V> build() {
            return new Cache<>(maxSize, computeFunction);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }
}
