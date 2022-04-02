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
		vo.setUsername("������3");
		vo.setAddress("seoul");
		vo.setGender("����");
		vo.setPhone("010-1234-6789");
		vo.setEmail("spring3@korea.com");
		vo.setUserpwd("1234");
		System.out.println("Target��ü�� �ٽɷ��� ����: "+service.registUser(vo));		
	}
}
