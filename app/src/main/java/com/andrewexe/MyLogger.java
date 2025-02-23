package com.andrewexe;

//must be deleted
public class MyLogger {
    public static void printErr(String module, String message) {
        System.out.println(String.format("ERROR in module %s: %s", module, message));
    }

    public static void printMessage(String module, String message) {
        System.out.println(String.format("Message from module %s: %s", module, message));
    }
}
