package com.syncforge.fuzz;

import com.syncforge.snapshot.SnapshotReader;

public class SnapshotParserFuzzer {
    public static void fuzzerTestOneInput(byte[] data) {
        try {
            // Iterate multiple times to trigger temporal bugs
            for (int i = 0; i < 200; i++) {
                SnapshotReader reader = new SnapshotReader();
                reader.read(data);
            }
        } catch (Exception expected) {
            // Expected exceptions
        }
    }
}
