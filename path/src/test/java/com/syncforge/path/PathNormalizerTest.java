package com.syncforge.path;

import com.syncforge.core.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathNormalizerTest {

    @Test
    public void testNormalizeBasic() {
        assertEquals("a/b/c", PathNormalizer.normalize("a/b/c"));
        assertEquals("a/b/c", PathNormalizer.normalize("a\\b\\c"));
        assertEquals("a/b/c", PathNormalizer.normalize("a//b///c"));
    }

    @Test
    public void testNormalizeAbsolute() {
        assertEquals("/a/b/c", PathNormalizer.normalize("/a/b/c"));
        assertEquals("C:/a/b/c", PathNormalizer.normalize("C:\\a\\\\b/c"));
    }

    @Test
    public void testTraversalRejection() {
        assertThrows(ValidationException.class, () -> PathNormalizer.normalize("a/../b"));
        assertThrows(ValidationException.class, () -> PathNormalizer.normalize("../a"));
    }

    @Test
    public void testReservedNamesRejection() {
        assertThrows(ValidationException.class, () -> PathNormalizer.normalize("a/CON/b"));
        assertThrows(ValidationException.class, () -> PathNormalizer.normalize("LPT1.txt"));
    }
}
