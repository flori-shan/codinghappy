package cn.nihility.spring.tx.service;

import cn.nihility.spring.tx.dao.TransferLoggerDao;
import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transfer 日志服务
 * @author muscari
 * @date 2019-05-31 11:15
 */
@Service
public class TransferLoggerService {

    @Autowired
    private TransferLoggerDao transferLoggerDao;

    @Transactional(transactionManager = "hikariDataSourceTransactionManager", propagation = Propagation.REQUIRES_NEW,
            rollbackFor = {ArithmeticException.class})
    public void transferLoggerNew(final String logName, final String status) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Transfer Logger Service Operation Name [{}], Status [{}]", logName, status);
        transferLoggerDao.transferLogger(logName, status);
    }

    @Transactional(transactionManager = "hikariDataSourceTransactionManager", propagation = Propagation.NESTED,
            rollbackFor = {ArithmeticException.class})
    public void transferLoggerNested(final String logName, final String status) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Transfer Logger Service Operation Name [{}], Status [{}]", logName, status);
        transferLoggerDao.transferLogger(logName, status);
    }
}
