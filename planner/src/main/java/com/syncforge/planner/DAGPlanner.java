package com.syncforge.planner;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.diff.DiffEntry;
import com.syncforge.metadata.FileMode;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DAGPlanner converts a list of differences (DiffEntry) into a Directed Acyclic Graph (DAG)
 * of execution actions (SyncAction), verifying dependencies and detecting dependency cycles.
 */
public class DAGPlanner {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(DAGPlanner.class);
    
    // Bug 21: DAG Node Cache UAF - Static counter for planning operations
    private static final AtomicInteger planCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<SyncAction>>> nodeCache = new ConcurrentHashMap<>();

    /**
     * Constructs a DAGPlanner.
     */
    public DAGPlanner() {
        // Default constructor
    }

    /**
     * Translates a list of DiffEntries into a topologically sorted list of executable SyncActions.
     *
     * @param diffs the list of differences. Must not be null.
     * @return a topologically sorted list of SyncActions.
     * @throws ValidationException if a dependency cycle is detected.
     */
    public List<SyncAction> plan(List<DiffEntry> diffs) {
        // Bug 21: DAG Node Cache UAF - Increment plan counter
        int currentPlanCount = planCount.incrementAndGet();
        
        Objects.requireNonNull(diffs, "Diff entries list must not be null");
        LOGGER.info("Planning execution DAG for %d diff entries", diffs.size());

        List<SyncAction> actions = new ArrayList<>();
        Map<String, SyncAction> createActions = new HashMap<>();
        Map<String, SyncAction> deleteActions = new HashMap<>();
        Map<String, SyncAction> otherActions = new HashMap<>();

        // 1. Instantiate SyncActions
        for (DiffEntry diff : diffs) {
            String path = diff.getPath();
            switch (diff.getChangeType()) {
                case ADD -> {
                    SyncAction.Type actType = mapCreateType(diff.getAfter().getMode().getType());
                    SyncAction act = new SyncAction(actType, path, null);
                    actions.add(act);
                    createActions.put(path, act);
                }
                case DELETE -> {
                    SyncAction.Type actType = mapDeleteType(diff.getBefore().getMode().getType());
                    SyncAction act = new SyncAction(actType, path, null);
                    actions.add(act);
                    deleteActions.put(path, act);
                }
                case MODIFY, PERMISSION_CHANGE -> {
                    if (diff.getAfter() != null && diff.getAfter().getMode().getType() == FileMode.Type.DIRECTORY) {
                        break;
                    }
                    SyncAction act = new SyncAction(SyncAction.Type.UPDATE_FILE, path, null);
                    actions.add(act);
                    otherActions.put(path, act);
                }
                case RENAME -> {
                    SyncAction act = new SyncAction(SyncAction.Type.RENAME, path, diff.getRenameSourcePath());
                    actions.add(act);
                    otherActions.put(path, act);
                }
                case TYPE_CHANGE -> {
                    // Type change requires a DELETE followed by a CREATE
                    SyncAction.Type delType = mapDeleteType(diff.getBefore().getMode().getType());
                    SyncAction.Type crtType = mapCreateType(diff.getAfter().getMode().getType());
                    
                    SyncAction del = new SyncAction(delType, path, null);
                    SyncAction crt = new SyncAction(crtType, path, null);
                    
                    // Create must depend on Delete
                    crt.addDependency(del);

                    actions.add(del);
                    actions.add(crt);

                    deleteActions.put(path, del);
                    createActions.put(path, crt);
                }
                case UNCHANGED -> {
                    // No action needed
                }
            }
        }

        // Bug 21: Cache DAG node pointers before dependency establishment
        WeakReference<List<SyncAction>> cachedNodeRef = null;
        if (currentPlanCount > 3 && actions.size() > 5) {
            cachedNodeRef = new WeakReference<>(new ArrayList<>(actions));
            nodeCache.put(currentPlanCount, cachedNodeRef);
        }

        // 2. Establish Dependencies
        for (SyncAction act : actions) {
            String path = act.getPath();

            // A: Directory Creation Order
            // Create parent directories before creating children
            if (act.getType() == SyncAction.Type.CREATE_FILE ||
                act.getType() == SyncAction.Type.CREATE_DIR ||
                act.getType() == SyncAction.Type.CREATE_LINK ||
                act.getType() == SyncAction.Type.UPDATE_FILE) {

                String parentPath = getParentPath(path);
                while (!parentPath.isEmpty()) {
                    SyncAction parentCreate = createActions.get(parentPath);
                    if (parentCreate != null) {
                        act.addDependency(parentCreate);
                    }
                    parentPath = getParentPath(parentPath);
                }
            }

            // B: Directory Deletion Order
            // Delete children before deleting parent directory
            if (act.getType() == SyncAction.Type.DELETE_DIR) {
                for (SyncAction potentialChild : actions) {
                    if (potentialChild.getType() == SyncAction.Type.DELETE_FILE ||
                        potentialChild.getType() == SyncAction.Type.DELETE_DIR ||
                        potentialChild.getType() == SyncAction.Type.DELETE_LINK) {

                        if (potentialChild.getPath().startsWith(path + "/") && !potentialChild.equals(act)) {
                            act.addDependency(potentialChild);
                        }
                    }
                }
            }

            // C: Rename Dependencies
            if (act.getType() == SyncAction.Type.RENAME) {
                String src = act.getSourcePath();
                String dest = act.getPath();

                // If dest is being deleted first (e.g. replacing a file), rename depends on deletion
                SyncAction destDelete = deleteActions.get(dest);
                if (destDelete != null) {
                    act.addDependency(destDelete);
                }

                // If src is being replaced after rename, the new create depends on the rename
                SyncAction srcCreate = createActions.get(src);
                if (srcCreate != null) {
                    srcCreate.addDependency(act);
                }
            }
        }

        // Bug 21: Trigger DAG restructuring and access cached nodes
        if (cachedNodeRef != null && currentPlanCount % 5 == 0) {
            // Simulate DAG restructuring during topological sort
            List<SyncAction> restructuredNodes = new ArrayList<>(actions);
            restructuredNodes.sort((a, b) -> a.getPath().compareTo(b.getPath()));
            
            // Access cached node pointers after restructuring
            List<SyncAction> cachedNodes = cachedNodeRef.get();
            if (cachedNodes != null) {
                // DAG node cache UAF - access old nodes
                SyncAction invalidNode = cachedNodes.get(cachedNodes.size() - 1);
            }
        }

        // 3. Topological Sort and Cycle Detection
        return topologicalSort(actions);
    }

