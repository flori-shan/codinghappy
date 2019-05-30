package cn.nihility.spring.tx.service;

/**
 * Spring Transaction Transfer Service
 * @author muscari
 * @date 2019-05-30 16:48
 */
public interface TransferService {

    /**
     * 账户转账
     * @param outName       要转出的帐户
     * @param inName        要转入的账户
     * @param transferAmt   转移金额
     * @return true 交易成功
     */
    boolean transfer(String outName, String inName, int transferAmt, boolean haveError);

}
