package com.andrewexe.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.andrewexe.io.LoadSaveFile;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        super();
        packMenuBar();
    }

    class OpenButtonEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
            // get filename by user
            if (jfc.showOpenDialog(ControlsAdapter.getMainFrame()) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                String contains = LoadSaveFile.openFile(file); // todo: remove dependency by interface
                ControlsAdapter.getTabbedPane().openFile(file.getName(), contains);
            }

        }
    }

    class SaveButtonEventHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            String text = ControlsAdapter.getTabbedPane().getTextFromCurrentArea();
            JFileChooser jfc = new JFileChooser();

            if (jfc.showSaveDialog(ControlsAdapter.getMainFrame()) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                LoadSaveFile.saveFile(file, text);
            }
        }
    }

    class ExitMenuItemHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ControlsAdapter.getMainFrame().dispose();
        }
    }

    private void packMenuBar() {
        JMenu menu = new JMenu("file");

        JMenuItem openItem = new JMenuItem("open");
        openItem.addActionListener(new OpenButtonEventHandler());

        JMenuItem saveItem = new JMenuItem("save");
        saveItem.addActionListener(new SaveButtonEventHandler());

        JMenuItem closeProgram = new JMenuItem("close");
        closeProgram.addActionListener(new ExitMenuItemHandler());

        menu.add(openItem);
        menu.add(saveItem);
        menu.add(closeProgram);

        this.add(menu);
    }
}
