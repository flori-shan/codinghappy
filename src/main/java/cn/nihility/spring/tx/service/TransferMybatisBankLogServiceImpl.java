package cn.nihility.spring.tx.service;

import cn.nihility.spring.tx.dao.TransferLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author muscari
 * @date 2019-06-01 14:13
 */
@Service
public class TransferMybatisBankLogServiceImpl implements TransferLogService {

    @Autowired
    private TransferLogDao transferLogDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "hikariDataSourceTransactionManager",
            rollbackFor = {ArithmeticException.class})
    @Override
    public boolean transferLogger(String operationTag, String status, boolean haveError) {
        if (haveError) { System.out.println( 1 / 0 ); }
        return transferLogDao.transferLogger(operationTag, status);
    }

}
