package com.andrewexe.ui.controls;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class UniversalTextArea extends JPanel {

    protected JButton decrButton;

    private JScrollPane scrollPane;
    private JTextPane textPane;

    public UniversalTextArea() {
        super(new BorderLayout());
        decrButton = new JButton();
        textPane = new JTextPane();
        packTotextPane();
        addListeners();
    }

    public UniversalTextArea(String text) {
        super(new BorderLayout());
        decrButton = new JButton();
        textPane = new JTextPane();
        //set text
        try{
            textPane.getDocument().insertString(0, text, null);
        }
        catch(Exception exc){
            //todo: messagebox
        }
        packTotextPane();
        addListeners();
    }

    private void packTotextPane() {
        scrollPane = new JScrollPane(textPane);
        SwingUtilities.updateComponentTreeUI(this); // exception fix
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    private void addListeners() {
        textPane.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (ControlsAdapter.getPositionLabel() != null) {
                    try {
                        int caretPosition = textPane.getCaretPosition();
                        int line = textPaneHandlers.getLineNumber(textPane, caretPosition) + 1;
                        int column = textPaneHandlers.getColumnNumber(textPane, caretPosition) + 1;
                        ControlsAdapter.getPositionLabel().setText(String.format("line: %d, col: %d", line, column));
                    } catch (Exception exc) {
                        System.err.println("Error: " + exc.getMessage());
                    }
                } else {
                    System.out.println("Position label not found");
                }
            }
        });
        textPane.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                //meta-c
                if(e.isMetaDown() && e.getKeyCode() == KeyEvent.VK_C){
                    String selected = textPane.getSelectedText();
                    StringSelection selection = new StringSelection(selected);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
                }
                //meta-v
                else if(e.isMetaDown() && e.getKeyCode() == KeyEvent.VK_V){
                    String clipboard;
                    try {
                        clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                        textPane.setText(textPane.getText() + clipboard);
                    } catch (HeadlessException | UnsupportedFlavorException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                }
                // meta-a
                //meta-z
                //meta-x

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
    }

    private static class textPaneHandlers {
        static int getLineNumber(JTextPane textPane, int position) throws BadLocationException {
            return 0;//textPane.getLineOfOffset(position);
        }

        static int getColumnNumber(JTextPane textPane, int position) throws BadLocationException {
            //int lineStart = textPane.getLineStartOffset(textPane.getLineOfOffset(position));
            return 0; //position - lineStart;
        }
    }
}