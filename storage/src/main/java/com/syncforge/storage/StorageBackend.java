package com.syncforge.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Interface for storage backends.
 * Defines the contract for different storage implementations.
 */
public interface StorageBackend {
    
    /**
     * Stores data with the given key.
     */
    void store(String key, byte[] data) throws IOException;
    
    /**
     * Stores data from an input stream.
     */
    void store(String key, InputStream data) throws IOException;
    
    /**
     * Retrieves data by key.
     */
    byte[] retrieve(String key) throws IOException;
    
    /**
     * Retrieves data as an input stream.
     */
    InputStream retrieveAsStream(String key) throws IOException;
    
    /**
     * Checks if data exists for the given key.
     */
    boolean exists(String key) throws IOException;
    
    /**
     * Deletes data by key.
     */
    void delete(String key) throws IOException;
    
    /**
     * Lists all keys in the storage.
     */
    java.util.List<String> listKeys() throws IOException;
    
    /**
     * Lists keys matching a prefix.
     */
    java.util.List<String> listKeys(String prefix) throws IOException;
    
    /**
     * Gets metadata for a key.
     */
    Map<String, String> getMetadata(String key) throws IOException;
    
    /**
     * Sets metadata for a key.
     */
    void setMetadata(String key, Map<String, String> metadata) throws IOException;
    
    /**
     * Gets the size of data for a key.
     */
    long getSize(String key) throws IOException;
    
    /**
     * Clears all data from the storage.
     */
    void clear() throws IOException;
    
    /**
     * Closes the storage backend and releases resources.
     */
    void close() throws IOException;
    
    /**
     * Gets the backend type identifier.
     */
    String getBackendType();
    
    /**
     * Checks if the backend is available.
     */
    boolean isAvailable();
}
