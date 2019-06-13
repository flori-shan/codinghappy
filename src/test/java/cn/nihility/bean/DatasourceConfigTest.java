package cn.nihility.bean;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Spring boot Configuration Datasource so on test.
 * @author muscari
 * @date 2019-06-13 16:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceConfigTest {

    @Autowired
    @Qualifier("hikariDataSource")
    private HikariDataSource hikariDataSource;
    @Autowired
    @Qualifier("hikariJdbcTemplate")
    private JdbcTemplate hikariJdbcTemplate;
    @Autowired
    @Qualifier("hikariDataSourceTransactionManager")
    private DataSourceTransactionManager hikariDataSourceTransactionManager;
    @Autowired
    @Qualifier("hikariTransactionTemplate")
    private TransactionTemplate hikariTransactionTemplate;

    @Resource(name = "comboPooledDataSource")
    private ComboPooledDataSource comboPooledDataSource;
    @Resource
    private JdbcTemplate comboJdbcTemplate;
    @Resource
    private DataSourceTransactionManager comboDataSourceTransactionManager;
    @Resource
    private TransactionTemplate comboTransactionTemplate;

    @Resource
    private DruidDataSource druidDataSource;
    @Resource
    private JdbcTemplate druidJdbcTemplate;
    @Resource
    private DataSourceTransactionManager druidDataSourceTransactionManager;
    @Resource
    private TransactionTemplate druidTransactionTemplate;

    @Resource
    private BasicDataSource basicDataSource;
    @Resource
    private JdbcTemplate basicJdbcTemplate;
    @Resource
    private DataSourceTransactionManager basicDataSourceTransactionManager;
    @Resource
    private TransactionTemplate basicTransactionTemplate;

    @Test
    public void testDataSourceConfig() throws SQLException {
        System.out.println("=================== Hikari Start =======================");
        System.out.println(hikariDataSource);
        System.out.println(hikariJdbcTemplate);
        System.out.println(hikariDataSourceTransactionManager);
        System.out.println(hikariTransactionTemplate);
        System.out.println("=================== Hikari End =======================");
        System.out.println("=================== c3p0 Start =======================");
        System.out.println(comboPooledDataSource);
        System.out.println(comboJdbcTemplate);
        System.out.println(comboDataSourceTransactionManager);
        System.out.println(comboTransactionTemplate);
        System.out.println("=================== c3p0 End =======================");
        System.out.println("=================== Druid Start =======================");
        System.out.println(druidDataSource);
        System.out.println(druidJdbcTemplate);
        System.out.println(druidDataSourceTransactionManager);
        System.out.println(druidTransactionTemplate);
        System.out.println("=================== Druid End =======================");
        System.out.println("=================== dbcp2 Start =======================");
        System.out.println(basicDataSource);
        System.out.println(basicJdbcTemplate);
        System.out.println(basicDataSourceTransactionManager);
        System.out.println(basicTransactionTemplate);
        System.out.println("=================== dbcp2 End =======================");

        Assert.assertNotNull(hikariDataSource);
        Assert.assertNotNull(hikariJdbcTemplate);
        Assert.assertNotNull(hikariDataSourceTransactionManager);
        Assert.assertNotNull(hikariTransactionTemplate);
        Assert.assertNotNull(comboPooledDataSource);
        Assert.assertNotNull(comboJdbcTemplate);
        Assert.assertNotNull(comboDataSourceTransactionManager);
        Assert.assertNotNull(comboTransactionTemplate);
        Assert.assertNotNull(druidDataSource);
        Assert.assertNotNull(druidJdbcTemplate);
        Assert.assertNotNull(druidDataSourceTransactionManager);
        Assert.assertNotNull(druidTransactionTemplate);
        Assert.assertNotNull(basicDataSource);
        Assert.assertNotNull(basicJdbcTemplate);
        Assert.assertNotNull(basicDataSourceTransactionManager);
        Assert.assertNotNull(basicTransactionTemplate);

        testConnectSelect(hikariDataSource.getConnection(), "Hikari");
        testConnectSelect(comboPooledDataSource.getConnection(), "C3P0");
        testConnectSelect(druidDataSource.getConnection(), "Druid");
        testConnectSelect(basicDataSource.getConnection(), "DBCP2");

    }


    public void testConnectSelect(Connection connection, String tag) {
        System.out.println("============================= " + tag + " Start =============================");
        String sql = "SELECT ID, NAME FROM test WHERE ID = ?";
        if (null == connection) { System.out.println("ERROR CONNECTION IS NULL"); }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(tag + " | Query Result ID = " + resultSet.getInt("id") + " : name = " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("============================= " + tag + " End =============================");
    }

}
