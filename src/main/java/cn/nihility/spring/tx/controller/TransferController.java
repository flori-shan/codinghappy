package cn.nihility.spring.tx.controller;

/**
 * Spring + Mybatis + Transaction Controller
 * @author muscari
 * @date 2019-06-01 14:18
 */
public interface TransferController {

    /**
     * bank 交易控制接口
     * @param outName 取款人
     * @param inName 收款人
     * @param transferAmt 交易金额
     * @param transferError 交易是否有错误产生
     * @param logError 日志记录是否产生错误
     * @param innerError 是否有内部错误
     * @return true 交易成功
     */
    boolean transfer(final String outName, final String inName, final int transferAmt,
                     final boolean transferError, final boolean logError, final boolean innerError);

}
