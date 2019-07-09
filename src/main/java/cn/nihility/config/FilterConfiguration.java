package cn.nihility.config;

import cn.nihility.filter.LoggerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

/**
 * Filter 过滤器的配置类, 同时可以通过注解的方式配置 Filter
 * @author muscari
 * @date 2019-07-09 23:50
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new LoggerFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("LoggerDurationFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
