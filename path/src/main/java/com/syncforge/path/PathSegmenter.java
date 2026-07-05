package com.syncforge.path;

import java.util.ArrayList;
import java.util.List;

/**
 * Segments paths into hierarchical components for efficient processing.
 * Supports various segmentation strategies and component analysis.
 */
public class PathSegmenter {
    
    private final boolean preserveEmptySegments;
    private final boolean trimWhitespace;
    
    public PathSegmenter() {
        this(false, true);
    }
    
    public PathSegmenter(boolean preserveEmptySegments, boolean trimWhitespace) {
        this.preserveEmptySegments = preserveEmptySegments;
        this.trimWhitespace = trimWhitespace;
    }
    
    /**
     * Segments a path into its components.
     */
    public List<String> segment(String path) {
        if (path == null || path.isEmpty()) {
            return new ArrayList<>();
        }
        
        // Normalize separators
        String normalized = path.replace('\\', '/');
        
        // Trim whitespace if enabled
        if (trimWhitespace) {
            normalized = normalized.trim();
        }
        
        // Split by separator
        String[] parts = normalized.split("/");
        List<String> segments = new ArrayList<>();
        
        for (String part : parts) {
            if (trimWhitespace) {
                part = part.trim();
            }
            
            if (preserveEmptySegments || !part.isEmpty()) {
                segments.add(part);
            }
        }
        
        return segments;
    }
    
    /**
     * Segments a path and returns segments as an array.
     */
    public String[] segmentArray(String path) {
        List<String> segments = segment(path);
        return segments.toArray(new String[0]);
    }
    
    /**
     * Gets the depth of a path (number of segments).
     */
    public int getDepth(String path) {
        return segment(path).size();
    }
    
    /**
     * Gets the segment at a specific depth.
     */
    public String getSegmentAtDepth(String path, int depth) {
        List<String> segments = segment(path);
        if (depth < 0 || depth >= segments.size()) {
            return null;
        }
        return segments.get(depth);
    }
    
    /**
     * Gets the common prefix of two paths.
     */
    public String getCommonPrefix(String path1, String path2) {
        List<String> segments1 = segment(path1);
        List<String> segments2 = segment(path2);
        
        int commonLength = 0;
        while (commonLength < Math.min(segments1.size(), segments2.size())) {
            if (!segments1.get(commonLength).equals(segments2.get(commonLength))) {
                break;
            }
            commonLength++;
        }
        
        if (commonLength == 0) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < commonLength; i++) {
            if (i > 0) {
                result.append("/");
            }
            result.append(segments1.get(i));
        }
        
        return result.toString();
    }
    
    /**
     * Checks if one path is a prefix of another.
     */
    public boolean isPrefix(String potentialPrefix, String path) {
        String common = getCommonPrefix(potentialPrefix, path);
        String normalizedPrefix = potentialPrefix.replace('\\', '/');
        return common.equals(normalizedPrefix);
    }
    
    /**
     * Gets the suffix of a path after a given prefix.
     */
    public String getSuffix(String path, String prefix) {
        String common = getCommonPrefix(path, prefix);
        if (!common.equals(prefix.replace('\\', '/'))) {
            return null;
        }
        
        List<String> pathSegments = segment(path);
        List<String> prefixSegments = segment(prefix);
        
        if (pathSegments.size() <= prefixSegments.size()) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = prefixSegments.size(); i < pathSegments.size(); i++) {
            if (result.length() > 0) {
                result.append("/");
            }
            result.append(pathSegments.get(i));
        }
        
        return result.toString();
    }
    
    /**
     * Reverses the segments of a path.
     */
    public String reverseSegments(String path) {
        List<String> segments = segment(path);
        java.util.Collections.reverse(segments);
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < segments.size(); i++) {
            if (i > 0) {
                result.append("/");
            }
            result.append(segments.get(i));
        }
        
        return result.toString();
    }
    
    /**
     * Removes duplicate consecutive segments.
     */
    public String removeDuplicateSegments(String path) {
        List<String> segments = segment(path);
        List<String> deduplicated = new ArrayList<>();
        
        String lastSegment = null;
        for (String segment : segments) {
            if (!segment.equals(lastSegment)) {
                deduplicated.add(segment);
                lastSegment = segment;
            }
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < deduplicated.size(); i++) {
            if (i > 0) {
                result.append("/");
            }
            result.append(deduplicated.get(i));
        }
        
        return result.toString();
    }
    
    /**
     * Truncates a path to a maximum depth.
     */
    public String truncateToDepth(String path, int maxDepth) {
        List<String> segments = segment(path);
        
        if (segments.size() <= maxDepth) {
            return path;
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxDepth; i++) {
            if (i > 0) {
                result.append("/");
            }
            result.append(segments.get(i));
        }
        
        return result.toString();
    }
    
    /**
     * Pads a path to a minimum depth with empty segments.
     */
    public String padToDepth(String path, int minDepth) {
        List<String> segments = segment(path);
        
        if (segments.size() >= minDepth) {
            return path;
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < minDepth; i++) {
            if (i > 0) {
                result.append("/");
            }
            if (i < segments.size()) {
                result.append(segments.get(i));
            } else {
                result.append("");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Gets the longest common segment sequence between paths.
     */
    public List<String> getLongestCommonSegmentSequence(String path1, String path2) {
        List<String> segments1 = segment(path1);
        List<String> segments2 = segment(path2);
        
        List<String> common = new ArrayList<>();
        int maxCommon = 0;
        int startIdx = 0;
        
        // Find longest common subsequence
        for (int i = 0; i < segments1.size(); i++) {
            for (int j = 0; j < segments2.size(); j++) {
                int currentCommon = 0;
                int k = 0;
                while (i + k < segments1.size() && j + k < segments2.size() &&
                       segments1.get(i + k).equals(segments2.get(j + k))) {
                    currentCommon++;
                    k++;
                }
                
                if (currentCommon > maxCommon) {
                    maxCommon = currentCommon;
                    startIdx = i;
                }
            }
        }
        
        for (int i = 0; i < maxCommon; i++) {
            common.add(segments1.get(startIdx + i));
        }
        
        return common;
    }
}
