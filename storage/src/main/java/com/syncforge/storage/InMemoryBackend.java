package com.syncforge.storage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory storage backend.
 * Stores data in memory for fast access without persistence.
 */
public class InMemoryBackend implements StorageBackend {
    
    private final Map<String, byte[]> dataStore;
    private final Map<String, Map<String, String>> metadataStore;
    private final String backendId;
    private long totalSize;
    
    public InMemoryBackend() {
        this("default");
    }
    
    public InMemoryBackend(String backendId) {
        this.backendId = backendId;
        this.dataStore = new ConcurrentHashMap<>();
        this.metadataStore = new ConcurrentHashMap<>();
        this.totalSize = 0;
    }
    
    @Override
    public void store(String key, byte[] data) throws IOException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        byte[] existing = dataStore.put(key, data);
        
        if (existing != null) {
            totalSize -= existing.length;
        }
        
        if (data != null) {
            totalSize += data.length;
        }
    }
    
    @Override
    public void store(String key, InputStream data) throws IOException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        byte[] bytes = data.readAllBytes();
        store(key, bytes);
    }
    
    @Override
    public byte[] retrieve(String key) throws IOException {
        return dataStore.get(key);
    }
    
    @Override
    public InputStream retrieveAsStream(String key) throws IOException {
        byte[] data = dataStore.get(key);
        return data != null ? new ByteArrayInputStream(data) : null;
    }
    
    @Override
    public boolean exists(String key) throws IOException {
        return dataStore.containsKey(key);
    }
    
    @Override
    public void delete(String key) throws IOException {
        byte[] removed = dataStore.remove(key);
        
        if (removed != null) {
            totalSize -= removed.length;
        }
        
        metadataStore.remove(key);
    }
    
    @Override
    public List<String> listKeys() throws IOException {
        return new java.util.ArrayList<>(dataStore.keySet());
    }
    
    @Override
    public List<String> listKeys(String prefix) throws IOException {
        List<String> matchingKeys = new java.util.ArrayList<>();
        
        for (String key : dataStore.keySet()) {
            if (key.startsWith(prefix)) {
                matchingKeys.add(key);
            }
        }
        
        return matchingKeys;
    }
    
    @Override
    public Map<String, String> getMetadata(String key) throws IOException {
        return metadataStore.getOrDefault(key, new HashMap<>());
    }
    
    @Override
    public void setMetadata(String key, Map<String, String> metadata) throws IOException {
        if (metadata != null) {
            metadataStore.put(key, new HashMap<>(metadata));
        } else {
            metadataStore.remove(key);
        }
    }
    
    @Override
    public long getSize(String key) throws IOException {
        byte[] data = dataStore.get(key);
        return data != null ? data.length : -1;
    }
    
    @Override
    public void clear() throws IOException {
        dataStore.clear();
        metadataStore.clear();
        totalSize = 0;
    }
    
    @Override
    public void close() throws IOException {
        clear();
    }
    
    @Override
    public String getBackendType() {
        return "memory";
    }
    
    @Override
    public boolean isAvailable() {
        return true;
    }
    
    /**
     * Gets the total size of all stored data.
     */
    public long getTotalSize() {
        return totalSize;
    }
    
    /**
     * Gets the number of stored keys.
     */
    public int getKeyCount() {
        return dataStore.size();
    }
    
    /**
     * Gets the backend ID.
     */
    public String getBackendId() {
        return backendId;
    }
    
    /**
     * Builder for creating in-memory backends.
     */
    public static class Builder {
        private String backendId = "default";
        
        public Builder withBackendId(String backendId) {
            this.backendId = backendId;
            return this;
        }
        
        public InMemoryBackend build() {
            return new InMemoryBackend(backendId);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
