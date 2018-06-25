package com.wilson.feelings.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppLogger {

	 private static Logger logger = LogManager.getLogger(AppLogger.class);
	
	@Around("@annotation(com.wilson.feelings.annotations.LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();

		Object proceed = joinPoint.proceed();

		long executionTime = System.currentTimeMillis() - start;

		String message = joinPoint.getSignature() + " executed in " + executionTime + "ms";
		System.out.println(message);
		logger.debug(message);
		return proceed;
	}

}