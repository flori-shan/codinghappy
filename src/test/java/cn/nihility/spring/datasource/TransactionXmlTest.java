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
 * @date 2019-05-30 17:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-datasource.xml", "classpath:spring/spring-tx-xml.xml"})
public class TransactionXmlTest {

    @Autowired
    private TransferService transferService;

    @Test
    public void testSuccessTransfer() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testFailTransfer() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, true));
    }

}
