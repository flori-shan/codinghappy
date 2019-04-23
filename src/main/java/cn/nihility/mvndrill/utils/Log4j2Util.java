package cn.nihility.mvndrill.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j2Util {
	
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

	public static void main(String[] args) {
		loggerDebug(Log4j2Util.class, "这个 {} 级别的日志", "DEBUGE");
	}

}
