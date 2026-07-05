package com.syncforge.fuzz;

import com.syncforge.path.PathNormalizer;

public class PathNormalizerFuzzer {
    public static void fuzzerTestOneInput(byte[] data) {
        try {
            String input = new String(data);
            // Iterate multiple times to trigger temporal bugs
            for (int i = 0; i < 200; i++) {
                PathNormalizer.normalize(input);
            }
        } catch (Exception expected) {
            // Expected exceptions
        }
    }
}
