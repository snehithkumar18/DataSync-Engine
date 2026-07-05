package com.syncforge.fuzz;

import com.syncforge.conflict.Conflict;
import com.syncforge.conflict.ConflictResolver;
import com.syncforge.conflict.ConflictType;
import com.syncforge.metadata.FileTimestamp;
import com.syncforge.metadata.FileMode;
import com.syncforge.metadata.DirectoryEntry;
import java.util.Collections;

public class ConflictResolverFuzzer {
    public static void fuzzerTestOneInput(byte[] data) {
        if (data.length < 2) return;
        try {
            int strategyIndex = Math.abs(data[0]) % ConflictResolver.ResolutionStrategy.values().length;
            ConflictResolver.ResolutionStrategy strategy = ConflictResolver.ResolutionStrategy.values()[strategyIndex];
            
            int typeIndex = Math.abs(data[1]) % ConflictType.values().length;
            ConflictType type = ConflictType.values()[typeIndex];
            
            String path = "test_path";
            FileTimestamp ts1 = FileTimestamp.fromEpochMilli(100, 100, 100);
            FileTimestamp ts2 = FileTimestamp.fromEpochMilli(200, 200, 200);
            
            FileMode mode = FileMode.builder().type(FileMode.Type.DIRECTORY).permissions(0755).build();
            
            DirectoryEntry source = new DirectoryEntry(path, ts1, mode, 0L, Collections.emptyList());
            DirectoryEntry target = new DirectoryEntry(path, ts2, mode, 0L, Collections.emptyList());
            
            Conflict conflict = new Conflict(path, type, source, source, target);
            
            // Iterate multiple times to trigger temporal bugs
            for (int i = 0; i < 200; i++) {
                ConflictResolver.resolve(conflict, strategy, "baseDir", "localDir", "remoteDir", "outputDir");
            }
        } catch (Exception expected) {
            // Expected exceptions
        }
    }
}
