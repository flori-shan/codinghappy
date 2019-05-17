package cn.nihility.activemq.queue;

import cn.nihility.util.LogbackUtil;
import cn.nihility.util.LoggerLevelEnum;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Activemq Send message to queue.
 * Created by yzx on 2019/5/17.
 */
public class SendQueue {

    static final String QUEUE_NAME = "one_queue";

    public static void main(String[] args) throws JMSException {

        /* 1. Create a Connection Factory */
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        /* 2. Create a new Connection */
        Connection connection = factory.createConnection();
        /* 3. start connection */
        connection.start();
        /* 4. create new Session */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        /* 5. use Session to create target object, queue[one to one]/topic[one to more] */
        Queue queue = session.createQueue(QUEUE_NAME);

        /* 6. create a produce object to this queue */
        MessageProducer producer = session.createProducer(queue);

        /* 7. use Session create a new message Object. */
        TextMessage textMessage = session.createTextMessage();

        int round = args.length > 0 ? Integer.parseInt(args[0]) : 10;
        String activeName = args.length > 1 ? args[1] : "default";
        String sentMessage = "[sent name |"+ activeName +"| activemq default queue message] ";
        while (round > 0) {
            /* 8. use Produce sent this message. */
            String sent = sentMessage + System.currentTimeMillis();
            LogbackUtil.logger(SendQueue.class, LoggerLevelEnum.DEBUG, "Activemq sent message, sent name [{}], sent message {}", activeName, sent);
            textMessage.setText(sent);
            producer.send(textMessage);
            round--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* 9. close all resources. */
        producer.close();
        session.close();
        connection.close();

    }

}
