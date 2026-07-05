package com.syncforge.snapshot;

import com.syncforge.metadata.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Technical unit tests for testing SFSN binary snapshot writing, reading, and Merkle tree verification.
 */
public class SnapshotTest {

    @Test
    public void testWriteAndReadSnapshot() throws IOException {
        SnapshotModel model = new SnapshotModel();
        
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1234567890L, 1234567890L, 1234567890L);
        FileMode fileMode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileMode dirMode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();
        FileMode linkMode = FileMode.builder().type(FileMode.Type.SYMLINK).permissions(0777).build();

        // 1. Add FileEntry with Xattrs and Alternate Data Streams
        Map<String, String> xattrs = new HashMap<>();
        xattrs.put("user.author", "SyncForge");
        xattrs.put("user.encrypted", "false");
        
        FileEntry.AlternateDataStream ads = new FileEntry.AlternateDataStream(
            "zone.identifier", 26L, new FileHash("SHA-256", "3f786850e387550fdab836ed7e6dc881de23001bdecff71265811b712f5a89d2")
        );

        FileEntry fileEntry = new FileEntry(
            "src/App.java", ts, fileMode, 100, 
            new FileHash("SHA-256", "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
            xattrs, List.of(ads)
        );

        // 2. Add DirectoryEntry
        DirectoryEntry dirEntry = new DirectoryEntry(
            "src", ts, dirMode, 0, List.of("App.java", "link.lnk")
        );

        // 3. Add SymlinkEntry
        SymlinkEntry symlinkEntry = new SymlinkEntry(
            "src/link.lnk", ts, linkMode, 12, "src/App.java"
        );

        model.getEntries().add(fileEntry);
        model.getEntries().add(dirEntry);
        model.getEntries().add(symlinkEntry);

        // --- Test Raw Uncompressed Serialization ---
        SnapshotWriter rawWriter = new SnapshotWriter(false);
        byte[] rawBytes = rawWriter.write(model);

        SnapshotReader rawReader = new SnapshotReader();
        SnapshotModel rawReadModel = rawReader.read(rawBytes);
        
        assertEquals(3, rawReadModel.getEntries().size());
        
        // Assert sorting order: "src", "src/App.java", "src/link.lnk"
        DirectoryEntry readDir = (DirectoryEntry) rawReadModel.getEntries().get(0);
        assertEquals("src", readDir.getNormalizedPath());
        assertEquals(0755, readDir.getMode().getPosixPermissions());
        assertTrue(readDir.getChildren().contains("App.java"));

        FileEntry readFile = (FileEntry) rawReadModel.getEntries().get(1);
        assertEquals("src/App.java", readFile.getNormalizedPath());
        assertEquals(100, readFile.getSize());
        assertEquals("SHA-256", readFile.getContentHash().algorithm());
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", readFile.getContentHash().value());
        assertEquals("SyncForge", readFile.getExtendedAttributes().get("user.author"));
        assertEquals(1, readFile.getAlternateDataStreams().size());
        assertEquals("zone.identifier", readFile.getAlternateDataStreams().get(0).name());
        assertEquals(26, readFile.getAlternateDataStreams().get(0).size());

        SymlinkEntry readLink = (SymlinkEntry) rawReadModel.getEntries().get(2);
        assertEquals("src/link.lnk", readLink.getNormalizedPath());
        assertEquals("src/App.java", readLink.getTargetPath());

        // Verify Merkle Root is not null and consistent
        assertNotNull(rawReadModel.getRootNode());
        assertTrue(MerkleTreeBuilder.verifyTreeIntegrity(rawReadModel.getRootNode()));

        // --- Test Compressed Serialization ---
        SnapshotWriter compWriter = new SnapshotWriter(true);
        byte[] compBytes = compWriter.write(model);

        SnapshotReader compReader = new SnapshotReader();
        SnapshotModel compReadModel = compReader.read(compBytes);
        
        assertEquals(3, compReadModel.getEntries().size());
        
        DirectoryEntry de = (DirectoryEntry) compReadModel.getEntries().get(0);
        assertEquals("src", de.getNormalizedPath());
        assertEquals(0755, de.getMode().getPosixPermissions());
        
        assertNotNull(compReadModel.getRootNode());
        assertEquals(rawReadModel.getRootNode().getHash(), compReadModel.getRootNode().getHash());
    }
}
