package cn.nihility.activemq.topic;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Activemq Topic Producer message
 * Created by yzx on 2019/5/17.
 */
public class TopicProducer {

    static final String QUEUE_NAME = "topic_queue";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(QUEUE_NAME);
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage();

        int round = args.length > 0 ? Integer.parseInt(args[0]) : 10;
        String activeName = args.length > 1 ? args[1] : "default";
        String sentMessage = "[sent name |"+ activeName +"| activemq default topic queue message] ";
        while (round > 0) {
            /* 8. use Produce sent this message. */
            String sent = sentMessage + System.currentTimeMillis();
            LogbackUtil.logger(TopicProducer.class, LoggerLevelEnum.DEBUG, "Activemq topic sent message, sent name [{}], sent message {}", activeName, sent);
            textMessage.setText(sent);
            producer.send(textMessage);
            round--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        session.commit();

        producer.close();
        session.close();
        connection.close();

    }

}
