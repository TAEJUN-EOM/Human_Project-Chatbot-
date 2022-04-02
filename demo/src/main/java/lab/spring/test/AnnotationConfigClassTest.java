package lab.spring.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lab.spring.service.HelloService;

public class AnnotationConfigClassTest {
	//�޼��� �̸��� bean�̸��� �ȴ�.
	//spring ioc �����̳� ��ü ����
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		HelloService service = context.getBean("hello", HelloService.class);
		service.sayHello();
	}
}

