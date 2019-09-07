package chapter;

import chapter2.Triangle;
import org.junit.Test;

// import static 在 java1.5 出现测导入某一个类的所有静态方法
// import 是导入一个类或者几个类
import static org.junit.Assert.assertEquals;

public class TriangleTest {
    @Test
    public void ifTriangle() {
        // 断言测试，结果和预测是否相同，因为使用了 import static 就不需要使用下面的通过类名找到其对应的静态方法
        // 但是为了代码的可读性，还是觉得下面的方式更加的明显
        assertEquals(false, Triangle.judge(1, 2, 3));
        //Assert.assertEquals(false, Triangle.judge(1, 2, 3));
    }
}
