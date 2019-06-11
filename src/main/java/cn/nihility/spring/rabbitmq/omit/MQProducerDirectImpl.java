package cn.nihility.spring.rabbitmq.omit;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author muscari
 * @date 2019-06-10 17:18
 */
public class MQProducerDirectImpl implements MQProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void omitDataToQueue(String queueKey, Object object) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "amqp Template [{}]", amqpTemplate);
        try {
            amqpTemplate.convertAndSend(queueKey, object);
            LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Send Message Direct Key [{}], Message [{}]", queueKey, object);
        } catch (AmqpException ex) {
            LogbackUtil.logger(getClass(), LoggerLevelEnum.ERROR, "Send Message Error [{}]", ex);
        }
    }
}
