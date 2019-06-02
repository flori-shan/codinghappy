package cn.nihility.spring.mybatis.dao;

/**
 * Bank 交易的 dao 接口
 * @author muscari
 * @date 2019-06-01 13:23
 */
public interface BankDao {

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
