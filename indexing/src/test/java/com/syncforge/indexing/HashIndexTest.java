package com.syncforge.indexing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HashIndex.
 */
@DisplayName("HashIndex Tests")
class HashIndexTest {
    
    private HashIndex<String, String> index;
    
    @BeforeEach
    void setUp() {
        index = new HashIndex<>("test-index");
    }
    
    @Test
    @DisplayName("Insert and retrieve value")
    void testInsertAndRetrieve() {
        index.insert("key1", "value1");
        
        assertEquals("value1", index.lookup("key1"));
        assertTrue(index.contains("key1"));
    }
    
    @Test
    @DisplayName("Update existing key")
    void testUpdateExistingKey() {
        index.insert("key1", "value1");
        index.insert("key1", "value2");
        
        assertEquals("value2", index.lookup("key1"));
    }
    
    @Test
    @DisplayName("Remove key")
    void testRemoveKey() {
        index.insert("key1", "value1");
        String removed = index.remove("key1");
        
        assertEquals("value1", removed);
        assertFalse(index.contains("key1"));
    }
    
    @Test
    @DisplayName("Remove non-existent key returns null")
    void testRemoveNonExistentKey() {
        String removed = index.remove("nonexistent");
        
        assertNull(removed);
    }
    
    @Test
    @DisplayName("Size is tracked correctly")
    void testSizeTracking() {
        assertEquals(0, index.size());
        
        index.insert("key1", "value1");
        assertEquals(1, index.size());
        
        index.insert("key2", "value2");
        assertEquals(2, index.size());
        
        index.remove("key1");
        assertEquals(1, index.size());
    }
    
    @Test
    @DisplayName("Get all keys")
    void testGetAllKeys() {
        index.insert("key1", "value1");
        index.insert("key2", "value2");
        index.insert("key3", "value3");
        
        assertEquals(3, index.getKeys().size());
        assertTrue(index.getKeys().contains("key1"));
        assertTrue(index.getKeys().contains("key2"));
        assertTrue(index.getKeys().contains("key3"));
    }
    
    @Test
    @DisplayName("Get all values")
    void testGetAllValues() {
        index.insert("key1", "value1");
        index.insert("key2", "value2");
        
        assertEquals(2, index.getValues().size());
        assertTrue(index.getValues().contains("value1"));
        assertTrue(index.getValues().contains("value2"));
    }
    
    @Test
    @DisplayName("Clear index")
    void testClearIndex() {
        index.insert("key1", "value1");
        index.insert("key2", "value2");
        
        index.clear();
        
        assertEquals(0, index.size());
        assertTrue(index.isEmpty());
    }
    
    @Test
    @DisplayName("Handle null key")
    void testHandleNullKey() {
        index.insert(null, "value");
        
        assertEquals(0, index.size());
    }
    
    @Test
    @DisplayName("Index name is preserved")
    void testIndexName() {
        assertEquals("test-index", index.getName());
    }
}
