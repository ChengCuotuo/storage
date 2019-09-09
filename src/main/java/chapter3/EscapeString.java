package chapter3;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/08
 * @Desc:  字符串转义
 */
public class EscapeString {
    public static void main(String[] args) {
        String str = "He didn't say, \"Stop!\"";

        System.out.println(str);

        // 转义
        String  escapedStr = StringEscapeUtils.escapeJava(str);
        System.out.println("escape: " + escapedStr);

        // 从转义字符转回来
        String str2 = StringEscapeUtils.unescapeJava(escapedStr);
        System.out.println("unescate: " + str2);
    }
}
