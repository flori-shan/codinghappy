package cn.nihility.mvndrill.rabbitmq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PublishSubcribeSend {
	
	public static final String EXCHANGE_NAME = "logs";
	private static final Logger logger = LoggerFactory.getLogger(PublishSubcribeSend.class);
	
	public static void main(String[] args) {
		String msg = "";
		if (args != null && args.length > 0) {
			msg = args[0];
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		send("[rebbitmq work sequeue send message,"+ msg +" | send time ("+sdf.format(new Date(System.currentTimeMillis()))+")]");
		PublishSubcribeSend.longSend("[rebbitmq work publish and subscribe send message,| "+ msg +" |]");
	}
	
	private static void send(String msg) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		String message = null == msg ? "info: Hello World!" : String.join(" ", msg);
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
			logger.debug(" [×] Sent '{}'", message);
		} catch (Exception ex) { logger.error("Publish Subcribe Send error = {}, send message = {}", ex.getMessage(), message); }
	}

	public static void longSend(String message) {
		PublishSubcribeThread sst = new PublishSubcribeThread(message);
		sst.start();
	}
	
	static class PublishSubcribeThread extends Thread {
		private String message;
		public PublishSubcribeThread(String message) {
			this.message = message;
		}

		@Override
		public void run() {
			int cnt = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			while (true) {
				send(message+" , [send time ("+sdf.format(new Date(System.currentTimeMillis()))+")]");
				cnt += 1;
				try { Thread.sleep(1000); }
				catch (Exception e) { logger.error("Sequeue Thread sleep error, error {}", e.getMessage()); }
				if (cnt == 100) { logger.debug("Sent rabbitmq sequeue end."); break; }
			}
		}
	}
	
}
