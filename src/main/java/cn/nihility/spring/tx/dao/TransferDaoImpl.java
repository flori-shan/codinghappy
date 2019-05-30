package cn.nihility.spring.tx.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author muscari
 * @date 2019-05-30 16:07
 */
public class TransferDaoImpl implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public TransferDaoImpl() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TransferDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean transferIn(String name, int amt) {
        return jdbcTemplate.update("UPDATE BANK SET AMT = AMT + ?, DEAL_DATE = NOW() WHERE NAME = ?", new Object[] {amt, name}) == 1;
    }

    @Override
    public boolean transferOut(String name, int amt) {
        return jdbcTemplate.update("UPDATE BANK SET AMT = AMT - ?, DEAL_DATE = NOW() where NAME = ?", new Object[] {amt, name}) == 1;
    }
}
