package com.syncforge.validation;

import com.syncforge.core.diagnostics.Diagnostic;
import com.syncforge.core.diagnostics.DiagnosticReporter;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.patch.PatchModel;
import com.syncforge.patch.PatchOperation;

import java.util.List;
import java.util.Set;

/**
 * Comprehensive validator for patch operations.
 * Validates operation ordering, path safety, and logical consistency.
 */
public class PatchValidator {
    
    private final DiagnosticReporter reporter;
    
    public PatchValidator() {
        this.reporter = new DiagnosticReporter();
    }
    
    /**
     * Validates a patch model comprehensively.
     */
    public void validate(PatchModel patch) throws ValidationException {
        if (patch == null) {
            throw new ValidationException("Patch cannot be null");
        }
        
        validateHeader(patch);
        validateOperations(patch);
        validateOperationOrdering(patch);
        validatePathSafety(patch);
        validateOperationDependencies(patch);
        
        if (reporter.hasErrors()) {
            List<Diagnostic> errors = reporter.getDiagnostics(Diagnostic.Severity.ERROR);
            throw new ValidationException("Patch validation failed with " + errors.size() + " error(s)", errors);
        }
    }
    
    private void validateHeader(PatchModel patch) {
        // Note: PatchModel currently only has operations list
        // These validations are commented out until those fields are added to the model
        /*
        // Validate version
        if (patch.getVersion() <= 0) {
            reporter.error("PATCH_VERSION_INVALID", "Patch version must be positive", null);
        }
        
        // Validate source snapshot reference
        if (patch.getSourceSnapshotId() == null || patch.getSourceSnapshotId().isEmpty()) {
            reporter.error("PATCH_SOURCE_EMPTY", "Patch source snapshot ID cannot be empty", null);
        }
        
        // Validate target snapshot reference
        if (patch.getTargetSnapshotId() == null || patch.getTargetSnapshotId().isEmpty()) {
            reporter.error("PATCH_TARGET_EMPTY", "Patch target snapshot ID cannot be empty", null);
        }
        
        // Validate timestamp
        if (patch.getTimestamp() <= 0) {
            reporter.error("PATCH_TIMESTAMP_INVALID", "Patch timestamp must be positive", null);
        }
        */
    }
    
    private void validateOperations(PatchModel patch) {
        List<PatchOperation> operations = patch.getOperations();
        
        if (operations == null) {
            reporter.error("PATCH_OPERATIONS_NULL", "Patch operations list cannot be null", null);
            return;
        }
        
        if (operations.isEmpty()) {
            reporter.warn("PATCH_EMPTY", "Patch contains no operations", null);
            return;
        }
        
        for (int i = 0; i < operations.size(); i++) {
            PatchOperation op = operations.get(i);
            if (op == null) {
                reporter.error("PATCH_OPERATION_NULL", "Patch operation at index " + i + " is null", null);
                continue;
            }
            
            validateOperation(op, i);
        }
    }
    
    private void validateOperation(PatchOperation operation, int index) {
        // Validate operation type
        if (operation.getType() == null) {
            reporter.error("PATCH_OPERATION_TYPE_NULL", 
                "Patch operation at index " + index + " has null type", null);
            return;
        }
        
        // Validate source path
        if (operation.getPath() == null || operation.getPath().isEmpty()) {
            reporter.error("PATCH_OPERATION_SOURCE_EMPTY", 
                "Patch operation at index " + index + " has empty source path", null);
        } else {
            validatePath(operation.getPath(), "source", index);
        }
        
        // Validate target path (for rename operations)
        if (operation.getType() == PatchOperation.Type.RENAME_PATH) {
            if (operation.getTargetPath() == null || operation.getTargetPath().isEmpty()) {
                reporter.error("PATCH_OPERATION_TARGET_EMPTY", 
                    "Rename operation at index " + index + " has empty target path", null);
            } else {
                validatePath(operation.getTargetPath(), "target", index);
            }
        }
        
        // Note: PatchOperation currently does not have getChecksum() or getSize() methods
        // These validations are commented out until those methods are added to the model
        /*
        // Validate checksum (for file operations)
        if (operation.getType() == PatchOperation.Type.CREATE_FILE || 
            operation.getType() == PatchOperation.Type.UPDATE_FILE) {
            if (operation.getChecksum() == null || operation.getChecksum().isEmpty()) {
                reporter.error("PATCH_OPERATION_CHECKSUM_EMPTY", 
                    "File operation at index " + index + " has empty checksum", null);
            } else {
                validateChecksum(operation.getChecksum(), index);
            }
        }
        
        // Validate size (for file operations)
        if (operation.getType() == PatchOperation.Type.CREATE_FILE || 
            operation.getType() == PatchOperation.Type.UPDATE_FILE) {
            if (operation.getSize() < 0) {
                reporter.error("PATCH_OPERATION_SIZE_INVALID", 
                    "File operation at index " + index + " has negative size", null);
            }
        }
        */
    }
    
    private void validatePath(String path, String pathType, int index) {
        // Check for path traversal
        if (path.contains("..")) {
            reporter.error("PATCH_OPERATION_PATH_TRAVERSAL", 
                "Patch operation at index " + index + " has " + pathType + " path with parent directory reference: " + path, null);
        }
        
        // Check for absolute paths
        if (path.startsWith("/")) {
            reporter.warn("PATCH_OPERATION_PATH_ABSOLUTE", 
                "Patch operation at index " + index + " has absolute " + pathType + " path: " + path, null);
        }
        
        // Check for extremely long paths
        if (path.length() > 4096) {
            reporter.error("PATCH_OPERATION_PATH_TOO_LONG", 
                "Patch operation at index " + index + " has " + pathType + " path exceeding maximum length", null);
        }
    }
    
