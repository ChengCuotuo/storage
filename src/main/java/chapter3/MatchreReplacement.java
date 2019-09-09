package chapter3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/07
 * @Desc: 正则表达式，替换
 */
public class MatchreReplacement {
    private static final String REGEX = "a*b";
    private static final String INPUT = "aabfooaabfooabfoobcdd";
    private static final String REPLACE = "-";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);
        StringBuffer s = new StringBuffer();

        // 全部替换
        while (matcher.find()) {
            matcher.appendReplacement(s, REPLACE);
        }
        System.out.println(s.toString()); // -foo-foo-foo-

        // 把最后的内容添加进来，达到的效果就是将原来的字符串中匹配 a*b 的内容完全删除，其他的保留
        matcher.appendTail(s);
        System.out.println(s.toString()); // -foo-foo-foo-cdd
    }
}
