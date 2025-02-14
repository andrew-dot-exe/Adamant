package com.andrewexe;

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

    public void run()
    {
        getMainWindow().run();
    }
}
