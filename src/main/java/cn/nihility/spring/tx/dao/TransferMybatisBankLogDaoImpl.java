package cn.nihility.spring.tx.dao;

import cn.nihility.spring.mybatis.dao.BankLogDao;
import cn.nihility.spring.mybatis.entity.BankLog;
import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author muscari
 * @date 2019-06-01 14:03
 */
@Repository
public class TransferMybatisBankLogDaoImpl implements TransferLogDao {

    @Autowired
    private BankLogDao bankLogDao;

    @Override
    public boolean transferLogger(final String operationTag, final String status) {
        BankLog bankLog = new BankLog();
        bankLog.setName(operationTag);
        bankLog.setStatus(status);
        boolean insert = bankLogDao.insertByObj(bankLog);
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Generate Primary Key : [{}]", bankLog.getId());
        return insert;
    }
}
