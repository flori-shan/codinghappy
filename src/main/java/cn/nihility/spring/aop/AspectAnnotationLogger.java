package cn.nihility.spring.aop;

import cn.nihility.utils.LogbackUtil;
import cn.nihility.utils.LoggerLevelEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author muscari
 * @date 2019-05-29 18:18
 */
@Component("aspectLogger")
@Aspect
public class AspectAnnotationLogger {

    @Pointcut("execution(* cn.nihility.spring.aop.Compute.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Annotation Logger Before, Method name [{}], Args [{}]",
                joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Annotation Logger After, Method name [{}], Args [{}]",
                joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Annotation Logger After Returning, Method name [{}], Args [{}], Result = [{}]",
                joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()), result);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, ArithmeticException ex) {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Annotation Logger After Throwing, Method name [{}], Args [{}], Exception [{}]",
                joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()), ex.getMessage());
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Annotation Logger Around Before, Method name [{}], Args [{}]",
                proceedingJoinPoint.getSignature().getName(), Arrays.asList(proceedingJoinPoint.getArgs()));
        Object proceed = proceedingJoinPoint.proceed();
        LogbackUtil.logger(getClass(), LoggerLevelEnum.INFO, "Aspect Annotation Logger Around After, Method name [{}], Args [{}], Process [{}]",
                proceedingJoinPoint.getSignature().getName(), Arrays.asList(proceedingJoinPoint.getArgs()), proceed);
        return proceed;
    }

}
