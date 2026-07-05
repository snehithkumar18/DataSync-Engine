package com.syncforge.runtime;

import com.syncforge.core.exceptions.ExecutionException;
import com.syncforge.planner.SyncAction;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RuntimeTest {

    @Test
    public void testCleanTransactionCommit() throws IOException {
        Path tempBaseDir = Files.createTempDirectory("sf_trans_base");
        Path tempTargetDir = Files.createTempDirectory("sf_trans_target");
        Path tempMetaDir = Files.createTempDirectory("sf_trans_meta");

        String relFile = "app.log";
        Files.writeString(tempBaseDir.resolve(relFile), "log info");

        SyncAction act = new SyncAction(SyncAction.Type.CREATE_FILE, relFile, null);

        TransactionExecutor exec = new TransactionExecutor(tempBaseDir.toString(), tempTargetDir.toString(), tempMetaDir.toString());
        exec.execute(List.of(act));

        // File must be created in target
        assertTrue(Files.exists(tempTargetDir.resolve(relFile)));
        assertEquals("log info", Files.readString(tempTargetDir.resolve(relFile)));

        // WAL directory must be cleaned up on commit
        assertFalse(Files.exists(tempMetaDir.resolve("wal/wal.log")));
    }

    @Test
    public void testTransactionRollbackOnFailure() throws IOException {
        Path tempBaseDir = Files.createTempDirectory("sf_fail_base");
        Path tempTargetDir = Files.createTempDirectory("sf_fail_target");
        Path tempMetaDir = Files.createTempDirectory("sf_fail_meta");

        String relFile = "test.txt";
        Files.writeString(tempTargetDir.resolve(relFile), "original content");
        Files.writeString(tempBaseDir.resolve(relFile), "new content");

        // Action 1: Update test.txt to new content
        // Action 2: Trigger invalid action to force crash
        SyncAction act1 = new SyncAction(SyncAction.Type.UPDATE_FILE, relFile, null);
        SyncAction actInvalid = new SyncAction(SyncAction.Type.CREATE_DIR, relFile + "/subdir", null);

        TransactionExecutor exec = new TransactionExecutor(tempBaseDir.toString(), tempTargetDir.toString(), tempMetaDir.toString());
        
        assertThrows(ExecutionException.class, () -> {
            exec.execute(List.of(act1, actInvalid));
        });

        // Target file must be rolled back to original content
        assertTrue(Files.exists(tempTargetDir.resolve(relFile)));
        assertEquals("original content", Files.readString(tempTargetDir.resolve(relFile)));
    }
}
