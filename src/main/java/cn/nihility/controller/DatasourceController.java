package cn.nihility.controller;

import cn.nihility.utils.LogbackUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * datasource test controller
 * @author muscari
 * @date 2019-06-14 10:24
 */
@RestController
@RequestMapping(value = "/tx")
public class DatasourceController {

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

    @Resource
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

    @GetMapping(path = {"/datasource"})
    public Map<String, Object> getData() {
        Map<String, Object> result = new HashMap<>();

        result.put("hikariDataSource", hikariDataSource.toString());
        result.put("hikariJdbcTemplate", hikariJdbcTemplate.toString());
        result.put("hikariDataSourceTransactionManager", hikariDataSourceTransactionManager.toString());
        result.put("hikariDataSourceTransactionManager", hikariTransactionTemplate.toString());

        result.put("comboPooledDataSource", comboPooledDataSource.toString());
        result.put("comboJdbcTemplate", comboJdbcTemplate.toString());
        result.put("comboDataSourceTransactionManager", comboDataSourceTransactionManager.toString());
        result.put("comboTransactionTemplate", comboTransactionTemplate.toString());

        result.put("druidDataSource", druidDataSource.toString());
        result.put("druidJdbcTemplate", druidJdbcTemplate.toString());
        result.put("druidDataSourceTransactionManager", druidDataSourceTransactionManager.toString());
        result.put("druidTransactionTemplate", druidTransactionTemplate.toString());

        result.put("basicDataSource", basicDataSource.toString());
        result.put("basicJdbcTemplate", basicJdbcTemplate.toString());
        result.put("basicDataSourceTransactionManager", basicDataSourceTransactionManager.toString());
        result.put("basicTransactionTemplate", basicTransactionTemplate.toString());

        return result;
    }

    @GetMapping(path = {"/query"})
    public Map<String, Object> getQueryData() throws SQLException {
        Map<String, Object> result = new HashMap<>();

        result.put("hikariDataSource", getConnectQueryResult(hikariDataSource.getConnection(), "Hikari"));
        result.put("comboPooledDataSource", getConnectQueryResult(comboPooledDataSource.getConnection(), "Combo"));
        result.put("druidDataSource", getConnectQueryResult(druidDataSource.getConnection(), "Druid"));
        result.put("basicDataSource", getConnectQueryResult(basicDataSource.getConnection(), "Basic"));

        return result;
    }

    public String getConnectQueryResult(Connection connection, String tag) {
        LogbackUtil.logger(getClass(), "============================= {} Start =============================", tag);
        String sql = "SELECT ID, NAME FROM test WHERE ID = ?";
        if (null == connection) { System.out.println("ERROR CONNECTION IS NULL"); }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String resultStr = "";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LogbackUtil.logger(getClass(), "[{}] | Query Result ID = [{}] : name = [{}]", tag, resultSet.getInt("id"), resultSet.getString("name"));
                resultStr = tag + " | Query Result ID = " + resultSet.getInt("id") + " : name = " + resultSet.getString("name");
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
        LogbackUtil.logger(getClass(), "============================= {} End =============================", tag);
        return resultStr;
    }


}
