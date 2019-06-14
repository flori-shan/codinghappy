package cn.nihility.bean.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.beans.PropertyVetoException;

/**
 *
 * @Autowired  --> 四种模式 ： byName、byType、constructor、autodectect
 * @Qualifier(value = "basicDataSource") 没有作用,不能够按照指定的名称注入 Bean
 *  执行顺序：1. 先查找 Autowired 指定类型的 Bean 2. 若没有找到 Bean 则会报异常
 *          3. 找到一个指定类型的 Bean 则会自动匹配，并把 Bean 装配到要 Inject 的字段当中
 *          4. 若有多个 Bean 则按照注入字段的名称匹配注入 Bean 值，匹配成功后装配到指定字段当中。
 * @author muscari
 * @date 2019-06-13 14:21
 */
@Configuration
@PropertySource(encoding = "UTF-8", value = {"classpath:properties/datasource02.properties"})
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

    @Bean(name = "hikariDataSource02", destroyMethod = "close")
    @ConfigurationProperties(prefix = "hikari")
    public HikariDataSource hikariDataSource2() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }

    @Bean(name = "hikariJdbcTemplate")
    @Autowired
    @Primary
    public JdbcTemplate hikariJdbcTemplate(HikariDataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }

    @Bean(name = "hikariDataSourceTransactionManager")
    @Autowired
    @Primary
    public DataSourceTransactionManager hikariDataSourceTransactionManager(HikariDataSource hikariDataSource) {
        return new DataSourceTransactionManager(hikariDataSource);
    }

    @Bean(name = "hikariTransactionTemplate")
    @Autowired
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

    @Bean(name = {"comboPooledDataSource02"}, destroyMethod = "close")
    @ConfigurationProperties(prefix = "combo")
    public ComboPooledDataSource  comboPooledDataSource02() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        return comboPooledDataSource;
    }

    @Bean(name = "comboJdbcTemplate")
    @Autowired
    public JdbcTemplate comboJdbcTemplate(ComboPooledDataSource comboPooledDataSource) {
        return new JdbcTemplate(comboPooledDataSource);
    }

    @Bean(name = "comboDataSourceTransactionManager")
    @Autowired
    public DataSourceTransactionManager comboDataSourceTransactionManager(ComboPooledDataSource comboPooledDataSource) {
        return new DataSourceTransactionManager(comboPooledDataSource);
    }

    @Bean(name = "comboTransactionTemplate")
    @Autowired
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

    @Bean(name = "druidDataSource02", destroyMethod = "close")
    @ConfigurationProperties(prefix = "druid")
    public DruidDataSource druidDataSource02() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean(name = "druidJdbcTemplate")
    @Autowired
    public JdbcTemplate druidJdbcTemplate(DruidDataSource druidDataSource) {
        return new JdbcTemplate(druidDataSource);
    }

    @Bean(name = "druidDataSourceTransactionManager")
    @Autowired
    public DataSourceTransactionManager druidDataSourceTransactionManager(DruidDataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }

    @Bean(name = "druidTransactionTemplate")
    @Autowired
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

    @Bean(name = "basicDataSource02", destroyMethod = "close")
    @ConfigurationProperties(prefix = "dbcp2")
    public BasicDataSource basicDataSource02() {
        BasicDataSource basicDataSource = new BasicDataSource();
        return basicDataSource;
    }

    @Bean(name = "basicJdbcTemplate")
    @Autowired
    public JdbcTemplate basicJdbcTemplate(BasicDataSource basicDataSource) {
        return new JdbcTemplate(basicDataSource);
    }

    @Bean(name = "basicDataSourceTransactionManager")
    @Autowired
    public DataSourceTransactionManager basicDataSourceTransactionManager(BasicDataSource basicDataSource) {
        return new DataSourceTransactionManager(basicDataSource);
    }

    @Bean(name = "basicTransactionTemplate")
    @Autowired
    public TransactionTemplate basicTransactionTemplate(DataSourceTransactionManager basicDataSourceTransactionManager) {
        return new TransactionTemplate(basicDataSourceTransactionManager);
    }
}
