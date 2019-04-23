package cn.nihility.mvndrill.test;

import org.junit.Test;

import cn.nihility.mvndrill.utils.LogLevelEnum;
import cn.nihility.mvndrill.utils.LogbackUtil;

public class LogTest {
	
	@Test
	public void testLog4j2Logger() {
		
		int count = 10;
		
		for (int i = 0; i < count; i++) {
			LogbackUtil.logger(this.getClass(), LogLevelEnum.TRACE, "这是 {} 的级别日志！", "TRACE");
			LogbackUtil.logger(this.getClass(), LogLevelEnum.DEBUG, "这是 {} 的级别日志！", "DEBUG");
			LogbackUtil.logger(this.getClass(), LogLevelEnum.INFO, "这是 {} 的级别日志！", "INFO");
			LogbackUtil.logger(this.getClass(), LogLevelEnum.WARN, "这是 {} 的级别日志！", "WARN");
			LogbackUtil.logger(this.getClass(), LogLevelEnum.ERROR, "这是 {} 的级别日志！", "ERROR");
		}
	}

	@Test
	public void logbackTest() {
		LogbackUtil.logger(this.getClass(), LogLevelEnum.DEBUG, "这是 {} 的级别日志！", "TRACE");
	}
	
}
