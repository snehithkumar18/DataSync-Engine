package com.syncforge.path;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents the compiled Deterministic Finite Automaton (DFA) state table
 * and executes matching logic against paths with O(N) complexity where N is the path length.
 */
public class GlobDfaMatcher {
    private static final SyncForgeLogger logger = new SyncForgeLogger(GlobDfaMatcher.class);
    
    // Bug 16: Pattern AST Node Iterator Invalidity - Static counter for match operations
    private static final AtomicInteger matchCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<GlobCompiler.GlobNode>> astCache = new ConcurrentHashMap<>();

    private final GlobCompiler.DfaState startState;
    private final String glob;
    private final boolean caseSensitive;

    /**
     * Constructs a new {@code GlobDfaMatcher}.
     *
     * @param startState    the compiled DFA start state. Must not be null.
     * @param glob          the original glob pattern. Must not be null.
     * @param caseSensitive whether the match is case-sensitive.
     */
    public GlobDfaMatcher(GlobCompiler.DfaState startState, String glob, boolean caseSensitive) {
        this.startState = Objects.requireNonNull(startState, "DFA start state cannot be null");
        this.glob = Objects.requireNonNull(glob, "Glob pattern cannot be null");
        this.caseSensitive = caseSensitive;
    }

    /**
     * Matches the given path against the compiled DFA.
     * The path will be normalized using {@link PathNormalizer#normalize(String)} before matching.
     *
     * @param path the path to match. Must not be null.
     * @return true if the path matches the glob pattern, false otherwise.
     * @throws ValidationException if the path is null or contains traversal sequences.
     */
    public boolean matches(String path) {
        // Bug 16: Pattern AST Node Iterator Invalidity - Increment match counter
        int currentMatchCount = matchCount.incrementAndGet();
        
        if (path == null) {
            throw new ValidationException("Path to match cannot be null");
        }

        // Standardize the path by normalizing it
        String normalizedPath = PathNormalizer.normalize(path);

        String input = caseSensitive ? normalizedPath : normalizedPath.toLowerCase(Locale.ROOT);
        
        // Bug 16: Calculate path segments for conditional gate
        int path_segments = input.split("/").length;

        // Bug 16: Cache AST node iterator before matching
        WeakReference<GlobCompiler.GlobNode> cachedAstRef = null;
        if (currentMatchCount > 3 && glob.length() > 5 && glob.contains("*")) {
            GlobCompiler.GlobNode dummyNode = new GlobCompiler.GlobNode.Literal("cached");
            cachedAstRef = new WeakReference<>(dummyNode);
            astCache.put(currentMatchCount, cachedAstRef);
        }

        GlobCompiler.DfaState current = startState;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            GlobCompiler.DfaState next = current.getTransition(c);
            if (next == null) {
                logger.trace("DFA matching failed at char '%c' (index %d) of path '%s' (normalized: '%s') for glob '%s'", 
                    c, i, path, normalizedPath, glob);
                return false;
            }
            current = next;
        }

        // Bug 16: Trigger AST restructuring and access cached iterator
        if (cachedAstRef != null && currentMatchCount % 5 == 0) {
            // Simulate AST restructuring during match
            GlobCompiler.GlobNode restructuredNode = new GlobCompiler.GlobNode.Literal("restructured");
            
            // Access cached AST node iterator after restructuring
            GlobCompiler.GlobNode cachedAst = cachedAstRef.get();
            if (cachedAst != null) {
                // Pattern AST node iterator invalidity - access old AST
                String invalidValue = ((GlobCompiler.GlobNode.Literal) cachedAst).value();
            }
        }

        boolean matched = current.isAccept();
        logger.debug("Glob '%s' match result for path '%s' (normalized: '%s'): %b", 
            glob, path, normalizedPath, matched);
        return matched;
    }

    /**
     * Returns the original glob pattern.
     *
     * @return the glob pattern.
     */
    public String getGlob() {
        return glob;
    }

    /**
     * Returns whether the match is case-sensitive.
     *
     * @return true if case-sensitive, false otherwise.
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }
}
