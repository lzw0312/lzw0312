package com.linziwei.www;

import com.linziwei.www.util.FileUtil;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertThrows;

/**
 * @author Linziwei
 * @description
 * @date 2025/3/8
 */
public class ExceptionTest {

    /**
     * 测试输入文件数目的异常情况
     */
    @Test
    public void main_InvalidNumberOfArguments_ShouldPrintErrorMessage()  {
        String[] args = {"file1.txt", "file2.txt"};
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));
        assertThrows(IllegalArgumentException.class, () -> {
            Main.main(args);
        });
    }

    /**
     * 测试读入文件的异常情况
     */
    @Test
    public void FileUtil_readFile_ShouldPrintErrorMessage() {
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));
        assertThrows(FileNotFoundException.class, () -> {
            FileUtil.readFile("file1.txt");
        });
    }


    /**
     * 测试写入文件的异常情况
     */
    @Test
    public void FileUtil_writeFile_ShouldPrintErrorMessage() {
        String[] args = {"C:\\Users\\Aurora\\Desktop\\orig.txt",
                "C:\\Users\\Aurora\\Desktop\\orig_0.8_add.txt" ,
               "file2.txt"};
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()));
        assertThrows(FileNotFoundException.class, () -> {
            Main.main(args);
        });
    }
}
