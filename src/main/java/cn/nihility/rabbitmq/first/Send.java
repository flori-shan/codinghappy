package cn.nihility.rabbitmq.first;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * Rabbitmq 消息发送者， Hello World
 * @author muscari
 * @date 2019-05-04 03:52
 */
public class Send {

    public final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {

        /* create a connection to the server */
        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
        factory.setHost("cos130");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String msg = "Hello World! Send from " + (args.length > 0 ? args[0] : "default") + ", Send time = ";
            publishQueueMsg(channel, msg + System.currentTimeMillis());
            while (true) {
                publishQueueMsg(channel, msg + System.currentTimeMillis());
                Thread.sleep(500);
            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void publishQueueMsg(Channel channel, String msg) throws IOException {
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
        LogbackUtil.logger(Send.class, LoggerLevelEnum.DEBUG, " [×] Sent message [{}]", msg);
    }

}
