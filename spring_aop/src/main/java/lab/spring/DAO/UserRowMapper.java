package lab.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lab.spring.core.UserVO;

public class UserRowMapper implements RowMapper<UserVO>{

	@Override
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserVO vo = new UserVO();
		vo.setUserid(rs.getString("userid"));	
		vo.setUsername(rs.getString("username"));
		vo.setUserpwd(rs.getString("userpwd"));
		vo.setPhone(rs.getString("phone"));
		vo.setAddress(rs.getString("address"));
		vo.setEmail(rs.getString("email"));
		vo.setGender(rs.getString("gender"));
		return vo;
	}
    
}
