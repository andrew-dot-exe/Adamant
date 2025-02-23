package com.andrewexe.ui;

import javax.swing.*;

import com.andrewexe.ui.controls.ControlsAdapter;

import java.awt.BorderLayout;
import java.awt.Font;


public class MainWindow implements IGUI {

    private final String moduleName = "GUI";


    public Font getFont() {
        return ControlsAdapter.getTextArea().getFont();
    }

    public void setFont(Font font) {
        ControlsAdapter.getTextArea().setFont(font);
    }

    public boolean getWordWrap() {
        return false; //ControlsAdapter.getTextArea().getLineWrap();
    }

    public void setWordWrap(boolean value) {
       //ControlsAdapter.getTextArea().setLineWrap(value);
    }

    public void useMacOSMenuBar() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
    }

    public void run() {
        ControlsAdapter.getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // todo: on mac change to close
                                                                                       // window
        ControlsAdapter.getMainFrame().setSize(600, 400);
        ControlsAdapter.getMainFrame().setJMenuBar(ControlsAdapter.getMenuBar());

        ControlsAdapter.getMainFrame().add(
                ControlsAdapter.getTabbedPane());

        ControlsAdapter.getMainFrame().add(ControlsAdapter.getBottomPanel(), BorderLayout.SOUTH);

        ControlsAdapter.getMainFrame().setVisible(true);
        // try {
        //     UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        //     SwingUtilities.updateComponentTreeUI(ControlsAdapter.getMainFrame());
            
        // } catch (Exception e) {
        //     System.out.println("no LAF");
        // }
    }

}