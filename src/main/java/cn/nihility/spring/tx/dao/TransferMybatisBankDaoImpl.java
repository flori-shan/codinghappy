package cn.nihility.spring.tx.dao;

import cn.nihility.spring.mybatis.dao.BankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author muscari
 * @date 2019-06-01 14:01
 */
@Repository
public class TransferMybatisBankDaoImpl implements TransferDao {

    @Autowired
    private BankDao bankDao;

    @Override
    public boolean transferIn(String name, int amt) {
        return bankDao.transferIn(name, amt);
    }

    @Override
    public boolean transferOut(String name, int amt) {
        return bankDao.transferOut(name, amt);
    }
}
