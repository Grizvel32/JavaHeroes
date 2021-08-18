package com.company.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class SettingsHelper {
    private static HashMap<String, String> settings = new HashMap<>();

    public static void loadFromFile() {
        try {
            FileReader fileReader = new FileReader("settings.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int length = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < length; i++) {
                String currentLine = bufferedReader.readLine();
                String[] parts = currentLine.split("=");

                settings.put(parts[0], parts[1]);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getIntValue(String key) {
        return Integer.parseInt(settings.get(key));
    }

    public static char getCharValue(String key) {
        return settings.get(key).charAt(0);
    }
}
