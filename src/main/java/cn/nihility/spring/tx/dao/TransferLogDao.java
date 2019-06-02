package cn.nihility.spring.tx.dao;

/**
 * Bank 交易的日志记录 Dao
 * @author muscari
 * @date 2019-06-01 14:08
 */
public interface TransferLogDao {
    /**
     * 记录日志
     * @param operationTag 操作标记
     * @param status 操作状态
     * @return true 操作成功
     */
    boolean transferLogger(final String operationTag, final String status);
}
