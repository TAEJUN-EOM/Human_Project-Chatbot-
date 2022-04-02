package lab.spring.test;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lab.spring.core.UserVO;
import lab.spring.service.UserService;

public class DBTest {

	public static void main(String[] args) {
		String[] configs = new String[]{"configDao.xml"};
		ApplicationContext context = 
				   new ClassPathXmlApplicationContext(configs);
		UserService service = 
				context.getBean("userService", UserService.class);
		
		
		/*
		UserVO vo = new UserVO();		 
		vo.setUserid("s3"); //PK 
		vo.setUsername("����3");
		vo.setUserpwd("1234");
		vo.setEmail("seoul3@korea.or.kr");
		vo.setPhone("02-129");
		vo.setAddress("����");
		vo.setGender("����");
		System.out.println("insert rows = >" + service.registUser(vo));
		System.out.println("#######s3 ���̵� ���� �˻� ###########"); 
		System.out.println(service.findUser("s3"));		
		*/
		System.out.println("#######��ü ��� ###########");
		List<UserVO> lists = service.findUserList();
		Iterator<UserVO> iter = lists.iterator();
		while (iter.hasNext()) {
			UserVO u = iter.next();
			System.out.println(u);
		}
		UserVO vo = new UserVO();	
		vo.setUserid("s3");		
		vo.setEmail("s3@gmail.or.kr");
		vo.setPhone("02-129-1234");
		vo.setAddress("�λ�");
		System.out.println("update :s3 =>"+service.updateUser(vo));
		System.out.println("delete :spring3 =>"+service.removeUser("spring3"));
		System.out.println("#######��ü ��� ###########");
		  lists = service.findUserList();
		  iter = lists.iterator();
		while (iter.hasNext()) {
			UserVO u = iter.next();
			System.out.println(u);
		}
	}

}

