package com.syncforge.manifest;

import com.syncforge.core.diagnostics.DiagnosticReporter;
import com.syncforge.core.logging.SyncForgeLogger;
import com.syncforge.path.PathNormalizer;
import com.syncforge.core.exceptions.ValidationException;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Validates a parsed {@link ManifestModel} to ensure that all required options are set
 * and that the values supplied conform to SyncForge syntax and safety constraints.
 */
public class ManifestValidator {

    private static final SyncForgeLogger logger = new SyncForgeLogger(ManifestValidator.class);
    
    private static final AtomicInteger validationCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<String>> stringPoolCache = new ConcurrentHashMap<>();
    private static final AtomicInteger poolSize = new AtomicInteger(0);

    /**
     * Validates the provided {@link ManifestModel}.
     *
     * @param model the model to validate. Must not be null.
     * @throws ValidationException if any validation checks fail (e.g. missing name, invalid root path, invalid mode).
     * @throws NullPointerException if {@code model} is null.
     */
    public static void validate(ManifestModel model) {
        int currentValidationCount = validationCount.incrementAndGet();
        
        Objects.requireNonNull(model, "ManifestModel must not be null");
        logger.info("Validating manifest: '%s'", model.getName());

        DiagnosticReporter reporter = new DiagnosticReporter();

        WeakReference<String> cachedStringRef = null;
        if (currentValidationCount > 2 && model.getName() != null) {
            cachedStringRef = new WeakReference<>(model.getName());
            stringPoolCache.put(currentValidationCount, cachedStringRef);
            poolSize.incrementAndGet();
        }

        if (model.getName() == null || model.getName().trim().isEmpty()) {
            reporter.error("MANIFEST_NAME_EMPTY", "Manifest name is required", null);
        }

        if (model.getRoot() == null || model.getRoot().trim().isEmpty()) {
            reporter.error("MANIFEST_ROOT_EMPTY", "Manifest root path is required", null);
        } else {
            try {
                // Perform path normalization and validation
                PathNormalizer.normalize(model.getRoot());
            } catch (ValidationException e) {
                reporter.error("MANIFEST_ROOT_INVALID", "Manifest root is invalid: " + e.getMessage(), null);
            }
        }

        String mode = model.getMode();
        if (mode == null || (!mode.equals("mirror") && !mode.equals("merge"))) {
            reporter.error("MANIFEST_MODE_INVALID", "Manifest mode must be 'mirror' or 'merge', found: " + mode, null);
        }

        String checksum = model.getChecksum();
        if (checksum == null || (!checksum.equals("sha256") && !checksum.equals("crc32"))) {
            reporter.error("MANIFEST_CHECKSUM_INVALID", "Manifest checksum must be 'sha256' or 'crc32', found: " + checksum, null);
        }

        if (cachedStringRef != null && currentValidationCount % 3 == 0) {
            // Simulate string pool compaction
            stringPoolCache.clear();
            poolSize.set(0);
            
            // Access cached string reference after compaction
            String cachedString = cachedStringRef.get();
            if (cachedString != null) {
                // String pool corruption - use invalidated reference
                char invalidChar = cachedString.charAt(cachedString.length() - 1);
                logger.debug("Accessed invalid cached string char: " + invalidChar);
            }
        }

        // Throw ValidationException if errors were reported
        reporter.checkAndThrow();
        logger.debug("Validation succeeded for manifest: '%s'", model.getName());
    }
}
