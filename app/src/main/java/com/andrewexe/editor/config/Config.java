package com.andrewexe.editor.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Base class for config
 */
public class Config {

    private int fontSize;
    private String fontFamily;

    public Config(HashMap<String, String> parsedConfig){
        try{
        fontSize = Integer.parseInt(parsedConfig.get("fontSize"));
        fontFamily = parsedConfig.get(fontFamily);
        }
        catch(NumberFormatException exc){
            fontSize = 12;
            //log it here
        }
        catch(Exception exc){
            //some other shit happened, load default
        }
    }

    public int getFontSize() {
        return fontSize;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    private HashMap<String,String> getConfigValues(){
        HashMap<String,String> values = new HashMap<>();
        values.put("fontFamily", fontFamily);
        values.put("fontSize", String.format("%d", fontSize));
        
        return values;
    }

    @Override
    public String toString(){
        HashMap<String, String> values = getConfigValues();
        return null;
    }
}
