package com.syncforge.fuzz;

import com.syncforge.query.QueryParser;

public class QueryParserFuzzer {
    public static void fuzzerTestOneInput(byte[] data) {
        try {
            String input = new String(data);
            // Iterate multiple times to trigger temporal bugs
            for (int i = 0; i < 200; i++) {
                new QueryParser(input).parse();
            }
        } catch (Exception expected) {
            // Expected exceptions
        }
    }
}
