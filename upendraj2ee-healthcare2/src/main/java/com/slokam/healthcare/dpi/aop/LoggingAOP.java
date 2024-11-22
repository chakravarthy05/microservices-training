package com.slokam.healthcare.dpi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class LoggingAOP {

	@Before("execution( * com.slokam.healthcare.dpi.*.*.*(..) )")
	public void beforeMethodLoggging(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName());
		System.out.println(joinPoint.getTarget().getClass());
		System.out.println("Entered into the method");
	}
	
	
	@After("execution(*  com.slokam.healthcare.dpi.*.*.*(..))")
	public void afterMethodLogging(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getName());
		System.out.println(joinPoint.getTarget().getClass());
		System.out.println("Exit from the method");
	}
	
}
