package lab.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import lab.spring.core.UserVO;


@Repository("dao") 
public class UserManageDAO {
	private JdbcTemplate template;     
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public UserVO loginProc(String uid, String upwd) {	    	
    	String sql = "select * from members where userid=? and userpwd=? ";
    	UserVO vo =  null;
    	Object args[] = new String[] { uid, upwd };
        vo = template.queryForObject(sql,  args, new UserRowMapper());
    	return vo;
	}
	
	
	public int insertMember(final UserVO vo) {
    	int rows = 0;    	
    	final String sql = "insert into members (userid, username, userpwd, email, "
    			+ "phone, address, gender )values ( ?,?,?,?,?,?,?)";
    	rows = template.update(new PreparedStatementCreator() {
    		public PreparedStatement createPreparedStatement(Connection con) 
    				                                        throws SQLException {
    			PreparedStatement stat = con.prepareStatement(sql.toString());
    			stat.setString(1, vo.getUserid());
        	    stat.setString(2, vo.getUsername());
        	    stat.setString(3, vo.getUserpwd());
        	    stat.setString(4, vo.getEmail());
        	    stat.setString(5, vo.getPhone());
        	    stat.setString(6, vo.getAddress());
        	    stat.setString(7, vo.getGender());
        	    return stat;
    		}
    	}); 
    	
    	return rows;
	}//insertMember() end
	
	public List<UserVO> findUserList() {		 
		String sql = "select * from members ";
		List<UserVO> userList = template.query(sql, new UserRowMapper());
		return userList.isEmpty()? null: userList;		
	}
	
	public UserVO findUser(String userid) {
		String sql = "select * from members where userid = ? ";
		return template.queryForObject(sql, new Object[]{userid}, new UserRowMapper());	
	}
	
	public int updateUser(final UserVO user) {
		int rows = 0;
		final String sql = "update members set email=?, phone=?, address=? where userid = ? ";
		rows = template.update(new PreparedStatementCreator() {					 
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstat = con.prepareStatement(sql);
				pstat.setString(1, user.getEmail());
				pstat.setString(2, user.getPhone());
				pstat.setString(3, user.getAddress());
				pstat.setString(4, user.getUserid());
				return pstat;
			}
		});
		return rows;
	}
	
	public int removeUser(final String userid) {
		int rows = 0;
		final String sql = "delete members where userid = ?  "; 
		rows = template.update(new PreparedStatementCreator() {		
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstat = con.prepareStatement(sql);
				pstat.setString(1, userid);				 				
				return pstat;
		}
		});
		return rows;  
	}
	

}
