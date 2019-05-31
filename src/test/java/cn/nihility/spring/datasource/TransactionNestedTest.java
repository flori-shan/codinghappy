package cn.nihility.spring.datasource;

import cn.nihility.spring.tx.service.TransferWithLoggerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 内部事务测试
 * @author muscari
 * @date 2019-05-31 11:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-datasource.xml", "classpath:spring/spring-tx-annotation.xml"})
public class TransactionNestedTest {

    @Autowired
    private TransferWithLoggerService transferService;

    @Test
    public void testSuccessTransferNew() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, false, true));
    }


    @Test
    public void testSuccessTransferNested() {
        Assert.assertTrue(transferService.transfer("小梅", "小红", 100, false, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testFailTransferNew() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, true, true));
    }

    @Test(expected = ArithmeticException.class)
    public void testFailTransferNested() {
        Assert.assertTrue(transferService.transfer("小梅", "小红", 100, true, false));
    }

}
