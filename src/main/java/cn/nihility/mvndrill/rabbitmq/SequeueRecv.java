package cn.nihility.mvndrill.rabbitmq;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class SequeueRecv {

	static final String QUEUE_NAME = "rabbit.hello";
	public static final Logger logger = LoggerFactory.getLogger(SequeueRecv.class);
	
	public static void recv() {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			logger.debug("[*] Waiting for messages. To exit press CTRL+C");
			/*DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String message = new String(delivery.getBody(), "UTF-8");
				logger.debug("[x] Received '{}'", message);
			};*/
			
			DeliverCallback deliverCallback = new DeliverCallback() {
				@Override
				public void handle(String arg0, Delivery delivery) throws IOException {
					String msg = new String(delivery.getBody(), "UTF-8");
					logger.debug("[x] Received '{}'", msg);
					
				}
			};
			/* false : 不会丢掉信息， ture 可能会丢掉信息 */
			channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {});
		} catch (Exception e) {
			logger.error("[x] Received error '{}'", e.getMessage());
		}
		
	}

	public static void main(String[] args) {
		recv();
	}

}
