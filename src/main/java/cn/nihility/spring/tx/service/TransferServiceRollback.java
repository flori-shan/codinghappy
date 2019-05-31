package cn.nihility.spring.tx.service;

import cn.nihility.spring.tx.dao.TransferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author muscari
 * @date 2019-05-31 15:28
 */
@Service
public class TransferServiceRollback {

    @Autowired
    private TransferDao transferDao;

    @Transactional(transactionManager = "hikariDataSourceTransactionManager", propagation = Propagation.REQUIRED/*, rollbackFor = {ArithmeticException.class}*/)
    public boolean transfer(String outName, String inName, int transferAmt, boolean haveError) {
        boolean out = transferDao.transferOut(outName, transferAmt);
        if (haveError) { System.out.println(1 / 0); }
        try {
            throw new RuntimeException();
        } catch (ArithmeticException ex) {
            System.out.println("Exception " + ex.getMessage());
            ex.printStackTrace();
        }
        boolean in = transferDao.transferIn(inName, transferAmt);
        return out && in;
    }
}
