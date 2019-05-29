package cn.nihility.spring.aop;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author muscari
 * @date 2019-05-29 18:18
 */
@Component
@Aspect
public class AspectLoggerTime {

    @Pointcut("execution(* cn.nihility.spring.aop.Compute.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void doBefore() {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Logger Before [{}]", System.currentTimeMillis());
    }

    @After("pointCut()")
    public void doAfter() {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Logger After [{}]", System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(Object result) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Logger After Returning [{}] result [{}]", System.currentTimeMillis(), result);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void doAfterThrowing(ArithmeticException ex) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Logger After Throwing [{}] exception [{}]", System.currentTimeMillis(), ex.getMessage());
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Logger Around Before [{}], Args [{}]", System.currentTimeMillis(), Arrays.asList(proceedingJoinPoint.getArgs()));
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Logger Around [{}]", System.currentTimeMillis());
        Object proceed = proceedingJoinPoint.proceed();
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Logger Around After [{}], result [{}]", System.currentTimeMillis(), proceed);
        return proceed;
    }

}
