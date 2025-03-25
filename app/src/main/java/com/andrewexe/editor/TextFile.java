package com.andrewexe.editor;

import java.io.IOError;

public abstract class TextFile {

    protected String filename;
    protected String text;

    public TextFile(String filename){
        this.filename = filename;
    }

    public void open() throws IOError
    {
        throw new IOError(new Error("File error"));
    }

    public String getText(){
        return text;    
    }
}
