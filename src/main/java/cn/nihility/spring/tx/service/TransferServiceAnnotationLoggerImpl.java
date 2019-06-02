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
public class TransferServiceAnnotationLoggerImpl implements TransferWithLoggerService {

    @Autowired
    @Qualifier("transferDaoAnnotationImpl")
    private TransferDao transferDao;
    @Autowired
    private TransferLoggerService transferLoggerService;

    @Transactional(transactionManager = "hikariDataSourceTransactionManager",
            propagation = Propagation.REQUIRED, rollbackFor = {ArithmeticException.class})
    @Override
    public boolean transfer(String outName, String inName, int transferAmt, boolean haveError, boolean newOrNested) {
        boolean out = transferDao.transferOut(outName, transferAmt);

        String status = outName + "->" + inName + ":" + transferAmt + " | " + (haveError ? "Will Error" : "Not Error");
        String name = outName + ":" + inName;
        if (newOrNested) {
            transferLoggerService.transferLoggerNew(name, status);
        } else {
            transferLoggerService.transferLoggerNested(name, status);
        }

        if (haveError) { System.out.println(1 / 0); }
        boolean in = transferDao.transferIn(inName, transferAmt);
        return out && in;
    }

}
