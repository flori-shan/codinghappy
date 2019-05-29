package cn.nihility.spring.aop;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;

import java.util.Date;

/**
 * @author muscari
 * @date 2019-05-29 18:18
 */
public class AspectLoggerTime {

    public void doBefore() {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Aspect Logger Before [{}]", System.currentTimeMillis());
    }

    public void doAfter() {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Aspect Logger After [{}]", System.currentTimeMillis());
    }

    public void doAfterReturning() {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Aspect Logger After Returning [{}]", System.currentTimeMillis());
    }

    public void doAfterThrowing() {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.DEBUG, "Aspect Logger After Throwing [{}]", System.currentTimeMillis());
    }

}
