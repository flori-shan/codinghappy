package cn.nihility.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muscari
 * @date 2019-06-18 23:17
 */
@RestController
@RequestMapping(value = {"hello"})
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(path = "/hello")
    public String hello() {
        logger.debug("HelloController hello request");
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
