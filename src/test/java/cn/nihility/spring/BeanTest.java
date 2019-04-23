package cn.nihility.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.nihility.spring.beans.BeanDemo;
import cn.nihility.spring.beans.BeanDemoXml;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-bean.xml"})
public class BeanTest {
	
	@Autowired
	private BeanDemo bean;
	
	@Autowired
	@Qualifier("beanxml")
	private BeanDemoXml bean1;
	@Autowired
	@Qualifier("beanxml01")
	private BeanDemoXml bean01;
	@Autowired
	@Qualifier("beanxml02")
	private BeanDemoXml bean02;
	@Autowired
	@Qualifier("beanxml03")
	private BeanDemoXml bean03;
	
	@Test
	public void testBeanDemo() {
		bean.say();
	}
	
	@Test
	public void testBeanXml() {
		
		bean1.say();
		bean01.say();
		bean02.say();
		bean03.say();
		
	}

}
