package com.syncforge.validation;

import com.syncforge.core.diagnostics.Diagnostic;
import com.syncforge.core.diagnostics.DiagnosticReporter;
import com.syncforge.core.diagnostics.SourceSpan;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.manifest.ManifestModel;
import com.syncforge.path.PathNormalizer;
import com.syncforge.path.PathSafetyValidator;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Comprehensive validator for manifest configurations.
 * Validates path safety, rule consistency, and configuration integrity.
 */
public class ManifestValidator {
    
    private final DiagnosticReporter reporter;
    private final PathNormalizer pathNormalizer;
    private final PathSafetyValidator pathSafetyValidator;
    
    public ManifestValidator() {
        this.reporter = new DiagnosticReporter();
        this.pathNormalizer = new PathNormalizer();
        this.pathSafetyValidator = new PathSafetyValidator();
    }
    
    /**
     * Validates a manifest model comprehensively.
     */
    public void validate(ManifestModel manifest) throws ValidationException {
        if (manifest == null) {
            throw new ValidationException("Manifest cannot be null");
        }
        
        validateName(manifest);
        validateRoot(manifest);
        validateMode(manifest);
        validateChecksum(manifest);
        validateRules(manifest);
        validateRuleConsistency(manifest);
        validatePathSafety(manifest);
        
        if (reporter.hasErrors()) {
            List<Diagnostic> errors = reporter.getDiagnostics(Diagnostic.Severity.ERROR);
            throw new ValidationException("Manifest validation failed with " + errors.size() + " error(s)", errors);
        }
    }
    
    private void validateName(ManifestModel manifest) {
        String name = manifest.getName();
        if (name == null || name.trim().isEmpty()) {
            reporter.error("MANIFEST_NAME_EMPTY", "Manifest name cannot be empty", null);
            return;
        }
        
        if (name.length() > 255) {
            reporter.error("MANIFEST_NAME_TOO_LONG", "Manifest name exceeds maximum length of 255 characters", null);
        }
        
        // Check for invalid characters
        if (!name.matches("^[a-zA-Z0-9_-]+$")) {
            reporter.error("MANIFEST_NAME_INVALID", "Manifest name contains invalid characters. Only alphanumeric, underscore, and hyphen are allowed", null);
        }
    }
    
    private void validateRoot(ManifestModel manifest) {
        String root = manifest.getRoot();
        if (root == null || root.trim().isEmpty()) {
            reporter.error("MANIFEST_ROOT_EMPTY", "Manifest root path cannot be empty", null);
            return;
        }
        
        try {
            // Normalize the path
            String normalized = pathNormalizer.normalize(root);
            
            // Check for path traversal
            if (normalized.contains("..")) {
                reporter.error("MANIFEST_ROOT_TRAVERSAL", "Manifest root path contains parent directory references (..)", null);
            }
            
            // Validate path safety
            if (!pathSafetyValidator.isSafePath(normalized)) {
                reporter.error("MANIFEST_ROOT_UNSAFE", "Manifest root path is unsafe: " + normalized, null);
            }
            
            // Check if it's an absolute path
            if (!Paths.get(normalized).isAbsolute()) {
                reporter.warn("MANIFEST_ROOT_RELATIVE", "Manifest root path is relative. Consider using an absolute path", null);
            }
            
        } catch (InvalidPathException e) {
            reporter.error("MANIFEST_ROOT_INVALID", "Manifest root path is invalid: " + e.getMessage(), null);
        }
    }
    
    private void validateMode(ManifestModel manifest) {
        String mode = manifest.getMode();
        if (mode == null || mode.trim().isEmpty()) {
            reporter.error("MANIFEST_MODE_EMPTY", "Manifest mode cannot be empty", null);
            return;
        }
        
        Set<String> validModes = Set.of("mirror", "backup", "sync", "archive");
        if (!validModes.contains(mode.toLowerCase())) {
            reporter.error("MANIFEST_MODE_INVALID", "Invalid manifest mode: " + mode + ". Valid modes are: " + validModes, null);
        }
    }
    
    private void validateChecksum(ManifestModel manifest) {
        String checksum = manifest.getChecksum();
        if (checksum == null || checksum.trim().isEmpty()) {
            reporter.warn("MANIFEST_CHECKSUM_EMPTY", "Manifest checksum algorithm not specified, defaulting to sha256", null);
            return;
        }
        
        Set<String> validAlgorithms = Set.of("md5", "sha1", "sha256", "sha384", "sha512", "crc32", "adler32");
        if (!validAlgorithms.contains(checksum.toLowerCase())) {
            reporter.error("MANIFEST_CHECKSUM_INVALID", "Invalid checksum algorithm: " + checksum + ". Valid algorithms are: " + validAlgorithms, null);
        }
    }
    
