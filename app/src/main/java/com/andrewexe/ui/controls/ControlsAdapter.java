package com.andrewexe.ui.controls;

import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlsAdapter {

    private static JFrame _frame;
    private static MenuBar _MenuBar;
    private static PlainTextArea _textArea;

    private static JLabel _positionLabel;
    private static BottomPanel  _panel;

    public static BottomPanel getBottomPanel(){
        if(_panel == null){
            _panel = new BottomPanel();
        }
        return _panel;
    }

    public static JLabel getPositionLabel() {
        if(_positionLabel == null){
            _positionLabel = new JLabel("line: 1 col: 1");
        }
        return _positionLabel;
    }

    public static MenuBar getMenuBar() {
        if(_MenuBar == null){
            _MenuBar = new MenuBar();
        }
        return _MenuBar;
    }

    public static PlainTextArea getTextArea() {
        if(_textArea == null){
            _textArea = new PlainTextArea();
        }
        return _textArea;
    }

    public static JFrame getMainFrame()
    {
        if(_frame == null){
            _frame = new JFrame("adamant");
        }
        return _frame;
    }
}
