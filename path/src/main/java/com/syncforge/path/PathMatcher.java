package com.syncforge.path;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Combines include and exclude glob patterns, compiling them to DFA matchers,
 * and executes overall match logic. It manages default match status when no includes are specified.
 */
public class PathMatcher {

    private static final SyncForgeLogger logger = new SyncForgeLogger(PathMatcher.class);

    private final List<GlobDfaMatcher> includes;
    private final List<GlobDfaMatcher> excludes;
    private final boolean defaultMatchStatus;

    /**
     * Constructs a new {@code PathMatcher}.
     *
     * @param includeGlobs       list of glob patterns to include. Must not be null.
     * @param excludeGlobs       list of glob patterns to exclude. Must not be null.
     * @param caseSensitive      whether the glob matching is case-sensitive.
     * @param defaultMatchStatus the default match status to return if the includes list is empty
     *                           and the path is not matched by any exclude pattern.
     * @throws ValidationException if includeGlobs or excludeGlobs is null.
     */
    public PathMatcher(List<String> includeGlobs, List<String> excludeGlobs, boolean caseSensitive, boolean defaultMatchStatus) {
        Objects.requireNonNull(includeGlobs, "Include globs list cannot be null");
        Objects.requireNonNull(excludeGlobs, "Exclude globs list cannot be null");

        this.includes = new ArrayList<>();
        for (String glob : includeGlobs) {
            this.includes.add(GlobCompiler.compile(glob, caseSensitive));
        }

        this.excludes = new ArrayList<>();
        for (String glob : excludeGlobs) {
            this.excludes.add(GlobCompiler.compile(glob, caseSensitive));
        }

        this.defaultMatchStatus = defaultMatchStatus;
        logger.info("Initialized PathMatcher with %d includes, %d excludes (caseSensitive=%b, defaultMatchStatus=%b)",
            includes.size(), excludes.size(), caseSensitive, defaultMatchStatus);
    }

    /**
     * Constructs a new {@code PathMatcher} with defaultMatchStatus set to true.
     *
     * @param includeGlobs  list of glob patterns to include. Must not be null.
     * @param excludeGlobs  list of glob patterns to exclude. Must not be null.
     * @param caseSensitive whether the glob matching is case-sensitive.
     * @throws ValidationException if includeGlobs or excludeGlobs is null.
     */
    public PathMatcher(List<String> includeGlobs, List<String> excludeGlobs, boolean caseSensitive) {
        this(includeGlobs, excludeGlobs, caseSensitive, true);
    }

    /**
     * Evaluates if a given path is matched by the includes and excludes configuration.
     * The evaluation logic is:
     * <ol>
     *   <li>If the path matches any exclude pattern, it is rejected (returns false).</li>
     *   <li>If there are include patterns:
     *     <ul>
     *       <li>If it matches at least one include pattern, it is accepted (returns true).</li>
     *       <li>Otherwise, it is rejected (returns false).</li>
     *     </ul>
     *   </li>
     *   <li>If there are no include patterns, the {@code defaultMatchStatus} is returned.</li>
     * </ol>
     *
     * @param path the path to match. Must not be null.
     * @return true if the path is matched and should be synchronized/processed, false otherwise.
     * @throws ValidationException if path is null or invalid.
     */
    public boolean matches(String path) {
        if (path == null) {
            throw new ValidationException("Path cannot be null");
        }

        // 1. If matches any exclude -> rejected
        for (GlobDfaMatcher excludeMatcher : excludes) {
            if (excludeMatcher.matches(path)) {
                logger.debug("Path '%s' excluded by pattern: '%s'", path, excludeMatcher.getGlob());
                return false;
            }
        }

        // 2. If includes list is empty -> return defaultMatchStatus
        if (includes.isEmpty()) {
            return defaultMatchStatus;
        }

        // 3. Otherwise, must match at least one include
        for (GlobDfaMatcher includeMatcher : includes) {
            if (includeMatcher.matches(path)) {
                logger.debug("Path '%s' included by pattern: '%s'", path, includeMatcher.getGlob());
                return true;
            }
        }

        logger.debug("Path '%s' did not match any include patterns", path);
        return false;
    }

    /**
     * Returns the compiled include matchers.
     *
     * @return an unmodifiable list of compiled include matchers.
     */
    public List<GlobDfaMatcher> getIncludes() {
        return Collections.unmodifiableList(includes);
    }

    /**
     * Returns the compiled exclude matchers.
     *
     * @return an unmodifiable list of compiled exclude matchers.
     */
    public List<GlobDfaMatcher> getExcludes() {
        return Collections.unmodifiableList(excludes);
    }

    /**
     * Returns the default match status.
     *
     * @return the default match status.
     */
    public boolean isDefaultMatchStatus() {
        return defaultMatchStatus;
    }
}
