package com.andrewexe.io;

public interface ISaveItem {

    public String[] loadFile(String filename);
    public void saveFile(String filename, String[] lines);

}