    private void validateChecksum(String checksum, int index) {
        // Check if checksum is valid hexadecimal
        if (!checksum.matches("^[0-9a-fA-F]+$")) {
            reporter.error("PATCH_OPERATION_CHECKSUM_INVALID", 
                "Patch operation at index " + index + " has invalid checksum format", null);
        }
        
        // Check checksum length
        if (checksum.length() != 64 && checksum.length() != 40 && checksum.length() != 32) {
            reporter.warn("PATCH_OPERATION_CHECKSUM_LENGTH", 
                "Patch operation at index " + index + " has unusual checksum length: " + checksum.length(), null);
        }
    }
    
    private void validateOperationOrdering(PatchModel patch) {
        List<PatchOperation> operations = patch.getOperations();
        if (operations == null || operations.isEmpty()) {
            return;
        }
        
        // Check for operations that would create files in non-existent directories
        Set<String> createdDirectories = new java.util.HashSet<>();
        
        for (int i = 0; i < operations.size(); i++) {
            PatchOperation op = operations.get(i);
            
            if (op.getType() == PatchOperation.Type.CREATE_DIRECTORY) {
                createdDirectories.add(op.getPath());
            } else if (op.getType() == PatchOperation.Type.CREATE_FILE || 
                       op.getType() == PatchOperation.Type.UPDATE_FILE) {
                // Check if parent directory exists or will be created
                String parentPath = getParentPath(op.getPath());
                if (parentPath != null && !parentPath.isEmpty() && 
                    !createdDirectories.contains(parentPath)) {
                    reporter.warn("PATCH_OPERATION_PARENT_MISSING", 
                        "Operation at index " + i + " creates file in directory that may not exist: " + parentPath, null);
                }
            }
        }
        
        // Check for delete operations after create operations on same path
        Set<String> createdPaths = new java.util.HashSet<>();
        for (int i = 0; i < operations.size(); i++) {
            PatchOperation op = operations.get(i);
            String path = op.getPath();
            
            if (op.getType() == PatchOperation.Type.CREATE_FILE || 
                op.getType() == PatchOperation.Type.CREATE_DIRECTORY) {
                if (createdPaths.contains(path)) {
                    reporter.warn("PATCH_OPERATION_DUPLICATE_CREATE", 
                        "Operation at index " + i + " creates path that was already created: " + path, null);
                }
                createdPaths.add(path);
            } else if (op.getType() == PatchOperation.Type.DELETE_FILE || 
                       op.getType() == PatchOperation.Type.DELETE_DIRECTORY) {
                if (!createdPaths.contains(path)) {
                    reporter.warn("PATCH_OPERATION_DELETE_NONEXISTENT", 
                        "Operation at index " + i + " deletes path that was not created in this patch: " + path, null);
                }
            }
        }
    }
    
    private void validatePathSafety(PatchModel patch) {
        List<PatchOperation> operations = patch.getOperations();
        if (operations == null) {
            return;
        }
        
        // Check for operations that would affect system directories
        Set<String> systemPaths = Set.of(
            "/bin", "/sbin", "/usr", "/etc", "/var", "/sys", "/proc", "/dev",
            "C:\\Windows", "C:\\Program Files", "C:\\Program Files (x86)"
        );
        
        for (int i = 0; i < operations.size(); i++) {
            PatchOperation op = operations.get(i);
            String path = op.getPath();
            
            for (String systemPath : systemPaths) {
                if (path.startsWith(systemPath) || path.equals(systemPath)) {
                    reporter.error("PATCH_OPERATION_SYSTEM_PATH", 
                        "Operation at index " + i + " affects system path: " + path, null);
                }
            }
        }
    }
    
    private void validateOperationDependencies(PatchModel patch) {
        List<PatchOperation> operations = patch.getOperations();
        if (operations == null || operations.isEmpty()) {
            return;
        }
        
        // Check for rename operations where source is deleted later
        for (int i = 0; i < operations.size(); i++) {
            PatchOperation op = operations.get(i);
            if (op.getType() == PatchOperation.Type.RENAME_PATH) {
                String sourcePath = op.getPath();
                String targetPath = op.getTargetPath();
                
                // Check if source is deleted later
                for (int j = i + 1; j < operations.size(); j++) {
                    PatchOperation laterOp = operations.get(j);
                    if ((laterOp.getType() == PatchOperation.Type.DELETE_FILE || 
                         laterOp.getType() == PatchOperation.Type.DELETE_DIRECTORY) &&
                        laterOp.getPath().equals(sourcePath)) {
                        reporter.warn("PATCH_OPERATION_RENAME_THEN_DELETE", 
                            "Rename at index " + i + " is followed by delete of source at index " + j, null);
                    }
                    
                    // Check if target is created before rename
                    if ((laterOp.getType() == PatchOperation.Type.CREATE_FILE || 
                         laterOp.getType() == PatchOperation.Type.CREATE_DIRECTORY) &&
                        laterOp.getPath().equals(targetPath)) {
                        reporter.error("PATCH_OPERATION_RENAME_TARGET_EXISTS", 
                            "Rename at index " + i + " targets path that is created at index " + j, null);
                    }
                }
            }
        }
    }
    
    private String getParentPath(String path) {
        int lastSlash = path.lastIndexOf('/');
        if (lastSlash > 0) {
            return path.substring(0, lastSlash);
        }
        return "";
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