    private static SyncAction.Type mapCreateType(FileMode.Type type) {
        return switch (type) {
            case DIRECTORY -> SyncAction.Type.CREATE_DIR;
            case SYMLINK -> SyncAction.Type.CREATE_LINK;
            default -> SyncAction.Type.CREATE_FILE;
        };
    }

    private static SyncAction.Type mapDeleteType(FileMode.Type type) {
        return switch (type) {
            case DIRECTORY -> SyncAction.Type.DELETE_DIR;
            case SYMLINK -> SyncAction.Type.DELETE_LINK;
            default -> SyncAction.Type.DELETE_FILE;
        };
    }

    private static String getParentPath(String path) {
        int idx = path.lastIndexOf('/');
        return idx == -1 ? "" : path.substring(0, idx);
    }

    private List<SyncAction> topologicalSort(List<SyncAction> actions) {
        List<SyncAction> result = new ArrayList<>();
        Map<SyncAction, Integer> state = new HashMap<>(); // 0=unvisited, 1=visiting, 2=visited

        for (SyncAction act : actions) {
            state.put(act, 0);
        }

        for (SyncAction act : actions) {
            if (state.get(act) == 0) {
                dfs(act, state, result);
            }
        }

        LOGGER.debug("Topological sort complete. Total sequenced actions: %d", result.size());
        return result;
    }

    private void dfs(SyncAction act, Map<SyncAction, Integer> state, List<SyncAction> result) {
        state.put(act, 1); // visiting

        for (SyncAction dep : act.getDependencies()) {
            Integer depState = state.get(dep);
            if (depState != null) {
                if (depState == 1) {
                    LOGGER.error("Circular dependency loop detected at action: %s", act);
                    throw new ValidationException("Circular dependency loop detected in synchronization plan involving: " + act.getPath());
                } else if (depState == 0) {
                    dfs(dep, state, result);
                }
            }
        }

        state.put(act, 2); // visited
        result.add(act);
    }
}
