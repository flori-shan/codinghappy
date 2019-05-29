package cn.nihility.spring.bean;

import org.springframework.stereotype.Component;

/**
 * @author muscari
 * @date 2019-05-29 17:45
 */
@Component("pm")
public class PersonMouthImpl implements Mouth {
    @Override
    public void doThings() {
        System.out.println("PersonMouthImpl::person use mouth talking and eating.");
    }
}
