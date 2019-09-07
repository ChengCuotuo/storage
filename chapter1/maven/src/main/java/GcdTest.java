import org.apache.commons.math3.util.ArithmeticUtils;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/07
 * @Desc: 借助第三方工具计算最大公约数，使用的 Apache Commons Math
 */
public class GcdTest {
    public static void main(String[] args) {
        int a = ArithmeticUtils.gcd(361, 285);
        System.out.println(a);
    }
}
