package cn.nihility.mvndrill.rabbitmq;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class SequenceUtils {
	private static final Logger logger = LoggerFactory.getLogger(SequenceUtils.class);
	
	public void producer(String msg) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		try (Connection conn = factory.newConnection(); Channel channel = conn.createChannel()) {
			channel.queueDeclare("hello", false, false, false, null);
			channel.basicPublish("", "hello", null, msg.getBytes());
			logger.debug("[x] Sent {} message", msg);
		} catch (Exception ex){
			logger.error("send sequence msg error, error msg : {}", ex.getMessage());
		}
	}
	
	
	public void consumer(String queueName) {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection conn = factory.newConnection(); Channel channel = conn.createChannel()) {
			channel.queueDeclare("hello", false, false, false, null);
			logger.debug("Waiting for message. waiting");
			
			DeliverCallback deliverCallback = new DeliverCallback() {
				@Override
				public void handle(String arg0, Delivery delivery) throws IOException {
					String msg = new String(delivery.getBody(), "UTF-8");
					logger.debug("Receive msg {}", msg);
					
				}
			};
			
			channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
			
		} catch (Exception e) {
		}
		
	}

	public static void main(String[] args) {
		SequenceUtils su = new SequenceUtils();
		su.producer("Hello world!");
		//su.consumer("hello");
	}

}
