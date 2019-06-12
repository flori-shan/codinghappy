package cn.nihility.bean;

import org.springframework.stereotype.Component;

/**
 * Dog wa wang
 * @author muscari
 * @date 2019-06-13 00:26
 */
@Component
public class DogTalkingImpl implements Talking {
    @Override
    public String say(String things) {
        return "Dog saying : " + things;
    }
}
