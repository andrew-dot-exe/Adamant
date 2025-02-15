package com.andrewexe;

import java.awt.*;
import com.andrewexe.editor.EditorSettings;
import com.andrewexe.editor.Logger;
import com.andrewexe.ui.MainWindow;

public class TextEditor {

    private MainWindow mw;

    private MainWindow getMainWindow()
    {
        if(mw == null)
        {
            mw = new MainWindow();
        }
        return mw;
    }

    private void loadSettings()
    {
        //try default paths for settings, if not, create new.
        EditorSettings settings = new EditorSettings();
        Font configFont = settings.getFontFromSettings();
        getMainWindow().setFont(configFont);
    }

    public void run()
    {
        //load settings
        loadSettings();
        Logger.printMessage("Editor", "we start gui");
        getMainWindow().run();
    }

    
}
