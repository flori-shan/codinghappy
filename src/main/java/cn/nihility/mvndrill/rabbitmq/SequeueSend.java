package cn.nihility.mvndrill.rabbitmq;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SequeueSend {
	public static final Logger logger = LoggerFactory.getLogger(SequeueSend.class);
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		send("[rebbitmq sequeue send hello message, send time ("+sdf.format(new Date(System.currentTimeMillis()))+")]");
		
		SequeueSend.longSend("rabbitmq thread message");
	}
	
	public static void send(String message) {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(SequeueRecv.QUEUE_NAME, false, false, false, null);
			channel.basicPublish("", SequeueRecv.QUEUE_NAME, null, message.getBytes());
			logger.debug("[x] Sent '{}'", message);
		} catch (Exception ex) {
			logger.error("error {}", ex.getMessage());
		}
		
	}
	
	public static void longSend(String message) {
		SequeueSendThread sst = new SequeueSendThread(message);
		sst.start();
	}
	
	static class SequeueSendThread extends Thread {
		
		private String message;
		public SequeueSendThread(String message) {
			this.message = message;
		}

		@Override
		public void run() {
			int cnt = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			while (true) {
				send("[rebbitmq sequeue send "+message+", send time ("+sdf.format(new Date(System.currentTimeMillis()))+")]");
				cnt += 1;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					logger.error("Sequeue Thread sleep error, error {}", e.getMessage());
				}
				
				if (cnt == 100) {
					logger.debug("Sent rabbitmq sequeue end.");
					break;
				}
			}
		}
		
	}

}
