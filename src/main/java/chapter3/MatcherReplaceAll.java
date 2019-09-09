package chapter3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/07
 * @Desc: 正则表达式，替换
 */
public class MatcherReplaceAll {
    private static String REGEX = "dog";
    private static String INPUT = "The dog says meow, All dogs say meow.";
    private static String REPLACE = "cat";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);
        INPUT = matcher.replaceAll(REPLACE);
        System.out.println(INPUT);
        // The cat says meow, All cats say meow.
    }
}
