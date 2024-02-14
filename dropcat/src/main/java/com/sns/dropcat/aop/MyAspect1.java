package com.sns.dropcat.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
//@Aspect
public class MyAspect1 
{
	
	
	//切入点方法（公共的切入点表达式）
    @Pointcut("execution(* com.sns.dropcat.controller.*.*(..))")
    private void pt()
    {


    }
	

	
	//前置通知
    @Before("pt()")
    public void before(JoinPoint joinPoint){
//        log.info("before ...");
        System.out.println("before =====//@Before" );
        

    }

    //环绕通知
    @Around("pt()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info("around before ...");
        System.out.println("around before =========@Around");

        //调用目标对象的原始方法执行
        Object result = proceedingJoinPoint.proceed();
        
        //原始方法如果执行时有异常，环绕通知中的后置代码不会在执行了
        
//        log.info("around after ...");
        System.out.println("around after=========@Around");
        return result;
    }

    //后置通知
    @After("pt()")
    public void after(JoinPoint joinPoint){
//        log.info("after ...");
        System.out.println("after===========@After");
    }

    //返回后通知（程序在正常执行的情况下，会执行的后置通知）
    @AfterReturning("pt()")
    public void afterReturning(JoinPoint joinPoint){
//        log.info("afterReturning ...");
        System.out.println("afterReturning============@AfterReturning");
    }

    //异常通知（程序在出现异常的情况下，执行的后置通知）
    @AfterThrowing("pt()")
    public void afterThrowing(JoinPoint joinPoint){
//        log.info("afterThrowing ...");
        System.out.println("afterThrowing=======@AfterThrowing");
    }

	
	
	

}
