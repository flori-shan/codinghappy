package cn.nihility.rabbitmq.workqueues;

import cn.nihility.rabbitmq.first.Send;
import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Work Queues 消息队列的信息发送，避免大量的数据处理时间占用，
 * @author muscari
 * @date 2019-05-04 23:36
 */
public class SendWorkQueue {

    public final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("cos130");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            String message = String.join("-", args);

            int cnt = 0;
            int stop = 40;
            if (args.length > 0) { stop = Integer.parseInt(args[0]); }
            while (cnt++ < stop) {
                publishQueueMsg(channel, message + System.currentTimeMillis());
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
        channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes("UTF-8"));
        LogbackUtil.logger(Send.class, LoggerLevelEnum.DEBUG, " [×] WorkQueues Sent message [{}]", msg);
    }

}
