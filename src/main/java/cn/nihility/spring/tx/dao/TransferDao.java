package cn.nihility.spring.tx.dao;

/**
 * Spring Transaction Dao
 * @author muscari
 * @date 2019-05-30 15:59
 */
public interface TransferDao {
    /**
     * 转入金额
     * @param name 操作人
     * @param amt 操作金额
     * @return true 操作成功
     */
    boolean transferIn(String name, int amt);

    /**
     * 转出金额
     * @param name 操作人
     * @param amt 操作金额
     * @return true 操作成功
     */
    boolean transferOut(String name, int amt);


}
