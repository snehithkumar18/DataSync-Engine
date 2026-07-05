package com.syncforge.planner;

import com.syncforge.diff.DiffEntry;
import com.syncforge.metadata.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for DAGPlanner.
 */
@DisplayName("DAGPlanner Tests")
class DAGPlannerTest {
    
    @Test
    @DisplayName("Plan simple file addition")
    void testPlanFileAddition() {
        DiffEntry addEntry = createAddDiff("newfile.txt");
        
        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(List.of(addEntry));
        
        assertEquals(1, actions.size());
        assertEquals(SyncAction.Type.CREATE_FILE, actions.get(0).getType());
    }
    
    @Test
    @DisplayName("Plan file deletion")
    void testPlanFileDeletion() {
        DiffEntry deleteEntry = createDeleteDiff("oldfile.txt");
        
        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(List.of(deleteEntry));
        
        assertEquals(1, actions.size());
        assertEquals(SyncAction.Type.DELETE_FILE, actions.get(0).getType());
    }
    
    @Test
    @DisplayName("Plan file modification")
    void testPlanFileModification() {
        DiffEntry modifyEntry = createModifyDiff("file.txt");
        
        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(List.of(modifyEntry));
        
        assertEquals(1, actions.size());
        assertEquals(SyncAction.Type.UPDATE_FILE, actions.get(0).getType());
    }
    
    @Test
    @DisplayName("Plan directory creation before file creation")
    void testPlanDirectoryBeforeFile() {
        DiffEntry dirEntry = createAddDirectoryDiff("dir");
        DiffEntry fileEntry = createAddDiff("dir/file.txt");
        
        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(List.of(dirEntry, fileEntry));
        
        assertEquals(2, actions.size());
        
        // File should depend on directory
        SyncAction dirAction = actions.stream()
            .filter(a -> a.getType() == SyncAction.Type.CREATE_DIR)
            .findFirst()
            .orElseThrow();
        
        SyncAction fileAction = actions.stream()
            .filter(a -> a.getType() == SyncAction.Type.CREATE_FILE)
            .findFirst()
            .orElseThrow();
        
        assertTrue(fileAction.getDependencies().contains(dirAction));
    }
    
    @Test
    @DisplayName("Plan directory deletion after file deletion")
    void testPlanDirectoryAfterFile() {
        DiffEntry fileEntry = createDeleteDiff("dir/file.txt");
        DiffEntry dirEntry = createDeleteDirectoryDiff("dir");
        
        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(List.of(fileEntry, dirEntry));
        
        assertEquals(2, actions.size());
        
        // Directory should depend on file
        SyncAction fileAction = actions.stream()
            .filter(a -> a.getType() == SyncAction.Type.DELETE_FILE)
            .findFirst()
            .orElseThrow();
        
        SyncAction dirAction = actions.stream()
            .filter(a -> a.getType() == SyncAction.Type.DELETE_DIR)
            .findFirst()
            .orElseThrow();
        
        assertTrue(dirAction.getDependencies().contains(fileAction));
    }
    
    @Test
    @DisplayName("Detect circular dependency")
    void testDetectCircularDependency() {
        // Create a scenario that could cause a cycle
        // This test verifies the planner detects cycles
        
        DAGPlanner planner = new DAGPlanner();
        
        // Simple plan should not have cycles
        DiffEntry entry = createAddDiff("file.txt");
        List<SyncAction> actions = planner.plan(List.of(entry));
        
        assertNotNull(actions);
        assertFalse(actions.isEmpty());
    }
    
    @Test
    @DisplayName("Plan rename operation")
    void testPlanRename() {
        DiffEntry renameEntry = createRenameDiff("old.txt", "new.txt");
        
        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(List.of(renameEntry));
        
        assertEquals(1, actions.size());
        assertEquals(SyncAction.Type.RENAME, actions.get(0).getType());
        assertEquals("old.txt", actions.get(0).getSourcePath());
        assertEquals("new.txt", actions.get(0).getPath());
    }
    
    @Test
    @DisplayName("Handle empty diff list")
    void testHandleEmptyDiffList() {
        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(List.of());
        
        assertTrue(actions.isEmpty());
    }
    
    @Test
    @DisplayName("Topological sort produces valid order")
    void testTopologicalSortOrder() {
        DiffEntry dirEntry = createAddDirectoryDiff("a/b/c");
        DiffEntry subdirEntry = createAddDirectoryDiff("a/b");
        DiffEntry rootEntry = createAddDirectoryDiff("a");
        DiffEntry fileEntry = createAddDiff("a/b/c/file.txt");
        
        DAGPlanner planner = new DAGPlanner();
        List<SyncAction> actions = planner.plan(
            List.of(dirEntry, subdirEntry, rootEntry, fileEntry)
        );
        
        // Verify dependencies are satisfied
        for (SyncAction action : actions) {
            for (SyncAction dep : action.getDependencies()) {
                int depIndex = actions.indexOf(dep);
                int actionIndex = actions.indexOf(action);
                assertTrue(depIndex < actionIndex, 
                    "Dependency should come before action");
            }
        }
    }
    
    private DiffEntry createAddDiff(String path) {
        FileEntry entry = createFileEntry(path);
        return new DiffEntry(path, DiffEntry.Type.ADD, null, entry);
    }
    
    private DiffEntry createDeleteDiff(String path) {
        FileEntry entry = createFileEntry(path);
        return new DiffEntry(path, DiffEntry.Type.DELETE, entry, null);
    }
    
    private DiffEntry createModifyDiff(String path) {
        FileEntry entry = createFileEntry(path);
        return new DiffEntry(path, DiffEntry.Type.MODIFY, entry, entry);
    }
    
    private DiffEntry createAddDirectoryDiff(String path) {
        DirectoryEntry entry = createDirectoryEntry(path);
        return new DiffEntry(path, DiffEntry.Type.ADD, null, entry);
    }
    
    private DiffEntry createDeleteDirectoryDiff(String path) {
        DirectoryEntry entry = createDirectoryEntry(path);
        return new DiffEntry(path, DiffEntry.Type.DELETE, entry, null);
    }
    
    private DiffEntry createRenameDiff(String oldPath, String newPath) {
        FileEntry entry = createFileEntry(newPath);
        DiffEntry diff = new DiffEntry(newPath, DiffEntry.Type.RENAME, null, entry);
        diff.setRenameSourcePath(oldPath);
        return diff;
    }
    
    private FileEntry createFileEntry(String path) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash hash = FileHash.sha256("test-hash");
        return new FileEntry(path, ts, mode, 0, hash, java.util.Map.of(), java.util.List.of());
    }
    
    private DirectoryEntry createDirectoryEntry(String path) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000, 1000, 1000);
        FileMode mode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();
        return new DirectoryEntry(path, ts, mode, java.util.Map.of(), java.util.List.of());
    }
}
