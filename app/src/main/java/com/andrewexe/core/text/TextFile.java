package com.andrewexe.core.text;

public abstract class TextFile {

    protected String filename;
    protected String text;

    public TextFile(String filename){
        this.filename = filename;
    }

    public abstract void open();

    public String getText(){
        return text;    
    }
}
