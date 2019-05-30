package cn.nihility.spring.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author muscari
 * @date 2019-05-30 16:07
 */
@Repository
public class TransferDaoAnnotationImpl implements TransferDao {

    @Autowired
    @Qualifier("hikariJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public TransferDaoAnnotationImpl() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TransferDaoAnnotationImpl(JdbcTemplate jdbcTemplate) {
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
