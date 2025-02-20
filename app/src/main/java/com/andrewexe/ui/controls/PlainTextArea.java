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
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class PlainTextArea extends JPanel {

    protected JButton decrButton;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    public PlainTextArea() {
        super(new BorderLayout());
        decrButton = new JButton();
        textArea = new JTextArea();
        packToTextArea();
        addListeners();
    }

    public PlainTextArea(String text) {
        super(new BorderLayout());
        decrButton = new JButton();
        textArea = new JTextArea(text);
        packToTextArea();
        addListeners();
    }

    private void packToTextArea() {
        scrollPane = new JScrollPane(textArea);
        SwingUtilities.updateComponentTreeUI(this); // exception fix
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    private void addListeners() {
        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (ControlsAdapter.getPositionLabel() != null) {
                    try {
                        int caretPosition = textArea.getCaretPosition();
                        int line = TextAreaHandlers.getLineNumber(textArea, caretPosition) + 1;
                        int column = TextAreaHandlers.getColumnNumber(textArea, caretPosition) + 1;
                        ControlsAdapter.getPositionLabel().setText(String.format("line: %d, col: %d", line, column));
                    } catch (Exception exc) {
                        System.err.println("Error: " + exc.getMessage());
                    }
                } else {
                    System.out.println("Position label not found");
                }
            }
        });
        textArea.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                //meta-c
                if(e.isMetaDown() && e.getKeyCode() == KeyEvent.VK_C){
                    String selected = textArea.getSelectedText();
                    StringSelection selection = new StringSelection(selected);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
                }
                //meta-v
                else if(e.isMetaDown() && e.getKeyCode() == KeyEvent.VK_V){
                    String clipboard;
                    try {
                        clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                        textArea.setText(textArea.getText() + clipboard);
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

    private static class TextAreaHandlers {
        static int getLineNumber(JTextArea textArea, int position) throws BadLocationException {
            return textArea.getLineOfOffset(position);
        }

        static int getColumnNumber(JTextArea textArea, int position) throws BadLocationException {
            int lineStart = textArea.getLineStartOffset(textArea.getLineOfOffset(position));
            return position - lineStart;
        }
    }
}