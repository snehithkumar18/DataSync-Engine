package com.syncforge.path;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Validates file system paths for security and OS compatibility constraints.
 * It checks for:
 * <ul>
 *   <li>Directory traversal sequences (e.g., {@code ..}, {@code ../}, {@code ..\}).</li>
 *   <li>Control characters (ASCII 0x00-0x1F and 0x7F-0x9F).</li>
 *   <li>Invalid characters on non-root path segments (e.g., {@code *}, {@code ?}, {@code :}, {@code <}, {@code >}, {@code |}, {@code "}).</li>
 *   <li>Windows reserved names (e.g., CON, PRN, AUX, NUL, COM1-9, LPT1-9) in any segment.</li>
 * </ul>
 */
public class PathSafetyValidator {

    private static final SyncForgeLogger logger = new SyncForgeLogger(PathSafetyValidator.class);

    private static final Set<String> RESERVED_NAMES = Set.of(
        "CON", "PRN", "AUX", "NUL",
        "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9",
        "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"
    );

    // Invalid chars on non-root segments
    private static final char[] INVALID_CHARS = {'*', '?', ':', '<', '>', '|', '"'};

    /**
     * Validates the safety of the given path.
     *
     * @param path the path to validate. Must not be null.
     * @throws ValidationException if the path violates any safety or compatibility rules.
     */
    public static void validate(String path) {
        if (path == null) {
            throw new ValidationException("Path cannot be null");
        }
        if (path.trim().isEmpty()) {
            throw new ValidationException("Path cannot be empty or blank");
        }

        logger.trace("Validating safety of path: '%s'", path);

        // 1. Check for literal directory traversal patterns in the string
        if (path.contains("../") || path.contains("..\\") || path.equals("..") || path.endsWith("/..") || path.endsWith("\\..")) {
            logger.warn("Security rejection: directory traversal attempt detected in path '%s'", path);
            throw new ValidationException("Directory traversal path attempt detected: " + path);
        }

        // 2. Check for control characters
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if ((c >= 0x00 && c <= 0x1F) || (c >= 0x7F && c <= 0x9F)) {
                logger.warn("Security rejection: control character 0x%02X at index %d in path '%s'", (int) c, i, path);
                throw new ValidationException("Path contains invalid control character at index " + i + ": " + path);
            }
        }

        // 3. Scan segments for invalid characters and reserved names
        // Normalize slashes temporarily for easy segment splitting
        String standardSlashes = path.replace('\\', '/');
        
        // Strip Windows Long Path prefix if present for segment validation
        String remaining = standardSlashes;
        if (standardSlashes.startsWith("//?/")) {
            if (standardSlashes.startsWith("//?/UNC/") || standardSlashes.startsWith("//?/unc/")) {
                remaining = standardSlashes.substring(8);
            } else {
                remaining = standardSlashes.substring(4);
            }
        }

        // Determine if there's a root segment (like C: or leading / or UNC host/share)
        // If it starts with a drive letter, the first segment is drive letter + optional empty segment, e.g. "C:"
        String[] segments = remaining.split("/");
        
        int startIdx = 0;
        if (segments.length > 0) {
            String first = segments[0];
            // Check if first segment is drive letter (e.g. "C:")
            if (first.length() == 2 && Character.isLetter(first.charAt(0)) && first.charAt(1) == ':') {
                startIdx = 1; // Skip checking drive letter colon
            }
        }

        for (int i = startIdx; i < segments.length; i++) {
            String segment = segments[i];
            if (segment.isEmpty() || segment.equals(".")) {
                continue;
            }

            // Check traversal segment
            if (segment.equals("..")) {
                logger.warn("Security rejection: directory traversal segment detected in path '%s'", path);
                throw new ValidationException("Directory traversal path attempt detected: " + path);
            }

            // Check invalid characters
            for (char invalidChar : INVALID_CHARS) {
                if (segment.indexOf(invalidChar) != -1) {
                    logger.warn("Security rejection: invalid character '%c' in segment '%s' in path '%s'", invalidChar, segment, path);
                    throw new ValidationException("Path segment contains invalid character '" + invalidChar + "': " + segment);
                }
            }

            // Check Windows reserved names
            String upperSegment = segment.toUpperCase(Locale.ROOT);
            int dotIndex = upperSegment.indexOf('.');
            String baseName = dotIndex == -1 ? upperSegment : upperSegment.substring(0, dotIndex);
            if (RESERVED_NAMES.contains(baseName)) {
                logger.warn("Security rejection: Windows reserved name '%s' in path '%s'", baseName, path);
                throw new ValidationException("Path contains Windows reserved name: " + segment);
            }
        }

        logger.debug("Path '%s' successfully passed safety validation", path);
    }
    
    /**
     * Checks if a path is safe without throwing an exception.
     *
     * @param path the path to check. Must not be null.
     * @return true if the path is safe, false otherwise.
     */
    public static boolean isSafePath(String path) {
        try {
            validate(path);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }
    
    /**
     * Checks if a glob pattern is safe (contains no dangerous patterns).
     *
     * @param pattern the glob pattern to check. Must not be null.
     * @return true if the pattern is safe, false otherwise.
     */
    public static boolean isSafePattern(String pattern) {
        if (pattern == null || pattern.trim().isEmpty()) {
            return false;
        }
        
        // Check for directory traversal in pattern
        if (pattern.contains("../") || pattern.contains("..\\") || pattern.equals("..")) {
            return false;
        }
        
        // Check for control characters
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if ((c >= 0x00 && c <= 0x1F) || (c >= 0x7F && c <= 0x9F)) {
                return false;
            }
        }
        
        return true;
    }
}
