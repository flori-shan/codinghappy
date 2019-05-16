package cn.nihility.rabbitmq.topic;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Rabbitmq use topic exchange send message.
 * @author muscari
 * @date 2019-05-16 23:07
 */
public class EmitLogTopic {

    static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("cos130");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            /* 这里的 type 有 fanout/topic/direct/headers */
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = args.length > 0 ? args[0] : "anonymous.info";
            String message = args.length > 1 ? args[1] : "default message.";
            String sentName = args.length > 2 ? args[2] : "default sent";
            int round = args.length > 3 ? Integer.parseInt(args[3]) : 10;

            String sentStaticMessage = String.join("-", "[", sentName, routingKey, message, "] ");

            while (round > 0) {
                String sentMessage = sentStaticMessage + System.currentTimeMillis();
                channel.basicPublish(EXCHANGE_NAME, routingKey, null, sentMessage.getBytes(StandardCharsets.UTF_8));
                LogbackUtil.logger(EmitLogTopic.class, LoggerLevelEnum.DEBUG, "[×] Sent [{}] routing key [{}], message [{}]", sentName, routingKey, sentMessage);
                round--;
                Thread.sleep(1000);
            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
