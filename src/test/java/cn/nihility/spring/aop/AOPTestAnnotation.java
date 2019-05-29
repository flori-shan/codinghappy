package cn.nihility.spring.aop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author muscari
 * @date 2019-05-30 00:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-aop-annotation.xml"})
public class AOPTestAnnotation {

    @Autowired
    private Compute compute;

    @Test
    public void testAop() {
        int add = compute.add(1, 2);
        System.out.println("add " + add);
        Assert.assertEquals(add, 3);
    }

    @Test
    public void testExceptionAop() {
        Integer division = compute.division(1, 0);
        System.out.println("division " + division);
    }

}
