package com.syncforge.fuzz;

import com.syncforge.patch.PatchReader;

public class PatchParserFuzzer {
    public static void fuzzerTestOneInput(byte[] data) {
        try {
            // Iterate multiple times to trigger temporal bugs
            for (int i = 0; i < 200; i++) {
                PatchReader reader = new PatchReader();
                reader.read(data);
            }
        } catch (Exception expected) {
            // Expected exceptions
        }
    }
}
