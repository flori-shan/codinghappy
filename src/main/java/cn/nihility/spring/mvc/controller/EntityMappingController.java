package cn.nihility.spring.mvc.controller;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC Entity Mapping, 数据绑定
 * @author muscari
 * @date 2019-06-02 23:02
 */
@Controller
public class EntityMappingController {

    /* int 等非非封装类型不可以为空 */
    @RequestMapping(method = {RequestMethod.GET}, value = {"/basic-type"}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String requestBasicType(@RequestParam("basicAge") int age) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "basic type get param age value [{}]", age);
        return "param age[" + age + "]";
    }

}
