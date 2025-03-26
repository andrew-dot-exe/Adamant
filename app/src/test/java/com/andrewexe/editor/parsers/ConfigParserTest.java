package com.andrewexe.editor.parsers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ConfigParserTest {

    private ArrayList<String> testLines;

    @BeforeEach
    void setUp() {
        testLines = new ArrayList<>();
        testLines.add("key1 = value1");
        testLines.add("key2 = value2");
        testLines.add("key3 = value with spaces");
    }

    @Test
    @DisplayName("Should parse valid config lines correctly")
    void getConfigValuesFromArrayList_ValidInput() {
        HashMap<String, String> result = ConfigParser.getConfigValues(testLines);
        
        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(3, result.size()),
            () -> assertEquals("value1", result.get("key1")),
            () -> assertEquals("value2", result.get("key2")),
            () -> assertEquals("value with spaces", result.get("key3"))
        );
    }

    @Test
    @DisplayName("Should handle empty input gracefully")
    void getConfigValuesFromArrayList_EmptyInput() {
        HashMap<String, String> result = ConfigParser.getConfigValues(new ArrayList<>());
        
        assertAll(
            () -> assertNotNull(result),
            () -> assertTrue(result.isEmpty())
        );
    }

    @Test
    @DisplayName("Should skip invalid lines and parse valid ones")
    void getConfigValuesFromArrayList_WithInvalidLines() {
        testLines.add("invalid line without equals");
        testLines.add("another invalid line");
        
        HashMap<String, String> result = ConfigParser.getConfigValues(testLines);
        
        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(3, result.size()),
            () -> assertEquals("value1", result.get("key1"))
        );
    }

    @Test
    @DisplayName("Should handle malformed lines correctly")
    void getConfigValuesFromArrayList_MalformedLines() {
        testLines.clear();
        testLines.add("key1 = value1");
        testLines.add("= value without key");
        testLines.add("key without value =");
        
        HashMap<String, String> result = ConfigParser.getConfigValues(testLines);
        
        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(1, result.size()),
            () -> assertEquals("value1", result.get("key1"))
        );
    }

    @Test
    @DisplayName("Should parse valid config file correctly")
    void getConfigValuesFromFile_ValidFile(@TempDir Path tempDir) throws IOException {
        // Create temporary config file
        Path configFile = tempDir.resolve("test_config.txt");
        Files.write(configFile, testLines);
        
        HashMap<String, String> result = ConfigParser.getConfigValues(configFile.toFile());
        
        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(3, result.size()),
            () -> assertEquals("value1", result.get("key1"))
        );
    }

    // @Test
    // @DisplayName("Should return null for non-existent file")
    // void getConfigValuesFromFile_NonExistentFile() {
    //     File nonExistentFile = new File("nonexistent_file.txt");
    //     assumeTrue(!nonExistentFile.exists(), "Test file unexpectedly exists");
        
    //     HashMap<String, String> result = ConfigParser.getConfigValues(nonExistentFile);
        
    //     assertNull(result);
    // }

    @Test
    @DisplayName("Should handle empty file correctly")
    void getConfigValuesFromFile_EmptyFile(@TempDir Path tempDir) throws IOException {
        Path emptyFile = tempDir.resolve("empty_config.txt");
        Files.createFile(emptyFile);
        
        HashMap<String, String> result = ConfigParser.getConfigValues(emptyFile.toFile());
        
        assertAll(
            () -> assertNotNull(result),
            () -> assertTrue(result.isEmpty())
        );
    }

    @Test
    @DisplayName("Should handle lines with extra whitespace")
    void getConfigValuesFromArrayList_WithWhitespace() {
        testLines.clear();
        testLines.add("  key1  =  value1  ");
        testLines.add("key2 = value2");
        
        HashMap<String, String> result = ConfigParser.getConfigValues(testLines);
        
        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(2, result.size()),
            () -> assertEquals("value1", result.get("key1")),
            () -> assertEquals("value2", result.get("key2"))
        );
    }
}