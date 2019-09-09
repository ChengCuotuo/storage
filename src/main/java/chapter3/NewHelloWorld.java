package chapter3;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/07
 * @Desc: 国际化
 */
public class NewHelloWorld {
    public static void main(String[] args) {
        // 获取系统默认的国家/语言环境
        Locale locale = Locale.getDefault();
        System.out.println(locale); // zh_CN

        // 根据指定的语言/国家环境加载资源文件
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        System.out.println(bundle.getString("hello")); // 你好，世界！

        Locale enLocal = new Locale("en_US");
        ResourceBundle enBoundle = ResourceBundle.getBundle("message", enLocal);
        System.out.println(enBoundle.getString("hello")); // hello, world!
    }
}
