package com.andrewexe.ui.controls;

import java.util.HashMap;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedTextArea extends JTabbedPane{

    private final String initialFilename = "empty";
    private int empties = 0;

    private HashMap<String, UniversalTextArea> areas = new HashMap<String, UniversalTextArea>();

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

    public JTextPane getAreaByFilename(String filename){
        return areas.get(filename).getTextPane(); //returns null if not found
    }

    public void openFile(String filename, String text){
        // лучше подходит под SR
        areas.put(filename, new UniversalTextArea(text));
        this.add(filename, areas.get(filename));
    }

    public void openEmpty(){
        empties += 1;
        String dummyFilename = String.format("%s %d",initialFilename, empties);
        openFile(dummyFilename, "");
    }

    public JTextPane getCurrentTextArea(){
        String filename = this.getTitleAt(this.getSelectedIndex());
        return areas.get(filename).getTextPane();
    }

    public String getTextFromCurrentArea()
    {
        String filename = this.getTitleAt(this.getSelectedIndex());
        return areas.get(filename).getTextPane().getText();
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
