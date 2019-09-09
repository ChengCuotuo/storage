package chapter3;

import com.sun.org.apache.regexp.internal.RE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/07
 * @Desc: 正则表达式，查找
 */
public class MatcherDemo {
    // 正则表达式，设置的格式 \b 表示边界值
    private static final String REGEX = "\\bdog\\b";
    private static final String INPUT = "dog dog dog doggie dogg";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX);

        Matcher matcher = pattern.matcher(INPUT);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("Match number: " + count);
            System.out.println("start(): " + matcher.start());
            System.out.println("end(): " + matcher.end());
        }

        /**
         * Match number: 1
         * start(): 0
         * end(): 3
         * Match number: 2
         * start(): 4
         * end(): 7
         * Match number: 3
         * start(): 8
         * end(): 11
         */
    }
}
