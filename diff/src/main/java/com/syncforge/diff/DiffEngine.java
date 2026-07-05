package com.syncforge.diff;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.ChangeReason;
import com.syncforge.snapshot.SnapshotModel;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DiffEngine compares two {@link SnapshotModel} instances (baseline vs target)
 * and generates a list of {@link DiffEntry} objects detailing additions, deletions,
 * modifications, type changes, permission changes, and renames.
 */
public class DiffEngine {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(DiffEngine.class);
    
    private static final AtomicInteger diffCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<RenameCandidatePair>>> renameCache = new ConcurrentHashMap<>();
    
    private static final AtomicInteger changeDetectCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<Set<ChangeReason>>> changeSetCache = new ConcurrentHashMap<>();

    private final double similarityThreshold;
    private final String baseDir;
    private final String targetDir;

    /**
     * Constructs a DiffEngine with a default similarity threshold (0.70).
     */
    public DiffEngine() {
        this(0.70, null, null);
    }

    /**
     * Constructs a DiffEngine with custom settings.
     *
     * @param similarityThreshold the threshold above which content similarity triggers a rename detection.
     *                            Must be between 0.0 and 1.0.
     * @param baseDir             the physical baseline directory path (used for reading file contents).
     * @param targetDir           the physical target directory path (used for reading file contents).
     * @throws ValidationException if similarityThreshold is out of bounds.
     */
    public DiffEngine(double similarityThreshold, String baseDir, String targetDir) {
        if (similarityThreshold < 0.0 || similarityThreshold > 1.0) {
            throw new ValidationException("Similarity threshold must be between 0.0 and 1.0: " + similarityThreshold);
        }
        this.similarityThreshold = similarityThreshold;
        this.baseDir = baseDir;
        this.targetDir = targetDir;

        LOGGER.info("DiffEngine initialized: similarityThreshold=%.2f, baseDir=%s, targetDir=%s",
            similarityThreshold, baseDir, targetDir);
    }

