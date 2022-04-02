package lab.spring.test;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lab.spring.service.HelloService;
import lab.spring.service.UserService;

public class MessageTest {
	public static void main(String[] args) {
		//클래스 패스에 스프링 설정파일을 기반으로 스프링 컨테이너 객체를 생성
		//1. xml 사용하는 방법
		//ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");// 스프링 컨텍스트, 스프링 컨테이너
		//2. annotation 기반으로 사용하는 방법
		ApplicationContext context = new ClassPathXmlApplicationContext("annotConfig.xml");
		
		HelloService service = null;
		//스프링 컨테이너에게 "hello"이름의 빈 요청
		
		service = (HelloService)context.getBean("hello", "HelloService.class"); //object를 Helloservice 클래스로 다운 캐스팅
		service.sayHello();
		
		Locale locale_en = Locale.ENGLISH;
		String greet = context.getMessage("greeting", new Object[0],locale_en);
		System.out.println("ENGLISH locale인삿말:"+greet);
		
		Locale locale = Locale.getDefault();
		greet = context.getMessage("greeting", new Object[0],locale);
		System.out.println("default locale인삿말:"+greet);
		
		UserService proc = context.getBean("loginService", UserService.class);
		String[] results = proc.login("IDTEST", "1234");
		for( String m: results) {
			
			System.out.println(m);
		}
		
	}
}
