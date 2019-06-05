package cn.nihility.spring.mvc.controller;

import cn.nihility.spring.mvc.entity.DataBindChildEntity;
import cn.nihility.spring.mvc.entity.DataBindChildEntityListDto;
import cn.nihility.spring.mvc.entity.DataBindEntity;
import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Spring MVC 数据绑定
 * @author muscari
 * @date 2019-06-05 14:22
 */
@Controller
@RequestMapping(value = {"/bind"})
public class DataBindingController {

    /*
    * 1. 注意： Model 会把 null 的 value 值自动移除
    * */

    @RequestMapping(path = {"/origin"}, method = {RequestMethod.GET})
    public ModelAndView originMapping(int iValue, long lValue, float fValue, double dValue,
                                char cValue, boolean bValue) {

        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG,
                "ivalue [{}], lValue [{}], fValue [{}], dValue [{}], cValue [{}], bValue [{}]",
                iValue, lValue, fValue, dValue, cValue, bValue);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc-bind-show");
        modelAndView.addObject("iValue", iValue);
        modelAndView.addObject("lValue", lValue);
        modelAndView.addObject("fValue", fValue);
        modelAndView.addObject("dValue", dValue);
        modelAndView.addObject("cValue", cValue);
        modelAndView.addObject("bValue", bValue);

        return modelAndView;
    }

    @RequestMapping(path = {"/reference"}, method = {RequestMethod.GET})
    public ModelAndView referenceMapping(Integer iValue, Long lValue, Float fValue, Double dValue,
                                      String cValue, Boolean bValue) {

        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG,
                "ivalue [{}], lValue [{}], fValue [{}], dValue [{}], cValue [{}], bValue [{}]",
                iValue, lValue, fValue, dValue, cValue, bValue);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc-bind-show");
        modelAndView.addObject("iValue", iValue == null ? "null" : iValue);
        modelAndView.addObject("lValue", lValue == null ? "null" : lValue);
        modelAndView.addObject("fValue", fValue == null ? "null" : fValue);
        modelAndView.addObject("dValue", dValue == null ? "null" : dValue);
        modelAndView.addObject("cValue", cValue == null ? "null" : cValue);
        modelAndView.addObject("bValue", bValue == null ? "null" : bValue);

        return modelAndView;
    }

    @RequestMapping(path = {"/list"}, method = {RequestMethod.GET})
    public ModelAndView referenceMapping(DataBindChildEntityListDto dto) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "List Value [{}]", dto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc-bind-show");
        modelAndView.addObject("dto", dto);
        return modelAndView;
    }

    @RequestMapping(value = {"/pojo"}, method = {RequestMethod.GET})
    public ModelAndView pojoBinding(DataBindEntity entity) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "POJO Entity [{}]", entity);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc-bind-show");
        modelAndView.addObject("entity", entity);
        return modelAndView;
    }

    @RequestMapping(value = {"/multi-pojo"}, method = {RequestMethod.GET})
    public ModelAndView multiPojoBinding(DataBindEntity entity) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Multi POJO Entity [{}]", entity);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc-bind-show");
        modelAndView.addObject("entity", entity);
        return modelAndView;
    }

    @RequestMapping(value = {"/array"}, method = {RequestMethod.GET})
    public ModelAndView arrayBinding(String[] array) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Array [{}]", Arrays.asList(array));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc-bind-show");
        modelAndView.addObject("array", array);
        return modelAndView;
    }



}
