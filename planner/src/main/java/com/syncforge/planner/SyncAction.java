package com.syncforge.planner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single atomic synchronization action inside the SyncForge DAG.
 */
public class SyncAction {

    /**
     * Types of sync actions.
     */
    public enum Type {
        CREATE_FILE,
        UPDATE_FILE,
        DELETE_FILE,
        CREATE_DIR,
        DELETE_DIR,
        CREATE_LINK,
        DELETE_LINK,
        RENAME,
        APPLY_PATCH
    }

    private final Type type;
    private final String path;
    private final String sourcePath; // For RENAME
    private final List<SyncAction> dependencies;

    /**
     * Constructs a SyncAction.
     *
     * @param type       the action type. Must not be null.
     * @param path       the target path of the action. Must not be null.
     * @param sourcePath the source path (e.g. for renames). Can be null.
     */
    public SyncAction(Type type, String path, String sourcePath) {
        this.type = Objects.requireNonNull(type, "SyncAction type must not be null");
        this.path = Objects.requireNonNull(path, "SyncAction path must not be null");
        this.sourcePath = sourcePath;
        this.dependencies = new ArrayList<>();
    }

    public Type getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public List<SyncAction> getDependencies() {
        return dependencies;
    }

    /**
     * Registers a dependency. This action cannot start until the dependency is complete.
     *
     * @param dependency the dependency action.
     */
    public void addDependency(SyncAction dependency) {
        if (dependency != null && dependency != this && !dependencies.contains(dependency)) {
            dependencies.add(dependency);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SyncAction other)) return false;
        return type == other.type &&
               Objects.equals(path, other.path) &&
               Objects.equals(sourcePath, other.sourcePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, path, sourcePath);
    }

    @Override
    public String toString() {
        return String.format("SyncAction[type=%s, path=%s, source=%s, depCount=%d]",
            type, path, sourcePath, dependencies.size());
    }
}
