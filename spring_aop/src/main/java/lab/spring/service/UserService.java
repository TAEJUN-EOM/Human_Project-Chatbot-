package lab.spring.service;

import java.util.List;

import lab.spring.core.UserVO;

public interface UserService {
	public UserVO  login(String userid, String userpwd);
	public int registUser(UserVO user);
	public List<UserVO> findUserList();
	public UserVO findUser(String uid);
	public int removeUser(String uid);
	public int updateUser(UserVO user);
}
