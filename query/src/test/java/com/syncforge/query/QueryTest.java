package com.syncforge.query;

import com.syncforge.metadata.*;
import com.syncforge.snapshot.SnapshotModel;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class QueryTest {

    @Test
    public void testBasicQueryEvaluation() {
        FileTimestamp ts1 = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileTimestamp ts2 = FileTimestamp.fromEpochMilli(5000L, 5000L, 5000L);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();

        FileEntry f1 = new FileEntry(
            "src/Main.java", ts1, mode, 500,
            FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"), Collections.emptyMap(), Collections.emptyList()
        );

        FileEntry f2 = new FileEntry(
            "src/Util.java", ts2, mode, 1500,
            FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b856"), Collections.emptyMap(), Collections.emptyList()
        );

        SnapshotModel model = new SnapshotModel(List.of(f1, f2), 1, 0, null);

        QueryEngine engine = new QueryEngine(model);

        // Test 1: size filter
        List<EntryMetadata> r1 = engine.execute("size > 1000");
        assertEquals(1, r1.size());
        assertEquals("src/Util.java", r1.get(0).getNormalizedPath());

        // Test 2: path contains
        List<EntryMetadata> r2 = engine.execute("path CONTAINS \"Main\"");
        assertEquals(1, r2.size());
        assertEquals("src/Main.java", r2.get(0).getNormalizedPath());

        // Test 3: AND logical operator
        List<EntryMetadata> r3 = engine.execute("size > 300 AND mtime >= 2000");
        assertEquals(1, r3.size());
        assertEquals("src/Util.java", r3.get(0).getNormalizedPath());
    }
}
