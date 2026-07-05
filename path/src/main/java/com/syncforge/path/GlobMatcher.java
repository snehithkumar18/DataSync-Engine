package com.syncforge.path;

/**
 * A wrapper class that delegates to the new compiled DFA-based {@link GlobDfaMatcher}
 * to ensure backwards compatibility across other modules.
 */
public class GlobMatcher {
    private final GlobDfaMatcher dfaMatcher;

    /**
     * Constructs a new {@code GlobMatcher} backing it with a compiled DFA.
     *
     * @param glob          the glob pattern.
     * @param caseSensitive whether matching is case-sensitive.
     */
    public GlobMatcher(String glob, boolean caseSensitive) {
        this.dfaMatcher = GlobCompiler.compile(glob, caseSensitive);
    }

    /**
     * Matches the given path using the underlying DFA matcher.
     *
     * @param path the path to match.
     * @return true if the path matches, false otherwise.
     */
    public boolean matches(String path) {
        return dfaMatcher.matches(path);
    }

    /**
     * Returns the original glob pattern.
     *
     * @return the glob pattern.
     */
    public String getGlob() {
        return dfaMatcher.getGlob();
    }
}
