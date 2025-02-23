package com.andrewexe.editor;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import com.andrewexe.MyLogger;

public class EditorSettings {

    private final String moduleName = "SettingsParser";

    class FontSettings { // should return font
        private Font mainFont = null;
        private int fontSize;

        FontSettings(String fontName, int size) {
            mainFont = new Font(fontName, Font.PLAIN, size);
        }

        public Font getFont() {
            return mainFont;
        }
    }

    class GeneralSettings { // should return setings for
        private boolean wrapWords = false;

    }

    private final String configFilename = "adamant.conf";

    private final String[] paths = {
            "/.config/adamant/", // linux and macos
            "c:/users/username/.adamant/" // windows
    };

    private FontSettings fontSettings;

    @SuppressWarnings("unchecked")
    private FontSettings parseFontSettings(Map<String, Object> root) throws Exception {
        try {
            Map<String, Object> font = (Map<String, Object>) root.get("font");
            int size = Math.toIntExact((long)font.get("size"));
            String family = (String) font.get("family");
            MyLogger.printMessage(moduleName, String.format("family: %s\nsize: %d", family, size));
            return new FontSettings(family, size);
        } catch (Exception exc) {
            MyLogger.printErr(moduleName, exc.getMessage());
            throw new Exception("Font info corrupted.");
        }
    }

    private String getPath(){

        Utils.OS os = Utils.getCurrentOS();
        switch (os) {
            case WIN:
                return System.getProperty("user.home") + paths[1] + configFilename;
            case MACOS:
                return System.getProperty("user.home") + paths[0] + configFilename;
            case LINUX:
                return System.getProperty("user.home") + paths[0] + configFilename;
            default:
                break;
        }
        return null;
    }

    public EditorSettings() {
        String configPath = getPath();
        MyLogger.printMessage(moduleName, String.format("configFilename: %s", configPath));
        try {
            // determine OS
            File configFile = new File(configPath);
            InputStream is = new FileInputStream(configFile);
            Map<String, Object> parsed = TomlParser.parse(is);
            fontSettings = parseFontSettings(parsed);

        } catch (Exception e) {
            MyLogger.printErr(moduleName, e.getMessage());
            MyLogger.printErr(moduleName, "config file not found");
            if(fontSettings == null)
            {
                fontSettings = new FontSettings("Monospaced",12);
                writeSettings();
            }
        }

    }

    public Font getFontFromSettings() {
        return fontSettings.getFont();
    }

    public void writeSettings()
    {
        String configPath = getPath();
        Map<String, Object> root = new LinkedHashMap<>();
        Map<String, Object> fontSettingsNode = new LinkedHashMap<>();
        
        fontSettingsNode.put("family", fontSettings.getFont().getFamily());
        fontSettingsNode.put("size", fontSettings.getFont().getSize());
        root.put("font", fontSettingsNode);

        try {
            TomlWriter.writeTomlFile(Path.of(configPath), root);
        } catch (Exception e) {
            MyLogger.printErr(moduleName, e.getMessage());
        }
        
    }

}
