package cn.nihility.spring.tx.dao;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 记录 transfer 的操作日志
 * @author muscari
 * @date 2019-05-31 11:10
 */
@Repository
public class TransferLoggerDao {

    @Autowired
    @Qualifier("hikariJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public TransferLoggerDao() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 记录日志
     * @param operationName 操作人
     * @param status 操作状态
     * @return true 操作成功
     */
    public boolean transferLogger(final String operationName, final String status) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Transfer Logger DAO Operation Name [{}], Status [{}]", operationName, status);
        return jdbcTemplate.update("INSERT INTO bank_log (opname, status) values (?, ?)", new Object[] {operationName, status}) == 1;
    }
}
