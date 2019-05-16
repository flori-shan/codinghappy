package cn.nihility.rabbitmq.routing;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq routing receive message
 * Created by yzx on 2019/5/16.
 */
public class ReceiveLogsDirect {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();

        if (args.length < 1) {
            LogbackUtil.logger(ReceiveLogsDirect.class, LoggerLevelEnum.DEBUG, "Usage: Receive Logs Direct [info] [warning] [error]");
            System.exit(1);
        }

        for (String severity : args) {
            channel.queueBind(queueName, EmitLogDirect.EXCHANGE_NAME, severity);
        }
        LogbackUtil.logger(ReceiveLogsDirect.class, LoggerLevelEnum.DEBUG, "[*] Waiting for messages. To exit press CTRL+C, Queue name = {}", queueName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            LogbackUtil.logger(ReceiveLogsDirect.class, LoggerLevelEnum.DEBUG, "[*] Received severity [{}], message [{}]. Queue name = {}", delivery.getEnvelope().getRoutingKey(), message, queueName);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

    }

}
