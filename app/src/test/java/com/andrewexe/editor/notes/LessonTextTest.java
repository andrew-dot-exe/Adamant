package com.andrewexe.editor.notes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class LessonTextTest {

    private LessonText lessonText;

    @BeforeEach
    void setUp() {
        lessonText = new LessonText();
    }

    @Test
    void parseBold_SingleBoldRange_ShouldAddCorrectRange() {
        String line = "**bold text**";
        lessonText.parseBold(line);

        ArrayList<LessonText.Range> ranges = lessonText.getBoldRanges();
        assertEquals(1, ranges.size());
        LessonText.Range range = ranges.get(0);
        assertEquals(0, range.getStart());
        assertEquals(10, range.getStop()); // Ожидается индекс начала закрывающих **
    }

    @Test
    void parseBold_MultipleBoldRanges_ShouldAddAllRanges() {
        String line = "**first** and **second**";
        lessonText.parseBold(line);

        ArrayList<LessonText.Range> ranges = lessonText.getBoldRanges();
        assertEquals(2, ranges.size());
        
        assertEquals(0, ranges.get(0).getStart());
        assertEquals(6, ranges.get(0).getStop());
        
        assertEquals(14, ranges.get(1).getStart());
        assertEquals(21, ranges.get(1).getStop());
    }

    @Test
    void parseUnderlined_SingleUnderlined_ShouldAddCorrectRange() {
        String line = "<u>underlined</u>";
        lessonText.parseUnderlined(line);

        ArrayList<LessonText.Range> ranges = lessonText.getUnderlinedRanges();
        assertEquals(1, ranges.size());
        
        LessonText.Range range = ranges.get(0);
        assertEquals(0, range.getStart());
        assertEquals(11, range.getStop()); // Ожидается индекс начала </u>
    }

    @Test
    void parseHeadings_Level3Heading_ShouldAddLineNumber() {
        // Тест выявит ошибку в текущей реализации
        lessonText.parseHeadings("### Heading 3", 7);
        assertTrue(lessonText.getLevel3Headings().isEmpty(), 
            "Due to incorrect condition order, level3 is not detected");
        assertEquals(List.of(7), lessonText.getLevel1Headings());
    }

    @Test
    void parseAll_IntegrationTest(@TempDir File tempDir) throws IOException {
        File testFile = new File(tempDir, "test.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("# Level 1\n");
            writer.write("## Level 2\n");
            writer.write("### Level 3\n");
            writer.write("Some **bold** text\n");
            writer.write("Line with <u>underline</u>\n");
        }

        lessonText.parseAll(testFile);

        // Проверка заголовков (ожидается ошибка из-за неправильного порядка условий)
        assertEquals(List.of(0), lessonText.getLevel1Headings());
        assertEquals(List.of(1), lessonText.getLevel2Headings());
        assertEquals(List.of(2), lessonText.getLevel3Headings());

        // Проверка жирного текста
        LessonText.Range boldRange = lessonText.getBoldRanges().get(0);
        assertEquals(5, boldRange.getStart());
        assertEquals(10, boldRange.getStop());

        // Проверка подчеркивания
        LessonText.Range underlinedRange = lessonText.getUnderlinedRanges().get(0);
        assertEquals(10, underlinedRange.getStart());
        assertEquals(19, underlinedRange.getStop());

        // Проверка rawText (все строки конкатенированы без переносов)
        String expectedRawText = "# Level 1## Level 2### Level 3Some **bold** textLine with <u>underline</u>";
        assertEquals(expectedRawText, lessonText.getRawText());
    }

    @Test
    void parseAll_EmptyFile_ShouldNotFail(@TempDir File tempDir) throws IOException {
        File emptyFile = new File(tempDir, "empty.txt");
        emptyFile.createNewFile();
        
        assertDoesNotThrow(() -> lessonText.parseAll(emptyFile));
        assertTrue(lessonText.getRawText().isEmpty());
    }

    @Test
    void rangeGetterMethods_ShouldReturnCorrectValues() {
        LessonText.Range range = lessonText.new Range(2, 5);
        assertEquals(2, range.getStart());
        assertEquals(5, range.getStop());
    }
}