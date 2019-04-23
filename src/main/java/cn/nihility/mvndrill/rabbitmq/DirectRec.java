package cn.nihility.mvndrill.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class DirectRec {

	private static final Logger logger = LoggerFactory.getLogger(DirectSend.class);
	
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			args = new String[] {"directRec"};
		}
		try {
			rec(args);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	private static void rec(String[] argv) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(DirectSend.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();
		
        if (argv.length < 1) {
        	logger.debug("Usage: ReceiveLogsDirect [info] [warning] [error]");
            System.exit(1);
        }

        for (String severity : argv) {
            channel.queueBind(queueName, DirectSend.EXCHANGE_NAME, severity);
        }
        logger.debug(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            logger.debug(" [x] Received '{}':'{}'", delivery.getEnvelope().getRoutingKey(), message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
		
	}
	
}
