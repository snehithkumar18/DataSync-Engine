package com.syncforge.fuzz;

import com.syncforge.metadata.*;
import com.syncforge.snapshot.*;
import com.syncforge.patch.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CorpusGeneratorTest {

    @Test
    public void generateCorpusSeeds() throws IOException {
        Path currentDir = Paths.get("").toAbsolutePath();
        Path corpusDir;
        if (currentDir.getFileName().toString().equals("fuzz")) {
            corpusDir = currentDir.resolve("corpus");
        } else {
            corpusDir = currentDir.resolve("fuzz/corpus");
        }
        Files.createDirectories(corpusDir.resolve("SnapshotParserFuzzer"));
        Files.createDirectories(corpusDir.resolve("PatchParserFuzzer"));
        
        SnapshotModel minModel = new SnapshotModel();
        byte[] minSnap = new SnapshotWriter(false).write(minModel);
        Files.write(corpusDir.resolve("SnapshotParserFuzzer/minimal.sfsn"), minSnap);
        
        SnapshotModel nestModel = new SnapshotModel();
        FileTimestamp ts = FileTimestamp.fromEpochMilli(123456L, 123456L, 123456L);
        FileMode fileMode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileMode dirMode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();

        nestModel.getEntries().add(new FileEntry(
            "src/App.java", ts, fileMode, 100L,
            new FileHash("SHA-256", "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
            Collections.emptyMap(), Collections.emptyList()
        ));
        nestModel.getEntries().add(new DirectoryEntry("src", ts, dirMode, 0L, List.of("App.java")));
        
        byte[] nestSnap = new SnapshotWriter(true).write(nestModel);
        Files.write(corpusDir.resolve("SnapshotParserFuzzer/nested-tree.sfsn"), nestSnap);
        
        byte[] minPatch = DeltaEncoder.encode("hello source".getBytes(), "hello target".getBytes());
        Files.write(corpusDir.resolve("PatchParserFuzzer/minimal.sfph"), minPatch);
    }
}
