package cn.nihility.bean.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.beans.PropertyVetoException;

/**
 * Datasource Configuration Bean
 * @author muscari
 * @date 2019-06-13 14:21
 */
@Configuration
public class DatasourceConfigurationBean {

    /* ======================== Hikari ======================== */

    @Bean(name = "hikariDataSource", destroyMethod = "close")
    @Autowired
    @Primary
    public HikariDataSource hikariDataSource(SelfHikariConfig config) {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setDriverClassName(config.getDriverClassName());
        hikariDataSource.setJdbcUrl(config.getJdbcUrl());
        hikariDataSource.setUsername(config.getUsername());
        hikariDataSource.setPassword(config.getPassword());
        hikariDataSource.setMaxLifetime(config.getMaxLifetime());
        hikariDataSource.setMaximumPoolSize(config.getMaximumPoolSize());
        hikariDataSource.setMinimumIdle(config.getMinimumIdle());
        hikariDataSource.setIdleTimeout(config.getIdleTimeout());
        hikariDataSource.setConnectionTimeout(config.getConnectionTimeout());

        return hikariDataSource;
    }

    @Bean(name = "hikariJdbcTemplate")
    @Autowired
    @Qualifier("hikariDataSource")
    @Primary
    public JdbcTemplate hikariJdbcTemplate(HikariDataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }

    @Bean(name = "hikariDataSourceTransactionManager")
    @Autowired
    @Qualifier("hikariDataSource")
    @Primary
    public DataSourceTransactionManager hikariDataSourceTransactionManager(HikariDataSource hikariDataSource) {
        return new DataSourceTransactionManager(hikariDataSource);
    }

    @Bean(name = "hikariTransactionTemplate")
    @Autowired
    @Qualifier("hikariDataSourceTransactionManager")
    @Primary
    public TransactionTemplate hikariTransactionTemplate(DataSourceTransactionManager hikariDataSourceTransactionManager) {
        return new TransactionTemplate(hikariDataSourceTransactionManager);
    }

    /* ======================== C3P0 ======================== */

    @Bean(name = {"comboPooledDataSource"}, destroyMethod = "close")
    @Autowired
    public ComboPooledDataSource  comboPooledDataSource(SelfC3P0Config config) throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setDriverClass(config.getDriverClass());
        comboPooledDataSource.setJdbcUrl(config.getJdbcUrl());
        comboPooledDataSource.setUser(config.getUser());
        comboPooledDataSource.setPassword(config.getPassword());
        comboPooledDataSource.setInitialPoolSize(config.getInitialPoolSize());
        comboPooledDataSource.setMinPoolSize(config.getMinPoolSize());
        comboPooledDataSource.setMaxPoolSize(config.getMaxPoolSize());
        comboPooledDataSource.setAcquireIncrement(config.getAcquireIncrement());
        comboPooledDataSource.setCheckoutTimeout(config.getCheckoutTimeout());
        comboPooledDataSource.setIdleConnectionTestPeriod(config.getIdleConnectionTestPeriod());
        comboPooledDataSource.setMaxIdleTime(config.getMaxIdleTime());
        comboPooledDataSource.setTestConnectionOnCheckin(config.isTestConnectionOnCheckin());
        comboPooledDataSource.setTestConnectionOnCheckout(config.isTestConnectionOnCheckout());
        comboPooledDataSource.setNumHelperThreads(config.getNumHelperThreads());

