package cn.nihility.bean;

import org.springframework.stereotype.Component;

/**
 * Person Talking
 * @author muscari
 * @date 2019-06-13 00:24
 */
@Component
public class PersonTalkingImpl implements Talking {
    @Override
    public String say(String things) {
        return "Person saying : " + things;
    }
}
