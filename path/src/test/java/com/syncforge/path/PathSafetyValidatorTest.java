package com.syncforge.path;

import com.syncforge.core.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathSafetyValidatorTest {

    @Test
    public void testValidPaths() {
        assertDoesNotThrow(() -> PathSafetyValidator.validate("a/b/c"));
        assertDoesNotThrow(() -> PathSafetyValidator.validate("src/main/java/App.java"));
        assertDoesNotThrow(() -> PathSafetyValidator.validate("C:/projects/syncforge"));
        assertDoesNotThrow(() -> PathSafetyValidator.validate("//?/C:/projects/syncforge"));
    }

    @Test
    public void testDirectoryTraversalRejection() {
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("../a/b"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/../b"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/b/.."));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/..\\b"));
    }

    @Test
    public void testControlCharactersRejection() {
        // ASCII 0x0A (newline) is a control character
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/b\nc"));
        // ASCII 0x00 (null byte)
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/b\u0000c"));
    }

    @Test
    public void testInvalidCharactersRejection() {
        // Colons are allowed on root (e.g. C:/) but not in sub-segments
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/b:c"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/b*c"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/b?c"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/b<c"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/b|c"));
    }

    @Test
    public void testWindowsReservedNamesRejection() {
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/con/b"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("a/prn.txt"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("NUL"));
        assertThrows(ValidationException.class, () -> PathSafetyValidator.validate("com3.log"));
    }
}
