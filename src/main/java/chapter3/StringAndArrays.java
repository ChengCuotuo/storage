package chapter3;

import chapter2.Triangle;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/08
 * @Desc:  字符串和数组之间的转化
 */
public class StringAndArrays {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("xiaohong ");
        list.add("xiaoming");
        list.add("xiaojiang");
        list.add("xiaohei");

        String str = String.join(",", list);
        System.out.println(str);

        String[] names = str.split(",");
        for (String s : names) {
            System.out.println(s);
        }


        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        /**
         * public static String join(CharSequence delimiter,
         *             Iterable<? extends CharSequence> elements)
         * String 进行拼接的时候给出了限定，elements 的数据类型必须是 Iterable 的子类并继承自 CharSequence 数据类型 Integer 等
         * 数值型是没有继承自 CharSequence 的这里无法使用
         */
//        String integerStr = String.join(",", integerList);

        /**
         * rg.apache.commons 里面的方法，加的限制很少，只要是继承了 Iterable 转换，也就是说普通类也可以
         * public static String join(Iterable<?> iterable, String separator)
         * 具体实现的方法就是使用 Iterable 遍历使用 toString() 方法，存储在一个 StringBuffer 中
         */
        String string = StringUtils.join(integerList, ",");
        System.out.println(string);
        //1,2,3,4

        List<Triangle> triangles = new ArrayList<>();
        triangles.add(new Triangle(1, 2, 3));
        triangles.add(new Triangle(4, 5, 6));
        triangles.add(new Triangle(7, 8, 9));
        String triangleStr = StringUtils.join(triangles, ",");
        System.out.println(triangleStr);
        // Triangle{a=1, b=2, c=3},Triangle{a=4, b=5, c=6},Triangle{a=7, b=8, c=9}
    }
}
