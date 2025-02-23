package com.andrewexe.ui.controls;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import com.andrewexe.MyLogger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class UniversalTextArea extends JPanel {

    protected JButton decrButton; //for exception workaround

    private JScrollPane scrollPane;
    private JTextPane textPane;

    private CaretStatus caretStatus;

    private void setup(){
        decrButton = new JButton();
        textPane = new JTextPane();
        this.caretStatus = new CaretStatus();
    }

    public UniversalTextArea() {
        super(new BorderLayout());
        setup();
        packTotextPane();
        addListeners();
    }

    public UniversalTextArea(String text) {
        super(new BorderLayout());
        setup();
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

    private void addListeners() { // all hotkey listeners
        setupBoldHotkey(); //meta-b

    }

    private void setupBoldHotkey() {
        //todo: command key for macos
        KeyStroke hotkey = KeyStroke.getKeyStroke("control B");

        InputMap map = textPane.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = textPane.getActionMap();
        Action boldAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyLogger.printMessage("bold hotkey", "i'm pressed");
                StyledDocument doc = textPane.getStyledDocument();
                int start = textPane.getSelectionStart();
                int end = textPane.getSelectionEnd();
                if (!caretStatus.isBold) {
                    MyLogger.printMessage("bold hotkey", "bold");
                    caretStatus.isBold = true;
                } else {
                    MyLogger.printMessage("bold hotkey", "clear");                
                    caretStatus.isBold = false;
                }
                Style style = doc.addStyle("bold", null);
                StyleConstants.setBold(style, caretStatus.isBold);
                doc.setCharacterAttributes(start, end - start, style, false);
                textPane.setCharacterAttributes(style, caretStatus.isBold);
            }
        };

        map.put(hotkey, "makeBold");
        actionMap.put("makeBold", boldAction);
    }

    public void CloseEditorInstance(){
        //save y/n

    }

    class CaretStatus {
        private boolean isBold;
        private boolean isUnderlined;
        private boolean isStrikethrough;
        private boolean isCursive;

        public CaretStatus() {
            this.isBold = false;
            this.isUnderlined = false;
            this.isStrikethrough = false;
            this.isCursive = false;
        }
    }
}