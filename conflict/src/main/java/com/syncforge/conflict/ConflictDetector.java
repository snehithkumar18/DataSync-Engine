package com.syncforge.conflict;

import com.syncforge.diff.DiffEntry;
import com.syncforge.diff.DiffEngine;
import com.syncforge.metadata.EntryMetadata;
import com.syncforge.metadata.FileEntry;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.SymlinkEntry;
import com.syncforge.snapshot.SnapshotModel;
import java.util.*;

/**
 * ConflictDetector identifies conflicts between concurrent changes from a source snapshot
 * and a target snapshot relative to a common baseline ancestor.
 */
public class ConflictDetector {

    /**
     * Private constructor.
     */
    private ConflictDetector() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Detects synchronization conflicts between source and target snapshot modifications.
     *
     * @param baseline the common ancestor snapshot.
     * @param source   the local/source snapshot.
     * @param target   the remote/target snapshot.
     * @return the list of detected conflicts.
     */
    public static List<Conflict> detect(
            SnapshotModel baseline,
            SnapshotModel source,
            SnapshotModel target) {
        
        List<Conflict> conflicts = new ArrayList<>();

        DiffEngine engine = new DiffEngine();
        List<DiffEntry> sourceDiffs = engine.diff(baseline, source);
        List<DiffEntry> targetDiffs = engine.diff(baseline, target);

        Map<String, DiffEntry> sourceMap = new HashMap<>();
        for (DiffEntry d : sourceDiffs) {
            if (d.getChangeType() != DiffEntry.Type.UNCHANGED) {
                sourceMap.put(d.getPath(), d);
                if (d.getChangeType() == DiffEntry.Type.RENAME) {
                    sourceMap.put(d.getRenameSourcePath(), d);
                }
            }
        }

        Map<String, DiffEntry> targetMap = new HashMap<>();
        for (DiffEntry d : targetDiffs) {
            if (d.getChangeType() != DiffEntry.Type.UNCHANGED) {
                targetMap.put(d.getPath(), d);
                if (d.getChangeType() == DiffEntry.Type.RENAME) {
                    targetMap.put(d.getRenameSourcePath(), d);
                }
            }
        }

        Set<String> allPaths = new HashSet<>();
        allPaths.addAll(sourceMap.keySet());
        allPaths.addAll(targetMap.keySet());

        for (String path : allPaths) {
            DiffEntry sDiff = sourceMap.get(path);
            DiffEntry tDiff = targetMap.get(path);

            if (sDiff != null && tDiff != null) {
                if (isIdenticalChange(sDiff, tDiff)) {
                    continue;
                }

                ConflictType type = ConflictType.CONTENT_MERGE_CONFLICT;
                EntryMetadata baseEntry = sDiff.getBefore() != null ? sDiff.getBefore() : tDiff.getBefore();
                EntryMetadata localEntry = sDiff.getAfter();
                EntryMetadata remoteEntry = tDiff.getAfter();

                if (sDiff.getChangeType() == DiffEntry.Type.DELETE || tDiff.getChangeType() == DiffEntry.Type.DELETE) {
                    type = ConflictType.DELETE_MODIFY_CONFLICT;
                } else if (sDiff.getChangeType() == DiffEntry.Type.TYPE_CHANGE || tDiff.getChangeType() == DiffEntry.Type.TYPE_CHANGE) {
                    type = ConflictType.TYPE_CLASH;
                } else if (localEntry != null && remoteEntry != null) {
                    if (localEntry.getMode().getType() != remoteEntry.getMode().getType()) {
                        type = ConflictType.TYPE_CLASH;
                    } else if (localEntry instanceof FileEntry sf && remoteEntry instanceof FileEntry tf) {
                        if (!sf.getContentHash().value().equals(tf.getContentHash().value())) {
                            type = ConflictType.CONTENT_MERGE_CONFLICT;
                        } else if (sf.getMode().getPosixPermissions() != tf.getMode().getPosixPermissions()) {
                            type = ConflictType.PERMISSION_CONFLICT;
                        }
                    } else if (localEntry.getMode().getPosixPermissions() != remoteEntry.getMode().getPosixPermissions()) {
                        type = ConflictType.PERMISSION_CONFLICT;
                    }
                }

                conflicts.add(new Conflict(path, type, baseEntry, localEntry, remoteEntry));
            }
        }

        return conflicts;
    }

    private static boolean isIdenticalChange(DiffEntry s, DiffEntry t) {
        if (s.getChangeType() == DiffEntry.Type.DELETE && t.getChangeType() == DiffEntry.Type.DELETE) {
            return true;
        }
        EntryMetadata se = s.getAfter();
        EntryMetadata te = t.getAfter();
        if (se == null || te == null) {
            return false;
        }
        if (se.getMode().getType() != te.getMode().getType()) {
            return false;
        }
        if (se.getMode().getPosixPermissions() != te.getMode().getPosixPermissions()) {
            return false;
        }
        if (se instanceof FileEntry sf && te instanceof FileEntry tf) {
            return sf.getContentHash().value().equals(tf.getContentHash().value()) && sf.getSize() == tf.getSize();
        }
        if (se instanceof SymlinkEntry ss && te instanceof SymlinkEntry ts) {
            return ss.getTargetPath().equals(ts.getTargetPath());
        }
        return se.getMode().getType() == FileMode.Type.DIRECTORY;
    }
}
