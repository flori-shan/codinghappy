package cn.nihility.mvndrill.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectSend {
	
	public static final String EXCHANGE_NAME = "direct_logs";
	private static final Logger logger = LoggerFactory.getLogger(DirectSend.class);
	
	public static void main(String[] args) throws IOException, TimeoutException {
		if (args == null || args.length == 0) {
			args = new String[] {"directRec"};
		}
		send(args);
	}

	public static void send(String[] argv) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
			String severity = getSeverity(argv);
			String message = getMessage(argv);
			channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
			logger.debug(" [x] Sent '{}':'{}'", severity, message);
		}
	}

	private static String getSeverity(String[] strings) {
		if (strings.length < 1)
			return "info";
		return strings[0];
	}

	private static String getMessage(String[] strings) {
		if (strings.length < 2)
			return "Hello World!";
		return joinStrings(strings, " ", 1);
	}

	private static String joinStrings(String[] strings, String delimiter, int startIndex) {
		int length = strings.length;
		if (length == 0)
			return "";
		if (length < startIndex)
			return "";
		StringBuilder words = new StringBuilder(strings[startIndex]);
		for (int i = startIndex + 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
