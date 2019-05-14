package cn.nihility.rabbitmq.workqueues;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 接收 Work Queue 队列的消息，避免处理密集型消息数据
 * @author muscari
 * @date 2019-05-04 23:49
 */
public class ReceiveWorkQueue {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("cos130");

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(SendWorkQueue.TASK_QUEUE_NAME, true, false, false, null);
        LogbackUtil.logger(ReceiveWorkQueue.class, LoggerLevelEnum.DEBUG, "[*] Waiting for message, To exist CTRL+C [{}]");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String msg = new String(delivery.getBody(), "UTF-8");

            LogbackUtil.logger(ReceiveWorkQueue.class, LoggerLevelEnum.DEBUG, "[*] Received message [{}]", msg);

            try {
                doWork(msg);
            } finally {
                LogbackUtil.logger(ReceiveWorkQueue.class, LoggerLevelEnum.DEBUG, "[*] Do work done. Received [{}]", msg);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        };
        channel.basicConsume(SendWorkQueue.TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {});

    }

    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
