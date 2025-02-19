package com.andrewexe.ui.controls;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import java.awt.BorderLayout;

public class PlainTextArea extends JPanel {

    protected JButton decrButton;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    public PlainTextArea() {
        super(new BorderLayout());
        decrButton = new JButton();
        textArea = new JTextArea();
        packToTextArea();
        addCaretListener();
    }

    public PlainTextArea(String text) {
        super(new BorderLayout());
        decrButton = new JButton();
        textArea = new JTextArea(text);
        packToTextArea();
        addCaretListener();
    }

    private void packToTextArea() {
        scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    private void addCaretListener() {
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