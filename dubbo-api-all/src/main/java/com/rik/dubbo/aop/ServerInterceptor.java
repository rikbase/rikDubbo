package com.rik.dubbo.aop;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.rik.dubbo.vo.FormModelViewVo;


@Configuration
@Aspect
@Component
public class ServerInterceptor {
	private static String SYS_Sign = "ServiceAOP";
	private static Logger logger =  Logger.getLogger(ServerInterceptor.class);
	@SuppressWarnings("unused")
	@Pointcut("execution(* com.rik.dubbo.service..*.*(..))")
	private void servicePointCut(){}
	
	@Before(value="servicePointCut()")
	public void before(JoinPoint joinPoint){logger.info(SYS_Sign+":"+joinPoint.toShortString()+"-start");}
	@Around(value="servicePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info(SYS_Sign+":"+joinPoint.toShortString()+"around-Before");
        System.out.println();
        Object object= joinPoint.proceed();
        logger.info(SYS_Sign+":"+joinPoint.toShortString()+"around-After");
        return object;
    }
	@AfterThrowing(value="servicePointCut()",throwing="throwable")
    public void afterThrowing(Throwable throwable){logger.info(SYS_Sign+": Throwable-"+throwable.getMessage());}
	@After(value="servicePointCut()")
    public void after(JoinPoint joinPoint){logger.info(SYS_Sign+":"+joinPoint.toShortString()+"-end");}
	@AfterReturning(value="servicePointCut()",returning="returnVal")
    public void afterReturn(List<FormModelViewVo> returnVal){logger.info(SYS_Sign+": get "+returnVal.size()+" results");}
}
