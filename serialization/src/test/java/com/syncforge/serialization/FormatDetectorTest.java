package com.syncforge.serialization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Unit tests for FormatDetector.
 */
@DisplayName("FormatDetector Tests")
class FormatDetectorTest {
    
    @Test
    @DisplayName("Detect JSON format from string")
    void testDetectJsonFromString() {
        FormatDetector detector = new FormatDetector();
        
        FormatDetector.SerializationFormat format = detector.detectFormat("{\"key\": \"value\"}");
        
        assertEquals(FormatDetector.SerializationFormat.JSON, format);
    }
    
    @Test
    @DisplayName("Detect JSON array format from string")
    void testDetectJsonArrayFromString() {
        FormatDetector detector = new FormatDetector();
        
        FormatDetector.SerializationFormat format = detector.detectFormat("[1, 2, 3]");
        
        assertEquals(FormatDetector.SerializationFormat.JSON, format);
    }
    
    @Test
    @DisplayName("Detect XML format from string")
    void testDetectXmlFromString() {
        FormatDetector detector = new FormatDetector();
        
        FormatDetector.SerializationFormat format = detector.detectFormat("<root><item>value</item></root>");
        
        assertEquals(FormatDetector.SerializationFormat.XML, format);
    }
    
    @Test
    @DisplayName("Detect unknown format from string")
    void testDetectUnknownFromString() {
        FormatDetector detector = new FormatDetector();
        
        FormatDetector.SerializationFormat format = detector.detectFormat("plain text");
        
        assertEquals(FormatDetector.SerializationFormat.UNKNOWN, format);
    }
    
    @Test
    @DisplayName("Detect format from JSON file")
    void testDetectFromJsonFile(@TempDir Path tempDir) throws Exception {
        Path jsonFile = tempDir.resolve("test.json");
        Files.writeString(jsonFile, "{\"key\": \"value\"}");
        
        FormatDetector detector = new FormatDetector();
        FormatDetector.SerializationFormat format = detector.detectFormat(jsonFile);
        
        assertEquals(FormatDetector.SerializationFormat.JSON, format);
    }
    
    @Test
    @DisplayName("Detect format from XML file")
    void testDetectFromXmlFile(@TempDir Path tempDir) throws Exception {
        Path xmlFile = tempDir.resolve("test.xml");
        Files.writeString(xmlFile, "<root><item>value</item></root>");
        
        FormatDetector detector = new FormatDetector();
        FormatDetector.SerializationFormat format = detector.detectFormat(xmlFile);
        
        assertEquals(FormatDetector.SerializationFormat.XML, format);
    }
    
    @Test
    @DisplayName("Detect format from file extension")
    void testDetectFromExtension(@TempDir Path tempDir) throws Exception {
        Path jsonFile = tempDir.resolve("test.json");
        Files.writeString(jsonFile, "not actually json");
        
        FormatDetector detector = new FormatDetector();
        FormatDetector.SerializationFormat format = detector.detectFormat(jsonFile);
        
        assertEquals(FormatDetector.SerializationFormat.JSON, format);
    }
    
    @Test
    @DisplayName("Detect binary format from signature")
    void testDetectBinaryFromSignature(@TempDir Path tempDir) throws Exception {
        Path binFile = tempDir.resolve("test.bin");
        byte[] data = new byte[]{0x53, 0x46, 0x42, 0x31, 0x00, 0x01}; // SFB1 signature
        Files.write(binFile, data);
        
        FormatDetector detector = new FormatDetector();
        FormatDetector.SerializationFormat format = detector.detectFormat(binFile);
        
        assertEquals(FormatDetector.SerializationFormat.BINARY, format);
    }
    
    @Test
    @DisplayName("Detect unknown format from empty string")
    void testDetectUnknownFromEmptyString() {
        FormatDetector detector = new FormatDetector();
        
        FormatDetector.SerializationFormat format = detector.detectFormat("");
        
        assertEquals(FormatDetector.SerializationFormat.UNKNOWN, format);
    }
    
    @Test
    @DisplayName("Detect unknown format from null")
    void testDetectUnknownFromNull() {
        FormatDetector detector = new FormatDetector();
        
        FormatDetector.SerializationFormat format = detector.detectFormat((String) null);
        
        assertEquals(FormatDetector.SerializationFormat.UNKNOWN, format);
    }
    
    @Test
    @DisplayName("Detect binary from non-printable characters")
    void testDetectBinaryFromNonPrintable(@TempDir Path tempDir) throws Exception {
        Path binFile = tempDir.resolve("test.dat");
        byte[] data = new byte[100];
        // Fill with non-printable characters
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i % 10);
        }
        Files.write(binFile, data);
        
        FormatDetector detector = new FormatDetector();
        FormatDetector.SerializationFormat format = detector.detectFormat(binFile);
        
        assertEquals(FormatDetector.SerializationFormat.BINARY, format);
    }
}
