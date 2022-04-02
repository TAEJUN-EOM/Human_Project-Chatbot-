package lab.spring.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import lab.spring.core.UserVO;
import lab.spring.dao.UserDAO;

@Service("userService") 
public class UserServiceImpl implements UserService {
	
	private UserDAO dao;	 
	 
	public UserServiceImpl() {
		super();
	}	
	
	@Autowired
	public void setDao(UserDAO userDao) {
		this.dao = userDao;
	}  
	
	@Override
	public int registUser(UserVO user) {		 
		return dao.insertMember(user);
	}
		
	public int updateUser(UserVO user) {	
		return dao.updateUser(user);
	}
	
	public int removeUser(String uid) {		 
		return dao.removeUser(uid);
	}
	
	public UserVO findUser(String uid) {		 
		return dao.findUser(uid);
	}
	
	public List<UserVO> findUserList() {		 
		return  dao.findUserList();
	}
	
	public UserVO login(String uid, String upwd) {	
		return  dao.loginProc(uid, upwd);
	}
	
}



