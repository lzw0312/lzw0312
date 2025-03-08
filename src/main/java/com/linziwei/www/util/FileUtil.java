package com.linziwei.www.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Linziwei
 * @description 工具类
 * @date 2025/3/6
 */
public class FileUtil {
    /**
     * @Author Linziwei
     * @Description 读取文件数据
     * @Date 2025/3/7
     */
    public static String readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        // 验证文件是否存在且是一个文件
        if (!file.exists() || !file.isFile()) {
            System.out.println("Invalid file path: " + filePath);
            throw new FileNotFoundException("Invalid file path: " + filePath);
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            // 去除最后一个多余的换行符
            if (!content.isEmpty() && content.charAt(content.length() - 1) == '\n') {
                content.setLength(content.length() - 1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            throw new RuntimeException("Error reading file: " + filePath, e);
        }

        return content.toString();
    }

    /**
     * @Author Linziwei
     * @Description 将数据写入文件
     * @Date 2025/3/7
     */
    public static void writeFile(String filePath, String content) {
        // 验证和规范化文件路径
        //File file = new File(filePath);
        // 验证和规范化文件路径
        Path path = Paths.get(filePath);
        try {
            // 使用 try-with-resources 确保 FileWriter 自动关闭
            try (FileWriter writer = new FileWriter(path.toFile(), false)) {
                writer.write(content);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error Writing file: " + filePath);
            throw new RuntimeException("Error Writing file: " + filePath, e);
        }
    }

}
