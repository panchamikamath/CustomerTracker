package kamath.panchami.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	//set up logger
	private Logger myLogger = Logger.getLogger(CRMLoggingAspect.class.getName());
	
	//set up pointcut declarations
	@Pointcut("execution(* kamath.panchami.controller.*.*(..))")
	private void forControllerPackage() {
		
	}
	
	@Pointcut("execution(* kamath.panchami.service.*.*(..))")
	private void forServicePackage() {
		
	}
	
	@Pointcut("execution(* kamath.panchami.dao.*.*(..))")
	private void forDAOPackage() {
		
	}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {
		
	}
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		String theMethod = theJoinPoint.getSignature().getName();
		myLogger.info("\n\n====> @Before: for method: "+theMethod);
		
		Object[] args = theJoinPoint.getArgs();
		for(Object tempArg : args)
			myLogger.info("===> arguement: "+tempArg);
	}
	
	//add @AfterReturning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		String theMethod = theJoinPoint.getSignature().getName();
		myLogger.info("\n\n====> @AfterReturning: for method: "+theMethod);
		
		myLogger.info("==> "+theResult);
	}
}
