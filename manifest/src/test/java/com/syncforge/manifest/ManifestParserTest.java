package com.syncforge.manifest;

import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManifestParserTest {

    @Test
    public void testParseValidManifest() {
        String dsl = """
            manifest "my_project" {
                root = "."
                mode = "mirror"
                checksum = "sha256"
                preserve_permissions = true
                include "**/*.java"
                exclude "build/**"
            }
            """;
        ManifestModel model = ManifestParser.parse(dsl, "test.sfm");
        assertEquals("my_project", model.getName());
        assertEquals(".", model.getRoot());
        assertEquals("mirror", model.getMode());
        assertEquals("sha256", model.getChecksum());
        assertTrue(model.isPreservePermissions());
        assertEquals(1, model.getIncludes().size());
        assertEquals("**/*.java", model.getIncludes().get(0));
        assertEquals(1, model.getExcludes().size());
        assertEquals("build/**", model.getExcludes().get(0));
    }

    @Test
    public void testParseInvalidMode() {
        String dsl = """
            manifest "my_project" {
                root = "."
                mode = "invalid_mode"
            }
            """;
        assertThrows(ValidationException.class, () -> ManifestParser.parse(dsl, "test.sfm"));
    }

    @Test
    public void testParseSyntaxError() {
        String dsl = """
            manifest "my_project" {
                root =
            }
            """;
        assertThrows(ParseException.class, () -> ManifestParser.parse(dsl, "test.sfm"));
    }
}
