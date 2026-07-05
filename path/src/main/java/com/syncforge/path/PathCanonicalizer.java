package com.syncforge.path;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Advanced path canonicalization with support for complex path resolution scenarios.
 * Handles path normalization, resolution of relative paths, and elimination of redundant components.
 */
public class PathCanonicalizer {
    
    private final boolean caseSensitive;
    private final boolean normalizeUnicode;
    
    public PathCanonicalizer() {
        this(true, true);
    }
    
    public PathCanonicalizer(boolean caseSensitive, boolean normalizeUnicode) {
        this.caseSensitive = caseSensitive;
        this.normalizeUnicode = normalizeUnicode;
    }
    
    /**
     * Canonicalizes a path string to its simplest form.
     */
    public String canonicalize(String path) throws InvalidPathException {
        if (path == null || path.isEmpty()) {
            throw new InvalidPathException(path, "Path cannot be null or empty");
        }
        
        // Normalize separators
        String normalized = normalizeSeparators(path);
        
        // Apply unicode normalization if enabled
        if (normalizeUnicode) {
            normalized = normalizeUnicode(normalized);
        }
        
        // Split into components
        String[] components = normalized.split("/");
        Deque<String> stack = new ArrayDeque<>();
        
        for (String component : components) {
            if (component.isEmpty() || component.equals(".")) {
                continue;
            }
            
            if (component.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                stack.addLast(component);
            }
        }
        
        // Reconstruct path
        StringBuilder result = new StringBuilder();
        if (path.startsWith("/")) {
            result.append("/");
        }
        
        boolean first = true;
        for (String component : stack) {
            if (!first) {
                result.append("/");
            }
            result.append(component);
            first = false;
        }
        
        if (result.length() == 0) {
            return ".";
        }
        
        return result.toString();
    }
    
    /**
     * Resolves a path relative to a base path.
     */
    public String resolve(String base, String relative) throws InvalidPathException {
        String canonicalBase = canonicalize(base);
        String canonicalRelative = canonicalize(relative);
        
        if (canonicalRelative.startsWith("/")) {
            return canonicalRelative;
        }
        
        if (canonicalBase.equals(".")) {
            return canonicalRelative;
        }
        
        return canonicalBase + "/" + canonicalRelative;
    }
    
    /**
     * Computes the relative path from one path to another.
     */
    public String relativize(String from, String to) throws InvalidPathException {
        String canonicalFrom = canonicalize(from);
        String canonicalTo = canonicalize(to);
        
        String[] fromComponents = canonicalFrom.split("/");
        String[] toComponents = canonicalTo.split("/");
        
        int commonLength = 0;
        while (commonLength < Math.min(fromComponents.length, toComponents.length)) {
            if (!componentsEqual(fromComponents[commonLength], toComponents[commonLength])) {
                break;
            }
            commonLength++;
        }
        
        StringBuilder result = new StringBuilder();
        
        // Add ".." for remaining components in from
        for (int i = commonLength; i < fromComponents.length; i++) {
            if (result.length() > 0) {
                result.append("/");
            }
            result.append("..");
        }
        
        // Add remaining components from to
        for (int i = commonLength; i < toComponents.length; i++) {
            if (result.length() > 0) {
                result.append("/");
            }
            result.append(toComponents[i]);
        }
        
        if (result.length() == 0) {
            return ".";
        }
        
        return result.toString();
    }
    
    /**
     * Normalizes path separators to forward slashes.
     */
    private String normalizeSeparators(String path) {
        return path.replace('\\', '/');
    }
    
    /**
     * Normalizes unicode characters in path.
     */
    private String normalizeUnicode(String path) {
        // Apply NFC normalization
        return java.text.Normalizer.normalize(path, java.text.Normalizer.Form.NFC);
    }
    
    /**
     * Compares path components with case sensitivity consideration.
     */
    private boolean componentsEqual(String a, String b) {
        if (caseSensitive) {
            return a.equals(b);
        }
        return a.equalsIgnoreCase(b);
    }
    
    /**
     * Checks if a path is absolute.
     */
    public boolean isAbsolute(String path) {
        return path != null && path.startsWith("/");
    }
    
    /**
     * Gets the parent directory of a path.
     */
    public String getParent(String path) throws InvalidPathException {
        String canonical = canonicalize(path);
        int lastSlash = canonical.lastIndexOf('/');
        
        if (lastSlash <= 0) {
            return ".";
        }
        
        return canonical.substring(0, lastSlash);
    }
    
    /**
     * Gets the file name from a path.
     */
    public String getFileName(String path) throws InvalidPathException {
        String canonical = canonicalize(path);
        int lastSlash = canonical.lastIndexOf('/');
        
        if (lastSlash < 0) {
            return canonical;
        }
        
        return canonical.substring(lastSlash + 1);
    }
    
    /**
     * Gets the file extension from a path.
     */
    public String getExtension(String path) throws InvalidPathException {
        String fileName = getFileName(path);
        int lastDot = fileName.lastIndexOf('.');
        
        if (lastDot <= 0 || lastDot == fileName.length() - 1) {
            return "";
        }
        
        return fileName.substring(lastDot + 1);
    }
    
    /**
     * Gets the base name without extension.
     */
    public String getBaseName(String path) throws InvalidPathException {
        String fileName = getFileName(path);
        int lastDot = fileName.lastIndexOf('.');
        
        if (lastDot <= 0) {
            return fileName;
        }
        
        return fileName.substring(0, lastDot);
    }
    
    /**
     * Joins multiple path components.
     */
    public String join(String... components) throws InvalidPathException {
        if (components == null || components.length == 0) {
            return ".";
        }
        
        String result = components[0];
        for (int i = 1; i < components.length; i++) {
            result = resolve(result, components[i]);
        }
        
        return canonicalize(result);
    }
}
