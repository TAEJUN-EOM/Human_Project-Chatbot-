package lab.spring.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lab.spring.core.UserVO;
import lab.spring.dao.UserManageDAO;

public class MyBatisTest {

   public static void main(String[] args) throws IOException {
      String resource = "mybatis-config.xml";//MyBatis��������
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory 
                     = new SqlSessionFactoryBuilder().build(inputStream);
      UserManageDAO userDao = new UserManageDAO();
      userDao.setSqlSessionFactory(sqlSessionFactory);
      
      System.out.println("#######��ü ��� ###########");
      List<UserVO> lists = userDao.findUserList();
      Iterator<UserVO> iter = lists.iterator();
      while (iter.hasNext()) {
         UserVO u = iter.next();
         System.out.println(u);
      }      
      UserVO vo = new UserVO();       
      vo.setUserid("s5"); //PK 
      vo.setUsername("����5");
      vo.setUserpwd("1234");
      vo.setEmail("seoul5@korea.or.kr");
      vo.setPhone("02-129");
      vo.setAddress("����");
      vo.setGender("����");
      System.out.println("insert rows = >" + userDao.insertMember(vo));      
      vo = new UserVO();   
      vo.setUserid("s5");      
      vo.setEmail("sr@gmail.or.kr");
      vo.setPhone("02-129-1234");
      vo.setAddress("��õ");
      System.out.println("update :s5 =>"+userDao.updateUser(vo));
      System.out.println("delete :s3 =>"+userDao.removeUser("s3"));
      System.out.println("#######��ü ��� ###########");
        lists = userDao.findUserList();
        iter = lists.iterator();
      while (iter.hasNext()) {
         UserVO u = iter.next();
         System.out.println(u);
      }

   }

}