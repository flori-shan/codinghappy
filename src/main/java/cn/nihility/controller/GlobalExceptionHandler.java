package cn.nihility.controller;

import cn.nihility.entity.Result;
import cn.nihility.exception.CustomException;
import cn.nihility.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author muscari
 * @date 2019-07-09 00:37
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public Result javaExceptionHandler(Exception ex) {
        LOGGER.error("catch error msg ", ex);
        return ResultUtil.error(-1, ex.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result innerExceptionHandler(Exception ex) {
        LOGGER.error("catch inner error msg ", ex);
        return ResultUtil.error(-1, ex.getMessage());
    }

}
