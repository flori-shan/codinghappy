package cn.nihility.spring.aop;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

/**
 * @author muscari
 * @date 2019-05-30 10:16
 */
public class AspectXMLLogger {

    public void doBefore(JoinPoint joinPoint) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Xml Logger Before, Method name [{}], Args [{}]",
                joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
    }

    public void doAfter(JoinPoint joinPoint) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Xml Logger After, Method name [{}], Args [{}]",
                joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
    }

    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Xml Logger After Returning, Method name [{}], Args [{}], Result = [{}]",
                joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()), result);
    }

    public void doAfterThrowing(JoinPoint joinPoint, ArithmeticException ex) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Xml Logger After Throwing, Method name [{}], Args [{}], Exception [{}]",
                joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()), ex.getMessage());
    }

    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Xml Logger Around Before, Method name [{}], Args [{}]",
                proceedingJoinPoint.getSignature().getName(), Arrays.asList(proceedingJoinPoint.getArgs()));
        Object proceed = proceedingJoinPoint.proceed();
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Xml Logger Around After, Method name [{}], Args [{}], Process [{}]",
                proceedingJoinPoint.getSignature().getName(), Arrays.asList(proceedingJoinPoint.getArgs()), proceed);
        return proceed;
    }

}
