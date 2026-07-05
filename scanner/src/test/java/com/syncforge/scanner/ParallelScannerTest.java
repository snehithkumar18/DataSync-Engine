package com.syncforge.scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Unit tests for ParallelScanner.
 */
@DisplayName("ParallelScanner Tests")
class ParallelScannerTest {
    
    @Test
    @DisplayName("Scan empty directory")
    void testScanEmptyDirectory(@TempDir Path tempDir) throws Exception {
        ParallelScanner scanner = new ParallelScanner(2);
        ScannerFilters filters = new ScannerFilters(List.of(), List.of(), true);
        
        ScanResult result = scanner.scan(tempDir, filters);
        
        assertEquals(0, result.getFileCount());
        assertEquals(0, result.getDirectoryCount());
    }
    
    @Test
    @DisplayName("Scan directory with files")
    void testScanDirectoryWithFiles(@TempDir Path tempDir) throws Exception {
        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(tempDir.resolve("file2.txt"));
        
        ParallelScanner scanner = new ParallelScanner(2);
        ScannerFilters filters = new ScannerFilters(List.of(), List.of(), true);
        
        ScanResult result = scanner.scan(tempDir, filters);
        
        assertEquals(2, result.getFileCount());
        assertEquals(0, result.getDirectoryCount());
    }
    
    @Test
    @DisplayName("Scan directory with subdirectories")
    void testScanDirectoryWithSubdirectories(@TempDir Path tempDir) throws Exception {
        Files.createDirectories(tempDir.resolve("subdir1"));
        Files.createDirectories(tempDir.resolve("subdir2"));
        Files.createFile(tempDir.resolve("subdir1/file.txt"));
        
        ParallelScanner scanner = new ParallelScanner(2);
        ScannerFilters filters = new ScannerFilters(List.of(), List.of(), true);
        
        ScanResult result = scanner.scan(tempDir, filters);
        
        assertEquals(1, result.getFileCount());
        assertTrue(result.getDirectoryCount() >= 2);
    }
    
    @Test
    @DisplayName("Scan with include filter")
    void testScanWithIncludeFilter(@TempDir Path tempDir) throws Exception {
        Files.createFile(tempDir.resolve("file.txt"));
        Files.createFile(tempDir.resolve("file.md"));
        
        ParallelScanner scanner = new ParallelScanner(2);
        ScannerFilters filters = new ScannerFilters(List.of("*.txt"), List.of(), true);
        
        ScanResult result = scanner.scan(tempDir, filters);
        
        assertEquals(1, result.getFileCount());
    }
    
    @Test
    @DisplayName("Scan with exclude filter")
    void testScanWithExcludeFilter(@TempDir Path tempDir) throws Exception {
        Files.createFile(tempDir.resolve("file.txt"));
        Files.createFile(tempDir.resolve("file.tmp"));
        
        ParallelScanner scanner = new ParallelScanner(2);
        ScannerFilters filters = new ScannerFilters(List.of(), List.of("*.tmp"), true);
        
        ScanResult result = scanner.scan(tempDir, filters);
        
        assertEquals(1, result.getFileCount());
    }
    
    @Test
    @DisplayName("Scanner statistics are recorded")
    void testScannerStatistics(@TempDir Path tempDir) throws Exception {
        Files.createFile(tempDir.resolve("file1.txt"));
        Files.createFile(tempDir.resolve("file2.txt"));
        
        ParallelScanner scanner = new ParallelScanner(2);
        ScannerFilters filters = new ScannerFilters(List.of(), List.of(), true);
        
        ScanResult result = scanner.scan(tempDir, filters);
        
        assertTrue(result.getScanDurationMillis() >= 0);
        assertTrue(result.getTotalFileSize() >= 0);
    }
}
