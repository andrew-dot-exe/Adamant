package com.andrewexe.ui.controls;

import java.util.HashMap;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class TabbedTextArea extends JTabbedPane{

    private final String initialFilename = "empty";

    private HashMap<String, PlainTextArea> areas = new HashMap<String, PlainTextArea>();

    public TabbedTextArea(int tabPosition, int scrollTabs){
        super(tabPosition, scrollTabs);

        areas.put(initialFilename, new PlainTextArea());
        this.add(initialFilename, areas.get(initialFilename));
    }

    public TabbedTextArea(int tabPosition, int scrollTabs, String filename){
        super(tabPosition, scrollTabs);

        areas.put(filename, new PlainTextArea());
        this.add(initialFilename, areas.get(initialFilename));
    }

    public JTextArea getAreaByFilename(String filename){
        return areas.get(filename); //returns null if not found
    }

    public void openNewTab(String filename, String text){
        // лучше подходит под SR
        areas.put(filename, new PlainTextArea(text));
        this.add(filename, areas.get(filename));
    }

    public String getTextFromCurrentArea()
    {
        String filename = this.getTitleAt(this.getSelectedIndex());
        return areas.get(filename).getText();
    }
}
