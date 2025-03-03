package com.andrewexe.editor;

import java.awt.Font;

public interface IGUI {
    public Font getFont();
    public void setFont(Font font);
    public boolean getWordWrap();
    public void setWordWrap(boolean value);

    public void addBoldText(String text);
    public void addUnderlinedText(String text);

    public void getText(String text);

    //themings, etc.
    public void useMacOSMenuBar();
}
