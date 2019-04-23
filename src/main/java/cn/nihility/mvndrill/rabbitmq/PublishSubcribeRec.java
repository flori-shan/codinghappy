package cn.nihility.mvndrill.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class PublishSubcribeRec {
	
	private static final Logger logger = LoggerFactory.getLogger(PublishSubcribeRec.class);
	
	public static void main(String[] args) throws IOException, TimeoutException {
		rec();
	}
	
	public static void rec() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.exchangeDeclare(PublishSubcribeSend.EXCHANGE_NAME, "fanout");
	    String queueName = channel.queueDeclare().getQueue();
	    channel.queueBind(queueName, PublishSubcribeSend.EXCHANGE_NAME, "");
	    
	    logger.debug(" [*] Waiting for messages. To exit press CTRL+C");
	    
	    /*DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        logger.debug(" [x] Received '{}'", message);
	    };*/
	    
	    DeliverCallback deliverCallback = new DeliverCallback() {
			@Override
			public void handle(String consumerTag, Delivery delivery) throws IOException {
				 String message = new String(delivery.getBody(), "UTF-8");
		        logger.debug(" [x] Received '{}'", message);
			}
		};
	    channel.basicConsume(queueName, false, deliverCallback, new CancelCallback() {
			@Override
			public void handle(String consumerTag) throws IOException { }
		});
	}

}
