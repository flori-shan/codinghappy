package cn.nihility.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Configuration 配置类
 * @author muscari
 * @date 2019-06-13 13:53
 */
@Configuration
//@Component
public class ConfigurationBean {

    /*
    * Configuration 时在 driver 和 spring 容器之中的是同一个对象，而使用 Component 时是不同的对象。
    * 造成不同结果的原因在 ConfigurationClassPostProcessor 类之中，通过调用 enhanceConfigurationClasses 方法
    * 为被注解 @Configuration 的类进行 CGLIB 代理
    * */

    /**
     * @Component 的时候
     * CarBean{id=100, name='Car Name'} : cn.nihility.bean.CarBean@6f9ad11c
     * CarBean{id=100, name='Car Name'} : cn.nihility.bean.CarBean@4b2d44bc
     * 不同的CarBean
     *
     * @Configuration 的时候
     * CarBean{id=100, name='Car Name'} : cn.nihility.bean.CarBean@376e7531
     * CarBean{id=100, name='Car Name'} : cn.nihility.bean.CarBean@376e7531
     * 同一个CarBean
     */

    /* @Scope prototype | singleton */
    @Bean
    @Scope(value = "prototype")
    public DriverBean driverBean() {
        DriverBean driverBean = new DriverBean();
        driverBean.setId(200);
        driverBean.setName("Driver Name");
        driverBean.setCar(carBean());
        return driverBean;
    }

    @Bean
    public CarBean carBean() {
        CarBean carBean = new CarBean();
        carBean.setId(100);
        carBean.setName("Car Name");
        return  carBean;
    }
}
