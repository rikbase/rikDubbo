package com.rik.dubbo.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Configuration

public class MongoDBInterceptor {
	private static String SYS_Sign = "MDBAOP";
	private static Logger logger =  Logger.getLogger(MongoDBInterceptor.class);
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.rik.dubbo.data.MongoManager.mongoDbFactory(..))")
	private void mongDbFactory(){}
	
	@Before(value="mongDbFactory()")
	public void before(JoinPoint joinPoint){
		logger.info(SYS_Sign+":"+joinPoint.toShortString()+"-start");
	}
	@Around(value="mongDbFactory()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info(SYS_Sign+":"+joinPoint.toShortString()+"around-Before");
        System.out.println();
        Object object= joinPoint.proceed();
        logger.info(SYS_Sign+":"+joinPoint.toShortString()+"around-After");
        return object;
    }
	@AfterThrowing(value="mongDbFactory()",throwing="throwable")
	public void afterThrowing(Throwable throwable){
		logger.info(SYS_Sign+": Throwable-"+throwable.getMessage());
    }
	@AfterReturning(value="mongDbFactory()",returning="mongoDbFactory")
	public void afterReturn(MongoDbFactory mongoDbFactory){
		logger.info(SYS_Sign+": getConnected "+mongoDbFactory.getDb().getName());
    }
}
