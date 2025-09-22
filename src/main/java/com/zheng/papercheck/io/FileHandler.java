package com.zheng.papercheck.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
public class FileHandler {
    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim();
    }

    public static void writeFile(String filePath, double result) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            writer.write(String.format("%.2f", result));
        }
    }
}
