package lab.spring.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/* �ٽ� ��� ���� ����� �α��� �����ϴ� ���� ����� Advice ������ ���� */
public class LoggingAdvice {
	public void beforeAdviceMethod(JoinPoint  joinPoint) {
		Class  clazz =joinPoint.getTarget().getClass();
		//JoinPoint�� �ٽɸ��(Pointcut) Ŭ���� ������ �ٽɷ��� �޼��� ������ �����ϰ� ���� 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("BeforeAdvice:"+className + "." + methodName 
				+ " �ٽ� �޼ҵ� ȣ�� ���� ���� ��� ����....");		
	}//beforeAdviceMethod() end
	
	public void afterReturningAdviceMethod(JoinPoint  joinPoint, Object retVal) {
		Class  clazz =joinPoint.getTarget().getClass();
		//JoinPoint�� �ٽɸ��(Pointcut) Ŭ���� ������ �ٽɷ��� �޼��� ������ �����ϰ� ���� 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("AfterReturningAdvice:"+className + "." + methodName 
				+ " �ٽ� �޼ҵ� ȣ�� ���� ���� ��� ����....");	
		System.out.println("return value is [" + ((Integer)retVal) + "]");
	}//afterReturningAdviceMethod() end
	
	
	public void afterThrowingAdviceMethod(JoinPoint  joinPoint, Exception exception) 
			                                               throws Exception{
		Class  clazz =joinPoint.getTarget().getClass();
		//JoinPoint�� �ٽɸ��(Pointcut) Ŭ���� ������ �ٽɷ��� �޼��� ������ �����ϰ� ���� 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("AfterThrowingAdvice:"+className + "." + methodName); 
		System.out.println("�ٽ� �޼ҵ尡 ���� �� ���ܻ����� ��ȯ�ϰ� �����ϴ� ��� ����ȴ�");
		System.err.println("������ �߻�:"+ exception.getMessage());
		throw new Exception("������ �߻��߽��ϴ�. ", exception);
	}//afterReturningAdviceMethod() end
	
	public void afterAdviceMethod(JoinPoint  joinPoint) {
		Class  clazz =joinPoint.getTarget().getClass();
		//JoinPoint�� �ٽɸ��(Pointcut) Ŭ���� ������ �ٽɷ��� �޼��� ������ �����ϰ� ���� 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("AfterAdvice:"+className + "." + methodName);	
		System.out.println("�ٽ� ���� �޼���  ���� ����� ���� �߻� ��츦 ��� ó�� �ϴ� Advice");
	}//afterAdviceMethod() end
	
	public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Class  clazz =joinPoint.getTarget().getClass();		 
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName =joinPoint.getSignature().getName();
		System.out.println("AroundAdvice:"+className + "." + methodName);
		System.out.println("�ٽ� �޼ҵ� ���� ���� ���� ��� ����........");
		long time1 = System.currentTimeMillis();
		Object retVal= joinPoint.proceed(); //Target���� �ٽ� �޼ҵ� ȣ��
		System.out.println("ProceedingJoinPoint executed. return value is [" + retVal + "]");
		System.out.println("���� �� ���� ==>  [" + ((Integer)retVal) + "(modified)" + "]");
		long time2 = System.currentTimeMillis();
		System.out.println("�ٽ� �޼ҵ� ���� ���� ���� ��� ����..... Time("+ (time2 - time1) + ")");
		return retVal;
		
	}
	
	
	
}





