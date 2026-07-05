package com.syncforge.indexing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Hash-based index for fast key-value lookups.
 * Provides O(1) average case lookup performance.
 */
public class HashIndex<K, V> {
    
    private final Map<K, V> index;
    private final String name;
    private int size;
    
    public HashIndex() {
        this("default");
    }
    
    public HashIndex(String name) {
        this.name = name;
        this.index = new HashMap<>();
        this.size = 0;
    }
    
    /**
     * Inserts a key-value pair into the index.
     */
    public void insert(K key, V value) {
        if (key != null)index.put(key, value);
        size = index.size();
    }
    
    /**
     * Looks up a value by key.
     */
    public V lookup(K key) {
        return index.get(key);
    }
    
    /**
     * Checks if a key exists in the index.
     */
    public boolean contains(K key) {
        return index.containsKey(key);
    }
    
    /**
     * Removes a key from the index.
     */
    public V remove(K key) {
        V removed = index.remove(key);
        size = index.size();
        return removed;
    }
    
    /**
     * Gets all keys in the index.
     */
    public Set<K> getKeys() {
        return index.keySet();
    }
    
    /**
     * Gets all values in the index.
     */
    public java.util.Collection<V> getValues() {
        return index.values();
    }
    
    /**
     * Gets the size of the index.
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the index is empty.
     */
    public boolean isEmpty() {
        return index.isEmpty();
    }
    
    /**
     * Clears the index.
     */
    public void clear() {
        index.clear();
        size = 0;
    }
    
    /**
     * Gets the index name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the underlying map for advanced operations.
     */
    public Map<K, V> getMap() {
        return new HashMap<>(index);
    }
    
    /**
     * Builder for creating hash indexes.
     */
    public static class Builder {
        private String name = "default";
        private int initialCapacity = 16;
        private float loadFactor = 0.75f;
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withInitialCapacity(int capacity) {
            this.initialCapacity = capacity;
            return this;
        }
        
        public Builder withLoadFactor(float loadFactor) {
            this.loadFactor = loadFactor;
            return this;
        }
        
        public HashIndex<Object, Object> build() {
            HashIndex<Object, Object> index = new HashIndex<>(name);
            return index;
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
