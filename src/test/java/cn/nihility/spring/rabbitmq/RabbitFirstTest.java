package cn.nihility.spring.rabbitmq;

import cn.nihility.spring.rabbitmq.omit.MQProducer;
import cn.nihility.spring.rabbitmq.rec.QueueDirectListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author muscari
 * @date 2019-06-10 17:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-rabbitmq.xml"})
public class RabbitFirstTest {

    @Autowired
    private MQProducer mqProducer;

    @Test
    public void testSend() {
        int cnt = 0;
        while (cnt < 10) {
            mqProducer.omitDataToQueue("spring.queue.key", "Hello Spring Rabbitmq Direct Message");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cnt += 1;
        }
    }

}
