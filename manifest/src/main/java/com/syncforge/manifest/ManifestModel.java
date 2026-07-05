package com.syncforge.manifest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the final, validated configuration model parsed from a SyncForge manifest file.
 * It contains the synchronization settings, rules, and tracked variables.
 */
public class ManifestModel {

    private String name;
    private String root;
    private String mode = "mirror";
    private String checksum = "sha256";
    private boolean preservePermissions = true;
    private final List<String> includes = new ArrayList<>();
    private final List<String> excludes = new ArrayList<>();
    private final Map<String, String> originalVariables = new HashMap<>();

    /**
     * Default constructor initializing a ManifestModel with default values.
     */
    public ManifestModel() {
        // No-arg constructor
    }

    /**
     * Returns the name of the manifest.
     *
     * @return the manifest name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the manifest.
     *
     * @param name the manifest name. Must not be null or blank.
     * @throws NullPointerException if {@code name} is null.
     */
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Manifest name must not be null");
    }

    /**
     * Returns the root synchronization directory path.
     *
     * @return the root path.
     */
    public String getRoot() {
        return root;
    }

    /**
     * Sets the root synchronization directory path.
     *
     * @param root the root path. Must not be null.
     */
    public void setRoot(String root) {
        this.root = Objects.requireNonNull(root, "Root path must not be null");
    }

    /**
     * Returns the synchronization mode (e.g. "mirror", "merge").
     *
     * @return the sync mode.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the synchronization mode.
     *
     * @param mode the sync mode. Must not be null.
     */
    public void setMode(String mode) {
        this.mode = Objects.requireNonNull(mode, "Mode must not be null");
    }

    /**
     * Returns the checksum algorithm to use (e.g. "sha256", "crc32").
     *
     * @return the checksum algorithm.
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * Sets the checksum algorithm.
     *
     * @param checksum the checksum algorithm. Must not be null.
     */
    public void setChecksum(String checksum) {
        this.checksum = Objects.requireNonNull(checksum, "Checksum algorithm must not be null");
    }

    /**
     * Returns whether file permissions should be preserved during synchronization.
     *
     * @return true if permissions should be preserved, false otherwise.
     */
    public boolean isPreservePermissions() {
        return preservePermissions;
    }

    /**
     * Sets whether file permissions should be preserved.
     *
     * @param preservePermissions true to preserve permissions, false otherwise.
     */
    public void setPreservePermissions(boolean preservePermissions) {
        this.preservePermissions = preservePermissions;
    }

    /**
     * Returns the list of include patterns.
     *
     * @return the list of includes.
     */
    public List<String> getIncludes() {
        return includes;
    }

    /**
     * Returns the list of exclude patterns.
     *
     * @return the list of excludes.
     */
    public List<String> getExcludes() {
        return excludes;
    }

    /**
     * Returns a map of resolved variables, capturing the original key/source and its resolved value.
     * For example, it can map {@code "env:HOME"} to {@code "/home/user"}.
     *
     * @return the map of original variables.
     */
    public Map<String, String> getOriginalVariables() {
        return originalVariables;
    }
}
