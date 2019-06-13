package cn.nihility.tx.dao;

/**
 * TX Test Transfer Dao Interface
 * @author muscari
 * @date 2019-06-13 23:38
 */
public interface TransferDao {

    /**
     * Transfer out money from outName bank account
     * @param outName bank account name
     * @param money transfer money
     * @return transfer success ?
     */
    boolean transferOut(String outName, int money);

    /**
     * Transfer in money to inName bank account
     * @param inName transfer in bank account name
     * @param money transfer in money
     * @return transfer in success ?
     */
    boolean transferIn(String inName, int money);
}
