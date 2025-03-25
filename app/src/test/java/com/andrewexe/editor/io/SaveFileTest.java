package com.andrewexe.editor.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SaveFileTest {

    @Test
    void saveText_writesAllLinesWithoutSeparators(@TempDir Path tempDir) throws Exception {
        File file = tempDir.resolve("output.txt").toFile();
        List<String> lines = Arrays.asList("Hello", "World", "!");

        SaveFile.saveText(lines, file);

        String content = Files.readString(file.toPath());
        assertEquals("Hello\nWorld\n!\n", content);
    }

    @Test
    void saveText_emptyListCreatesEmptyFile(@TempDir Path tempDir) throws Exception {
        File file = tempDir.resolve("empty.txt").toFile();

        SaveFile.saveText(Collections.emptyList(), file);

        assertTrue(file.exists());
        assertEquals(0, file.length());
    }


    @Test
    void saveText_writesUnicodeCharacters(@TempDir Path tempDir) throws Exception {
        File file = tempDir.resolve("unicode.txt").toFile();
        List<String> lines = Arrays.asList("Привет", "🌍");

        SaveFile.saveText(lines, file);

        String content = Files.readString(file.toPath());
        assertEquals("Привет\n🌍\n", content);
    }
}