        return comboPooledDataSource;
    }

    @Bean(name = "comboJdbcTemplate")
    @Autowired
    @Qualifier("comboPooledDataSource")
    public JdbcTemplate comboJdbcTemplate(ComboPooledDataSource comboPooledDataSource) {
        return new JdbcTemplate(comboPooledDataSource);
    }

    @Bean(name = "comboDataSourceTransactionManager")
    @Autowired
    @Qualifier("comboPooledDataSource")
    public DataSourceTransactionManager comboDataSourceTransactionManager(ComboPooledDataSource comboPooledDataSource) {
        return new DataSourceTransactionManager(comboPooledDataSource);
    }

    @Bean(name = "comboTransactionTemplate")
    @Autowired
    @Qualifier("comboDataSourceTransactionManager")
    public TransactionTemplate comboTransactionTemplate(DataSourceTransactionManager comboDataSourceTransactionManager) {
        return new TransactionTemplate(comboDataSourceTransactionManager);
    }

    /* ======================== Druid ======================== */

    @Bean(name = "druidDataSource", destroyMethod = "close")
    @Autowired
    public DruidDataSource druidDataSource(SelfDruidConfig config) {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setDriverClassName(config.getDriverClassName());
        druidDataSource.setUrl(config.getUrl());
        druidDataSource.setPassword(config.getPassword());
        druidDataSource.setUsername(config.getUsername());
        druidDataSource.setInitialSize(config.getInitialSize());
        druidDataSource.setMinIdle(config.getMinIdle());
        druidDataSource.setMaxActive(config.getMaxActive());
        druidDataSource.setMaxWait(config.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setTestOnBorrow(config.isTestOnBorrow());
        druidDataSource.setTestOnReturn(config.isTestOnReturn());
        druidDataSource.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis());
        druidDataSource.setTestWhileIdle(config.isTestWhileIdle());

        return druidDataSource;
    }

    @Bean(name = "druidJdbcTemplate")
    @Autowired
    @Qualifier("druidDataSource")
    public JdbcTemplate druidJdbcTemplate(DruidDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "druidDataSourceTransactionManager")
    @Autowired
    @Qualifier("druidDataSource")
    public DataSourceTransactionManager druidDataSourceTransactionManager(DruidDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "druidTransactionTemplate")
    @Autowired
    @Qualifier("druidDataSourceTransactionManager")
    public TransactionTemplate druidTransactionTemplate(DataSourceTransactionManager druidDataSourceTransactionManager) {
        return new TransactionTemplate(druidDataSourceTransactionManager);
    }

    /* ======================== DBCP2 ======================== */

    @Bean(name = "basicDataSource", destroyMethod = "close")
    @Autowired
    public BasicDataSource basicDataSource(SelfDbcp2Config config) {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(config.getDriverClassName());
        basicDataSource.setUrl(config.getUrl());
        basicDataSource.setPassword(config.getPassword());
        basicDataSource.setUsername(config.getUsername());
        basicDataSource.setInitialSize(config.getInitialSize());
        basicDataSource.setMaxTotal(config.getMaxTotal());
        basicDataSource.setMaxIdle(config.getMaxIdle());
        basicDataSource.setMinIdle(config.getMinIdle());
        basicDataSource.setMaxWaitMillis(config.getMaxWaitMillis());
        basicDataSource.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
        basicDataSource.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis());
        basicDataSource.setTestOnBorrow(config.isTestOnBorrow());
        basicDataSource.setTestOnReturn(config.isTestOnReturn());
        basicDataSource.setNumTestsPerEvictionRun(config.getNumTestsPerEvictionRun());
        basicDataSource.setTestWhileIdle(config.isTestWhileIdle());

        return basicDataSource;
    }

    @Bean(name = "basicJdbcTemplate")
    @Autowired
    @Qualifier("basicDataSource")
    public JdbcTemplate basicJdbcTemplate(BasicDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "basicDataSourceTransactionManager")
    @Autowired
    @Qualifier("basicDataSource")
    public DataSourceTransactionManager basicDataSourceTransactionManager(BasicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "basicTransactionTemplate")
    @Autowired
    @Qualifier("basicDataSourceTransactionManager")
    public TransactionTemplate basicTransactionTemplate(DataSourceTransactionManager basicDataSourceTransactionManager) {
        return new TransactionTemplate(basicDataSourceTransactionManager);
    }
}
