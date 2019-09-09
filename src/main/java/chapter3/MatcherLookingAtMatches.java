package chapter3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/07
 * @Desc: 正则表达式，部分匹配和完全匹配
 */
public class MatcherLookingAtMatches {
    private static final String REGEX = "foo";
    private static final String INPUT = "fooooooooooooooo";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);

        System.out.println("Current REGEX is: " + REGEX);
        System.out.println("Current INPUT is: " + INPUT);

        System.out.println("LookingAt(): " + matcher.lookingAt()); // 部分匹配 match(from, NOANCHOR);
        System.out.println("matches(): " + matcher.matches()); // 完全匹配 return match(from, ENDANCHOR);

        /**
         * Current REGEX is: foo
         * Current INPUT is: fooooooooooooooo
         * LookingAt(): true
         * matches(): false
         */
    }
}
