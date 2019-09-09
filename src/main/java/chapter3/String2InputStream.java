package chapter3;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/08
 * @Desc:  采用 Apache Commons IO 完成字符串转成字符流
 */
public class String2InputStream {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Xiaohong", "Xiaoming", "Daming", "Libai");
        // 合并为一个字符串，以逗号相连
        String nameStr = String.join(",", names);

        /**
         * 将上面的 List 中的内容转成一个字符串，再将字符串转成一个输入流
         * 将输入流设置为系统的输入流，接着设置系统读取的内容为设置的输入流，
         * 最后设置分隔符，将输入流读取
         */
        // 将字符串作为默认的输入流
        /**
         * 使用的是 javaIO 中的将字符数组转成输入流的方法
         * new ByteArrayInputStream(input.getBytes(Charsets.toCharset(encoding)))
         */
        InputStream in = IOUtils.toInputStream(nameStr, Charsets.toCharset("UTF-8"));
        // 重置系统的输入流
        System.setIn(in);

        // 模拟键盘输入，这也是 OJ 平台测试用例输入的原则
        // 此处也可以换成一个文件输入流
        Scanner sc = new Scanner(System.in);
        // 设置分隔符
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}
