package com.andrewexe.editor.notes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.andrewexe.editor.exceptions.ParsingFailedException;

class NotSingleLineException extends Exception {
    public NotSingleLineException(String message) {
        super(message);
    }
}

public class LessonText {

    private ArrayList<Integer> level1Headings;
    private ArrayList<Integer> level2Headings;
    private ArrayList<Integer> level3Headings;

    private ArrayList<Range> boldHeadings;
    //private ArrayList<Range> underlinedHeadings;

    public ArrayList<Range> getBoldHeadings() {
        return boldHeadings;
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

    public LessonText() {
        level1Headings = new ArrayList<Integer>();
        level2Headings = new ArrayList<Integer>();
        level3Headings = new ArrayList<Integer>();
        boldHeadings = new ArrayList<Range>();
    }

    // will be fully rewrited
    public void parse(File textFile) throws ParsingFailedException {
        int lineNumber = 1;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {

            while ((line = bufferedReader.readLine()) != null) {
                parseLine(line, lineNumber);
                lineNumber += 1;
            }
        } catch (Exception e) {
            throw new ParsingFailedException(lineNumber);
        }
    }

    public void parse(String raw) {
        int lineNumber = 1;
        for (String line : raw.split("\n")) {
            parseLine(line, lineNumber);
            lineNumber += 1;
        }
    }

    public void parseLine(String line, int number) {
        processHeaders(line, number);
        processFormat(line, number);
    }

    private void processHeaders(String line, int number) {
        String firstLexem = line.split(" ")[0];
        if (firstLexem.equals("#")) {
            level1Headings.add(number);
        } else if (firstLexem.equals("##")) {
            level2Headings.add(number);
        }
    }

    public void processFormat(String line, int number) {
        int start = -1;
        int end = -1;
        for (int pos = 0; pos < line.length() - 1; pos++) {
            if (line.charAt(pos) == '*' ) {
                if(line.charAt(pos + 1) == '*' ){
                    if(start == -1)
                        start = pos + 2;
                    else
                        end = pos - 1;
                }
            }
            if (start != -1 && end != -1) {
                boldHeadings.add(new Range(start, end));
                start = -1;
                end = -1;
            }
        }

    }


}