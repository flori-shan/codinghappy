package cn.nihility.spring.rabbitmq.rec;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;

/**
 * rabbitmq direct message rec.
 * @author muscari
 * @date 2019-06-10 17:27
 */
@Repository
public class QueueDirectListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Receive Message Direct [{}]", new String(message.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Receive Message Direct Error [{}]", ex);
        }
    }
}
