package cn.nihility.rabbitmq.first;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 消息接收， hello world
 * @author muscari
 * @date 2019-05-04 04:21
 */
public class Receiving {

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("cos130");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(Send.QUEUE_NAME, false, false, false, null);
            LogbackUtil.logger(Receiving.class, LoggerLevelEnum.DEBUG, "[*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = ((consumerTag, message) -> {
                String msg = new String(message.getBody(), "UTF-8");
                LogbackUtil.logger(Receiving.class, LoggerLevelEnum.DEBUG, "[*] Received message [{}]", msg);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            channel.basicConsume(Send.QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}
