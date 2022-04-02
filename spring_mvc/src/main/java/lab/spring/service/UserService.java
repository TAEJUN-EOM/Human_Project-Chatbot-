package lab.spring.service;

import java.util.List;

import lab.spring.model.UserVO;

 

public interface UserService {
	public UserVO  login(String userid, String userpwd);
	public int registUser(UserVO user);
	public List<UserVO> findUserList();
	public UserVO findUser(String uid);
	public int removeUser(String uid);
	public int updateUser(UserVO user);
	public List<UserVO> searchUser (String condition, String keyword);
	
}
