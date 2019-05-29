package cn.nihility.spring.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author muscari
 * @date 2019-05-29 17:10
 */
@Component("fb")
//@Qualifier("fb")
public class FunctionBean {

    public void say() {
        System.out.println("Function Bean say now time : " + new Date());
    }

    public void say(String msg) {
        System.out.println("Function Bean say msg : " + msg);
    }

}
