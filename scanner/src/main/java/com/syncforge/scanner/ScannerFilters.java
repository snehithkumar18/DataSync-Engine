package com.syncforge.scanner;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.path.GlobMatcher;
import com.syncforge.path.PathMatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Filter configuration that matches file system paths against include and exclude globs.
 * Internally uses {@link PathMatcher} to evaluate path inclusion/exclusion rules,
 * and maintains a list of {@link GlobMatcher} for compatibility.
 */
public class ScannerFilters {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(ScannerFilters.class);

    private final PathMatcher pathMatcher;
    private final List<GlobMatcher> includes;
    private final List<GlobMatcher> excludes;

    /**
     * Constructs a new {@code ScannerFilters} instance.
     *
     * @param includeGlobs   list of glob patterns to include. Must not be null.
     * @param excludeGlobs   list of glob patterns to exclude. Must not be null.
     * @param caseSensitive whether matching should be case-sensitive.
     * @throws ValidationException if includeGlobs or excludeGlobs is null.
     */
    public ScannerFilters(List<String> includeGlobs, List<String> excludeGlobs, boolean caseSensitive) {
        Objects.requireNonNull(includeGlobs, "Include globs list must not be null");
        Objects.requireNonNull(excludeGlobs, "Exclude globs list must not be null");

        this.pathMatcher = new PathMatcher(includeGlobs, excludeGlobs, caseSensitive);

        List<GlobMatcher> incList = new ArrayList<>();
        for (String glob : includeGlobs) {
            incList.add(new GlobMatcher(glob, caseSensitive));
        }
        this.includes = Collections.unmodifiableList(incList);

        List<GlobMatcher> exList = new ArrayList<>();
        for (String glob : excludeGlobs) {
            exList.add(new GlobMatcher(glob, caseSensitive));
        }
        this.excludes = Collections.unmodifiableList(exList);

        LOGGER.info("ScannerFilters initialized with %d includes and %d excludes (caseSensitive=%b)",
            includes.size(), excludes.size(), caseSensitive);
    }

    /**
     * Determines whether a given relative path should be included in the scan results.
     *
     * @param relativePath the relative path to evaluate. Must not be null.
     * @return true if the path is allowed, false if excluded or not included.
     * @throws ValidationException if relativePath is null or invalid.
     */
    public boolean shouldInclude(String relativePath) {
        Objects.requireNonNull(relativePath, "Relative path must not be null");
        boolean allowed = pathMatcher.matches(relativePath);
        LOGGER.trace("Filter check for '%s' -> %s", relativePath, allowed ? "ALLOWED" : "REJECTED");
        return allowed;
    }

    /**
     * Gets the list of include patterns.
     *
     * @return an unmodifiable list of include {@link GlobMatcher} instances.
     */
    public List<GlobMatcher> getIncludes() {
        return includes;
    }

    /**
     * Gets the list of exclude patterns.
     *
     * @return an unmodifiable list of exclude {@link GlobMatcher} instances.
     */
    public List<GlobMatcher> getExcludes() {
        return excludes;
    }

    /**
     * Gets the underlying path matcher.
     *
     * @return the {@link PathMatcher}.
     */
    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }
}
