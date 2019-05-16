package cn.nihility.rabbitmq.pubsub;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Receive Publish/Subscribe log
 * Created by yzx on 2019/5/16.
 */
public class ReceiveLogs {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EmitLog.EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EmitLog.EXCHANGE_NAME, "");

        LogbackUtil.logger(ReceiveLogs.class, LoggerLevelEnum.DEBUG, "[*] Waiting for messages. To exit press CTRL+C, Queue name = {}", queueName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            LogbackUtil.logger(ReceiveLogs.class, LoggerLevelEnum.DEBUG, "[*] Received message [{}]. Queue name = {}", message, queueName);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

    }

}
