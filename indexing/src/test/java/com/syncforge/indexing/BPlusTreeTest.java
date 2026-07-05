package com.syncforge.indexing;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BPlusTreeTest {

    @Test
    public void testBPlusTreeLookups() {
        BPlusTree<String, Integer> tree = new BPlusTree<>(3); // low degree to force splits
        
        tree.insert("apple", 1);
        tree.insert("banana", 2);
        tree.insert("cherry", 3);
        tree.insert("date", 4);
        tree.insert("elderberry", 5);

        // Test search
        assertEquals(1, tree.search("apple"));
        assertEquals(3, tree.search("cherry"));
        assertEquals(5, tree.search("elderberry"));
        assertNull(tree.search("fig"));
    }

    @Test
    public void testRangeQueries() {
        BPlusTree<Long, String> tree = new BPlusTree<>(3);

        tree.insert(100L, "File A");
        tree.insert(200L, "File B");
        tree.insert(300L, "File C");
        tree.insert(400L, "File D");
        tree.insert(500L, "File E");

        // Range query [150, 450] -> should return B, C, D
        List<String> rangeResult = tree.searchRange(150L, 450L);
        assertEquals(3, rangeResult.size());
        assertTrue(rangeResult.contains("File B"));
        assertTrue(rangeResult.contains("File C"));
        assertTrue(rangeResult.contains("File D"));
        assertFalse(rangeResult.contains("File A"));
        assertFalse(rangeResult.contains("File E"));
    }
}
