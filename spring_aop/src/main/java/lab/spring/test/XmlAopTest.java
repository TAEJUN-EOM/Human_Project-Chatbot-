package lab.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lab.spring.core.UserVO;
import lab.spring.service.UserService;

public class XmlAopTest {
	public static void main(String[] args) {
		//String[] configs = new String[]{"xmlAop.xml"};
		String[] configs = new String[]{"annotationAop.xml"};
		ApplicationContext context = 
				   new ClassPathXmlApplicationContext(configs);
		UserService service = 
				context.getBean("userService", UserService.class);
		UserVO vo = new UserVO();
		vo.setUserid("spring3"); //PK
		vo.setUsername("스프링3");
		vo.setAddress("seoul");
		vo.setGender("남자");
		vo.setPhone("010-1234-6789");
		vo.setEmail("spring3@korea.com");
		vo.setUserpwd("1234");
		System.out.println("Target객체의 핵심로직 수행: "+service.registUser(vo));		
	}
}
