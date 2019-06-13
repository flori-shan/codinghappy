package cn.nihility.bean.datasource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author muscari
 * @date 2019-06-13 15:43
 */
@Component
@PropertySource(value = {"classpath:properties/datasource.properties"}, encoding = "UTF-8")
@Getter
@Setter
@ToString
public class SelfC3P0Config {
    @Value("${mysql.driverClass}")
    private String driverClass;
    @Value("${mysql.url}")
    private String jdbcUrl;
    @Value("${mysql.user}")
    private String user;
    @Value("${mysql.password}")
    private String password;
    @Value("${initialPoolSize}")
    private int initialPoolSize;
    @Value("${minPoolSize}")
    private int minPoolSize;
    @Value("${maxPoolSize}")
    private int maxPoolSize;
    @Value("${acquireIncrement}")
    private int acquireIncrement;
    @Value("${checkoutTimeout}")
    private int checkoutTimeout;
    @Value("${idleConnectionTestPeriod}")
    private int idleConnectionTestPeriod;
    @Value("${maxIdleTime}")
    private int maxIdleTime;
    @Value("${testConnectionOnCheckout}")
    private boolean testConnectionOnCheckout;
    @Value("${testConnectionOnCheckin}")
    private boolean testConnectionOnCheckin;
    @Value("${numHelperThreads}")
    private int numHelperThreads;
}
