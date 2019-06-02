package cn.nihility.spring.mybatis;

import cn.nihility.spring.tx.controller.TransferController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring Transaction With Mybatis Test
 * @author muscari
 * @date 2019-06-01 13:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-datasource.xml", "classpath:spring/spring-mybatis.xml"})
public class MybatisTransferTest {

    @Autowired
    private TransferController transferController;

    @Test
    public void testSuccessTransfer() {
        Assert.assertTrue(transferController.transfer("小红", "小梅", 100, false, false, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testOkTransferAndErrorLogger() {
        Assert.assertTrue(transferController.transfer("小梅", "小红", 200, false, true, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testFailTransferAndOkLogger() {
        Assert.assertTrue(transferController.transfer("小梅", "小红", 300, true, false, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testInnerError() {
        Assert.assertTrue(transferController.transfer("小梅", "小红", 400, false, false, true));
    }


}
