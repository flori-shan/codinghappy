package cn.nihility.activemq.topic;

import cn.nihility.activemq.queue.ConsumerQueue;
import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Activemq topic consumer message
 * Created by yzx on 2019/5/17.
 */
public class TopicConsumer {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TopicProducer.QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(topic);

        try {
            while (true) {
                consumer.setMessageListener((message) -> {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            LogbackUtil.logger(TopicConsumer.class, LoggerLevelEnum.DEBUG, "Receive active Queue [{}]", textMessage.getText());
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
