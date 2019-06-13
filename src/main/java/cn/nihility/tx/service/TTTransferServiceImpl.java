package cn.nihility.tx.service;

import cn.nihility.tx.dao.TransferDao;
import cn.nihility.utils.LogbackUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author muscari
 * @date 2019-06-14 00:04
 */
@Service
public class TTTransferServiceImpl implements TransferService {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private TransferDao transferDao;

    @Override
    public boolean transfer(final String outName, final String inName, final int money, boolean haveError) {
       return transactionTemplate.execute((status) -> {
           LogbackUtil.logger(getClass(), "transfer begin, [{}] -> [{}] money [{}], have error [{}]",
                   outName, inName, money, haveError);
           boolean out = transferDao.transferOut(outName, money);
           if (haveError) { System.out.println(1 / 0); }
           boolean in = transferDao.transferIn(inName, money);
           return in && out;
        });
    }
}
