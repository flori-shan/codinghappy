package cn.nihility.mvndrill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		String msg = "Hello World!";
		
		logger.debug(msg);
		logger.info(msg);
		logger.warn(msg);
		logger.error(msg);
		
	}
}
