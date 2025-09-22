package com.zheng.papercheck.core;
import java.util.*;
import java.util.regex.Pattern;
public class Calculator {
    public static String preprocessText(String text) {
        if (text == null) return "";
        // 转小写，去除非文字数字字符，替换为空格
        return text.toLowerCase()
                .replaceAll("[^a-z0-9\\u4e00-\\u9fa5]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public static List<String> segmentText(String text) {
        // 字符级分词：对中文更有效，将每个字符作为一个单元
        List<String> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!Character.isWhitespace(c)) { // 跳过空格
                characters.add(String.valueOf(c));
            }
        }
        return characters;
    }
    // 计算词频向量
    public static Map<String, Integer> getWordFrequencyVector(List<String> words) {
        Map<String, Integer> vector = new HashMap<>();
        for (String word : words) {
            vector.put(word, vector.getOrDefault(word, 0) + 1);
        }
        return vector;
    }

    // 获取所有词汇表（两个文本词汇的并集）
    public static Set<String> getVocabulary(Map<String, Integer> vec1, Map<String, Integer> vec2) {
        Set<String> vocabulary = new HashSet<>(vec1.keySet());
        vocabulary.addAll(vec2.keySet());
        return vocabulary;
    }

    // 将词频Map转换为定长向量数组
    public static double[] mapToArray(Map<String, Integer> wordVector, Set<String> vocabulary) {
        double[] array = new double[vocabulary.size()];
        int i = 0;
        for (String word : vocabulary) {
            array[i] = wordVector.getOrDefault(word, 0);
            i++;
        }
        return array;
    }

    // 计算余弦相似度
    public static double calculateCosineSimilarity(double[] vec1, double[] vec2) {
        if (vec1.length != vec2.length) {
            throw new IllegalArgumentException("Vectors must be of the same length");
        }

        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < vec1.length; i++) {
            dotProduct += vec1[i] * vec2[i];
            norm1 += Math.pow(vec1[i], 2);
            norm2 += Math.pow(vec2[i], 2);
        }

        if (norm1 == 0 || norm2 == 0) {
            return 0.0; // 避免除以零
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    // 主计算方法，对外暴露的API
    public static double calculateSimilarity(String text1, String text2) {
        // 1. 预处理
        String processedText1 = preprocessText(text1);
        String processedText2 = preprocessText(text2);

        // 2. 分词
        List<String> words1 = segmentText(processedText1);
        List<String> words2 = segmentText(processedText2);

        // 3. 获取词频向量
        Map<String, Integer> vec1 = getWordFrequencyVector(words1);
        Map<String, Integer> vec2 = getWordFrequencyVector(words2);

        // 4. 获取词汇表
        Set<String> vocabulary = getVocabulary(vec1, vec2);

        // 5. 转换为数组
        double[] array1 = mapToArray(vec1, vocabulary);
        double[] array2 = mapToArray(vec2, vocabulary);

        // 6. 计算余弦相似度
        return calculateCosineSimilarity(array1, array2);
    }
}
