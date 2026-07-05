package com.syncforge.manifest;

import com.syncforge.core.logging.SyncForgeLogger;
import java.util.Objects;

/**
 * Serializes a {@link ManifestModel} instance back to the SyncForge custom DSL syntax format.
 * Ensures proper escaping, indentation, and path separator normalization.
 */
public class ManifestSerializer {

    private static final SyncForgeLogger logger = new SyncForgeLogger(ManifestSerializer.class);

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ManifestSerializer() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Formats the given {@link ManifestModel} into a custom DSL string.
     *
     * @param model the configuration model to serialize. Must not be null.
     * @return the serialized DSL string.
     * @throws NullPointerException if {@code model} is null.
     */
    public static String serialize(ManifestModel model) {
        Objects.requireNonNull(model, "ManifestModel must not be null");
        logger.info("Serializing ManifestModel for '%s' to DSL", model.getName());

        StringBuilder sb = new StringBuilder();
        
        // Header
        sb.append("manifest \"").append(escapeString(model.getName())).append("\" {\n");

        // Root path, unified with Unix forward slashes
        String rootPath = model.getRoot();
        if (rootPath != null) {
            rootPath = rootPath.replace('\\', '/');
            sb.append("    root = \"").append(escapeString(rootPath)).append("\"\n");
        }

        // Mode
        if (model.getMode() != null) {
            sb.append("    mode = \"").append(escapeString(model.getMode())).append("\"\n");
        }

        // Checksum
        if (model.getChecksum() != null) {
            sb.append("    checksum = \"").append(escapeString(model.getChecksum())).append("\"\n");
        }

        // Preserve permissions
        sb.append("    preserve_permissions = ").append(model.isPreservePermissions()).append("\n");

        // Include rules
        if (model.getIncludes() != null) {
            for (String includePattern : model.getIncludes()) {
                sb.append("    include \"").append(escapeString(includePattern)).append("\"\n");
            }
        }

        // Exclude rules
        if (model.getExcludes() != null) {
            for (String excludePattern : model.getExcludes()) {
                sb.append("    exclude \"").append(escapeString(excludePattern)).append("\"\n");
            }
        }

        // Closing brace
        sb.append("}\n");

        logger.debug("Serialization completed for manifest '%s'", model.getName());
        return sb.toString();
    }

    /**
     * Escapes special characters (double quotes, backslashes, newlines, etc.) to produce
     * a valid string literal value.
     */
    private static String escapeString(String raw) {
        if (raw == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '/' -> sb.append("\\/");
                case '\b' -> sb.append("\\b");
                case '\f' -> sb.append("\\f");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default -> sb.append(c);
            }
        }
        return sb.toString();
    }
}
