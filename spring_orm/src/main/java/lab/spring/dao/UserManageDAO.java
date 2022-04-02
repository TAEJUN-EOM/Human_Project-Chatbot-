package lab.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import lab.spring.core.UserVO;



public class UserManageDAO {
   
   
   private SqlSessionFactory sqlSessionFactory;   
   
   public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
      this.sqlSessionFactory = sqlSessionFactory;
   }
   
   
   public UserVO loginProc(String uid, String upwd) {     
      SqlSession sqlSession = sqlSessionFactory.openSession(true);
      UserVO  vo = null;
      HashMap<String, String> hm =  new HashMap<String, String>();
      hm.put("uid", uid);
      hm.put("upwd", upwd);
      try {
         vo = sqlSession.selectOne("lab.user.UserMapper.login", hm);
      }finally {
         sqlSession.close();
      }
      return  vo ;      
   }   
   
   public int insertMember(final UserVO vo) {  
      SqlSession sqlSession = sqlSessionFactory.openSession(true);
      try {
         return sqlSession.insert("lab.user.UserMapper.addUser", vo);
      }finally {
         sqlSession.close();
      }        
   }//insertMember() end
   
   public List<UserVO> findUserList() {          
      SqlSession sqlSession = sqlSessionFactory.openSession(true);
      try {
         return sqlSession.selectList("lab.user.UserMapper.getUserList" );
      }finally {
         sqlSession.close();
      }       
   }
   
   public UserVO findUser(String userid) {
      SqlSession sqlSession = sqlSessionFactory.openSession(true);
      try {
         return sqlSession.selectOne("lab.user.UserMapper.getUser" , userid);
      }finally {
         sqlSession.close();
      }          
   }
   
   public int updateUser(final UserVO user) {
      SqlSession sqlSession = sqlSessionFactory.openSession(true);
      try {
         return sqlSession.update("lab.user.UserMapper.modifyUser" , user);
      }finally {
         sqlSession.close();
      }    
   }
   
   public int removeUser(final String userid) {
      SqlSession sqlSession = sqlSessionFactory.openSession(true);
      try {
         return sqlSession.delete("lab.user.UserMapper.removeUser" , userid);
      }finally {
         sqlSession.close();
      }    
   }
   

}