package cn.nihility.spring.datasource;

import cn.nihility.spring.tx.service.TransferService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author muscari
 * @date 2019-05-30 18:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-datasource.xml", "classpath:spring/spring-tx-advice.xml"})
public class TransactionAdviceTest {

    @Autowired
    private TransferService transferService;

    @Test
    public void testAdviceSuccessService() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testAdviceFailService() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, true));
    }
}
