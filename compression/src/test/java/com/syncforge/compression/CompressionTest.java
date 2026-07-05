package com.syncforge.compression;

import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class CompressionTest {

    @Test
    public void testCompressAndDecompress() {
        String original = "This is a large string containing a lot of repeated text. Let's make sure it compresses and decompresses cleanly! "
                + "This is a large string containing a lot of repeated text. Let's make sure it compresses and decompresses cleanly!";
        
        byte[] originalBytes = original.getBytes(StandardCharsets.UTF_8);
        byte[] compressed = CompressionEngine.compress(originalBytes);
        
        assertNotNull(compressed);
        assertTrue(compressed.length < originalBytes.length); // should actually compress it

        byte[] decompressed = CompressionEngine.decompress(compressed, originalBytes.length);
        assertEquals(original, new String(decompressed, StandardCharsets.UTF_8));
    }
}
