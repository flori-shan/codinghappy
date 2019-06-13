package cn.nihility.bean.datasource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Hikari 参数配置
 * @author muscari
 * @date 2019-06-13 15:21
 */
@Component
@PropertySource(value = {"classpath:properties/datasource.properties"}, encoding = "UTF-8")
@Getter
@Setter
@ToString
public class SelfHikariConfig {
    @Value("${mysql.driverClass}")
    private String driverClassName;
    @Value("${mysql.url}")
    private String jdbcUrl;
    @Value("${mysql.user}")
    private String username;
    @Value("${mysql.password}")
    private String password;
    @Value("${maxLifetime}")
    private int maxLifetime;
    @Value("${maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${minimumIdle}")
    private int minimumIdle;
    @Value("${idleTimeout}")
    private int idleTimeout;
    @Value("${connectionTimeout}")
    private int connectionTimeout;
}
