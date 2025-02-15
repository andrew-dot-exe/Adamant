package com.andrewexe.editor;

public class Utils {
    public enum OS{
        WIN, MACOS, LINUX, OTHER
    }

    private static OS currentOS = null;

    public static OS getCurrentOS()
    {
        if (currentOS == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                currentOS = OS.WIN;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                currentOS = OS.LINUX;
            } else if (operSys.contains("mac")) {
                currentOS = OS.MACOS;
            } else {
                currentOS = OS.OTHER;
            }
        }
        return currentOS;
    }
}
