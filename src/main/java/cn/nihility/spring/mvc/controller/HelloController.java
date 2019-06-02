package cn.nihility.spring.mvc.controller;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring MVC Hello Controller
 * @author muscari
 * @date 2019-06-02 13:03
 */
@Controller
public class HelloController {

    @RequestMapping(value = {"/hello"}, method = {RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<Object, Object> helloController() {
        Map<Object, Object> result = new HashMap<>();
        result.put("hello", "Hello First Controller");
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "First Hello Controller Result [{}]", result);
        return result;
    }

}
