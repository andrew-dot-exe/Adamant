package com.andrewexe.ui;

import javax.swing.*;

import com.andrewexe.io.LoadSaveFile;
import com.andrewexe.ui.controls.ControlsAdapter;
import com.andrewexe.editor.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.util.ResourceBundle.Control;

public class MainWindow implements IGUI {

    private final String moduleName = "GUI";

    private void showText(String text) {
        // places text to UI
        ControlsAdapter.getTextArea().setText(text);
    }

    private String getAreaText() {
        return ControlsAdapter.getTextArea().getText();
    }

    public Font getFont() {
        return ControlsAdapter.getTextArea().getFont();
    }

    public void setFont(Font font) {
        ControlsAdapter.getTextArea().setFont(font);
    }

    public boolean getWordWrap() {
        return ControlsAdapter.getTextArea().getLineWrap();
    }

    public void setWordWrap(boolean value) {
        ControlsAdapter.getTextArea().setLineWrap(value);
    }

    public void useMacOSMenuBar() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
    }

    public void run() {
        // runs the UI

        ControlsAdapter.getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // todo: on mac change to close
                                                                                       // window
        ControlsAdapter.getMainFrame().setSize(600, 400);
        ControlsAdapter.getMainFrame().setJMenuBar(ControlsAdapter.getMenuBar());

        ControlsAdapter.getScrollPane().add(ControlsAdapter.getTextArea());

        ControlsAdapter.getMainFrame().add(
                ControlsAdapter.getTabbedPane());

        ControlsAdapter.getMainFrame().add(ControlsAdapter.getBottomPanel(), BorderLayout.SOUTH);

        ControlsAdapter.getMainFrame().setVisible(true);
    }

}