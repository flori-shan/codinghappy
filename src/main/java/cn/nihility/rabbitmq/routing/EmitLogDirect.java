package cn.nihility.rabbitmq.routing;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/** Rabbitmq routing emit message
 * Created by yzx on 2019/5/16.
 */
public class EmitLogDirect {

    static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            /* args[0] */
            String severity = getSeverity(args);
            String message = getMessage(args);

            int round = args.length > 1 ? Integer.parseInt(args[1]) : 10;
            String sendMessage = "[" + severity + " : " + message + "]";
            while (round > 0) {
                String emitMessage = sendMessage + System.currentTimeMillis();
                channel.basicPublish(EXCHANGE_NAME, severity, null, emitMessage.getBytes("UTF-8"));
                LogbackUtil.logger(EmitLogDirect.class, LoggerLevelEnum.DEBUG, "[*] Sent severity [{}] message [{}]", severity, emitMessage);
                Thread.sleep(1000);
                round--;
            }

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static String getSeverity(String[] args) {
        if (args.length < 1) { return "info"; }
        return args[0];
    }

    private static String getMessage(String[] args) {
        if (args.length < 2) { return "Hello World!"; }
        return joinStrings(args, " ", 1);
    }

    private static String joinStrings(String[] args, String delimiter, int startIndex) {
        int len = args.length;
        if (len == 0) { return ""; }
        if (len <= startIndex) { return ""; }
        StringBuilder words = new StringBuilder(args[startIndex]);
        for (int i = startIndex + 1; i < len; i++) {
            words.append(delimiter).append(args[i]);
        }
        return words.toString();
    }

}
