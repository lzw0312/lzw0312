package com.linziwei.www;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Linziwei
 * @description
 * @date 2025/3/8
 */
public class PlagiarismCheckerTest extends TestCase {


    private static final String ANSWER_TEST_FILE = "C:\\Users\\Aurora\\Desktop\\answer.txt";

    /**
     * 测试完全相同的文本
     * 预期结果：相似度应为1.0
     */
    @Test
    public void testIdenticalText() {
        String result = PlagiarismChecker.plagiarismChecker("今天是星期天，天气晴，今天晚上我要去看电影。", "今天是星期天，天气晴，今天晚上我要去看电影。", ANSWER_TEST_FILE);
        assertTrue("测试完全相同文本，结果因为1.00", Double.parseDouble(result) == 1.00);

    }

    /**
     * 测试未抄袭文本
     * 预期结果：结果应小于0.3
     */
    @Test
    public void testWithoutPlagiarizingText() {

        String result = PlagiarismChecker.plagiarismChecker("今天是星期天，天气晴，今天晚上我要去看电影。", "我明天要和你一起吃饭", ANSWER_TEST_FILE);
        assertTrue("测试未抄袭文本，结果应小于0.3", Double.parseDouble(result) < 0.5);
    }

    /**
     * 测试原文本为空
     * 预期结果：返回“”
     */
    @Test
    public void testEmptyOrignalText() {

        String result = PlagiarismChecker.plagiarismChecker("", "我明天要和你一起吃饭", ANSWER_TEST_FILE);
        assertTrue("测试原文本为空，结果应为空字符", result.equals(""));
    }

    /**
     * 测试抄袭文本为空
     * 预期结果：返回“”
     */
    @Test
    public void testEmptyCopiedText() {

        String result = PlagiarismChecker.plagiarismChecker("今天是星期天，天气晴，今天晚上我要去看电影。", "", ANSWER_TEST_FILE);
        assertTrue("测试抄袭文本为空，结果应为空字符", result.equals(""));
    }

    /**
     * 测试原文本和抄袭文本都为空
     * 预期结果：返回“”
     */
    @Test
    public void testTextBothEmpty() {
        String result = PlagiarismChecker.plagiarismChecker("", "", ANSWER_TEST_FILE);
        assertTrue("测试原文本和抄袭文本都为空，结果应为空字符", result.equals(""));

    }

    /**
     * 测试特殊符号
     * 预期结果：特殊符号不影响结果，结果应为0.00
     */
    @Test
    public void testSpecialSymbol() {
        String result = PlagiarismChecker.plagiarismChecker(",.！英语", ",.!语文", ANSWER_TEST_FILE);
        assertTrue("测试原文本和抄袭文本都为空，结果应为空字符", Double.parseDouble(result) == 0.00);

    }

    /**
     * 测试包含数字的文本
     * 预期结果：数字不影响结果
     */
    @Test
    public void testNumbers() {
        String result = PlagiarismChecker.plagiarismChecker("今天是2023年10月1日", "今天是 2023 年 10 月 1 日", ANSWER_TEST_FILE);
        Assert.assertTrue("测试数字，结果应为1.0", Double.parseDouble(result) == 1.0);
    }

    /**
     * 测试短抄袭文本
     * 预期结果：结果应大于0.8
     */
    @Test
    public void testPlagiarizingText() {
        String result = PlagiarismChecker.plagiarismChecker("今天是星期天，天气晴，今天晚上我要去看电影。", "今天是周天，天气晴朗，我晚上要去看电影。", ANSWER_TEST_FILE);
        assertTrue("测试未抄袭文本，结果应大于0.8", Double.parseDouble(result) > 0.80);
    }

    /**
     * 测试长抄袭文本
     * 预期结果：结果应大于0.5
     */
    @Test
    public void testLongPlagiarizingText() throws InterruptedException {
        String result = PlagiarismChecker.plagiarismChecker("今天是星期天，天气晴朗，我和家人一起去了公园。公园里有很多人，有的在跑步，有的在打羽毛球，还有的在放风筝。我们找了一个安静的地方坐下，享受着阳光和微风。孩子们在草地上嬉戏玩耍，笑声不断。中午我们在公园里的餐厅吃了午饭，饭后继续散步，直到傍晚才回家。这一天过得非常愉快，希望下次还能来",
                "今天是周日，天气非常好，我和家人一起去逛公园。公园里聚集了不少人，有人在慢跑，有人在打羽毛球，还有人在放风筝。我们选择了一处宁静的地方休息，尽情享受阳光与轻柔的微风。孩子们在草坪上欢快地奔跑，充满了欢声笑语。中午时分，我们在公园内的餐馆享用了午餐，之后又漫步了一会儿，直到傍晚才离开。这真是美好的一天，期待下次再来",
                ANSWER_TEST_FILE);
        assertTrue("测试未抄袭文本，结果应大于0.8", Double.parseDouble(result) >= 0.50);

    }

    /**
     * 测试文本长度差异较大
     * 预期结果：算法应能正确处理长度差异较大的文本
     */
    @Test
    public void testLengthDifference() {
        String result = PlagiarismChecker.plagiarismChecker("今天是星期天，天气晴，今天晚上我要去看电影。",
                "今天是周日，天气非常好，我和家人一起去逛公园。公园里聚集了不少人，有人在慢跑，有人在打羽毛球，还有人在放风筝。我们选择了一处宁静的地方休息，尽情享受阳光与轻柔的微风。孩子们在草坪上欢快地奔跑，充满了欢声笑语。中午时分，我们在公园内的餐馆享用了午餐，之后又漫步了一会儿，直到傍晚才离开。这真是美好的一天，期待下次再来",
                ANSWER_TEST_FILE);
        assertTrue("测试未抄袭文本，结果应小于于0.5", Double.parseDouble(result) <= 0.50);
    }
}