package com.syncforge.validation;

import com.syncforge.core.diagnostics.Diagnostic;
import com.syncforge.core.diagnostics.DiagnosticReporter;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.patch.PatchOperation;

import java.util.*;

/**
 * Validates operation graphs for cycles, dependencies, and safe execution ordering.
 * Ensures that operations can be executed without conflicts or deadlocks.
 */
public class OperationGraphValidator {
    
    private final DiagnosticReporter reporter;
    
    public OperationGraphValidator() {
        this.reporter = new DiagnosticReporter();
    }
    
    /**
     * Validates a list of operations as a directed graph.
     */
    public void validate(List<PatchOperation> operations) throws ValidationException {
        if (operations == null) {
            throw new ValidationException("Operations list cannot be null");
        }
        
        if (operations.isEmpty()) {
            return; // Empty graph is valid
        }
        
        // Build dependency graph
        Map<Integer, Set<Integer>> graph = buildDependencyGraph(operations);
        
        // Check for cycles
        detectCycles(graph, operations);
        
        // Check for conflicting operations
        detectConflicts(operations);
        
        // Validate topological ordering
        validateTopologicalOrder(operations, graph);
        
        if (reporter.hasErrors()) {
            List<Diagnostic> errors = reporter.getDiagnostics(Diagnostic.Severity.ERROR);
            throw new ValidationException("Operation graph validation failed with " + errors.size() + " error(s)", errors);
        }
    }
    
    private Map<Integer, Set<Integer>> buildDependencyGraph(List<PatchOperation> operations) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int i = 0; i < operations.size(); i++) {
            graph.put(i, new HashSet<>());
        }
        
        // Build dependencies based on operation types
        for (int i = 0; i < operations.size(); i++) {
            PatchOperation op = operations.get(i);
            
            for (int j = i + 1; j < operations.size(); j++) {
                PatchOperation other = operations.get(j);
                
                // Create dependencies based on path relationships
                if (hasDependency(op, other)) {
                    graph.get(i).add(j);
                }
            }
        }
        
        return graph;
    }
    
    private boolean hasDependency(PatchOperation first, PatchOperation second) {
        String firstPath = first.getPath();
        String secondPath = second.getPath();
        
        // If operations affect the same path, there's a dependency
        if (firstPath.equals(secondPath)) {
            return true;
        }
        
        // If one operation is in a directory affected by another
        if (firstPath.startsWith(secondPath + "/") || secondPath.startsWith(firstPath + "/")) {
            return true;
        }
        
        // Rename operations have special dependencies
        if (first.getType() == PatchOperation.Type.RENAME_PATH) {
            String targetPath = first.getTargetPath();
            if (secondPath.equals(targetPath) || secondPath.startsWith(targetPath + "/")) {
                return true;
            }
        }
        
        if (second.getType() == PatchOperation.Type.RENAME_PATH) {
            String targetPath = second.getTargetPath();
            if (firstPath.equals(targetPath) || firstPath.startsWith(targetPath + "/")) {
                return true;
            }
        }
        
        return false;
    }
    
    private void detectCycles(Map<Integer, Set<Integer>> graph, List<PatchOperation> operations) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();
        
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (hasCycleDFS(node, graph, visited, recursionStack, operations)) {
                    return; // Stop after first cycle is found
                }
            }
        }
    }
    
    private boolean hasCycleDFS(int node, Map<Integer, Set<Integer>> graph, 
                                Set<Integer> visited, Set<Integer> recursionStack,
                                List<PatchOperation> operations) {
        visited.add(node);
        recursionStack.add(node);
        
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleDFS(neighbor, graph, visited, recursionStack, operations)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) {
                // Cycle detected
                reporter.error("OPERATION_GRAPH_CYCLE", 
                    "Cycle detected in operation graph between operations " + node + 
                    " and " + neighbor, null);
                return true;
            }
        }
        
        recursionStack.remove(node);
        return false;
    }
    
    private void detectConflicts(List<PatchOperation> operations) {
        Map<String, List<Integer>> pathToOperations = new HashMap<>();
        
        // Group operations by path
        for (int i = 0; i < operations.size(); i++) {
            PatchOperation op = operations.get(i);
            String path = op.getPath();
            
            pathToOperations.computeIfAbsent(path, k -> new ArrayList<>()).add(i);
            
            // Also track target paths for renames
            if (op.getType() == PatchOperation.Type.RENAME_PATH && op.getTargetPath() != null) {
                pathToOperations.computeIfAbsent(op.getTargetPath(), k -> new ArrayList<>()).add(i);
            }
        }
        
        // Check for conflicting operations on same path
        for (Map.Entry<String, List<Integer>> entry : pathToOperations.entrySet()) {
            List<Integer> opIndices = entry.getValue();
            
            if (opIndices.size() > 1) {
                // Check if operations are compatible
                boolean hasConflict = false;
                PatchOperation.Type firstType = operations.get(opIndices.get(0)).getType();
                
                for (int i = 1; i < opIndices.size(); i++) {
                    PatchOperation.Type secondType = operations.get(opIndices.get(i)).getType();
                    
                    if (!areCompatibleOperations(firstType, secondType)) {
                        hasConflict = true;
                        break;
                    }
                }
                
                if (hasConflict) {
                    reporter.error("OPERATION_GRAPH_CONFLICT", 
                        "Conflicting operations on path: " + entry.getKey() + 
                        " at indices " + opIndices, null);
                }
            }
        }
    }
    
    private boolean areCompatibleOperations(PatchOperation.Type first, PatchOperation.Type second) {
        // Operations are compatible if they don't conflict
        // For example, multiple permission updates on the same file are OK
        if (first == PatchOperation.Type.UPDATE_PERMISSIONS && 
            second == PatchOperation.Type.UPDATE_PERMISSIONS) {
            return true;
        }
        
        // All other combinations are potentially conflicting
        return false;
    }
    
    private void validateTopologicalOrder(List<PatchOperation> operations, 
                                          Map<Integer, Set<Integer>> graph) {
        // Perform topological sort and validate against current order
        List<Integer> topologicalOrder = topologicalSort(graph);
        
        if (topologicalOrder == null) {
            // Cycle already detected
            return;
        }
        
        // Check if current order respects dependencies
        Map<Integer, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < operations.size(); i++) {
            positionMap.put(i, i);
        }
        
        for (int i = 0; i < operations.size(); i++) {
            for (int dependency : graph.get(i)) {
                if (positionMap.get(i) > positionMap.get(dependency)) {
                    reporter.warn("OPERATION_GRAPH_ORDER", 
                        "Operation at index " + i + " should execute before " + 
                        "operation at index " + dependency + " based on dependencies", null);
                }
            }
        }
    }
    
    private List<Integer> topologicalSort(Map<Integer, Set<Integer>> graph) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> tempVisited = new HashSet<>();
        
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (!topologicalSortDFS(node, graph, visited, tempVisited, result)) {
                    return null; // Cycle detected
                }
            }
        }
        
        return result;
    }
    
    private boolean topologicalSortDFS(int node, Map<Integer, Set<Integer>> graph,
                                        Set<Integer> visited, Set<Integer> tempVisited,
                                        List<Integer> result) {
        visited.add(node);
        tempVisited.add(node);
        
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                if (!topologicalSortDFS(neighbor, graph, visited, tempVisited, result)) {
                    return false;
                }
            } else if (tempVisited.contains(neighbor)) {
                return false; // Cycle
            }
        }
        
        tempVisited.remove(node);
        result.add(node);
        return true;
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
