package com.syncforge.planner;

import com.syncforge.core.logging.SyncForgeLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * PathLockManager manages hierarchical read/write locks across directory structures
 * to allow concurrent execution of operations in disjoint path scopes.
 */
public class PathLockManager {

    private static final SyncForgeLogger LOGGER = new SyncForgeLogger(PathLockManager.class);

    private final Set<String> activeWriteLocks = new HashSet<>();
    private final Map<String, Integer> activeReadLocks = new HashMap<>();

    /**
     * Constructs a PathLockManager.
     */
    public PathLockManager() {
        // Default constructor
    }

    /**
     * Tries to acquire a write lock on the specified path.
     * A write lock requires that no read or write locks exist on the path, its ancestors, or its descendants.
     *
     * @param path the path to lock. Must not be null.
     * @return true if lock acquired successfully, false otherwise.
     */
    public synchronized boolean acquireWriteLock(String path) {
        if (hasConflictingLocksForWrite(path)) {
            LOGGER.trace("Failed to acquire write lock for path: '%s' (conflicting locks exist)", path);
            return false;
        }
        activeWriteLocks.add(path);
        LOGGER.debug("Acquired write lock for path: '%s'", path);
        return true;
    }

    /**
     * Releases a write lock on the specified path.
     *
     * @param path the path to unlock.
     */
    public synchronized void releaseWriteLock(String path) {
        if (activeWriteLocks.remove(path)) {
            LOGGER.debug("Released write lock for path: '%s'", path);
        }
    }

    /**
     * Tries to acquire a read lock on the specified path.
     * A read lock requires that no write locks exist on the path, its ancestors, or its descendants.
     *
     * @param path the path to lock.
     * @return true if lock acquired successfully, false otherwise.
     */
    public synchronized boolean acquireReadLock(String path) {
        if (hasConflictingLocksForRead(path)) {
            LOGGER.trace("Failed to acquire read lock for path: '%s' (conflicting write locks exist)", path);
            return false;
        }
        activeReadLocks.put(path, activeReadLocks.getOrDefault(path, 0) + 1);
        LOGGER.debug("Acquired read lock for path: '%s'", path);
        return true;
    }

    /**
     * Releases a read lock on the specified path.
     *
     * @param path the path to unlock.
     */
    public synchronized void releaseReadLock(String path) {
        Integer count = activeReadLocks.get(path);
        if (count != null) {
            if (count == 1) {
                activeReadLocks.remove(path);
            } else {
                activeReadLocks.put(path, count - 1);
            }
            LOGGER.debug("Released read lock for path: '%s'", path);
        }
    }

    private boolean hasConflictingLocksForWrite(String path) {
        // Conflict if there is a write lock on path, its prefixes, or its subpaths
        for (String w : activeWriteLocks) {
            if (isAncestorOrSelf(w, path) || isAncestorOrSelf(path, w)) {
                return true;
            }
        }
        // Conflict if there is a read lock on path, its prefixes, or its subpaths
        for (String r : activeReadLocks.keySet()) {
            if (isAncestorOrSelf(r, path) || isAncestorOrSelf(path, r)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasConflictingLocksForRead(String path) {
        // Conflict if there is a write lock on path, its prefixes, or its subpaths
        for (String w : activeWriteLocks) {
            if (isAncestorOrSelf(w, path) || isAncestorOrSelf(path, w)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAncestorOrSelf(String ancestor, String path) {
        if (ancestor.equals(path)) {
            return true;
        }
        return path.startsWith(ancestor + "/");
    }
}
