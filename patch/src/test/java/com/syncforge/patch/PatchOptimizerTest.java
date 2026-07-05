package com.syncforge.patch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for PatchOptimizer.
 */
@DisplayName("PatchOptimizer Tests")
class PatchOptimizerTest {
    
    @Test
    @DisplayName("Optimize empty patch")
    void testOptimizeEmptyPatch() {
        PatchOptimizer optimizer = new PatchOptimizer();
        List<PatchOperation> result = optimizer.optimize(List.of());
        
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Merge adjacent create operations")
    void testMergeAdjacentCreates() {
        PatchOperation op1 = new PatchOperation(
            PatchOperation.Type.CREATE_FILE, "file1.txt", null, "hash1", 100, 0644
        );
        PatchOperation op2 = new PatchOperation(
            PatchOperation.Type.CREATE_FILE, "file2.txt", null, "hash2", 200, 0644
        );
        
        PatchOptimizer optimizer = new PatchOptimizer(true, false, false);
        List<PatchOperation> result = optimizer.optimize(List.of(op1, op2));
        
        assertEquals(2, result.size());
    }
    
    @Test
    @DisplayName("Eliminate no-op operations")
    void testEliminateNoOps() {
        PatchOperation op = new PatchOperation(
            PatchOperation.Type.CREATE_FILE, "file.txt", null, "hash", 100, 0644
        );
        
        PatchOptimizer optimizer = new PatchOptimizer(false, true, false);
        List<PatchOperation> result = optimizer.optimize(List.of(op));
        
        // No-op elimination depends on context, so operation may remain
        assertFalse(result.isEmpty());
    }
    
    @Test
    @DisplayName("Reorder operations for efficiency")
    void testReorderOperations() {
        PatchOperation deleteOp = new PatchOperation(
            PatchOperation.Type.DELETE_FILE, "file.txt", null, null, 0, null
        );
        PatchOperation createOp = new PatchOperation(
            PatchOperation.Type.CREATE_FILE, "newfile.txt", null, "hash", 100, 0644
        );
        
        PatchOptimizer optimizer = new PatchOptimizer(false, false, true);
        List<PatchOperation> result = optimizer.optimize(List.of(deleteOp, createOp));
        
        assertEquals(2, result.size());
    }
    
    @Test
    @DisplayName("Calculate optimization statistics")
    void testCalculateOptimizationStats() {
        PatchOperation op1 = new PatchOperation(
            PatchOperation.Type.CREATE_FILE, "file1.txt", null, "hash1", 100, 0644
        );
        PatchOperation op2 = new PatchOperation(
            PatchOperation.Type.CREATE_FILE, "file2.txt", null, "hash2", 200, 0644
        );
        
        PatchOptimizer optimizer = new PatchOptimizer();
        List<PatchOperation> original = List.of(op1, op2);
        List<PatchOperation> optimized = optimizer.optimize(original);
        
        PatchOptimizer.OptimizationStats stats = optimizer.calculateStats(original, optimized);
        
        assertNotNull(stats);
        assertEquals(2, stats.originalCount());
        assertTrue(stats.reductionPercent() >= 0);
    }
    
    @Test
    @DisplayName("Builder creates optimizer with custom settings")
    void testBuilderCreatesOptimizer() {
        PatchOptimizer optimizer = PatchOptimizer.builder()
            .withMergeAdjacentCreates(true)
            .withEliminateNoOps(true)
            .withReorderOperations(false)
            .build();
        
        assertNotNull(optimizer);
    }
    
    @Test
    @DisplayName("Optimize with all features enabled")
    void testOptimizeWithAllFeatures() {
        PatchOperation op1 = new PatchOperation(
            PatchOperation.Type.CREATE_FILE, "file.txt", null, "hash", 100, 0644
        );
        
        PatchOptimizer optimizer = new PatchOptimizer(true, true, true);
        List<PatchOperation> result = optimizer.optimize(List.of(op1));
        
        assertNotNull(result);
    }
    
    @Test
    @DisplayName("Handle permission update operations")
    void testHandlePermissionUpdate() {
        PatchOperation op = new PatchOperation(
            PatchOperation.Type.UPDATE_PERMISSIONS, "file.txt", null, null, 0, 0755
        );
        
        PatchOptimizer optimizer = new PatchOptimizer();
        List<PatchOperation> result = optimizer.optimize(List.of(op));
        
        assertEquals(1, result.size());
    }
    
    @Test
    @DisplayName("Handle rename operations")
    void testHandleRenameOperations() {
        PatchOperation op = new PatchOperation(
            PatchOperation.Type.RENAME_PATH, "new.txt", "old.txt", null, 100, null
        );
        
        PatchOptimizer optimizer = new PatchOptimizer();
        List<PatchOperation> result = optimizer.optimize(List.of(op));
        
        assertEquals(1, result.size());
        assertEquals(PatchOperation.Type.RENAME_PATH, result.get(0).getType());
    }
}
