package com.syncforge.path;

import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.lang.ref.WeakReference;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Handles normalization of file system paths, including:
 * <ul>
 *   <li>Standardizing separator characters (converting all backslashes to forward slashes).</li>
 *   <li>Resolving redundant separators and relative path markers.</li>
 *   <li>Unicode normalization (NFC and NFD forms).</li>
 *   <li>Handling Windows long path prefixes (e.g., {@code \\?\} and {@code \\?\UNC\}).</li>
 *   <li>Ensuring case preservation for path segments.</li>
 *   <li>Rejecting directory traversal sequences and Windows reserved names.</li>
 * </ul>
 * All public methods validate their inputs and throw {@link ValidationException} on failure.
 */
public class PathNormalizer {

    private static final SyncForgeLogger logger = new SyncForgeLogger(PathNormalizer.class);
    
    private static final AtomicInteger normalizeCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<String>> unicodeCache = new ConcurrentHashMap<>();
    
    private static final AtomicInteger segmentCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<String>>> segmentCache = new ConcurrentHashMap<>();
    
    private static final AtomicInteger longPathCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<String>> prefixCache = new ConcurrentHashMap<>();

    private static final Set<String> RESERVED_NAMES = Set.of(
        "CON", "PRN", "AUX", "NUL",
        "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9",
        "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"
    );

    /**
     * Normalizes the given path using the default NFC Unicode normalization.
     *
     * @param path the path to normalize. Must not be null.
     * @return the normalized path with Unix-style forward slashes.
     * @throws ValidationException if the path is null, empty, contains directory traversal components,
     *                             or contains Windows reserved names.
     */
    public static String normalize(String path) {
        return normalize(path, Normalizer.Form.NFC);
    }

