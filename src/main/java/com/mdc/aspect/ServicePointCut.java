package com.mdc.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ServicePointCut {

	private Log log = LogFactory.getLog(getClass());

	@Around("execution(* com.mdc.service.*.*(..))")
	public Object aroundService(ProceedingJoinPoint joinpoint) throws Throwable {
		log.info("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
		log.info("调用方法:" + joinpoint.getSignature().getDeclaringType().getName() + "."
				+ joinpoint.getSignature().getName());
		long before = System.currentTimeMillis();
		Object[] args = joinpoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			log.info("参数[" + i + "]" + args[i]);
		}
		Object obj = joinpoint.proceed();
		if (obj != null) {
			log.info("返回结果:" + obj.toString());
		}
		long after = System.currentTimeMillis();
		log.info("方法耗时:" + (after - before) + "毫秒");
		log.info("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
		return obj;
	}

}