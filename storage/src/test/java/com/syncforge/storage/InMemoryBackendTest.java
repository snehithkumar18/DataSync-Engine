package com.syncforge.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Unit tests for InMemoryBackend.
 */
@DisplayName("InMemoryBackend Tests")
class InMemoryBackendTest {
    
    private InMemoryBackend backend;
    
    @BeforeEach
    void setUp() {
        backend = new InMemoryBackend("test-backend");
    }
    
    @Test
    @DisplayName("Store and retrieve data")
    void testStoreAndRetrieve() throws IOException {
        byte[] data = "test data".getBytes();
        backend.store("key1", data);
        
        byte[] retrieved = backend.retrieve("key1");
        
        assertArrayEquals(data, retrieved);
    }
    
    @Test
    @DisplayName("Store from input stream")
    void testStoreFromInputStream() throws IOException {
        byte[] data = "stream data".getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        
        backend.store("key1", stream);
        
        byte[] retrieved = backend.retrieve("key1");
        
        assertArrayEquals(data, retrieved);
    }
    
    @Test
    @DisplayName("Retrieve as stream")
    void testRetrieveAsStream() throws IOException {
        byte[] data = "test data".getBytes();
        backend.store("key1", data);
        
        var stream = backend.retrieveAsStream("key1");
        
        assertNotNull(stream);
        byte[] retrieved = stream.readAllBytes();
        assertArrayEquals(data, retrieved);
    }
    
    @Test
    @DisplayName("Check existence")
    void testCheckExistence() throws IOException {
        backend.store("key1", "data".getBytes());
        
        assertTrue(backend.exists("key1"));
        assertFalse(backend.exists("nonexistent"));
    }
    
    @Test
    @DisplayName("Delete data")
    void testDeleteData() throws IOException {
        backend.store("key1", "data".getBytes());
        backend.delete("key1");
        
        assertFalse(backend.exists("key1"));
        assertNull(backend.retrieve("key1"));
    }
    
    @Test
    @DisplayName("List all keys")
    void testListAllKeys() throws IOException {
        backend.store("key1", "data1".getBytes());
        backend.store("key2", "data2".getBytes());
        backend.store("key3", "data3".getBytes());
        
        List<String> keys = backend.listKeys();
        
        assertEquals(3, keys.size());
        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
        assertTrue(keys.contains("key3"));
    }
    
    @Test
    @DisplayName("List keys with prefix")
    void testListKeysWithPrefix() throws IOException {
        backend.store("prefix:key1", "data1".getBytes());
        backend.store("prefix:key2", "data2".getBytes());
        backend.store("other:key3", "data3".getBytes());
        
        List<String> keys = backend.listKeys("prefix:");
        
        assertEquals(2, keys.size());
        assertTrue(keys.contains("prefix:key1"));
        assertTrue(keys.contains("prefix:key2"));
        assertFalse(keys.contains("other:key3"));
    }
    
    @Test
    @DisplayName("Get and set metadata")
    void testGetAndSetMetadata() throws IOException {
        backend.store("key1", "data".getBytes());
        
        Map<String, String> metadata = Map.of("author", "test", "version", "1.0");
        backend.setMetadata("key1", metadata);
        
        Map<String, String> retrieved = backend.getMetadata("key1");
        
        assertEquals("test", retrieved.get("author"));
        assertEquals("1.0", retrieved.get("version"));
    }
    
    @Test
    @DisplayName("Get size")
    void testGetSize() throws IOException {
        byte[] data = "test data".getBytes();
        backend.store("key1", data);
        
        long size = backend.getSize("key1");
        
        assertEquals(data.length, size);
    }
    
    @Test
    @DisplayName("Get size for non-existent key")
    void testGetSizeForNonExistentKey() throws IOException {
        long size = backend.getSize("nonexistent");
        
        assertEquals(-1, size);
    }
    
    @Test
    @DisplayName("Clear all data")
    void testClearAll() throws IOException {
        backend.store("key1", "data1".getBytes());
        backend.store("key2", "data2".getBytes());
        
        backend.clear();
        
        assertEquals(0, backend.getKeyCount());
        assertFalse(backend.exists("key1"));
    }
    
    @Test
    @DisplayName("Track total size")
    void testTrackTotalSize() throws IOException {
        backend.store("key1", "data1".getBytes());
        backend.store("key2", "data2".getBytes());
        
        assertEquals(10, backend.getTotalSize()); // "data1" + "data2" = 10 bytes
    }
    
    @Test
    @DisplayName("Throw exception on null key")
    void testThrowExceptionOnNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            backend.store(null, "data".getBytes());
        });
    }
    
    @Test
    @DisplayName("Backend is always available")
    void testBackendAlwaysAvailable() {
        assertTrue(backend.isAvailable());
    }
    
    @Test
    @DisplayName("Get backend type")
    void testGetBackendType() {
        assertEquals("memory", backend.getBackendType());
    }
}
