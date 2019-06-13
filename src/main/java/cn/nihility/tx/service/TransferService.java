package cn.nihility.tx.service;

/**
 * Tx Transfer Services
 * @author muscari
 * @date 2019-06-13 23:51
 */
public interface TransferService {

    /**
     * Transfer money from outName account to inName account
     * @param outName out money account
     * @param inName in money account
     * @param money transfer money
     * @param haveError transfer have error
     * @return transfer successful ?
     */
    boolean transfer(String outName, String inName, int money, boolean haveError);

}
