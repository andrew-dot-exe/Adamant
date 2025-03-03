package com.andrewexe.editor.notes;

import java.io.File;

public interface LessonNote {
    
    public void openNote(File file);

    public void saveNote();

    public String getText();

    public String getInfo();

    public void updateChangeTime();

}