package com.syncforge.integration;

import com.syncforge.core.exceptions.ExecutionException;
import com.syncforge.planner.SyncAction;
import com.syncforge.runtime.TransactionExecutor;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CrashInjectionTest {

    @Test
    public void testMidTransactionFailureAndRecovery() throws IOException {
        Path tempBaseDir = Files.createTempDirectory("sf_crash_base");
        Path tempTargetDir = Files.createTempDirectory("sf_crash_target");
        Path tempMetaDir = Files.createTempDirectory("sf_crash_meta");

        // 1. Setup 10 original files in target
        List<String> originalFiles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "file_" + i + ".txt";
            originalFiles.add(name);
            Files.writeString(tempTargetDir.resolve(name), "original content " + i);
            Files.writeString(tempBaseDir.resolve(name), "new content " + i);
        }

        // Create sync actions to update all 10 files
        List<SyncAction> actions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            actions.add(new SyncAction(SyncAction.Type.UPDATE_FILE, originalFiles.get(i), null));
        }

        // Action 11 is an invalid action that tries to create a directory under a file
        // which is guaranteed to throw a NoSuchFileException or FileAlreadyExistsException
        actions.add(new SyncAction(SyncAction.Type.CREATE_DIR, "file_0.txt/sub_directory", null));

        TransactionExecutor exec = new TransactionExecutor(tempBaseDir.toString(), tempTargetDir.toString(), tempMetaDir.toString());

        // Execute transaction, it must throw ExecutionException because of the 11th invalid action
        assertThrows(ExecutionException.class, () -> {
            exec.execute(actions);
        });

        // 2. Assert rollback: Every single file in target directory must be restored to its original state!
        for (int i = 0; i < 10; i++) {
            String name = "file_" + i + ".txt";
            assertTrue(Files.exists(tempTargetDir.resolve(name)));
            assertEquals("original content " + i, Files.readString(tempTargetDir.resolve(name)));
        }

        // The WAL folder must be empty or cleaned up after successful rollback
        assertFalse(Files.exists(tempMetaDir.resolve("wal/wal.log")));
    }
}
