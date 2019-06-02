package cn.nihility.spring.tx.service;

import cn.nihility.spring.tx.dao.TransferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author muscari
 * @date 2019-06-01 13:38
 */
@Service
public class TransferMybatisBankServiceImpl implements TransferService {

    @Autowired
    @Qualifier("transferMybatisBankDaoImpl")
    private TransferDao transferDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "hikariDataSourceTransactionManager",
            rollbackFor = {ArithmeticException.class})
    @Override
    public boolean transfer(String outName, String inName, int transferAmt, boolean haveError) {
        boolean out = transferDao.transferOut(outName, transferAmt);

        if (haveError) { System.out.println(1 / 0); }

        boolean in = transferDao.transferIn(inName, transferAmt);
        return out && in;
    }
}
