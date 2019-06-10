package cn.nihility.spring.rabbitmq.omit;

/**
 * Rabbit MQ 发送接口
 * @author muscari
 * @date 2019-06-10 17:16
 */
public interface MQProducer {

    /**
     * 发送消息到指定队列
     * @param queueKey 队列 Key
     * @param object 发送的消息对象
     */
    void omitDataToQueue(String queueKey, Object object);
}
