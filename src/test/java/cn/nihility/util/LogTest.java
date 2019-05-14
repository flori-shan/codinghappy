package cn.nihility.util;

import org.junit.Test;

/**
 * 日志打印测试
 * @author muscari
 * @date 2019-05-04 03:17
 */
public class LogTest {

    @Test
    public void testLogger() {
        String msg = "Logback logger test, logger level = {}, msg = {}";

        LogbackUtil.logger(this.getClass(), LoggerLevelEnum.TRACE, msg, "TRACE", "Hello logback");
        LogbackUtil.logger(this.getClass(), LoggerLevelEnum.DEBUG, msg, "DEBUG", "Hello logback");
        LogbackUtil.logger(this.getClass(), LoggerLevelEnum.INFO, msg, "INFO", "Hello logback");
        LogbackUtil.logger(this.getClass(), LoggerLevelEnum.WARN, msg, "WARN", "Hello logback");
        LogbackUtil.logger(this.getClass(), LoggerLevelEnum.ERROR, msg, "ERROR", "Hello logback");

    }

}
