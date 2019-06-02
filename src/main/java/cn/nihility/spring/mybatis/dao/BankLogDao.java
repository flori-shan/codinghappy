package cn.nihility.spring.mybatis.dao;

import cn.nihility.spring.mybatis.entity.BankLog;

/**
 * Bank transfer transaction logger dao interface
 * @author muscari
 * @date 2019-06-01 13:24
 */
public interface BankLogDao {
    /**
     * 插入 bank 交易日志
     * @param bankLog 交易日志的实体对象
     * @return true 插入成功
     */
    boolean insertByObj(BankLog bankLog);
}
