package cn.nihility.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author muscari
 * @date 2019-06-13 23:42
 */
@Repository
public class TransferDaoImpl implements TransferDao {

    @Autowired
    @Qualifier("hikariJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean transferOut(final String outName, final int money) {
        return jdbcTemplate.update("UPDATE BANK SET AMT = AMT - ?, DEAL_DATE = NOW() WHERE NAME = ?",
                new Object[] {money, outName}) == 1;
    }

    @Override
    public boolean transferIn(String inName, int money) {
        return jdbcTemplate.update("UPDATE BANK SET AMT = AMT + ?, DEAL_DATE = NOW() WHERE NAME = ?",
                new Object[] {money, inName}) == 1;
    }
}
