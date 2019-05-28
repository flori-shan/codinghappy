package cn.nihility.spring.bean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Bean test
 * @author muscari
 * @date 2019-05-29 00:11
 */
public class BeanTest {

    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:spring/spring-bean.xml");
    }

    @After
    public void destroy() {
        context = null;
    }

    @Test
    public void testBean() {
        Person person = context.getBean("person", Person.class);
        Assert.assertNotNull(person);
        System.out.println(person);
    }

}
