package cn.nihility.spring.mvc.controller;

import cn.nihility.spring.mvc.entity.TransferUserDto;
import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Controller 向视图传递数据接口
 * @author muscari
 * @date 2019-06-05 10:24
 */
@Controller
@RequestMapping(value = "/inner")
public class InnerTransferDataController {

    /*
    * 1. 通过 Model 来传递数据
    * 2. 通过 ModelMap 来传递数据
    * 注意： 通过 model 等参数传递的时候 key 不能有横线分隔, jsp 用 ${} 不能解析， 如： model-and-view 错误
    *      会把属性添加到 request 的 Attribute 当中
    * 3. HashMap
    * 4. Session
    * 5. Request
    * */

    @RequestMapping(path = {"/model"}, method = {RequestMethod.GET})
    public ModelAndView model(Model model, ModelMap modelMap, HashMap<String, Object> map,
                              HttpSession session, HttpServletRequest request) {
        TransferUserDto userDto = new TransferUserDto();
        userDto.setId(1);
        userDto.setName("Model Transfer Data");
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Model Transfer way [{}], model [{}]", userDto, model);
        model.addAttribute("model", userDto);

        userDto = new TransferUserDto();
        userDto.setId(2);
        userDto.setName("Model Map Transfer Data");
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Model Transfer way [{}], model map [{}]", userDto, modelMap);
        modelMap.addAttribute("modelmap", userDto);

        userDto = new TransferUserDto();
        userDto.setId(3);
        userDto.setName("Model And View Transfer Data");
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Model Transfer way [{}]", userDto);

        ModelAndView mv = new ModelAndView();
        mv.addObject("modelAndView", userDto);
        mv.setViewName("show-transfer-data-view");

        userDto = new TransferUserDto();
        userDto.setId(4);
        userDto.setName("Hash Map Transfer Data");
        map.put("hashMap", userDto);
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Hash Map Transfer way [{}]", userDto);

        userDto = new TransferUserDto();
        userDto.setId(5);
        userDto.setName("Http Session Transfer Data");
        session.setAttribute("httpSession", userDto);
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Http Session Transfer way [{}], request [{}]", userDto, session);

        userDto = new TransferUserDto();
        userDto.setId(6);
        userDto.setName("Http Servlet Transfer Data");
        request.setAttribute("httpRequest", userDto);
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Http Servlet Request Transfer way [{}]", userDto, request);

        return mv;
    }


}
