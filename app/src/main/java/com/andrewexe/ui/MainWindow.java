package com.andrewexe.ui;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.andrewexe.io.LoadSaveFile;
import com.andrewexe.editor.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;


public class MainWindow implements IGUI {

    private final String moduleName = "GUI";

    private JFrame mainFrame;

    private JFrame getJFrame() {
        if (mainFrame == null) {
            mainFrame = new JFrame("adamant");
        }
        return mainFrame;
    }

    private JMenuBar mJMenuBar;

    private JScrollPane areaScrollPane;
    private JTextArea textArea;
    private JPanel statusPanel;
    private JLabel positionLabel;

    private JLabel getPosLabel() {
        if (positionLabel == null) {
            positionLabel = new JLabel("line: 1, col: 0");
        }
        return positionLabel;
    }

    private void packMenuBar(JMenuBar menuBar) {
        JMenu menu = new JMenu("file");

        JMenuItem openItem = new JMenuItem("open");
        openItem.addActionListener(new OpenButtonEventHandler());

        JMenuItem saveItem = new JMenuItem("save");
        saveItem.addActionListener(new SaveButtonEventHandler());

        JMenuItem closeProgram = new JMenuItem("close");
        closeProgram.addActionListener(new ExitMenuItemHandler());

        menu.add(openItem);
        menu.add(saveItem);
        menu.add(closeProgram);

        menuBar.add(menu);
    }

    private JMenuBar getMenuBar() {
        // sets menubar with
        if (mJMenuBar == null) {
            mJMenuBar = new JMenuBar();
            packMenuBar(mJMenuBar);
        }
        return mJMenuBar;
    }

    private class TextAreaHanlders {
        private static int getLineNumber(JTextArea textArea, int position) throws BadLocationException {
            return textArea.getLineOfOffset(position);
        }

        // Получение позиции в строке (начиная с 0)
        private static int getColumnNumber(JTextArea textArea, int position) throws BadLocationException {
            int lineStart = textArea.getLineStartOffset(
                    textArea.getLineOfOffset(position));
            return position - lineStart;
        }
    }

    public JTextArea getTextArea() {
        if (textArea == null) {
            textArea = new JTextArea();
            textArea.addCaretListener(new CaretListener() {

                @Override
                public void caretUpdate(CaretEvent e) {
                    if (getPosLabel() != null) {
                        try {
                            int caretPosition = getTextArea().getCaretPosition();
                            int line = TextAreaHanlders.getLineNumber(getTextArea(), caretPosition) + 1;
                            int column = TextAreaHanlders.getColumnNumber(getTextArea(), caretPosition) + 1;            
                            getPosLabel().setText(String.format("line: %d, col: %d", line, column));
                        } catch (Exception exc) {
                            Logger.printErr(moduleName, exc.getMessage());
                            return;
                        }
                    }
                }

            });
        }
        return textArea;
    }

    private JScrollPane getJScrollPane() {
        if (areaScrollPane == null) {
            areaScrollPane = new JScrollPane(getTextArea());
        }
        return areaScrollPane;
    }

    private void packPanel(JPanel panel) {
        panel.add(getPosLabel());
    }

    private JPanel getStatusPanel() {
        if (statusPanel == null) {
            statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            packPanel(statusPanel);
            ;
        }
        return statusPanel;
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

        getJFrame().add(getStatusPanel(), BorderLayout.SOUTH);
        getJFrame().add(getJScrollPane());

        getJFrame().setVisible(true);
    }

}