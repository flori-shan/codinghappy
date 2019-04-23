package cn.nihility.mvndrill.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class WorkQueueRec {
	
	private static final Logger logger = LoggerFactory.getLogger(WorkQueueRec.class);
	
	public static void main(String[] args) {
		try {
			rec();
		} catch (Exception e) {
			logger.error(" [×] rec message error = {}", e.getMessage());
		}
	}
	
	private static void rec() throws IOException, TimeoutException  {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		
		channel.queueDeclare(WorkQueueSend.TASK_QUEUE_NAME, true, false, false, null);
		logger.debug(" [*] Waiting for messages. To exit press CTRL+C");
		
		channel.basicQos(1);
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");

	        logger.debug(" [x] Received '{}'", message);
	        try {
	            doWork(message);
	        } finally {
	        	logger.debug(" [x] Done");
	            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	        }
	    };
	    channel.basicConsume(WorkQueueSend.TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
	}

	private static void doWork(String task) {
		for (char ch : task.toCharArray()) {
	        if (ch == '.') {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException _ignored) {
	                Thread.currentThread().interrupt();
	            }
	        }
	    }
	}

}
