package cn.nihility.tx.service;

import cn.nihility.tx.dao.TransferDao;
import cn.nihility.utils.LogbackUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author muscari
 * @date 2019-06-13 23:54
 */
@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferDao transferDao;

    @Override
    @Transactional(transactionManager = "hikariDataSourceTransactionManager",
            rollbackFor = {ArithmeticException.class})
    public boolean transfer(String outName, String inName, int money, boolean haveError) {
        LogbackUtil.logger(getClass(), "transfer begin, [{}] -> [{}] money [{}], have error [{}]",
                outName, inName, money, haveError);
        boolean out = transferDao.transferOut(outName, money);
        if (haveError) { System.out.println(1 / 0); }
        boolean in = transferDao.transferIn(inName, money);
        return in && out;
    }
}
