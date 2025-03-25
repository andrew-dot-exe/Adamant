package com.andrewexe.editor.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class SaveFile {
    public static void saveText(List<String> text, File file)
    {
        try (FileWriter writer = new FileWriter(file)) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for(String line: text){
                for(int i = 0; i < line.length(); i++){
                    bufferedWriter.write(line.charAt(i));
                }
                bufferedWriter.write('\n');
            }
            bufferedWriter.close();
            writer.close();     
        } catch (Exception e) {
            // TODO: handle exception

        }
    }
}
