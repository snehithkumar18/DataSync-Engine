package com.syncforge.fuzz;

import com.syncforge.manifest.ManifestParser;

public class ManifestParserFuzzer {
    public static void fuzzerTestOneInput(byte[] data) {
        try {
            String input = new String(data);
            // Iterate multiple times to trigger temporal bugs
            for (int i = 0; i < 200; i++) {
                ManifestParser.parse(input, "fuzz_input.sfm");
            }
        } catch (Exception expected) {
            // Expected exceptions
        }
    }
}
