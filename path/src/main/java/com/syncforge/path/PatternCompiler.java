package com.syncforge.path;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Compiles glob patterns into optimized regex patterns.
 * Supports complex glob syntax including character classes, ranges, and wildcards.
 */
public class PatternCompiler {
    
    private final boolean caseSensitive;
    private final boolean dotFilesMatch;
    
    public PatternCompiler() {
        this(true, false);
    }
    
    public PatternCompiler(boolean caseSensitive, boolean dotFilesMatch) {
        this.caseSensitive = caseSensitive;
        this.dotFilesMatch = dotFilesMatch;
    }
    
    /**
     * Compiles a glob pattern to a regex Pattern.
     */
    public Pattern compile(String glob) {
        String regex = globToRegex(glob);
        int flags = caseSensitive ? 0 : Pattern.CASE_INSENSITIVE;
        return Pattern.compile(regex, flags);
    }
    
    /**
     * Converts a glob pattern to a regex string.
     */
    public String globToRegex(String glob) {
        StringBuilder regex = new StringBuilder();
        
        // Anchor at start
        regex.append("^");
        
        int i = 0;
        while (i < glob.length()) {
            char c = glob.charAt(i);
            
            switch (c) {
                case '*':
                    if (i + 1 < glob.length() && glob.charAt(i + 1) == '*') {
                        // ** - match zero or more path components
                        regex.append("(?:[^/]*(?:\\/|$))*");
                        i += 2;
                    } else {
                        // * - match zero or more characters within a path component
                        if (dotFilesMatch) {
                            regex.append("[^/]*");
                        } else {
                            regex.append("(?:[^./]|\\.(?!\\.))*");
                        }
                        i++;
                    }
                    break;
                    
                case '?':
                    // ? - match exactly one character within a path component
                    if (dotFilesMatch) {
                        regex.append("[^/]");
                    } else {
                        regex.append("(?:[^./]|\\.(?!\\.))");
                    }
                    i++;
                    break;
                    
                case '[':
                    // Character class
                    i = compileCharacterClass(glob, i, regex);
                    break;
                    
                case '{':
                    // Alternation group
                    i = compileAlternation(glob, i, regex);
                    break;
                    
                case '\\':
                    // Escaped character
                    if (i + 1 < glob.length()) {
                        regex.append(Pattern.quote(String.valueOf(glob.charAt(i + 1))));
                        i += 2;
                    } else {
                        regex.append("\\\\");
                        i++;
                    }
                    break;
                    
                case '.':
                case '+':
                case '(':
                case ')':
                case '|':
                case '^':
                case '$':
                case '@':
                case '%':
                    // Special regex characters - escape them
                    regex.append(Pattern.quote(String.valueOf(c)));
                    i++;
                    break;
                    
                default:
                    // Regular character
                    regex.append(Pattern.quote(String.valueOf(c)));
                    i++;
                    break;
            }
        }
        
        // Anchor at end
        regex.append("$");
        
        return regex.toString();
    }
    
    private int compileCharacterClass(String glob, int start, StringBuilder regex) {
        regex.append("[");
        int i = start + 1;
        
        if (i >= glob.length()) {
            regex.append("\\]");
            return i;
        }
        
        // Handle negation
        if (glob.charAt(i) == '!') {
            regex.append("^");
            i++;
        } else if (glob.charAt(i) == '^') {
            regex.append("^");
            i++;
        }
        
        while (i < glob.length() && glob.charAt(i) != ']') {
            char c = glob.charAt(i);
            
            if (c == '\\') {
                // Escaped character
                if (i + 1 < glob.length()) {
                    regex.append(Pattern.quote(String.valueOf(glob.charAt(i + 1))));
                    i += 2;
                } else {
                    regex.append("\\\\");
                    i++;
                }
            } else if (i + 2 < glob.length() && glob.charAt(i + 1) == '-') {
                // Character range
                char startChar = c;
                char endChar = glob.charAt(i + 2);
                regex.append(Pattern.quote(String.valueOf(startChar)));
                regex.append("-");
                regex.append(Pattern.quote(String.valueOf(endChar)));
                i += 3;
            } else {
                regex.append(Pattern.quote(String.valueOf(c)));
                i++;
            }
        }
        
        if (i < glob.length()) {
            regex.append("]");
            i++;
        } else {
            // Unclosed character class - close it
            regex.append("]");
        }
        
        return i;
    }
    
    private int compileAlternation(String glob, int start, StringBuilder regex) {
        regex.append("(?:");
        int i = start + 1;
        
        List<String> alternatives = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int depth = 1;
        
        while (i < glob.length() && depth > 0) {
            char c = glob.charAt(i);
            
            if (c == '{' && i > start) {
                depth++;
                current.append(c);
            } else if (c == '}') {
                depth--;
                if (depth == 0) {
                    alternatives.add(current.toString());
                    i++;
                    break;
                } else {
                    current.append(c);
                }
            } else if (c == ',' && depth == 1) {
                alternatives.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
            i++;
        }
        
        if (alternatives.isEmpty()) {
            // Empty alternation - treat as literal {
            regex.append("\\{");
            return start + 1;
        }
        
        for (int j = 0; j < alternatives.size(); j++) {
            if (j > 0) {
                regex.append("|");
            }
            regex.append(globToRegex(alternatives.get(j)));
        }
        
        regex.append(")");
        return i;
    }
    
    /**
     * Compiles multiple glob patterns into a single combined pattern.
     */
    public Pattern compileMultiple(List<String> globs) {
        if (globs == null || globs.isEmpty()) {
            return Pattern.compile("^$");
        }
        
        StringBuilder combined = new StringBuilder();
        combined.append("(?:");
        
        for (int i = 0; i < globs.size(); i++) {
            if (i > 0) {
                combined.append("|");
            }
            combined.append(globToRegex(globs.get(i)));
        }
        
        combined.append(")");
        
        int flags = caseSensitive ? 0 : Pattern.CASE_INSENSITIVE;
        return Pattern.compile(combined.toString(), flags);
    }
    
    /**
     * Optimizes a glob pattern by simplifying redundant components.
     */
    public String optimizeGlob(String glob) {
        if (glob == null || glob.isEmpty()) {
            return glob;
        }
        
        // Remove consecutive **
        String optimized = glob.replaceAll("\\*\\*+", "**");
        
        // Remove redundant ./
        optimized = optimized.replace("/./", "/");
        if (optimized.startsWith("./")) {
            optimized = optimized.substring(2);
        }
        
        // Simplify trailing /
        if (optimized.endsWith("/") && optimized.length() > 1) {
            optimized = optimized.substring(0, optimized.length() - 1);
        }
        
        return optimized;
    }
    
    /**
     * Checks if a glob pattern is valid.
     */
    public boolean isValidGlob(String glob) {
        if (glob == null || glob.isEmpty()) {
            return false;
        }
        
        try {
            compile(glob);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Extracts literal prefix from a glob pattern.
     */
    public String extractLiteralPrefix(String glob) {
        StringBuilder prefix = new StringBuilder();
        int i = 0;
        
        while (i < glob.length()) {
            char c = glob.charAt(i);
            
            if (c == '*' || c == '?' || c == '[' || c == '{') {
                break;
            }
            
            if (c == '\\') {
                if (i + 1 < glob.length()) {
                    prefix.append(glob.charAt(i + 1));
                    i += 2;
                    continue;
                }
            }
            
            prefix.append(c);
            i++;
        }
        
        return prefix.toString();
    }
}
