package com.andrewexe.editor.io;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class OpenFileTest {
    
    @TempDir
    Path tempDir;

    @Test
    void testReadLines() throws Exception {
        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, List.of("first", "second", "third"));
        
        List<String> result = OpenFile.readLines(testFile.toFile());
        assertIterableEquals(List.of("first", "second", "third"), result);
    }

    @Test
    void testReadAsText() throws Exception {
        Path testFile = tempDir.resolve("multi.txt");
        Files.write(testFile, List.of("line1", "line2"));
        
        String result = OpenFile.readAsText(testFile.toString());
        assertEquals("line1" + System.lineSeparator() + "line2", result);
    }

    @Test
    void testEmptyFile() throws Exception {
        Path testFile = tempDir.resolve("empty.txt");
        Files.createFile(testFile);
        
        assertTrue(OpenFile.readLines(testFile.toFile()).isEmpty());
        assertTrue(OpenFile.readAsText(testFile.toFile()).isEmpty());
    }
}