package lab.spring.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.spring.core.UserVO;

@Repository("dao")
public class UserDAO {
   @Autowired
   SqlSession sqlSession;//SqlSessionTemplate¿Ã ¡÷¿‘µ 

   public UserVO loginProc(String uid, String upwd) {
      UserVO vo = null;
      HashMap<String, String> hm = new HashMap<String, String>();
      hm.put("uid", uid);
      hm.put("upwd", upwd);
      return vo;
   }

   public int insertMember(final UserVO vo) {
         return sqlSession.insert("lab.user.UserMapper.addUser", vo);
   }// insertMember() end

   public List<UserVO> findUserList() {
         return sqlSession.selectList("lab.user.UserMapper.getUserList");
   }

   public UserVO findUser(String userid) {
         return sqlSession.selectOne("lab.user.UserMapper.getUser", userid);
   }

   public int updateUser(final UserVO user) {
         return sqlSession.update("lab.user.UserMapper.modifyUser", user);
   }

   public int removeUser(final String userid) {
         return sqlSession.delete("lab.user.UserMapper.removeUser", userid);
   }

}