    private void validateRules(ManifestModel manifest) {
        Set<String> seenPatterns = new HashSet<>();
        
        for (String include : manifest.getIncludes()) {
            if (include == null || include.trim().isEmpty()) {
                reporter.error("MANIFEST_INCLUDE_EMPTY", "Include rule cannot be empty", null);
                continue;
            }
            
            if (seenPatterns.contains(include)) {
                reporter.warn("MANIFEST_INCLUDE_DUPLICATE", "Duplicate include rule: " + include, null);
            }
            seenPatterns.add(include);
            
            validateGlobPattern(include, "include");
        }
        
        seenPatterns.clear();
        
        for (String exclude : manifest.getExcludes()) {
            if (exclude == null || exclude.trim().isEmpty()) {
                reporter.error("MANIFEST_EXCLUDE_EMPTY", "Exclude rule cannot be empty", null);
                continue;
            }
            
            if (seenPatterns.contains(exclude)) {
                reporter.warn("MANIFEST_EXCLUDE_DUPLICATE", "Duplicate exclude rule: " + exclude, null);
            }
            seenPatterns.add(exclude);
            
            validateGlobPattern(exclude, "exclude");
        }
    }
    
    private void validateGlobPattern(String pattern, String ruleType) {
        // Check for invalid glob patterns
        if (pattern.contains("//")) {
            reporter.error("MANIFEST_" + ruleType.toUpperCase() + "_INVALID", 
                "Invalid glob pattern contains double slashes: " + pattern, null);
        }
        
        if (pattern.contains("\\\\")) {
            reporter.error("MANIFEST_" + ruleType.toUpperCase() + "_INVALID", 
                "Invalid glob pattern contains backslashes: " + pattern, null);
        }
        
        // Check for extremely long patterns
        if (pattern.length() > 4096) {
            reporter.error("MANIFEST_" + ruleType.toUpperCase() + "_TOO_LONG", 
                "Glob pattern exceeds maximum length of 4096 characters", null);
        }
        
        // Check for patterns that might cause performance issues
        if (pattern.contains("**") && pattern.indexOf("**") != pattern.lastIndexOf("**")) {
            reporter.warn("MANIFEST_" + ruleType.toUpperCase() + "_COMPLEX", 
                "Glob pattern contains multiple double wildcards which may impact performance: " + pattern, null);
        }
    }
    
    private void validateRuleConsistency(ManifestModel manifest) {
        // Check for conflicting include/exclude rules
        Set<String> includes = new HashSet<>(manifest.getIncludes());
        Set<String> excludes = new HashSet<>(manifest.getExcludes());
        
        // Find exact matches
        Set<String> conflicts = new HashSet<>(includes);
        conflicts.retainAll(excludes);
        
        if (!conflicts.isEmpty()) {
            for (String conflict : conflicts) {
                reporter.warn("MANIFEST_RULE_CONFLICT", 
                    "Pattern appears in both include and exclude rules: " + conflict, null);
            }
        }
        
        // Check for overly broad excludes that might negate all includes
        if (excludes.contains("**") || excludes.contains("*")) {
            reporter.error("MANIFEST_EXCLUDE_OVERLY_BROAD", 
                "Exclude rule is overly broad and may exclude all files", null);
        }
        
        // Check if there are any includes at all
        if (includes.isEmpty()) {
            reporter.warn("MANIFEST_NO_INCLUDES", 
                "No include rules specified. All files will be included by default", null);
        }
    }
    
    private void validatePathSafety(ManifestModel manifest) {
        // Validate all paths in rules
        for (String include : manifest.getIncludes()) {
            if (!pathSafetyValidator.isSafePattern(include)) {
                reporter.error("MANIFEST_INCLUDE_UNSAFE", 
                    "Include pattern is potentially unsafe: " + include, null);
            }
        }
        
        for (String exclude : manifest.getExcludes()) {
            if (!pathSafetyValidator.isSafePattern(exclude)) {
                reporter.error("MANIFEST_EXCLUDE_UNSAFE", 
                    "Exclude pattern is potentially unsafe: " + exclude, null);
            }
        }
    }
    
    /**
     * Gets all diagnostics from validation.
     */
    public List<Diagnostic> getDiagnostics() {
        return reporter.getDiagnostics();
    }
    
    /**
     * Gets only error diagnostics.
     */
    public List<Diagnostic> getErrors() {
        return reporter.getDiagnostics(Diagnostic.Severity.ERROR);
    }
    
    /**
     * Gets only warning diagnostics.
     */
    public List<Diagnostic> getWarnings() {
        return reporter.getDiagnostics(Diagnostic.Severity.WARNING);
    }
}
