package com.andrewexe.editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;


public class TomlParser {
    public static Map<String, Object> parse(InputStream input) throws IOException {
        Map<String, Object> root = new LinkedHashMap<>();
        Map<String, Object> currentContext = root;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#"))
                    continue;

                if (line.startsWith("[")) {
                    currentContext = handleTableDeclaration(line, root);
                } else {
                    handleKeyValue(line, currentContext);
                }
            }
        }

        return root;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> handleTableDeclaration(String line, Map<String, Object> root) {
        String tableName = line.replaceAll("[\\[\\]]", "").trim();
        String[] parts = tableName.split("\\.");

        Map<String, Object> context = root;
        for (String part : parts) {
            if (!context.containsKey(part)) {
                context.put(part, new LinkedHashMap<String, Object>());
            }
            Object obj = context.get(part);
            if (obj instanceof Map) {
                context = (Map<String, Object>) obj;
            } else {
                throw new RuntimeException("Invalid table declaration: " + tableName);
            }
        }
        return context;
    }

    @SuppressWarnings("unchecked")
    private static void handleKeyValue(String line, Map<String, Object> context) {
        int eqIndex = line.indexOf('=');
        if (eqIndex == -1)
            return;

        String key = line.substring(0, eqIndex).trim();
        String value = line.substring(eqIndex + 1).trim();

        String[] keyParts = key.split("\\.");
        Map<String, Object> currentMap = context;

        for (int i = 0; i < keyParts.length - 1; i++) {
            String part = keyParts[i];
            if (!currentMap.containsKey(part)) {
                currentMap.put(part, new LinkedHashMap<String, Object>());
            }
            currentMap = (Map<String, Object>) currentMap.get(part);
        }

        String lastKeyPart = keyParts[keyParts.length - 1];
        currentMap.put(lastKeyPart, parseValue(value));
    }

    private static Object parseValue(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        } else if (value.startsWith("'") && value.endsWith("'")) {
            return value.substring(1, value.length() - 1);
        } else if (value.equalsIgnoreCase("true")) {
            return true;
        } else if (value.equalsIgnoreCase("false")) {
            return false;
        } else if (value.contains(".")) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return value;
            }
        } else {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return value;
            }
        }
    }
}
