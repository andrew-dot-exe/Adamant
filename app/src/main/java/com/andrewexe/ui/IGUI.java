package com.andrewexe.ui;

import java.awt.Font;

public interface IGUI {
    public Font getFont();
    public void setFont(Font font);
    public boolean getWordWrap();
    public void setWordWrap(boolean value);

    //themings, etc.
    public void useMacOSMenuBar();
}
