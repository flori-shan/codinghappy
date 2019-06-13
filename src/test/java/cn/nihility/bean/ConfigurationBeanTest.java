package cn.nihility.bean;

import cn.nihility.bean.datasource.SelfC3P0Config;
import cn.nihility.bean.datasource.SelfHikariConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Configuration Annotation Test
 * @author muscari
 * @date 2019-06-13 14:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationBeanTest {

    @Autowired
    private CarBean carBean;
    @Autowired
    private DriverBean driverBean;

    @Autowired
    private SelfHikariConfig hikariConfig;
    @Autowired
    private SelfC3P0Config c3P0Config;

    @Test
    public void testBeanEqual() {
        boolean result = carBean == driverBean.getCar();
        System.out.println(carBean);
        System.out.println(driverBean.getCar());
        System.out.println(result ? "同一个CarBean" : "不同的CarBean");
    }

    @Test
    public void testHikariConfig() {
        System.out.println(hikariConfig);
    }

    @Test
    public void testC3P0Config() {
        System.out.println(c3P0Config);
    }
}
