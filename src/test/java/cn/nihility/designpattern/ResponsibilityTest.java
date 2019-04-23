package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.chainofresposibility.AbstractLogger;
import cn.nihility.designpattern.chainofresposibility.ConsoleLogger;
import cn.nihility.designpattern.chainofresposibility.ErrorLogger;
import cn.nihility.designpattern.chainofresposibility.FileLogger;

public class ResponsibilityTest {

	@Test
	public void testResposibility() {
		AbstractLogger loggerChain = getChainOfLoggers();

		loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
		System.out.println("--------------------------");
		loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");
		System.out.println("--------------------------");
		loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
	}

	private static AbstractLogger getChainOfLoggers() {

		AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

		fileLogger.setNextLogger(consoleLogger);
		errorLogger.setNextLogger(fileLogger);

		return errorLogger;
	}

}
