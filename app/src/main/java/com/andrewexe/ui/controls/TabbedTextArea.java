package com.andrewexe.ui.controls;

import java.util.HashMap;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedTextArea extends JTabbedPane{

    private final String initialFilename = "empty";
    private int empties = 0;

    private HashMap<String, PlainTextArea> areas = new HashMap<String, PlainTextArea>();

    public TabbedTextArea(int tabPosition, int scrollTabs){
        
        super(tabPosition, scrollTabs);
        bindEvents();
        openEmpty();
    }

    public TabbedTextArea(int tabPosition, int scrollTabs, String filename, String text){
        super(tabPosition, scrollTabs);
        bindEvents();
        openFile(filename, text);
    }

    private void bindEvents(){
        this.addChangeListener(new TabChabgeListener());
    }

    public JTextArea getAreaByFilename(String filename){
        return areas.get(filename).getTextArea(); //returns null if not found
    }

    public void openFile(String filename, String text){
        // лучше подходит под SR
        areas.put(filename, new PlainTextArea(text));
        this.add(filename, areas.get(filename));
    }

    public void openEmpty(){
        empties += 1;
        String dummyFilename = String.format("%s %d",initialFilename, empties);
        openFile(dummyFilename, "");
    }

    public JTextArea getCurrentTextArea(){
        String filename = this.getTitleAt(this.getSelectedIndex());
        return areas.get(filename).getTextArea();
    }

    public String getTextFromCurrentArea()
    {
        String filename = this.getTitleAt(this.getSelectedIndex());
        return areas.get(filename).getTextArea().getSelectedText();
    }

    public List<String> getAllTabs(){
        return null;
    }

    class TabChabgeListener implements ChangeListener{
        public void stateChanged(ChangeEvent event){
            System.out.println("tab chabged");
            ControlsAdapter.getPositionLabel().setText(String.format("line: 1, col: 1"));
        }
    }
}
