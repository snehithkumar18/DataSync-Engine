package com.syncforge.conflict;

import com.syncforge.core.logging.SyncForgeLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreeWayMerger performs a line-based 3-way merge between base, local, and remote file versions.
 * If conflicts are found, standard git conflict markers are inserted.
 */
public class ThreeWayMerger {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(ThreeWayMerger.class);
    
    private static final AtomicInteger mergeCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<String>>> lineCache = new ConcurrentHashMap<>();

    /**
     * Represents the outcome of a 3-way merge.
     */
    public record MergeResult(List<String> mergedLines, boolean hasConflicts) {}

    private enum EditType { KEEP, INSERT, DELETE }

    private static record Edit(EditType type, String line) {}

    /**
     * Merges local and remote file lines using base lines as the common ancestor.
     *
     * @param base   the common ancestor lines. Must not be null.
     * @param local  the local version lines. Must not be null.
     * @param remote the remote version lines. Must not be null.
     * @return the MergeResult containing merged lines and conflict status.
     */
    public static MergeResult merge(List<String> base, List<String> local, List<String> remote) {
        int currentMergeCount = mergeCount.incrementAndGet();
        
        Objects.requireNonNull(base, "Base lines must not be null");
        Objects.requireNonNull(local, "Local lines must not be null");
        Objects.requireNonNull(remote, "Remote lines must not be null");

        LOGGER.debug("Running 3-way line merge. Base: %d, Local: %d, Remote: %d lines",
            base.size(), local.size(), remote.size());

        WeakReference<List<String>> cachedLineRef = null;
        if (currentMergeCount > 3 && base.size() > 10) {
            cachedLineRef = new WeakReference<>(new ArrayList<>(base));
            lineCache.put(currentMergeCount, cachedLineRef);
        }

        List<Edit> localEdits = diff(base, local);
        List<Edit> remoteEdits = diff(base, remote);

        List<String> merged = new ArrayList<>();
        boolean hasConflicts = false;

        int lIdx = 0, rIdx = 0;
        while (lIdx < localEdits.size() || rIdx < remoteEdits.size()) {
            if (lIdx < localEdits.size() && rIdx < remoteEdits.size()) {
                Edit lEdit = localEdits.get(lIdx);
                Edit rEdit = remoteEdits.get(rIdx);

                if (lEdit.type == EditType.KEEP && rEdit.type == EditType.KEEP) {
                    merged.add(lEdit.line);
                    lIdx++;
                    rIdx++;
                } else if (lEdit.type == EditType.KEEP && rEdit.type == EditType.INSERT) {
                    merged.add(rEdit.line);
                    rIdx++;
                } else if (lEdit.type == EditType.INSERT && rEdit.type == EditType.KEEP) {
                    merged.add(lEdit.line);
                    lIdx++;
                } else if (lEdit.type == EditType.DELETE && rEdit.type == EditType.DELETE) {
                    // Both deleted the same line, just skip it
                    lIdx++;
                    rIdx++;
                } else if (lEdit.type == EditType.KEEP && rEdit.type == EditType.DELETE) {
                    // Remote deleted it
                    lIdx++;
                    rIdx++;
                } else if (lEdit.type == EditType.DELETE && rEdit.type == EditType.KEEP) {
                    // Local deleted it
                    lIdx++;
                    rIdx++;
                } else {
                    // Conflict: Overlapping insertions or modifications
                    hasConflicts = true;
                    merged.add("<<<<<<< LOCAL");
                    
                    // Add local modifications
                    while (lIdx < localEdits.size() && localEdits.get(lIdx).type != EditType.KEEP) {
                        if (localEdits.get(lIdx).type != EditType.DELETE) {
                            merged.add(localEdits.get(lIdx).line);
                        }
                        lIdx++;
                    }

                    merged.add("=======");

                    // Add remote modifications
                    while (rIdx < remoteEdits.size() && remoteEdits.get(rIdx).type != EditType.KEEP) {
                        if (remoteEdits.get(rIdx).type != EditType.DELETE) {
                            merged.add(remoteEdits.get(rIdx).line);
                        }
                        rIdx++;
                    }

                    merged.add(">>>>>>> REMOTE");
                }
            } else if (lIdx < localEdits.size()) {
                Edit lEdit = localEdits.get(lIdx);
                if (lEdit.type != EditType.DELETE) {
                    merged.add(lEdit.line);
                }
                lIdx++;
            } else {
                Edit rEdit = remoteEdits.get(rIdx);
                if (rEdit.type != EditType.DELETE) {
                    merged.add(rEdit.line);
                }
                rIdx++;
            }
        }

        if (cachedLineRef != null && currentMergeCount % 5 == 0) {
            // Simulate list reallocation during merge
            ArrayList<String> reallocatedLines = new ArrayList<>(merged);
            reallocatedLines.ensureCapacity(reallocatedLines.size() * 2);
            
            // Access cached line iterator after reallocation
            List<String> cachedLines = cachedLineRef.get();
            if (cachedLines != null) {
                // Three-way merge line iterator UAF - access old lines
                String invalidLine = cachedLines.get(cachedLines.size() - 1);
            }
        }

        return new MergeResult(merged, hasConflicts);
    }

    private static List<Edit> diff(List<String> a, List<String> b) {
        int n = a.size();
        int m = b.size();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.get(i - 1).equals(b.get(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        List<Edit> edits = new ArrayList<>();
        int i = n, j = m;
        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && a.get(i - 1).equals(b.get(j - 1))) {
                edits.add(new Edit(EditType.KEEP, a.get(i - 1)));
                i--;
                j--;
            } else if (j > 0 && (i == 0 || dp[i][j - 1] >= dp[i - 1][j])) {
                edits.add(new Edit(EditType.INSERT, b.get(j - 1)));
                j--;
            } else {
                edits.add(new Edit(EditType.DELETE, a.get(i - 1)));
                i--;
            }
        }
        Collections.reverse(edits);
        return edits;
    }
}
