package cn.nihility.bean.datasource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author muscari
 * @date 2019-06-13 16:04
 */
@Component
@PropertySource(value = {"classpath:properties/datasource.properties"}, encoding = "UTF-8")
@Getter
@Setter
@ToString
public class SelfDbcp2Config {
    @Value("${mysql.driverClass}")
    private String driverClassName;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.user}")
    private String username;
    @Value("${mysql.password}")
    private String password;
    @Value("${initialSize}")
    private int initialSize;
    @Value("${maxTotal}")
    private int maxTotal;
    @Value("${maxIdle}")
    private int maxIdle;
    @Value("${minIdle}")
    private int minIdle;
    @Value("${maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${testOnReturn}")
    private boolean testOnReturn;
    @Value("${numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;
    @Value("${testWhileIdle}")
    private boolean testWhileIdle;
}
