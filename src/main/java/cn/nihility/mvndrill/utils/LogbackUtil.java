package cn.nihility.mvndrill.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackUtil {

	public static void loggerDebug(Class<?> clazz, String msg, Object... params) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.debug(msg, params);
	}

	public static void loggerInfo(Class<?> clazz, String msg, Object... params) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.info(msg, params);
	}

	public static void loggerError(Class<?> clazz, String msg, Object... params) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.error(msg, params);
	}

	public static void logger(Class<?> clazz, LogLevelEnum level, String msg, Object... params) {
		Logger logger = LoggerFactory.getLogger(clazz);
		switch (level) {
		case TRACE:
			logger.trace(msg, params);
			break;
		case DEBUG:
			logger.debug(msg, params);
			break;
		case INFO:
			logger.info(msg, params);
			break;
		case WARN:
			logger.warn(msg, params);
			break;
		case ERROR:
			logger.error(msg, params);
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		logger(LogbackUtil.class, LogLevelEnum.DEBUG, "这是 {} 级别的 logback 日志.", "DEBUG");
	}

}
