package cn.nihility.rabbitmq.rpc;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RPC Remote Procedure Call Server.
 * Created by yzx on 2019/5/17.
 */
public class RPCServer {

    static final String RPC_QUEUE_NAME = "rpc_queue";

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(RPC_QUEUE_NAME);

            channel.basicQos(1);

            LogbackUtil.logger(RPCServer.class, LoggerLevelEnum.DEBUG, "[*] Waiting RPC requests");

            Object monitor = new Object();
            DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                String response = "";

                try {
                    String message = new String(delivery.getBody(), "UTF-8");
                    int n = Integer.parseInt(message);
                    LogbackUtil.logger(RPCServer.class, LoggerLevelEnum.DEBUG, "Receive message fib [{}]", message);
                    response += fib(n);

                } catch (RuntimeException e) {
                    LogbackUtil.logger(RPCServer.class, LoggerLevelEnum.ERROR, "Receive message error [{}]", e);
                } finally {
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    // RabbitMq consumer worker thread notifies the RPC server owner thread
                    synchronized (monitor) {
                        monitor.notify();
                    }
                }
            });

            channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {}));
            // Wait and be prepared to consume the message from RPC client.
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
