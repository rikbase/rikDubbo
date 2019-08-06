package com.rik.dubbo.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;

@Configuration
@Aspect
@Component
public class RedisInterceptor {
	private static String SYS_Sign = "RDSAOP";
	private static Logger logger =  Logger.getLogger(RedisInterceptor.class);
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.rik.dubbo.data.RedisManager.getJedisPool(..))")
	private void redisPointCut(){}
	@Before(value="redisPointCut()")
	public void before(JoinPoint joinPoint){logger.info(SYS_Sign+":"+joinPoint.toShortString()+"-start");}
	@AfterReturning(value="redisPointCut()",returning="jedisPool")
	public void afterReturn(JedisPool jedisPool){logger.info(SYS_Sign+": get numActive = "+jedisPool.getNumActive());}
}
