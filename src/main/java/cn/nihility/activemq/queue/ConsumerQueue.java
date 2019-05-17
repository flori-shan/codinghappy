package cn.nihility.activemq.queue;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Activemq consumer message
 * Created by yzx on 2019/5/17.
 */
public class ConsumerQueue {

    public static void main(String[] args) throws JMSException {


        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(SendQueue.QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        try {
            while (true) {
                consumer.setMessageListener((message) -> {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            LogbackUtil.logger(ConsumerQueue.class, LoggerLevelEnum.DEBUG, "Receive active Queue [{}]", textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
            session.close();
            connection.close();
        }

    }

}
