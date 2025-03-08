package com.linziwei.www;

import java.io.File;
import java.io.FileNotFoundException;

import static com.linziwei.www.PlagiarismChecker.*;
import static com.linziwei.www.util.FileUtil.readFile;
import static com.linziwei.www.util.FileUtil.writeFile;
import static java.lang.Thread.sleep;

/**
 * @author Linziwei
 * @description ${description}
 * @date 2025/3/6
 */

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        //验证参数个数
        if (args.length != 3) {
            System.out.println("请输入三个参数：原文件路径、抄袭文件路径、答案文件路径");
            throw new IllegalArgumentException("请输入三个参数：原文件路径、抄袭文件路径、答案文件路径");
        }
        String originalText = null;
        String copiedText = null;
        try {
            originalText = readFile(args[0]);
            copiedText = readFile(args[1]);
        } catch (Exception e) {
            return;
        }

        String answerTest = args[2];
        File file = new File(answerTest);
        // 验证文件路径是否有效
        if (answerTest.trim().isEmpty() || !file.exists()) {
            System.out.println("Invalid file path: " + answerTest);
            throw new FileNotFoundException("Invalid file path: " + answerTest);
        }
        //计算重复率
        String result = plagiarismChecker(originalText, copiedText, answerTest);
        // 输出结果
        System.out.println("Cosine Similarity: " + result);
        writeFile(answerTest, "重复率为：" + result);

    }
}

