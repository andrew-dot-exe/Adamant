package com.andrewexe.editor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class TomlWriter {

    public static void writeTomlFile(Path filePath, Map<String, Object> data) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writeTable(writer, data, "");
        }
    }

    @SuppressWarnings("unchecked")
    private static void writeTable(BufferedWriter writer, Map<String, Object> data, String parentKey) throws IOException {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                String fullKey = parentKey.isEmpty() ? key : parentKey + "." + key;
                writer.write("[" + fullKey + "]");
                writer.newLine();
                writeTable(writer, (Map<String, Object>) value, fullKey);
            } else {
                // if (!parentKey.isEmpty()) {
                //     key = parentKey + "." + key;
                // }
                writer.write(key + " = ");
                writeValue(writer, value);
                writer.newLine();
            }
        }
    }

    private static void writeValue(BufferedWriter writer, Object value) throws IOException {
        if (value instanceof String) {
            writer.write("\"" + escapeString((String) value) + "\"");
        } else if (value instanceof List) {
            writeArray(writer, (List<?>) value);
        } else if (value instanceof Boolean) {
            writer.write(value.toString());
        } else if (value instanceof Number) {
            writer.write(value.toString());
        } else {
            throw new IOException("Unsupported data type: " + value.getClass().getSimpleName());
        }
    }

    private static void writeArray(BufferedWriter writer, List<?> list) throws IOException {
        writer.write("[");
        for (int i = 0; i < list.size(); i++) {
            writeValue(writer, list.get(i));
            if (i < list.size() - 1) {
                writer.write(", ");
            }
        }
        writer.write("]");
    }

    private static String escapeString(String str) {
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\t", "\\t");
    }

}