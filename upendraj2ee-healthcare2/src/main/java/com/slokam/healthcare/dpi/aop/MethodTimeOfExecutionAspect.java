package com.slokam.healthcare.dpi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class MethodTimeOfExecutionAspect {

	@Around("execution( * com.slokam.healthcare.dpi.*.*.*(..) )")
	public Object aroundMethod(ProceedingJoinPoint pjp) {

		long startTime = System.currentTimeMillis();

		// target
		Object obj = null;
		try {
			obj = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		System.out.println("Time taken by "+methodName+"from the  "+className+ "is :"+time);
		return obj;
	}

	@AfterReturning(pointcut = "execution( * com.slokam.healthcare.dpi.*.*.*(..) )", returning = "ret" )
	public Object afterReturning(Object ret, JoinPoint jp) {
		return null;
	}
	@AfterThrowing(pointcut = "execution( * com.slokam.healthcare.dpi.*.*.*(..) )",throwing = "thr" )
	public void afterThrowing(Throwable thr,JoinPoint jp) {
		
	}
	
	// Aspect --> Its class , It's a splitting logic containing one or more advices.
	
	// Advice --> Its method containing piece of splitting logic assosiated with pointcut.
	
	// pointcut --> Its a expression  , identifies join points.
	
	//joinpoint -->  its a target method to which advice is applied, available in target object.
}
