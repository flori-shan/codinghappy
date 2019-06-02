package cn.nihility.spring.datasource;

import cn.nihility.spring.tx.service.TransferServiceRollback;
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
public class TransactionRollbackTest {

    @Autowired
    private TransferServiceRollback transferService;

    @Test(expected = RuntimeException.class)
    public void testSuccessTransferNew() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testFailTransferNew() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, true));
    }

}
