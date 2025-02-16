package com.andrewexe.ui.controls;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class PlainTextArea extends JTextArea {
    public PlainTextArea(){
        super();
        addCaretListener();
    }

    public PlainTextArea(String text){
        super(text);
        addCaretListener();
    }

    private void addCaretListener(){
        this.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                if (ControlsAdapter.getPositionLabel() != null) {
                    try {
                        int caretPosition = ControlsAdapter.getTextArea().getCaretPosition();
                        int line = TextAreaHanlders.getLineNumber(ControlsAdapter.getTextArea(), caretPosition) + 1;
                        int column = TextAreaHanlders.getColumnNumber(ControlsAdapter.getTextArea(), caretPosition) + 1;            
                        ControlsAdapter.getPositionLabel().setText(String.format("line: %d, col: %d", line, column));
                    } catch (Exception exc) {
                        //Logger.printErr(moduleName, exc.getMessage());
                        return;
                    }
                }
            }
        });
    }

    class TextAreaHanlders {
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

}
