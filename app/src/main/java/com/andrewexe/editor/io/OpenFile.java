package com.andrewexe.editor.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OpenFile {

    /**
     * Читает содержимое текстового файла и возвращает строки в виде списка
     * @param file - файл для чтения
     * @return список строк (пустой список при ошибках)
     */
    public static List<String> readLines(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } 
        catch (IOException | SecurityException e) {
            System.err.println("Ошибка чтения: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Читает содержимое файла как единую строку с сохранением переносов
     * @param file - файл для чтения
     * @return содержимое файла с оригинальными переносами строк
     */
    public static String readAsText(File file) {
        return String.join(System.lineSeparator(), readLines(file));
    }

    /**
     * Версия метода для работы с путём к файлу
     * @param path - путь к файлу
     * @return список строк (пустой список при ошибках)
     */
    public static List<String> readLines(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile() && file.canRead()) {
            return readLines(file);
        }
        return Collections.emptyList();
    }

    /**
     * Версия метода для работы с путём к файлу
     * @param path - путь к файлу
     * @return содержимое файла с оригинальными переносами строк
     */
    public static String readAsText(String path) {
        return String.join(System.lineSeparator(), readLines(path));
    }
}