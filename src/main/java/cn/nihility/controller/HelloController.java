package cn.nihility.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muscari
 * @date 2019-06-12 14:33
 */
@RestController
public class HelloController {

    @RequestMapping(path = {"/hello"}, method = {RequestMethod.GET})
    public String hello() {
        return "Hello Spring Boot";
    }

}
