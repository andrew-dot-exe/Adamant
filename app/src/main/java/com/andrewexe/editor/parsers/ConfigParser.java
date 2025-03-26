package com.andrewexe.editor.parsers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.andrewexe.editor.io.OpenFile;

public class ConfigParser {
    public static HashMap<String, String> getConfigValues(ArrayList<String> textFile) {
        HashMap<String, String> values = new HashMap<>();
        try {
            for (String line : textFile) {
                try {
                    String[] splitted = line.split(" = "); // maybe shit
                    values.put(splitted[0].trim(), splitted[1].trim());
                } catch (IndexOutOfBoundsException exception) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            return values;
        }
        return values;
    }

    public static HashMap<String, String> getConfigValues(File configFile) {
        ArrayList<String> file = (ArrayList<String>) OpenFile.readLines(configFile);
        if(file == null){
            return null;
        }
        return getConfigValues(file);
    }

}
