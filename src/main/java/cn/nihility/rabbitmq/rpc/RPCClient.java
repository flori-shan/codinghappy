package cn.nihility.rabbitmq.rpc;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * RPC Remote Procedure Call Client.
 * Created by yzx on 2019/5/17.
 */
public class RPCClient implements AutoCloseable {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] args) {

        try (RPCClient fibonacciRpc = new RPCClient()) {

            for (int i = 0; i < 32; i++) {
                String i_str = Integer.toString(i);
                LogbackUtil.logger(RPCClient.class, LoggerLevelEnum.DEBUG, "[*] Requesting fib ({}}", i_str);
                String response = fibonacciRpc.call(i_str);
                LogbackUtil.logger(RPCClient.class, LoggerLevelEnum.DEBUG, "[.] Requesting fib ({}} response [{}]", i_str, response);
            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String call(String i_str) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();

        /* 注意这里是 rabbitmq 申请的随机队列 */
        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, i_str.getBytes("UTF-8"));

        final ArrayBlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, ((consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }), consumerTag -> {});

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
