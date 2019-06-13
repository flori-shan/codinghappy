package cn.nihility.aop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * aop test
 * @author muscari
 * @date 2019-06-13 23:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculateAspectTest {

    @Autowired
    private Calculate calculate;

    @Test
    public void testAdd() {
        Assert.assertEquals(3, calculate.add(1, 2));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivision() {
        calculate.division(10, 0);
    }

}
