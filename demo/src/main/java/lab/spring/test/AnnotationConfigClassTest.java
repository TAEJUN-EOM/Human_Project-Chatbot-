package lab.spring.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lab.spring.service.HelloService;

public class AnnotationConfigClassTest {
	//메서드 이름이 bean이름이 된다.
	//spring ioc 컨테이너 객체 생성
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		HelloService service = context.getBean("hello", HelloService.class);
		service.sayHello();
	}
}

