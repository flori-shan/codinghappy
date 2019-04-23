package cn.nihility.spring;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.nihility.spring.beans.BeanDemoXml;

public class BeanBasicTest {
	
	private ApplicationContext context;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("classpath:spring/spring-bean.xml");
	}
	
	@Test
	public void testBeanScop() {
		BeanDemoXml bean1 = context.getBean("beanxml", BeanDemoXml.class);
		BeanDemoXml bean2 = context.getBean("beanxml01", BeanDemoXml.class);
		
		BeanDemoXml bean3 = context.getBean("beanxml", BeanDemoXml.class);
		BeanDemoXml bean4 = context.getBean("beanxml01", BeanDemoXml.class);
		
		Assert.assertNotEquals(bean1, bean3);
		Assert.assertEquals(bean2, bean4);
	}

	
	@After
	public void destroy() {
		context = null;
	}
}
