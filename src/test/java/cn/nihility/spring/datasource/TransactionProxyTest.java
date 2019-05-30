package cn.nihility.spring.datasource;

import cn.nihility.spring.tx.service.TransferService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author muscari
 * @date 2019-05-30 18:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-datasource.xml", "classpath:spring/spring-tx-proxy.xml"})
public class TransactionProxyTest {

    /* 此处使用的是代理对象，不然就做不到事务管理 */
    @Resource(name = "proxyTransactionService")
    private TransferService transferService;

    @Test
    public void testProxySuccessService() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testProxyFailService() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, true));
    }

}
