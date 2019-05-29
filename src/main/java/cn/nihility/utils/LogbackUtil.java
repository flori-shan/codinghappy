package cn.nihility.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback 的日志输出工具类
 * @author muscari
 * @date 2019-05-04 03:05
 */
public class LogbackUtil {

    public static void logger(Class<?> clazz, LoggerLevelEnum level, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz);

        switch (level) {
            case TRACE: logger.trace(msg, params); break;
            case DEBUG: logger.debug(msg, params); break;
            case INFO: logger.info(msg, params); break;
            case WARN: logger.warn(msg, params); break;
            case ERROR: logger.error(msg, params); break;
            default: logger.debug(msg, params);
        }

    }

    public static void main(String[] args) {
        String msg = "LogbackUtil main function test, logger level = {}, msg = {}";

        LogbackUtil.logger(LogbackUtil.class, LoggerLevelEnum.TRACE, msg, "TRACE", "Hello logback");
        LogbackUtil.logger(LogbackUtil.class, LoggerLevelEnum.DEBUG, msg, "DEBUG", "Hello logback");
        LogbackUtil.logger(LogbackUtil.class, LoggerLevelEnum.INFO, msg, "INFO", "Hello logback");
        LogbackUtil.logger(LogbackUtil.class, LoggerLevelEnum.WARN, msg, "WARN", "Hello logback");
        LogbackUtil.logger(LogbackUtil.class, LoggerLevelEnum.ERROR, msg, "ERROR", "Hello logback");
    }
}
