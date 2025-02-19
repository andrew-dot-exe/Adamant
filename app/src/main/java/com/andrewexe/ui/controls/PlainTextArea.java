package com.andrewexe.ui.controls;

import java.awt.TextArea;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class PlainTextArea extends JScrollPane {

    private JTextArea _TextArea;

    private void packToTextArea(String text)
    {
        _TextArea = new JTextArea(text);
    }

    private void packToTextArea()
    {
        _TextArea = new JTextArea();
    }

    public JTextArea getTextArea()
    {
        return this._TextArea;
    }

    public PlainTextArea(){
        super();
        packToTextArea();
        addCaretListener();
    }

    public PlainTextArea(String text){
        super();
        packToTextArea(text);
        addCaretListener();
    }

    private void addCaretListener(){
        getTextArea().addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                if (ControlsAdapter.getPositionLabel() != null) {
                    // try {
                    //     int caretPosition = ControlsAdapter.getTextArea().getCaretPosition();
                    //     int line = TextAreaHanlders.getLineNumber(ControlsAdapter.getTextArea(), caretPosition) + 1;
                    //     int column = TextAreaHanlders.getColumnNumber(ControlsAdapter.getTextArea(), caretPosition) + 1;            
                    //     ControlsAdapter.getPositionLabel().setText(String.format("line: %d, col: %d", line, column));
                    // } catch (Exception exc) {
                    //     //Logger.printErr(moduleName, exc.getMessage());
                    //     return;
                    // }
                }
                else{
                    System.out.println("pos label not found");
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
