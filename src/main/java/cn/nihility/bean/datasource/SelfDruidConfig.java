package cn.nihility.bean.datasource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author muscari
 * @date 2019-06-13 15:59
 */
@Component
@PropertySource(value = {"classpath:properties/datasource.properties"}, encoding = "UTF-8")
@Getter
@Setter
@ToString
public class SelfDruidConfig {
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
    @Value("${minIdle}")
    private int minIdle;
    @Value("${maxActive}")
    private int maxActive;
    @Value("${maxWait}")
    private int maxWait;
    @Value("${timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${testOnReturn}")
    private boolean testOnReturn;
    @Value("${minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${testWhileIdle}")
    private boolean testWhileIdle;
}
