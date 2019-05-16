package cn.nihility.rabbitmq.topic;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Receive topic exchange message.
 * @author muscari
 * @date 2019-05-16 23:34
 */
public class ReceiveLogsTopic {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("cos130");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /* 声明 exchange 的名称和类型 */
        channel.exchangeDeclare(EmitLogTopic.EXCHANGE_NAME, "topic");
        /* 获取一个 rabbitmq 随机分配的 Queue Name */
        final String queueName = channel.queueDeclare().getQueue();

        if (args.length < 1) {
            System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
            System.exit(1);
        }

        /* 设置此个消息接受那些 key 类别的信息 */
        for (String bindingKey : args) {
            channel.queueBind(queueName, EmitLogTopic.EXCHANGE_NAME, bindingKey);
        }

        LogbackUtil.logger(ReceiveLogsTopic.class, LoggerLevelEnum.DEBUG, "[*] Waiting for message. To exit press CTRL+C");

        DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            LogbackUtil.logger(ReceiveLogsTopic.class, LoggerLevelEnum.DEBUG, "[×] Received routing key [{}], queue name [{}], message [{}]", delivery.getEnvelope().getRoutingKey(), queueName, message);
        });

        channel.basicConsume(queueName, deliverCallback, consumerTag -> {});

    }

}
