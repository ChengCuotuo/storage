package chapter2;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/07
 * @Desc: 三角形
 */
public class Triangle {
    private int a;
    private int b;
    private int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static boolean judge(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return false;
        }

        if (a + b <= c) {
            return  false;
        }

        if (b + c <= a) {
            return false;
        }

        if (a + c <= b) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
