package cn.nihility.spring.tx.controller;

import cn.nihility.spring.tx.service.TransferLogService;
import cn.nihility.spring.tx.service.TransferService;
import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author muscari
 * @date 2019-06-01 14:20
 */
@Controller
public class TransferMybatisControllerImpl implements TransferController {

    @Resource
    private TransferService transferMybatisBankServiceImpl;
    @Autowired
    private TransferLogService transferLogService;

    @Transactional(propagation = Propagation.REQUIRED, transactionManager = "hikariDataSourceTransactionManager")
    @Override
    public boolean transfer(final String outName, final String inName, final int transferAmt,
                            final boolean transferError, final boolean loggerError, final boolean innerError) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Transfer Controller Start");

        /* 交易 */
        boolean transfer = false;
        try {
            transfer = transferMybatisBankServiceImpl.transfer(outName, inName, transferAmt, transferError);
        } catch (Exception ex) {
            System.out.println("Transfer Exception " + ex.getMessage());
        }

        /* 记录日志 */
        boolean logger = false;
        try {
            String status = outName + "->" + inName + ":" + transferAmt + " | " + (loggerError || transferError ? "Will Error" : "Not Error");
            String name = outName + ":" + inName;
            logger = transferLogService.transferLogger(name, status, loggerError);
        } catch (Exception ex) {
            System.out.println("Logger Exception " + ex.getMessage());
        }

        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Transfer Controller End");

        if (innerError) { System.out.println( 1 / 0); }
        return transfer && logger;
    }
}
