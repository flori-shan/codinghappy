package cn.nihility.mvndrill.test;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import cn.nihility.mvndrill.utils.LogLevelEnum;
import cn.nihility.mvndrill.utils.LogbackUtil;

public class PropertiesGet {

	@Test
	public void testProperties() {
		
		String propertiesFileName = PropertiesGet.class.getResource("/mybatis/db.properties").getFile();
		Assert.assertNotNull(propertiesFileName);
		LogbackUtil.loggerInfo(this.getClass(), "db.properties file path = {}", propertiesFileName);
		
		String classLoaderPath = PropertiesGet.class.getClassLoader().getResource("mybatis/db.properties").getFile();
		Assert.assertNotNull(classLoaderPath);
		LogbackUtil.loggerInfo(this.getClass(), "classloader db.properties file path = {}", classLoaderPath);
		
		Properties properties = new Properties();
		try {
			properties.load(PropertiesGet.class.getClassLoader().getResourceAsStream("mybatis/db.properties"));
			String oracleUrl = properties.getProperty("oracle.url");
			Assert.assertNotNull(oracleUrl);
			LogbackUtil.logger(this.getClass(), LogLevelEnum.INFO, "oracle url = {}", oracleUrl);
		} catch (IOException e) {
			LogbackUtil.logger(this.getClass(), LogLevelEnum.ERROR, "load db.properties error, {}", e);
		}
	}
	
}
