package chapter1;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/07
 * @Desc: 汉字转化，简体字和繁体字之间的转化
 */
public class ChineseTest {
    public static void main(String[] args) {
        String str = "声明在于运动";
        String result = ZhConverterUtil.convertToTraditional(str);
        System.out.println(result);
    }
}
