package cn.nihility.rabbitmq.pubsub;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Publish/Subscribe exchange send
 * Created by yzx on 2019/5/16.
 */
public class EmitLog {

    static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            int round = args.length > 0 ? Integer.parseInt(args[0]) : 10;
            String sentName = args.length > 0 ? args[1] : "default";
            String message = "[" + sentName + "]" + (args.length < 1 ? " info : Hello World! " : String.join(" ", args));

            while (round > 0) {
                String sentMessage = message + System.currentTimeMillis();
                channel.basicPublish(EXCHANGE_NAME, "", null, sentMessage.getBytes("UTF-8"));
                LogbackUtil.logger(EmitLog.class, LoggerLevelEnum.DEBUG, "[*] Sent Message = [{}]", sentMessage);
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
