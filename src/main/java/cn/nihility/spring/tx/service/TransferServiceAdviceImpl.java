package cn.nihility.spring.tx.service;

import cn.nihility.spring.tx.dao.TransferDao;

/**
 * Spring Transaction 事务增强
 * @author muscari
 * @date 2019-05-30 18:21
 */
public class TransferServiceAdviceImpl implements TransferService {

    private TransferDao transferDao;

    public void setTransferDao(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @Override
    public boolean transfer(String outName, String inName, int transferAmt, boolean haveError) {
        boolean out = transferDao.transferOut(outName, transferAmt);
        if (haveError) { System.out.println(1 / 0); }
        boolean in = transferDao.transferIn(inName, transferAmt);
        return out && in;
    }
}
