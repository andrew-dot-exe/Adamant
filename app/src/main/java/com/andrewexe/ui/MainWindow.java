package com.andrewexe.ui;

import javax.swing.*;

import com.andrewexe.io.LoadSaveFile;

import java.awt.Font;
import java.awt.event.*;
import java.io.File;

public class MainWindow implements IGUI {

    private JFrame mainFrame;

    private JFrame getJFrame() {
        if (mainFrame == null) {
            mainFrame = new JFrame("Adamant");
        }
        return mainFrame;
    }

    private JMenuBar mJMenuBar;

    private JScrollPane areaScrollPane;
    private JTextArea textArea;

    private JMenuBar getMenuBar() {
        // sets menubar with
        if (mJMenuBar == null) {
            mJMenuBar = new JMenuBar();
            // fill
            JMenu menu = new JMenu("File");

            JMenuItem openItem = new JMenuItem("Open");
            openItem.addActionListener(new OpenButtonEventHandler());

            JMenuItem saveItem = new JMenuItem("Save");
            saveItem.addActionListener(new SaveButtonEventHandler());

            JMenuItem closeProgram = new JMenuItem("Close");
            closeProgram.addActionListener(new ExitMenuItemHandler());

            menu.add(openItem);
            menu.add(saveItem);
            menu.add(closeProgram);

            mJMenuBar.add(menu);
        }
        return mJMenuBar;
    }

    public JTextArea getTextArea() {
        if (textArea == null) {
            textArea = new JTextArea();
        }
        return textArea;
    }

    private JScrollPane getJScrollPane() {
        if (areaScrollPane == null) {
            areaScrollPane = new JScrollPane(getTextArea());
        }
        return areaScrollPane;
    }

    class OpenButtonEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
            // get filename by user
            if (jfc.showOpenDialog(getJFrame()) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                String contains = LoadSaveFile.openFile(file);
                showText(contains);
            }

        }
    }

    class SaveButtonEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();

            if (jfc.showSaveDialog(getJFrame()) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                LoadSaveFile.saveFile(file, getAreaText());
            }
        }
    }

    class ExitMenuItemHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getJFrame().dispose();
        }
    }

    private void showText(String text) {
        // places text to UI
        getTextArea().setText(text);
    }

    private String getAreaText() {
        return getTextArea().getText();
    }

    public Font getFont() {
        return getTextArea().getFont();
    }

    public void setFont(Font font) {
        getTextArea().setFont(font);
    }

    public boolean getWordWrap() {
        return getTextArea().getLineWrap();
    }

    public void setWordWrap(boolean value) {
        textArea.setLineWrap(value);
    }

    public void run() {
        // runs the UI

        getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // todo: on mac change to close window
        getJFrame().setSize(600, 400);
        getJFrame().setJMenuBar(getMenuBar());

        getJFrame().add(getJScrollPane());
        getJFrame().setVisible(true);
    }

}