package cn.nihility.mvndrill.jdbc;

import cn.nihility.mvndrill.utils.LogLevelEnum;
import cn.nihility.mvndrill.utils.LogbackUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.UUID;

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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String select = "SELECT id, name, age, gender, email from mybatis_test WHERE test_address like ?";

        try {
            connection = dataSource.getConnection();

            statement = connection.prepareStatement(select);
            statement.setString(1, "%Hikari%");
            resultSet = statement.executeQuery();
            LogbackUtil.loggerInfo(JDBCTest.class, "======================");
            while (resultSet.next()) {
                LogbackUtil.loggerInfo(JDBCTest.class, "id={}, name={}, age={}, gender={}, email={}",
                        resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"),
                        resultSet.getString("gender"), resultSet.getString("email"));
            }
        } catch (SQLException e) {
            LogbackUtil.loggerError(JDBCTest.class, "Execute error = {}", e);
        } finally {
            JDBCUtils.release(connection, statement, resultSet);
        }
    }

    @Test
    public void testBatchInsert() {

        long start = System.currentTimeMillis();
        long partial = System.currentTimeMillis();

        HikariDataSource hikariDataSource = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Savepoint savepoint = null;
        int saveCount = 10;

		String insert = "insert into mybatis_test(id, name, age, gender, email, test_address) values (MYBATISTEST_SEQUENCE.Nextval,?,?,?,?,?)";
//        String insert = "insert into mybatis_test(name, age, gender, email, test_address) values (?,?,?,?,?)";
        try {
//			connection = JDBCUtils.getConnection("oracle");

            connection = dataSource.getConnection();

            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint();
            statement = connection.prepareStatement(insert);

            Random random = new Random(System.currentTimeMillis());
            String uuid;
            int index;
            for (index = 1; index <= saveCount; index++) {
                uuid = UUID.randomUUID().toString().substring(0, 8);
                statement.setString(1, uuid);
                statement.setInt(2, (random.nextInt(10) + 10));
                statement.setString(3, (index % 3 == 0 ? "F" : "M"));
                statement.setString(4, uuid+"@test.com");
                statement.setString(5, uuid+"Druid");
                statement.addBatch();

                if (index % 1000 == 0 || index == saveCount) {
                    statement.executeBatch();
                    connection.commit();
                    statement.clearBatch();
                    LogbackUtil.loggerInfo(this.getClass(), "Batch execute duration time = {}", (System.currentTimeMillis() -partial));
                    partial = System.currentTimeMillis();
                }
            }

            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            LogbackUtil.loggerError(JDBCTest.class, "Execute error = {}", e);
            try {
                if (null != savepoint) {
                    connection.rollback(savepoint);
                } else {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                LogbackUtil.loggerError(JDBCTest.class, "Execute rollback error = {}", e1);
            }
        } finally {
            JDBCUtils.release(connection, statement, resultSet);
        }
        LogbackUtil.loggerInfo(this.getClass(), "Execute all statement duration = {}", (System.currentTimeMillis() - start));
    }

}
