package com.andrewexe.editor.notes;

import org.junit.jupiter.api.Test;
import org.checkerframework.checker.fenum.qual.SwingTextOrientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import com.andrewexe.editor.exceptions.ParsingFailedException;
import com.andrewexe.editor.notes.Range;

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
    void testParseLine_Heading1()
    {
        String testLine = "# Test string with heading 1";
        lessonText.parseLine(testLine, 1);
        assertEquals(1, lessonText.getLevel1Headings().get(0));
    }

    @Test
    void testParseLine_Heading2(){
        String testLine = "## Test string with heading 2";
        lessonText.parseLine(testLine, 1);
        assertEquals(1, lessonText.getLevel2Headings().get(0));
    }

    @Test
    void testParseLine_BoldText(){
        String testLine = "**some bold text**";
        int leftRange = 2;
        int rightRange = 15;
        Range range = new Range(leftRange, rightRange);
        lessonText.parse(testLine);
        assertEquals(range.getStart(), lessonText.getBoldHeadings().get(0).getStart());
        assertEquals(range.getStop(), lessonText.getBoldHeadings().get(0).getStop());
    }


    @Test
    void testParseString()
    {
        String raw = "# text with heading 1\n## text with heading 2]\nthis text shoudln't be a **problem**";
        Range range = new Range(27, 33);
        lessonText.parse(raw);
        // level 1 heading at line 1
        assertEquals(1, lessonText.getLevel1Headings().get(0));
        // level 2 heading at line 2
        assertEquals(2, lessonText.getLevel2Headings().get(0));
        // bold text at line 3
        // the bold text is on position (?,?)
        assertEquals(range.getStart(), lessonText.getBoldHeadings().get(0).getStart());
        assertEquals(range.getStop(), lessonText.getBoldHeadings().get(0).getStop());

    }

    @Test
    void testParseFile(){
        File file = new File("/home/andrew/Adamant/docs/MDPARSER.md");
        try {
            lessonText.parse(file);
        } catch (ParsingFailedException exc) {
            // assertThrows(ParsingFailedException.class, exc.getClass());
            Assertions.fail("parsing failed");
        }
        assertEquals(1, lessonText.getLevel1Headings().get(0));
    }
}