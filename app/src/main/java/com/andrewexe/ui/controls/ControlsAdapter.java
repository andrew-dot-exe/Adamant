package com.andrewexe.ui.controls;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class ControlsAdapter {

    private static JFrame _frame;
    private static MenuBar _MenuBar;

    private static TabbedTextArea _tabPane;
    private static JScrollPane _scrollPane;
    private static PlainTextArea _textArea;

    private static JLabel _positionLabel;
    private static BottomPanel _panel;

    public static TabbedTextArea getTabbedPane() {
        if (_tabPane == null) {
            _tabPane = new TabbedTextArea(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
            _tabPane.openFile("first", "test");
            _tabPane.openFile("second", "test");
            _tabPane.openFile("third", "test");

        }
        return _tabPane;
    }

    public static JScrollPane getScrollPane() {
        if (_scrollPane == null) {
            _scrollPane = new JScrollPane();
        }
        return _scrollPane;
    }

    public static BottomPanel getBottomPanel() {
        if (_panel == null) {
            _panel = new BottomPanel();
        }
        return _panel;
    }

    public static JLabel getPositionLabel() {
        if (_positionLabel == null) {
            _positionLabel = new JLabel("line: 1 col: 1");
        }
        return _positionLabel;
    }

    public static MenuBar getMenuBar() {
        if (_MenuBar == null) {
            _MenuBar = new MenuBar();
        }
        return _MenuBar;
    }

    public static PlainTextArea getTextArea() {
        return getTabbedPane().getCurrentTextArea();
    }

    public static JFrame getMainFrame() {
        if (_frame == null) {
            _frame = new JFrame("adamant");
        }
        return _frame;
    }
}
