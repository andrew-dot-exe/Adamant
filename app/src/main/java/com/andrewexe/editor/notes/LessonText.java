package com.andrewexe.editor.notes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LessonText {

    private ArrayList<Range> boldRanges;
    private ArrayList<Range> underlinedRanges;
    private ArrayList<Integer> level1Headings;
    private ArrayList<Integer> level2Headings;
    private ArrayList<Integer> level3Headings;

    public ArrayList<Range> getBoldRanges() {
        return boldRanges;
    }

    public ArrayList<Range> getUnderlinedRanges() {
        return underlinedRanges;
    }

    public ArrayList<Integer> getLevel1Headings() {
        return level1Headings;
    }

    public ArrayList<Integer> getLevel2Headings() {
        return level2Headings;
    }

    public ArrayList<Integer> getLevel3Headings() {
        return level3Headings;
    }

    public String getRawText() {
        return rawText;
    }

    String rawText = "";

    public LessonText() {
        this.level1Headings = new ArrayList<Integer>();
        this.level2Headings = new ArrayList<Integer>();
        this.level3Headings = new ArrayList<Integer>();

        this.boldRanges = new ArrayList<Range>();
        this.underlinedRanges = new ArrayList<Range>();
    }

    public void parseAll(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            rawText = "";
            int lineNumber = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                parseHeadings(line, lineNumber);
                parseBold(line);
                parseUnderlined(line);
                rawText += line + "\n"; // Добавляем перенос строки
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseBold(String line) {
        int index = 0;
        while (index != -1) {
            int startMarker = line.indexOf("**", index);
            if (startMarker == -1) break;
    
            int endMarker = line.indexOf("**", startMarker + 2);
            if (endMarker == -1) break;
    
            // Оставляем диапазон ВКЛЮЧАЯ маркеры **
            boldRanges.add(new Range(startMarker, endMarker + 2)); // +2 чтобы захватить закрывающие **
            index = endMarker + 2;
        }
    }


    public void parseUnderlined(String line) {
        int index = 0;
        while (index != -1) {
            int startMarker = line.indexOf("<u>", index);
            if (startMarker == -1) break;
    
            int endMarker = line.indexOf("</u>", startMarker + 3);
            if (endMarker == -1) break;
    
            // Диапазон ВКЛЮЧАЕТ теги <u> и </u>
            underlinedRanges.add(new Range(startMarker, endMarker + 4)); // +4 для закрывающего </u>
            index = endMarker + 4;
        }
    }

    public void parseHeadings(String line, int lineNumber) {
        if (line.startsWith("###")) {
            level3Headings.add(lineNumber);
        } else if (line.startsWith("##")) {
            level2Headings.add(lineNumber);
        } else if (line.startsWith("#")) {
            level1Headings.add(lineNumber);
        }
    }

    public class Range {
        private int start;
        private int stop;

        public int getStart() {
            return start;
        }

        public int getStop() {
            return stop;
        }

        public Range(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

    }

}
