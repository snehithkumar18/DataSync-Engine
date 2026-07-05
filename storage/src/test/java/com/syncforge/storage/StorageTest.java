package com.syncforge.storage;

import com.syncforge.metadata.*;
import com.syncforge.snapshot.SnapshotModel;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void testSnapshotStoreOperations() throws IOException {
        Path tempRepoDir = Files.createTempDirectory("sf_repo");

        SnapshotStore store = new SnapshotStore(tempRepoDir);

        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileEntry file = new FileEntry(
            "Main.java", ts, mode, 200,
            FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"), Collections.emptyMap(), Collections.emptyList()
        );

        SnapshotModel model = new SnapshotModel(List.of(file), 1, 0, null);

        // Test save
        String id = store.save(model, false);
        assertNotNull(id);

        // Test list
        List<String> list = store.list();
        assertTrue(list.contains(id));

        // Test load
        SnapshotModel loaded = store.load(id);
        assertEquals(1, loaded.getEntries().size());
        assertEquals("Main.java", loaded.getEntries().get(0).getNormalizedPath());

        // Test delete
        assertTrue(store.delete(id));
        assertFalse(store.list().contains(id));
    }
}
