package cn.nihility.spring.tx.service;

import cn.nihility.spring.tx.dao.TransferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring 事务注解
 * @author muscari
 * @date 2019-05-31 00:43
 */
@Service
public class TransferServiceAnnotationImpl implements TransferService {

    @Autowired
    @Qualifier("transferDaoAnnotationImpl")
    private TransferDao transferDao;

    @Transactional(transactionManager = "hikariDataSourceTransactionManager",
            propagation = Propagation.REQUIRED, rollbackFor = {ArithmeticException.class})
    @Override
    public boolean transfer(String outName, String inName, int transferAmt, boolean haveError) {
        boolean out = transferDao.transferOut(outName, transferAmt);
        if (haveError) { System.out.println(1 / 0); }
        boolean in = transferDao.transferIn(inName, transferAmt);
        return out && in;
    }
}
