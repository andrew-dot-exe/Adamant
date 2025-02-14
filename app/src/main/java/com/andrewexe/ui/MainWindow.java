package com.andrewexe.ui;

import javax.swing.*;

public class MainWindow {

    private JFrame mainFrame;
    private JMenuBar mJMenuBar;

    private JFrame getJFrame() {
        if (mainFrame == null) {
            mainFrame = new JFrame("Adamant");
        }
        return mainFrame;
    }

    private JMenuBar getMenuBar(){
        // sets menubar with 
        if(mJMenuBar == null){
            mJMenuBar = new JMenuBar();
            // fill 
            JMenu menu = new JMenu("File");
            mJMenuBar.add(menu);
        }
        return mJMenuBar;
    }

    public void run() {
        // runs the UI

        getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // todo: on mac change to close window
        getJFrame().setSize(600, 400);
        getJFrame().setVisible(true);
        getJFrame().setJMenuBar(getMenuBar());
    }
}
