package com.andrewexe.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow{

    private JFrame mainFrame;
    private JMenuBar mJMenuBar;

    private JScrollPane areaScrollPane;
    private JTextArea textArea;

    private JFrame getJFrame() {
        if (mainFrame == null) {
            mainFrame = new JFrame("Adamant");
        }
        return mainFrame;
    }

    private JMenuBar getMenuBar(){
        // sets menubar with 
        if(mJMenuBar == null){
            mJMenuBar = new JMenuBar();
            // fill 
            JMenu menu = new JMenu("File");
            JMenuItem openItem = new JMenuItem("Open");
            
            JMenuItem saveItem = new JMenuItem("Save");

            JMenuItem closeProgram = new JMenuItem("Close");
            closeProgram.addActionListener(new ExitMenuItemHandler());

            menu.add(openItem);
            menu.add(saveItem);
            menu.add(closeProgram);

            mJMenuBar.add(menu);
        }
        return mJMenuBar;
    }

    public JTextArea getTextArea(){
        if(textArea == null){
            textArea = new JTextArea( );
            textArea.setLineWrap(true);
        }
        return textArea;
    }

    private JScrollPane getJScrollPane()
    {
        if(areaScrollPane == null)
        {
            areaScrollPane = new JScrollPane(getTextArea());
        }
        return areaScrollPane;
    }

    class OpenButtonEventHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //open file dialog
        }
    }

    class SaveButtonEventHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //open file dialog
        }
    }


    class ExitMenuItemHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getJFrame().dispose();
        }
    }


    public void run() {
        // runs the UI

        getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // todo: on mac change to close window
        getJFrame().setSize(600, 400);
        getJFrame().setJMenuBar(getMenuBar());

        getJFrame().add(getJScrollPane());

        getJFrame().setVisible(true);
    }

}