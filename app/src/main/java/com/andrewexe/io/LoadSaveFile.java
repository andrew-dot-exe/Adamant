package com.andrewexe.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class LoadSaveFile {
    public static String openFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            String out = "";
            int i;
            while ((i = fis.read()) != -1) {
                out += (char) i;
            }
            return out;

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public static void saveFile(File file, String text)
    {
        try (FileOutputStream fos = new FileOutputStream(file)){
            byte[] buff = text.getBytes();
            fos.write(buff);
            fos.write((byte)'\n');
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
