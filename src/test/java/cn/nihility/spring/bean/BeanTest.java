package cn.nihility.spring.bean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author muscari
 * @date 2019-05-29 16:45
 */
public class BeanTest {

    private ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("classpath:spring/spring-bean.xml");
    }

    @After
    public void tearDown() {
        context = null;
    }

    @Test
    public void testBeanGenerate() {
        Person person = context.getBean("person", Person.class);
        Person person1 = context.getBean("person", Person.class);

        System.out.println(person);
        System.out.println(person1);

        /* prototype -> false; singleton -> true */
        Assert.assertEquals(person, person1);
    }

    @Test
    public void testConstructorBean() {
        Person pc = context.getBean("pc", Person.class);
        Person pc1 = context.getBean("pc", Person.class);

        System.out.println(pc);
        System.out.println(pc1);

        Assert.assertNotNull(pc);
        Assert.assertNotNull(pc1);
    }

    @Test
    public void testBean() {
        /*PersonScan personScan = context.getBean("personScan", PersonScan.class);
        System.out.println(personScan);
        Assert.assertNotNull(personScan);
        personScan.say();*/

        Person innerPerson = context.getBean("innerPerson", Person.class);
        System.out.println(innerPerson);
        Assert.assertNotNull(innerPerson);
    }

}
