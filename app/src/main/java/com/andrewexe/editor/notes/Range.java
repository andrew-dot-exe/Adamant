package com.andrewexe.editor.notes;

public class Range {
    private final int start;
    private final int stop;

    public Range(int start, int stop) {
        this.start = start;
        this.stop = stop;
    }

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }
}