package com.andrewexe.core.text;

public class Range {
    private int start;
    private int stop;
    
    public Range(int start, int stop){
        this.start = start;
        this.stop = stop;
    }

    public int getStartPos(){
        return start;
    }

    public int getStopPos(){
        return stop;
    }

    public void setStartPos(int start){
        this.start = start;
    }

    public void setEndPos(int stop){
        this.stop = stop;
    }
}
