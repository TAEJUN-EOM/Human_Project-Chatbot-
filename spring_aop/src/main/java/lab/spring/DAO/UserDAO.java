package lab.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.spring.core.UserVO;
import lab.spring.util.DBUtil;

//@Repository("dao") 
public class UserDAO {
	private DBUtil dbUtil;
    
	public UserDAO() {
		super();
	}
    @Autowired
	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	public boolean loginProc(String uid, String upwd) {
		Connection con = null;
    	PreparedStatement stat = null;
    	String sql = "select * from members where userid=? and userpwd=? ";
    	ResultSet rs = null;
    	boolean success = false;
    	try {
    		con = dbUtil.dbCon();
    		stat = con.prepareStatement(sql);
    		stat.setString(1, uid);
    		stat.setString(2, upwd);
    		rs = stat.executeQuery();
    		while(rs.next()) {
    			success = true;
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		dbUtil.dbClose(con, stat, rs);
    	}
    	return success;
	}
	
	public int insertMember(UserVO vo) {
    	int rows = 0;
    	Connection con = null;
    	PreparedStatement stat = null;
    	String sql = "insert into members (userid, username, userpwd, email, "
    			+ "phone, address, gender )values ( ?,?,?,?,?,?,?)";
    	try {
    	    con = dbUtil.dbCon();
    	    stat = con.prepareStatement(sql);
    	    stat.setString(1, vo.getUserid());
    	    stat.setString(2, vo.getUsername());
    	    stat.setString(3, vo.getUserpwd());
    	    stat.setString(4, vo.getEmail());
    	    stat.setString(5, vo.getPhone());
    	    stat.setString(6, vo.getAddress());
    	    stat.setString(7, vo.getGender());
    	    rows =stat.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		dbUtil.dbClose(con, stat, null);
    	}
    	return rows;
	}

}
