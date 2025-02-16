package com.andrewexe.ui.controls;

import javax.swing.JPanel;

public class BottomPanel extends JPanel {
    public BottomPanel(){
        super();
        packPanel();
    }

    private void packPanel(){
        this.add(ControlsAdapter.getPositionLabel());
    }
}
