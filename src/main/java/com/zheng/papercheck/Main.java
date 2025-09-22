package com.zheng.papercheck;
import com.zheng.papercheck.core.Calculator;
import com.zheng.papercheck.io.FileHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // 1. 检查命令行参数数量
        if (args.length != 3) {
            System.err.println("Usage: java -jar main.jar [orig_path] [plagiarized_path] [ans_path]");
            System.exit(1);
        }

        String origFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String ansFilePath = args[2];

        try {
            // 2. 读取文件
            String origText = FileHandler.readFile(origFilePath);
            String plagiarizedText = FileHandler.readFile(plagiarizedFilePath);

            // 3. 计算相似度
            double similarity = Calculator.calculateSimilarity(origText, plagiarizedText);

            // 4. 结果写入文件
            FileHandler.writeFile(ansFilePath, similarity);

            // 5. 控制台也输出一下，方便调试
            System.out.println("Calculation completed. Similarity: " + String.format("%.2f", similarity));

        } catch (IOException e) {
            System.err.println("Error: File operation failed - " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred - " + e.getMessage());
            System.exit(1);
        }
    }
}
