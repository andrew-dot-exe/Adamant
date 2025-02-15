package com.andrewexe.ui;

import javax.swing.JFrame;

public class SettingsWindow {

    private JFrame mainFrame;

    private JFrame getJFrame() {
        if (mainFrame == null) {
            mainFrame = new JFrame("Settings");
        }
        return mainFrame;
    }

    public void run() {
        getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // todo: on mac change to close window
        getJFrame().setSize(600, 400);
        getJFrame().setVisible(true);
    }
}
