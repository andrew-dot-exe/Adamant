package com.andrewexe.editor.notes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class LessonText {

    public enum FORMATS {
        BOLD
    }

    private String rawText;

    private List<Range> boldRanges;

    public LessonText(String rawText)
    {
        this.rawText = rawText;
    }

    private void parseText(String line)
    {
        int index = 0;
        while (index != -1) {
            // Находим начало **
            int startMarker = line.indexOf("**", index);
            if (startMarker == -1) break;

            // Находим конец **
            int endMarker = line.indexOf("**", startMarker + 2);
            if (endMarker == -1) break;

            // Извлекаем слово между **
            String word = line.substring(startMarker + 2, endMarker);
            index = endMarker + 2;
        }
    }

    public void setBoldRange(List<Range> ranges) {
        this.boldRanges = ranges;
    }

    public void addRange(int start, int stop, FORMATS formats) {
        Range range = new Range(start, stop);
        switch (formats) {
            case BOLD:
                this.boldRanges.add(range);
                break;

            default:
                return;
        }
    }

    private void addBoldRange(Range range){
        this.boldRanges.add(range);
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
