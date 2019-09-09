package chapter3;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/08
 * @Desc:  使用 Google Guava 进行字符串处理
 */
public class GuavaUtil {
    public static void main(String[] args) {
        // 传统 List 的操作
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        System.out.println(list);

        // 使用 Arrays 快速构造 List
        List<Integer> integers = Arrays.asList(123, 456);
        System.out.println(integers);

        // 直接初始化 List 数组
        List<Integer> integerList = Lists.newArrayList(123, 456);
        System.out.println(integerList);

        // 拆分字符串，忽略空字符串
        Iterable<String> split = Splitter.on(",") // 以 , 作为基础切分
                .trimResults() // 去除切分后的每个字符串的前后空格
                .omitEmptyStrings() // 省略空字符串
                .split("123,321,, abc");
        for (String s : split) {
            System.out.println(s);
        }

        // 驼峰命名
        String s1 = "CONSTANT_NAME";
        String s2 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s1);
        System.out.println(s2); // constantName
    }
}
