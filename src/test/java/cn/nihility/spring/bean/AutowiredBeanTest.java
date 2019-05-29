package cn.nihility.spring.bean;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author muscari
 * @date 2019-05-29 17:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-bean.xml"})
public class AutowiredBeanTest {

    @Autowired
    @Qualifier("pc")
    private Person person;

    @Resource
    private Person innerPerson;

    /*@Autowired*/
    @Resource(name = "fb")
    private FunctionBean bean;

    @Autowired
    @Qualifier("mouth")
    private Mouth personMouth;

    @Resource
    private Mouth dogMouthImpl;

    @Resource(name = "cat")
    private Mouth catMouth;

    @Test
    public void testResource() {
        System.out.println(innerPerson);
        Assert.assertNotNull(innerPerson);
    }

    @Test
    public void testAutowired() {
        System.out.println(person);
        Assert.assertNotNull(person);
    }

    @Test
    public void testFunctionBean() {
        System.out.println(bean);
        bean.say();
        Assert.assertNotNull(bean);
    }

    @Test
    public void testImpl() {
        personMouth.doThings();
        Assert.assertNotNull(personMouth);

        dogMouthImpl.doThings();
        Assert.assertNotNull(dogMouthImpl);

        catMouth.doThings();
        Assert.assertNotNull(catMouth);

    }
}