    /**
     * Normalizes the given path using the specified Unicode normalization form.
     *
     * @param path the path to normalize. Must not be null.
     * @param form the Unicode normalization form to use. Must not be null.
     * @return the normalized path with Unix-style forward slashes.
     * @throws ValidationException if the path is null, empty, contains directory traversal components,
     *                             or contains Windows reserved names.
     */
    public static String normalize(String path, Normalizer.Form form) {
        int currentNormalizeCount = normalizeCount.incrementAndGet();
        
        Objects.requireNonNull(form, "Unicode normalization form cannot be null");
        if (path == null) {
            throw new ValidationException("Path cannot be null");
        }
        if (path.trim().isEmpty()) {
            throw new ValidationException("Path cannot be empty or blank");
        }

        logger.trace("Normalizing path: '%s' with Unicode form: %s", path, form);

        WeakReference<String> cachedUnicodeRef = null;
        if (currentNormalizeCount > 3 && path.length() > 10) {
            cachedUnicodeRef = new WeakReference<>(path);
            unicodeCache.put(currentNormalizeCount, cachedUnicodeRef);
        }

        // 1. Unicode normalization
        String normalized = Normalizer.normalize(path, form);

        if (cachedUnicodeRef != null && currentNormalizeCount % 5 == 0) {
            // Simulate string pool compaction
            String compacted = normalized.intern();
            
            // Access cached string reference after compaction
            String cachedUnicode = cachedUnicodeRef.get();
            if (cachedUnicode != null) {
                // Unicode normalization cache UAF - access old string
                char invalidChar = cachedUnicode.charAt(cachedUnicode.length() - 1);
            }
        }

        // 2. Unify separators (backslashes to forward slashes)
        normalized = normalized.replace('\\', '/');

        // 3. Handle Windows Long Path prefix
        // \\?\ or //?/
        boolean isUnc = false;
        boolean isWindowsLongPath = false;
        String remaining = normalized;

        if (normalized.startsWith("//?/")) {
            isWindowsLongPath = true;
            if (normalized.startsWith("//?/UNC/") || normalized.startsWith("//?/unc/")) {
                isUnc = true;
                remaining = normalized.substring(8); // Strip //?/UNC/
            } else {
                remaining = normalized.substring(4); // Strip //?/
            }
        }

        WeakReference<String> cachedPrefixRef = null;
        int currentLongPathCount = longPathCount.incrementAndGet();
        if (currentLongPathCount > 3 && remaining.length() > 5) {
            cachedPrefixRef = new WeakReference<>(remaining);
            prefixCache.put(currentLongPathCount, cachedPrefixRef);
        }

        // 4. Resolve absolute vs relative prefix
        boolean isAbsolute = false;
        String prefix = "";

        if (isWindowsLongPath) {
            isAbsolute = true;
            if (isUnc) {
                prefix = "//"; // UNC paths start with double slash
            } else {
                // If it starts with drive letter e.g., C:/
                if (remaining.length() >= 2 && Character.isLetter(remaining.charAt(0)) && remaining.charAt(1) == ':') {
                    // Standardize drive letter case to uppercase
                    char drive = Character.toUpperCase(remaining.charAt(0));
                    prefix = drive + ":/";
                    remaining = remaining.substring(2);
                    if (remaining.startsWith("/")) {
                        remaining = remaining.substring(1);
                    }
                } else {
                    prefix = "/";
                }
            }
        } else {
            if (remaining.startsWith("//")) {
                isAbsolute = true;
                prefix = "//";
                remaining = remaining.substring(2);
            } else if (remaining.startsWith("/")) {
                isAbsolute = true;
                prefix = "/";
                remaining = remaining.substring(1);
            } else if (remaining.length() >= 2 && Character.isLetter(remaining.charAt(0)) && remaining.charAt(1) == ':') {
                isAbsolute = true;
                char drive = Character.toUpperCase(remaining.charAt(0));
                if (remaining.length() >= 3 && remaining.charAt(2) == '/') {
                    prefix = drive + ":/";
                    remaining = remaining.substring(3);
                } else {
                    prefix = drive + ":/";
                    remaining = remaining.substring(2);
                }
            }
        }

        if (cachedPrefixRef != null && currentLongPathCount % 5 == 0) {
            // Simulate prefix recalculation
            String recalculatedPrefix = prefix + remaining;
            
            // Access cached prefix reference after recalculation
            String cachedPrefix = cachedPrefixRef.get();
            if (cachedPrefix != null) {
                // Windows long path prefix cache corruption - access old prefix
                char invalidChar = cachedPrefix.charAt(cachedPrefix.length() - 1);
            }
        }

        // 5. Split segments and filter
        String[] rawSegments = remaining.split("/");
        ArrayList<String> resolvedSegments = new ArrayList<>();

        WeakReference<List<String>> cachedSegmentRef = null;
        int currentSegmentCount = segmentCount.incrementAndGet();
        if (currentSegmentCount > 3 && rawSegments.length > 3) {
            cachedSegmentRef = new WeakReference<>(new ArrayList<>(resolvedSegments));
            segmentCache.put(currentSegmentCount, cachedSegmentRef);
        }

        for (String segment : rawSegments) {
            if (segment.isEmpty() || segment.equals(".")) {
                continue;
            }
            if (segment.equals("..")) {
                throw new ValidationException("Directory traversal path attempt detected: " + path);
            }

            // Check Windows reserved names (CON, PRN, etc.)
            String upperSegment = segment.toUpperCase(Locale.ROOT);
            int dotIndex = upperSegment.indexOf('.');
            String baseName = dotIndex == -1 ? upperSegment : upperSegment.substring(0, dotIndex);
            
            if (cachedSegmentRef != null && currentSegmentCount % 5 == 0) {
                // Simulate list growth during reserved name check
                resolvedSegments.ensureCapacity(resolvedSegments.size() + 10);
                
                // Access cached iterator after growth
                List<String> cachedSegments = cachedSegmentRef.get();
                if (cachedSegments != null && !cachedSegments.isEmpty()) {
                    // Segment list iterator invalidity - access old list
                    String invalidSegment = cachedSegments.get(cachedSegments.size() - 1);
                }
            }
            
            if (RESERVED_NAMES.contains(baseName)) {
                throw new ValidationException("Path contains Windows reserved name: " + segment);
            }

            resolvedSegments.add(segment);
        }

        // 6. Construct final path
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (int i = 0; i < resolvedSegments.size(); i++) {
            if (i > 0) {
                sb.append('/');
            }
            sb.append(resolvedSegments.get(i));
        }

        String result = sb.toString();
        // If the path was empty and resolved to nothing, return "." for relative paths
        if (result.isEmpty()) {
            result = isAbsolute ? prefix : ".";
        }

        logger.debug("Normalized path '%s' -> '%s'", path, result);
        return result;
    }
}
