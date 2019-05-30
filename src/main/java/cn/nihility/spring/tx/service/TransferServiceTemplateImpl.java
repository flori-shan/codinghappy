package cn.nihility.spring.tx.service;

import cn.nihility.spring.tx.dao.TransferDao;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Spring Transaction Transfer Service impl, TransactionTemplate manage transaction
 * @author muscari
 * @date 2019-05-30 16:52
 */
public class TransferServiceTemplateImpl implements TransferService {

    private TransferDao transferDao;
    private TransactionTemplate transactionTemplate;

    public TransferServiceTemplateImpl() {
    }

    public void setTransferDao(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public boolean transfer(final String outName, final String inName, final int transferAmt, boolean haveError) {
        return transactionTemplate.execute((status) -> {
            boolean out = transferDao.transferOut(outName, transferAmt);
            if (haveError) { System.out.println(1 / 0); }
            boolean in = transferDao.transferIn(inName, transferAmt);
            return out && in;
        });
    }
}
