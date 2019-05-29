package cn.nihility.spring.bean;

import org.springframework.stereotype.Component;

/**
 * @author muscari
 * @date 2019-05-29 17:47
 */
@Component("cat")
public class CatMouthImpl implements Mouth {
    @Override
    public void doThings() {
        System.out.println("CatMouthImpl::Cat use mouth to miao miao.");
    }
}
