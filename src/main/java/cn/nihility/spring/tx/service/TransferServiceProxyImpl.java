package cn.nihility.spring.tx.service;

import cn.nihility.spring.tx.dao.TransferDao;

/**
 * spring 代理 事务管理
 * @author muscari
 * @date 2019-05-30 17:44
 */
public class TransferServiceProxyImpl implements TransferService {

    private TransferDao transferDao;

    public TransferServiceProxyImpl() {
    }

    public TransferServiceProxyImpl(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

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
