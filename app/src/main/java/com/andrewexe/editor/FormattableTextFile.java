package com.andrewexe.editor;

/*
 * Представляет абстракцию над форматированным текстовым документом
 */
public abstract class FormattableTextFile extends TextFile{
    public FormattableTextFile(String filename){
        super(filename);
    }

    public abstract void setBoldRange(int start, int stop);
    public abstract void setUnderlinedRange(int start, int stop);
    public abstract void setHeading1(int line);
    public abstract void setHeading2(int line);
    public abstract void setHeading3(int line);
}
