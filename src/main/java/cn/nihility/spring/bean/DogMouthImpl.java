package cn.nihility.spring.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author muscari
 * @date 2019-05-29 17:46
 */
@Component
public class DogMouthImpl implements Mouth {
    @Override
    public void doThings() {
        System.out.println("DogMouthImpl::dog use mouth to wang wang.");
    }
}
