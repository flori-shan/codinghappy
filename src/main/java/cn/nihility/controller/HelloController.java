package cn.nihility.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author muscari
 * @date 2019-06-18 23:17
 */
@RestController
@RequestMapping(value = {"hello"})
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * 随机抛出异常
     */
    private void randomException() throws Exception {
        Exception[] exceptions = { //异常集合
                new NullPointerException(),
                new ArrayIndexOutOfBoundsException(),
                new NumberFormatException(),
                new SQLException()};
        //发生概率
        double probability = 0.75;
        if (Math.random() < probability) {
            //情况1：要么抛出异常
            throw exceptions[(int) (Math.random() * exceptions.length)];
        } else {
            //情况2：要么继续运行
        }

    }

    @GetMapping(path = "/hello")
    public String hello() throws Exception {
        logger.debug("HelloController hello request");
        randomException();
        return "Hello Spring Boot";
    }

    @GetMapping(path = {"/entity"})
    public Entity getLombokEntity() {
        Entity entity = new Entity();
        entity.setId(100);
        entity.setName("Lombok Name JRebel Restart");
        logger.debug("Lombok to entity [{}]", entity);
        return entity;
    }

    @Getter
    @Setter
    @ToString
    public static class Entity {
        private Integer id;
        private String name;
    }

}
