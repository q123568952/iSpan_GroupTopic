package com.sns.dropcat.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
//@Component
//@Aspect
public class TimeAspect {
	
// 	@Around("execution(* com.sns.dropcat.*.*(..))") // 切入點表達式
 	@Around("execution(* com.sns.dropcat.controller.PageController.findAll(..))") // 切入點表達式
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //记录方法执行开始时间
        long begin = System.currentTimeMillis();

        //执行原始方法
        Object result = pjp.proceed();

        //记录方法执行结束时间
        long end = System.currentTimeMillis();

        long totalTime=end-begin;
        //计算方法执行耗时
//        log.info(pjp.getSignature()+"执行耗时: {}毫秒",end-begin);
        System.out.printf(pjp.getSignature()+"执行耗时: {}毫秒",end-begin);
        System.out.println("在TimeAspect-----" + totalTime);

        return result;
    }
		
		
	
	
	
	

}
