package lab.spring.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect 
public class AnnotationLoggingAdvice {
	
	@Pointcut("execution(* lab.spring.service.*Service.*(..))")
	public void logPointcut() {	}
	
	@Before("logPointcut()")
	public void beforeAdviceMethod(JoinPoint  joinPoint) {
		Class  clazz =joinPoint.getTarget().getClass();
		//JoinPoint는 핵심모듈(Pointcut) 클래스 정보와 핵심로직 메서드 정보를 저장하고 있음 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("BeforeAdvice:"+className + "." + methodName 
				+ " 핵심 메소드 호출 전에 공통 기능 수행....");		
	}//beforeAdviceMethod() end
	
	@AfterReturning(pointcut="logPointcut()", returning="retVal")
	public void afterReturningAdviceMethod(JoinPoint  joinPoint, Object retVal) {
		Class  clazz =joinPoint.getTarget().getClass();
		//JoinPoint는 핵심모듈(Pointcut) 클래스 정보와 핵심로직 메서드 정보를 저장하고 있음 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("AfterReturningAdvice:"+className + "." + methodName 
				+ " 핵심 메소드 호출 전에 공통 기능 수행....");	
		System.out.println("return value is [" + ((Integer)retVal) + "]");
	}//afterReturningAdviceMethod() end
	
	@AfterThrowing(pointcut="logPointcut()", throwing="exception")
	public void afterThrowingAdviceMethod(JoinPoint  joinPoint, Exception exception) 
			                                               throws Exception{
		Class  clazz =joinPoint.getTarget().getClass();
		//JoinPoint는 핵심모듈(Pointcut) 클래스 정보와 핵심로직 메서드 정보를 저장하고 있음 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("AfterThrowingAdvice:"+className + "." + methodName); 
		System.out.println("핵심 메소드가 수행 중 예외사항을 반환하고 종료하는 경우 수행된다");
		System.err.println("에러가 발생:"+ exception.getMessage());
		throw new Exception("에러가 발생했습니다. ", exception);
	}//afterReturningAdviceMethod() end
	
	@After("logPointcut()") 
	public void afterAdviceMethod(JoinPoint  joinPoint) {
		Class  clazz =joinPoint.getTarget().getClass();
		//JoinPoint는 핵심모듈(Pointcut) 클래스 정보와 핵심로직 메서드 정보를 저장하고 있음 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("AfterAdvice:"+className + "." + methodName);	
		System.out.println("핵심 로직 메서드  정상 종료와 예외 발생 경우를 모두 처리 하는 Advice");
	}//afterAdviceMethod() end
	
	@Around("logPointcut()")
	public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Class  clazz =joinPoint.getTarget().getClass();		 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("AroundAdvice:"+className + "." + methodName);
		System.out.println("핵심 메소드 수행 전의 공통 기능 수행........");
		long time1 = System.currentTimeMillis();
		Object retVal= joinPoint.proceed(); //Target빈의 핵심 메소드 호출
		System.out.println("ProceedingJoinPoint executed. return value is [" + retVal + "]");
		System.out.println("리턴 값 변경 ==>  [" + ((Integer)retVal) + "(modified)" + "]");
		long time2 = System.currentTimeMillis();
		System.out.println("핵심 메소드 수행 후의 공통 기능 수행..... Time("+ (time2 - time1) + ")");
		return retVal;
		
	}
	
	
	
}





