package com.syncforge.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A trie data structure optimized for path storage and lookup.
 * Supports efficient prefix matching, path insertion, and traversal.
 */
public class PathTrie<T> {
    
    private final Node<T> root;
    private final char separator;
    private int size;
    
    public PathTrie() {
        this('/');
    }
    
    public PathTrie(char separator) {
        this.separator = separator;
        this.root = new Node<>();
        this.size = 0;
    }
    
    /**
     * Inserts a path into the trie with an associated value.
     */
    public void insert(String path, T value) {
        if (path == null || path.isEmpty()) {
            return;
        }
        
        String[] segments = path.split(String.valueOf(separator));
        Node<T> current = root;
        
        for (String segment : segments) {
            if (segment.isEmpty()) {
                continue;
            }
            
            if (!current.children.containsKey(segment)) {
                current.children.put(segment, new Node<>());
            }
            
            current = current.children.get(segment);
        }
        
        if (current.value == null) {
            size++;
        }
        
        current.value = value;
        current.path = path;
    }
    
    /**
     * Looks up a path in the trie.
     */
    public T lookup(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        
        String[] segments = path.split(String.valueOf(separator));
        Node<T> current = root;
        
        for (String segment : segments) {
            if (segment.isEmpty()) {
                continue;
            }
            
            if (!current.children.containsKey(segment)) {
                return null;
            }
            
            current = current.children.get(segment);
        }
        
        return current.value;
    }
    
    /**
     * Checks if a path exists in the trie.
     */
    public boolean contains(String path) {
        return lookup(path) != null;
    }
    
    /**
     * Finds all values that have the given path as a prefix.
     */
    public List<T> findWithPrefix(String prefix) {
        List<T> results = new ArrayList<>();
        
        if (prefix == null || prefix.isEmpty()) {
            collectAll(root, results);
            return results;
        }
        
        String[] segments = prefix.split(String.valueOf(separator));
        Node<T> current = root;
        
        for (String segment : segments) {
            if (segment.isEmpty()) {
                continue;
            }
            
            if (!current.children.containsKey(segment)) {
                return results;
            }
            
            current = current.children.get(segment);
        }
        
        collectAll(current, results);
        return results;
    }
    
    /**
     * Finds the longest matching prefix path.
     */
    public String findLongestPrefix(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        
        String[] segments = path.split(String.valueOf(separator));
        Node<T> current = root;
        String longestMatch = null;
        
        for (String segment : segments) {
            if (segment.isEmpty()) {
                continue;
            }
            
            if (!current.children.containsKey(segment)) {
                break;
            }
            
            current = current.children.get(segment);
            
            if (current.value != null) {
                longestMatch = current.path;
            }
        }
        
        return longestMatch;
    }
    
    /**
     * Removes a path from the trie.
     */
    public T remove(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        
        String[] segments = path.split(String.valueOf(separator));
        List<Node<T>> pathNodes = new ArrayList<>();
        Node<T> current = root;
        
        for (String segment : segments) {
            if (segment.isEmpty()) {
                continue;
            }
            
            if (!current.children.containsKey(segment)) {
                return null;
            }
            
            pathNodes.add(current);
            current = current.children.get(segment);
        }
        
        T value = current.value;
        if (value != null) {
            size--;
        }
        
        current.value = null;
        current.path = null;
        
        // Clean up empty nodes
        for (int i = pathNodes.size() - 1; i >= 0; i--) {
            Node<T> node = pathNodes.get(i);
            String segment = segments[i];
            
            Node<T> child = node.children.get(segment);
            if (child.value == null && child.children.isEmpty()) {
                node.children.remove(segment);
            } else {
                break;
            }
        }
        
        return value;
    }
    
    /**
     * Gets all paths in the trie.
     */
    public List<String> getAllPaths() {
        List<String> paths = new ArrayList<>();
        collectPaths(root, paths);
        return paths;
    }
    
    /**
     * Gets all values in the trie.
     */
    public List<T> getAllValues() {
        List<T> values = new ArrayList<>();
        collectAll(root, values);
        return values;
    }
    
    /**
     * Gets the number of paths in the trie.
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the trie is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Clears all entries from the trie.
     */
    public void clear() {
        root.children.clear();
        root.value = null;
        root.path = null;
        size = 0;
    }
    
    private void collectAll(Node<T> node, List<T> results) {
        if (node.value != null) {
            results.add(node.value);
        }
        
        for (Node<T> child : node.children.values()) {
            collectAll(child, results);
        }
    }
    
    private void collectPaths(Node<T> node, List<String> paths) {
        if (node.path != null) {
            paths.add(node.path);
        }
        
        for (Node<T> child : node.children.values()) {
            collectPaths(child, paths);
        }
    }
    
    private static class Node<T> {
        Map<String, Node<T>> children = new HashMap<>();
        T value;
        String path;
    }
}
