package com.wondersgroup.base0300aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author chenlin
 * @create 2019-06-05 22:50
 * @description: TODO
 * @version：1.0
 **/
@Aspect
@Component
public class Aop4Log {

    @Pointcut("execution(public * com.wondersgroup.base0300aop.services.*.*(..))")
    public void join() {

    }

    @Before("join()")
    public void before(JoinPoint jp) {
        System.out.println(" before aop args:" + jp.getArgs() + "," + jp.getSignature());
    }

    @After("join()")
    public void after(JoinPoint jp) {
        System.out.println(" after aop");
    }

    @AfterReturning("join()")
    public void doAfterReturning(JoinPoint joinPoint) {
        System.out.println("doAfterReturning");
    }

    @AfterThrowing("join()")
    public void deAfterThrowing(JoinPoint joinPoint) {
        System.out.println("deAfterThrowing");
    }

    @Around("join()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("deAround  in");
        Object proceed = joinPoint.proceed();
        System.out.println("doAround  over");
        return proceed;
    }
}

