package cn.nihility.mvndrill.jdbc;

import cn.nihility.mvndrill.utils.LogLevelEnum;
import cn.nihility.mvndrill.utils.LogbackUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hikari Pool 连接池的测试类
 * Created by yzx on 2019/4/20.
 */
public class HikariPoolTest {

    private HikariDataSource dataSource;
    private Connection connection;

    @Before
    public void loadDataSource() {
        try {
            dataSource = JDBCUtils.getHikariDataSource("oracle");
            LogbackUtil.logger(this.getClass(), LogLevelEnum.DEBUG, "Get Hikari data source instance = {}", dataSource);
            Assert.assertNotNull(dataSource);

            connection = dataSource.getConnection();
            LogbackUtil.logger(this.getClass(), LogLevelEnum.DEBUG, "Get Hikari data source connection instance = {}", connection);
            Assert.assertNotNull(connection);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void releaseResources() {
        JDBCUtils.release(connection, null, null);
        releaseHikariDataSource();
    }

    private void releaseHikariDataSource() {
        if (null != dataSource) { dataSource.close(); }
    }

    @Test
    public void testGetDataSource() {
        LogbackUtil.logger(HikariPoolTest.class, LogLevelEnum.DEBUG, "test hikari data source and connection.");
    }

    @Test
    public void testSQLQuery() {

    }

}
