package cn.nihility.mvndrill.rabbitmq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class WorkQueueSend {
	
	public static final String TASK_QUEUE_NAME = "task_queue";
	private static final Logger logger = LoggerFactory.getLogger(WorkQueueSend.class);
	
	public static void main(String[] args) {
		String msg = "";
		if (args != null && args.length > 0) {
			msg = args[0];
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		send("[rebbitmq work sequeue send message,"+ msg +" | send time ("+sdf.format(new Date(System.currentTimeMillis()))+")]");
		WorkQueueSend.longSend("[rebbitmq work sequeue send message,"+ msg +" |  send time ("+sdf.format(new Date(System.currentTimeMillis()))+")]");
	}
	
	private static void send(String msg) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		String message = String.join(" ", msg);
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
			channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
			logger.debug(" [×] Sent '{}'", message);
		} catch (Exception ex) {
			logger.error(" [×] send message error = {}, message = {}", ex.getMessage(), message);
		}
	}
	
	public static void longSend(String message) {
		WorkQueueThread sst = new WorkQueueThread(message);
		sst.start();
	}
	
	static class WorkQueueThread extends Thread {
		private String message;
		public WorkQueueThread(String message) {
			this.message = message;
		}

		@Override
		public void run() {
			int cnt = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			while (true) {
				send("[rebbitmq sequeue send "+message+", send time ("+sdf.format(new Date(System.currentTimeMillis()))+")]");
				cnt += 1;
				try { Thread.sleep(1000); }
				catch (Exception e) { logger.error("Sequeue Thread sleep error, error {}", e.getMessage()); }
				if (cnt == 100) { logger.debug("Sent rabbitmq sequeue end."); break; }
			}
		}
	}

}
