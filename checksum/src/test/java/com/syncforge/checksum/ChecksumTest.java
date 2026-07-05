package com.syncforge.checksum;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class ChecksumTest {

    @Test
    public void testSHA256Hashing() throws IOException {
        String data = "SyncForge Hashing Checksum Verification Test";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        String hash1 = ChecksumHasher.computeSHA256(bytes);
        String hash2 = ChecksumHasher.computeSHA256(new ByteArrayInputStream(bytes));

        assertEquals(hash1, hash2);
        assertEquals(64, hash1.length()); // 64 hex characters
    }

    @Test
    public void testCRC32Hashing() throws IOException {
        String data = "SyncForge CRC32 Test";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        long crc1 = ChecksumHasher.computeCRC32(bytes);
        long crc2 = ChecksumHasher.computeCRC32(new ByteArrayInputStream(bytes));

        assertEquals(crc1, crc2);
        assertTrue(crc1 > 0);
    }
}
