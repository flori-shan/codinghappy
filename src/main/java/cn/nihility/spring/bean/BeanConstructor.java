package cn.nihility.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author muscari
 * @date 2019-05-29 17:13
 */
@Configuration
public class BeanConstructor {

    @Bean(name = {"innerPerson"})
    public Person constructorPerson() {
        Person person = new Person();
        person.setAge(21);
        person.setName("BeanConstructor Name");
        person.setEmail("bean@spring.com");
        person.setBirthDate(new Date());
        return person;
    }

}
