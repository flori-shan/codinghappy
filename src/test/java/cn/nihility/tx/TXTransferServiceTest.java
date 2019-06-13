package cn.nihility.tx;

import cn.nihility.tx.service.TransferService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author muscari
 * @date 2019-06-13 23:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TXTransferServiceTest {

    @Autowired
    @Qualifier("transferServiceImpl")
    private TransferService transferService;

    @Resource
    private TransferService TTTransferServiceImpl;

    @Test
    public void testSuccessfulTransfer() {
        Assert.assertTrue(transferService.transfer("小红", "小梅", 100, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testFailTransfer() {
        Assert.assertFalse(transferService.transfer("小红", "小梅", 100, true));
    }

    @Test
    public void testSuccessfulTTransfer() {
        Assert.assertTrue(TTTransferServiceImpl.transfer("小红", "小梅", 100, false));
    }

    @Test(expected = ArithmeticException.class)
    public void testFailTTransfer() {
        Assert.assertFalse(TTTransferServiceImpl.transfer("小红", "小梅", 100, true));
    }

}