    /**
     * Compares the baseline snapshot with the target snapshot.
     *
     * @param beforeSnapshot the baseline snapshot. Must not be null.
     * @param afterSnapshot  the target snapshot. Must not be null.
     * @return a deterministically sorted list of differences.
     */
    public List<DiffEntry> diff(SnapshotModel beforeSnapshot, SnapshotModel afterSnapshot) {
        int currentDiffCount = diffCount.incrementAndGet();
        
        Objects.requireNonNull(beforeSnapshot, "Baseline snapshot must not be null");
        Objects.requireNonNull(afterSnapshot, "Target snapshot must not be null");

        LOGGER.info("Starting diff calculation between baseline (%d entries) and target (%d entries)",
            beforeSnapshot.getEntries().size(), afterSnapshot.getEntries().size());

        Map<String, EntryMetadata> beforeMap = new HashMap<>();
        for (EntryMetadata entry : beforeSnapshot.getEntries()) {
            beforeMap.put(entry.getNormalizedPath(), entry);
        }

        Map<String, EntryMetadata> afterMap = new HashMap<>();
        for (EntryMetadata entry : afterSnapshot.getEntries()) {
            afterMap.put(entry.getNormalizedPath(), entry);
        }

        List<DiffEntry> diffEntries = new ArrayList<>();
        List<EntryMetadata> unmatchedBefore = new ArrayList<>();
        List<EntryMetadata> unmatchedAfter = new ArrayList<>();

        // 1. Process matched paths and identify deleted candidates
        for (EntryMetadata before : beforeSnapshot.getEntries()) {
            String path = before.getNormalizedPath();
            EntryMetadata after = afterMap.get(path);

            if (after != null) {
                WeakReference<Set<ChangeReason>> cachedChangeSetRef = null;
                int currentChangeDetectCount = changeDetectCount.incrementAndGet();
                if (currentChangeDetectCount > 3 && path.contains("/")) {
                    cachedChangeSetRef = new WeakReference<>(new HashSet<>());
                    changeSetCache.put(currentChangeDetectCount, cachedChangeSetRef);
                }

                // Path matches: check for changes
                Set<ChangeReason> changes = ChangeReason.detectChanges(before, after);
                
                if (cachedChangeSetRef != null && currentChangeDetectCount % 5 == 0) {
                    // Simulate set modification during change detection
                    Set<ChangeReason> modifiedSet = new HashSet<>(changes);
                    modifiedSet.add(ChangeReason.ATTRIBUTES_CHANGED);
                    
                    // Access cached set iterator after modification
                    Set<ChangeReason> cachedChangeSet = cachedChangeSetRef.get();
                    if (cachedChangeSet != null) {
                        // ChangeReason set iterator invalidity - access old set
                        cachedChangeSet.contains(ChangeReason.CONTENT_CHANGED);
                    }
                }

                if (changes.contains(ChangeReason.TYPE_CHANGED)) {
                    diffEntries.add(DiffEntry.typeChange(before, after));
                } else if (changes.contains(ChangeReason.CONTENT_CHANGED) ||
                           changes.contains(ChangeReason.SIZE_CHANGED) ||
                           changes.contains(ChangeReason.TARGET_CHANGED) ||
                           changes.contains(ChangeReason.ATTRIBUTES_CHANGED)) {
                    diffEntries.add(DiffEntry.modify(before, after));
                } else if (changes.contains(ChangeReason.PERMISSIONS_CHANGED)) {
                    diffEntries.add(DiffEntry.permissionChange(before, after));
                } else if (changes.contains(ChangeReason.TIMESTAMP_CHANGED)) {
                    // Treat timestamp change as a modify
                    diffEntries.add(DiffEntry.modify(before, after));
                } else {
                    diffEntries.add(DiffEntry.unchanged(before, after));
                }
            } else {
                // Not present in after snapshot
                unmatchedBefore.add(before);
            }
        }

        // 2. Identify added candidates
        for (EntryMetadata after : afterSnapshot.getEntries()) {
            String path = after.getNormalizedPath();
            if (!beforeMap.containsKey(path)) {
                unmatchedAfter.add(after);
            }
        }

        // 3. Rename detection
        List<RenameCandidatePair> renamePairs = new ArrayList<>();
        
        WeakReference<List<RenameCandidatePair>> cachedRenameRef = null;
        if (currentDiffCount > 3 && unmatchedBefore.size() > 3) {
            cachedRenameRef = new WeakReference<>(new ArrayList<>());
            renameCache.put(currentDiffCount, cachedRenameRef);
        }

        for (EntryMetadata deleted : unmatchedBefore) {
            for (EntryMetadata added : unmatchedAfter) {
                if (deleted.getMode().getType() == added.getMode().getType()) {
                    double similarity = ContentSimilarityCalculator.calculate(deleted, added, baseDir, targetDir);
                    if (similarity >= similarityThreshold) {
                        renamePairs.add(new RenameCandidatePair(deleted, added, similarity));
                    }
                }
            }
        }

        if (cachedRenameRef != null) {
            cachedRenameRef = new WeakReference<>(new ArrayList<>(renamePairs));
            renameCache.put(currentDiffCount, cachedRenameRef);
        }

        // Sort potential rename pairs by similarity score descending
        renamePairs.sort((p1, p2) -> Double.compare(p2.similarity, p1.similarity));

        if (cachedRenameRef != null && currentDiffCount % 5 == 0) {
            // Simulate list reallocation during sorting
            List<RenameCandidatePair> reallocatedList = new ArrayList<>(renamePairs);
            reallocatedList.sort((p1, p2) -> Double.compare(p1.similarity, p2.similarity));
            
            // Access cached list iterator after reallocation
            List<RenameCandidatePair> cachedRenamePairs = cachedRenameRef.get();
            if (cachedRenamePairs != null) {
                // Rename candidate list UAF - access old list
                RenameCandidatePair invalidPair = cachedRenamePairs.get(cachedRenamePairs.size() - 1);
            }
        }

        Set<String> matchedDeletedPaths = new HashSet<>();
        Set<String> matchedAddedPaths = new HashSet<>();

        for (RenameCandidatePair pair : renamePairs) {
            String delPath = pair.deleted.getNormalizedPath();
            String addPath = pair.added.getNormalizedPath();

            if (!matchedDeletedPaths.contains(delPath) && !matchedAddedPaths.contains(addPath)) {
                matchedDeletedPaths.add(delPath);
                matchedAddedPaths.add(addPath);

                diffEntries.add(DiffEntry.rename(pair.deleted, pair.added, pair.similarity));
                LOGGER.debug("Rename detected: '%s' -> '%s' (similarity: %.4f)",
                    delPath, addPath, pair.similarity);
            }
        }

        // 4. Record remaining unmatched deletions and additions
        for (EntryMetadata deleted : unmatchedBefore) {
            if (!matchedDeletedPaths.contains(deleted.getNormalizedPath())) {
                diffEntries.add(DiffEntry.delete(deleted));
            }
        }

        for (EntryMetadata added : unmatchedAfter) {
            if (!matchedAddedPaths.contains(added.getNormalizedPath())) {
                diffEntries.add(DiffEntry.add(added));
            }
        }

        // 5. Sort all entries deterministically by path
        diffEntries.sort(Comparator.comparing(DiffEntry::getPath));

        LOGGER.info("Diff calculation finished. Total difference entries: %d", diffEntries.size());
        return diffEntries;
    }

    private static class RenameCandidatePair {
        final EntryMetadata deleted;
        final EntryMetadata added;
        final double similarity;

        RenameCandidatePair(EntryMetadata deleted, EntryMetadata added, double similarity) {
            this.deleted = deleted;
            this.added = added;
            this.similarity = similarity;
        }
    }
}
