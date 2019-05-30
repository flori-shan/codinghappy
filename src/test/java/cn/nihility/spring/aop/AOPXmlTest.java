package cn.nihility.spring.aop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring AOP Test
 * @author muscari
 * @date 2019-05-29 23:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-aop.xml"})
public class AOPXmlTest {

    @Autowired
    private Compute compute;

    @Test
    public void testAOP() {
        int add = compute.add(1, 2);
        System.out.println("add " + add);
        Assert.assertEquals(add, 3);
    }

    @Test(expected = ArithmeticException.class)
    public void testExceptionAop() {
        Integer division = compute.division(1, 0);
        System.out.println("division " + division);
    }

}
