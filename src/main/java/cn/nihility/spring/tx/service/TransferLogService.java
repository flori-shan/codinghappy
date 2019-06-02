package cn.nihility.spring.tx.service;

/**
 * Bank 交易日志的 Service
 * @author muscari
 * @date 2019-06-01 14:14
 */
public interface TransferLogService {
    /**
     * 记录日志
     * @param operationTag 操作标记
     * @param status 操作状态
     * @param haveError 此次记录是否有错误
     * @return true 操作成功
     */
    boolean transferLogger(final String operationTag, final String status, boolean haveError);
}
