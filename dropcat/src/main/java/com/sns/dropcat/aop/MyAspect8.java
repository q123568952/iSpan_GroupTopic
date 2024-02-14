package com.sns.dropcat.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;




/**
 * Spring中AOP的通知類型：
 * 	@Around：環繞通知，此註解標註的通知方法在目標方法前、後都被執行
	@Before：前先通知，此註解標註的通知方法在目標方法前被執行
	@After：後置通知，此註解標註的通知方法在目標方法後被執行，無論是否有異常都會執行
	@AfterReturning： 返回後通知，此註解標註的通知方法在目標方法後被執行，有異常不會執行
	@AfterThrowing： 異常後通知，此註解標註的通知方法發生異常後執行

 * 
 **/



// 通知放在切面類中的方法裡面
@Slf4j
//@Component
//@Aspect
public class MyAspect8 
{
	
	/**
	 * 	annotation 切入點表達式:
		基於註解的方式來搭配切入點法。這種方式雖然多一步操作
		，我們需要自訂一個註解，但是相對來比較靈活。
		我們需要配對哪個方法，就在方法上加上對應的註解就可以了
	 */
	
	 @Pointcut("@annotation(com.sns.dropcat.aop.MyLog)")
	 private void pt(){}
	 
	 
	 
	 @Autowired
	 private HttpServletRequest request;
	 
	 
	 
	 
	 
	 
	   
	    //前置通知
//	    @Before("pt()")
	    public void before(JoinPoint joinPoint)
	    {
//	        log.info(joinPoint.getSignature().getName() + " MyAspect7 -> before ...");
	    
	    	System.out.println();
	        System.out.println(joinPoint.getSignature().getName() + " MyAspect7 -> before ...");
	    
	    
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //后置通知
//	    @After("pt()")
	    public void after(JoinPoint joinPoint){
//	        log.info(joinPoint.getSignature().getName() + " MyAspect7 -> after ...");
	    
	        System.out.println( joinPoint.getSignature().getName() + " MyAspect7 -> after ...");
	    
	    }

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    // around(ProceedingJoinPoint pjp)為通知方法
	    //环绕通知
	    @Around("pt()")
	    public Object around(ProceedingJoinPoint pjp) throws Throwable {
	    	//记录方法执行开始时间
	        long begin = System.currentTimeMillis();
	    	
	    	
	    	
	    	System.out.println( "這是@Around，around before .........");
	    	//获取目标类名
	        String name = pjp.getTarget().getClass().getName();
//	        log.info("目标类名：{}",name);

	        System.out.println( "這是@Around，目標類名：{}" + name);
	        
	        
	        //目标方法名
	        String methodName = pjp.getSignature().getName();
//	        log.info("目标方法名：{}",methodName);

	        
	        System.out.println("這是@Around，目標方法名：{}" + methodName);
	        
	        //获取方法执行时需要的参数
	        Object[] args = pjp.getArgs();
//	        log.info("目标方法参数：{}", Arrays.toString(args));

	        
	        System.out.println("目标方法参数：{}" +Arrays.toString(args));
	        
	        //执行原始方法
	        Object returnValue = pjp.proceed();
	        //记录方法执行结束时间
	        long end = System.currentTimeMillis();
	        long totalTime=end-begin;
	        System.out.printf(pjp.getSignature()+"執行耗时: {}毫秒", end-begin);
	        System.out.println("================================================");
	        System.out.println("在TimeAspect-----totalTime----" + totalTime);
	        
	        System.out.println( "這是@Around，around after ... .........");
	        return returnValue;
	    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
