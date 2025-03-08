package com.linziwei.www;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.*;

import static com.linziwei.www.util.FileUtil.writeFile;

/**
 * @author Linziwei
 * @description 查重类
 * @date 2025/3/6
 */
public class PlagiarismChecker {
    /**
     * @author Linziwei
     * @Description 计算重复率
     * @Date 2025/3/7
     */
    public static String plagiarismChecker(String originalText, String copiedText, String answerTest) {
        if (originalText == null || originalText.equals("")) {
            System.out.println("originalText is null");
            return "";
        }
        if (copiedText == null || copiedText.equals("")) {
            System.out.println("copiedText is null");
            return "";
        }
        // 文本预处理
        String processedOriginal = preprocess(originalText);
        String processedCopied = preprocess(copiedText);

        // 分词
        Set<String> originalTokens = tokenize(processedOriginal);
        Set<String> copiedTokens = tokenize(processedCopied);

        // 计算相似度
        double cosineSimilarity = cosineSimilarity(originalTokens, copiedTokens);
        return String.format("%.2f", cosineSimilarity);

    }

    /**
     * @Author Linziwei
     * @Description 文本预处理
     * @Date 2025/3/7
     */
    public static String preprocess(String text) {
        // 去除所有标点符号、特殊字符和空白字符
        String noPunctuation = text.replaceAll("[\\p{P}\\p{S}\\p{Z}]", " ");
        // 去除多余空格
        noPunctuation = noPunctuation.replaceAll("\\s+", "").trim();
        // 统一转换为小写
        return noPunctuation.toLowerCase();

    }

    /**
     * @Author Linziwei
     * @Description 分词
     * @Date 2025/3/7
     */
    public static Set<String> tokenize(String text) {
        Set<String> tokens = new HashSet<>();
        // 使用 HanLP 对中文分词
        List<Term> termList = HanLP.segment(text);
        for (Term term : termList) {
            tokens.add(term.word);
        }
        return tokens;
    }

    /**
     * @Author Linziwei
     * @Description 计算余弦相似度
     * @Date 2025/3/7
     */
    public static double cosineSimilarity(Set<String> set1, Set<String> set2) {
        Map<String, Integer> vector1 = new HashMap<>();
        Map<String, Integer> vector2 = new HashMap<>();

        // 构建词频向量
        for (String word : set1) {
            vector1.put(word, vector1.getOrDefault(word, 0) + 1);
        }

        for (String word : set2) {
            vector2.put(word, vector2.getOrDefault(word, 0) + 1);
        }

        // 计算点积
        double dotProduct = 0.0;
        for (String word : vector1.keySet()) {
            if (vector2.containsKey(word)) {
                dotProduct += vector1.get(word) * vector2.get(word);

            }
        }

        // 计算向量模长
        double magnitude1 = Math.sqrt(vector1.values().stream().mapToDouble(v -> Math.pow(v, 2)).sum());
        double magnitude2 = Math.sqrt(vector2.values().stream().mapToDouble(v -> Math.pow(v, 2)).sum());

        // 计算余弦相似度
        return dotProduct / (magnitude1 * magnitude2);
    }
}
