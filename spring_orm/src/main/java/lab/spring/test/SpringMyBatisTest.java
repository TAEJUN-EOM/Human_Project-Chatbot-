package lab.spring.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lab.spring.dao.UserDAO;
import lab.spring.core.UserVO;
import lab.spring.service.UserService;

public class SpringMyBatisTest {

   public static void main(String[] args) throws IOException {
      String[] configs = new String[] {"application.xml"}; // MyBatis?  ?  ?  ?  
      ApplicationContext context = 
            new ClassPathXmlApplicationContext(configs);
      UserService service = context.getBean("userService", UserService.class);
      

      System.out.println("#######��ü ��� ###########");
      List<UserVO> lists = service.findUserList();
      Iterator<UserVO> iter = lists.iterator();
      while (iter.hasNext()) {
         UserVO u = iter.next();
         System.out.println(u);
      }      
      UserVO vo = new UserVO();       
      vo.setUserid("s1"); //PK 
      vo.setUsername("����1");
      vo.setUserpwd("1234");
      vo.setEmail("seoul3@korea.or.kr");
      vo.setPhone("02-129");
      vo.setAddress("����");
      vo.setGender("����");
      System.out.println("insert rows = >" + service.registUser(vo));      
      vo = new UserVO();   
      vo.setUserid("s3");      
      vo.setEmail("seoul@gmail.or.kr");
      vo.setPhone("02-123-1234");
      vo.setAddress("��õ");
      System.out.println("update :s3 =>"+service.updateUser(vo));
      System.out.println("delete :spring5 =>"+service.removeUser("spring5"));
      System.out.println("#######��ü��� ###########");
        lists = service.findUserList();
        iter = lists.iterator();
      while (iter.hasNext()) {
         UserVO u = iter.next();
         System.out.println(u);
      }

   }
}