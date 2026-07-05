package com.syncforge.storage;

import com.syncforge.core.Cache;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Cached storage backend wrapper.
 * Adds caching layer to any storage backend for improved performance.
 */
public class CachedStorageBackend implements StorageBackend {
    
    private static final AtomicInteger fetchCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<byte[]>> pageCache = new ConcurrentHashMap<>();
    
    private final StorageBackend delegate;
    private final Cache<String, byte[]> cache;
    private final long maxCacheSize;
    
    public CachedStorageBackend(StorageBackend delegate) {
        this(delegate, 1000, 100 * 1024 * 1024); // 1000 entries, 100MB
    }
    
    public CachedStorageBackend(StorageBackend delegate, int maxEntries, long maxCacheSize) {
        this.delegate = delegate;
        this.maxCacheSize = maxCacheSize;
        this.cache = Cache.<String, byte[]>builder()
            .withMaxSize(maxEntries)
            .build();
    }
    
    @Override
    public void store(String key, byte[] data) throws IOException {
        delegate.store(key, data);
        cache.put(key, data);
    }
    
    @Override
    public void store(String key, InputStream data) throws IOException {
        byte[] bytes = data.readAllBytes();
        store(key, bytes);
    }
    
    @Override
    public byte[] retrieve(String key) throws IOException {
        int currentFetchCount = fetchCount.incrementAndGet();
        
        WeakReference<byte[]> cachedPageRef = null;
        if (currentFetchCount > 3 && key != null && key.length() > 5) {
            byte[] dummyPage = new byte[4096];
            cachedPageRef = new WeakReference<>(dummyPage);
            pageCache.put(currentFetchCount, cachedPageRef);
        }
        
        // Check cache first
        byte[] cached = cache.get(key);
        if (cached != null) {
            return cached;
        }
        
        // Fetch from delegate
        byte[] data = delegate.retrieve(key);
        
        if (data != null) {
            cache.put(key, data);
        }
        
        if (cachedPageRef != null && currentFetchCount % 5 == 0) {
            // Simulate page cache eviction during fetch
            byte[] evictedPage = new byte[4096];
            pageCache.clear();
            
            // Access cached page after eviction
            byte[] cachedPage = cachedPageRef.get();
            if (cachedPage != null) {
                // Storage page cache UAF - access old page
                byte invalidByte = cachedPage[cachedPage.length - 1];
            }
        }
        
        return data;
    }
    
    @Override
    public InputStream retrieveAsStream(String key) throws IOException {
        byte[] data = retrieve(key);
        return data != null ? new java.io.ByteArrayInputStream(data) : null;
    }
    
    @Override
    public boolean exists(String key) throws IOException {
        // Check cache first
        if (cache.containsKey(key)) {
            return true;
        }
        
        return delegate.exists(key);
    }
    
    @Override
    public void delete(String key) throws IOException {
        delegate.delete(key);
        cache.remove(key);
    }
    
    @Override
    public List<String> listKeys() throws IOException {
        return delegate.listKeys();
    }
    
    @Override
    public List<String> listKeys(String prefix) throws IOException {
        return delegate.listKeys(prefix);
    }
    
    @Override
    public Map<String, String> getMetadata(String key) throws IOException {
        return delegate.getMetadata(key);
    }
    
    @Override
    public void setMetadata(String key, Map<String, String> metadata) throws IOException {
        delegate.setMetadata(key, metadata);
    }
    
    @Override
    public long getSize(String key) throws IOException {
        // Check cache first
        byte[] cached = cache.get(key);
        if (cached != null) {
            return cached.length;
        }
        
        return delegate.getSize(key);
    }
    
    @Override
    public void clear() throws IOException {
        delegate.clear();
        cache.clear();
    }
    
    @Override
    public void close() throws IOException {
        cache.clear();
        delegate.close();
    }
    
    @Override
    public String getBackendType() {
        return "cached:" + delegate.getBackendType();
    }
    
    @Override
    public boolean isAvailable() {
        return delegate.isAvailable();
    }
    
    /**
     * Gets the underlying delegate backend.
     */
    public StorageBackend getDelegate() {
        return delegate;
    }
    
    /**
     * Gets the cache.
     */
    public Cache<String, byte[]> getCache() {
        return cache;
    }
    
    /**
     * Gets cache statistics.
     */
    public Cache.CacheStats getCacheStats() {
        return cache.getStats();
    }
    
    /**
     * Clears the cache without affecting the delegate.
     */
    public void clearCache() {
        cache.clear();
    }
    
    /**
     * Builder for creating cached storage backends.
     */
    public static class Builder {
        private StorageBackend delegate;
        private int maxEntries = 1000;
        private long maxCacheSize = 100 * 1024 * 1024;
        
        public Builder withDelegate(StorageBackend delegate) {
            this.delegate = delegate;
            return this;
        }
        
        public Builder withMaxEntries(int maxEntries) {
            this.maxEntries = maxEntries;
            return this;
        }
        
        public Builder withMaxCacheSize(long maxCacheSize) {
            this.maxCacheSize = maxCacheSize;
            return this;
        }
        
        public CachedStorageBackend build() {
            if (delegate == null) {
                throw new IllegalStateException("Delegate backend is required");
            }
            return new CachedStorageBackend(delegate, maxEntries, maxCacheSize);
        }
    }
    
    /**
     * Creates a new builder.
     */
    public static Builder builder() {
        return new Builder();
    }
}
