package com.syncforge.patch;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class PatchTest {

    @Test
    public void testDeltaEncodingAndDecoding() throws IOException {
        String sourceText = "Hello World! This is the SyncForge custom VCDIFF binary patch engine. It operates with high efficiency.";
        String targetText = "Hello World! This is the SyncForge custom VCDIFF binary patch engine. It operates with extra high efficiency! Let's append more text.";

        byte[] source = sourceText.getBytes(StandardCharsets.UTF_8);
        byte[] target = targetText.getBytes(StandardCharsets.UTF_8);

        // 1. Encode patch
        byte[] patch = DeltaEncoder.encode(source, target);
        assertNotNull(patch);
        assertTrue(patch.length > 0);

        // 2. Decode patch
        byte[] reconstructed = DeltaDecoder.decode(source, patch);
        assertArrayEquals(target, reconstructed);
        assertEquals(targetText, new String(reconstructed, StandardCharsets.UTF_8));
    }

    @Test
    public void testDeltaWithRepeatedRuns() throws IOException {
        byte[] source = new byte[]{1, 2, 3, 4, 5};
        byte[] target = new byte[]{1, 2, 3, 4, 5, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 2, 3};

        byte[] patch = DeltaEncoder.encode(source, target);
        byte[] reconstructed = DeltaDecoder.decode(source, patch);
        assertArrayEquals(target, reconstructed);
    }
}
