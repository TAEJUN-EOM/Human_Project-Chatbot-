package lab.spring.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.spring.util.DBUtil;

@Repository("dao")
public class UserDAO {
	private DBUtil dbUtil;
//주입받을때
@Autowired
	public void setDbUtil(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public boolean loginProc(String uid, String upwd) {
		Connection con = null;
		PreparedStatement stat = null;
		String sql = " select * from members where userid = ? and userpwd=? ";
		ResultSet rs = null;
		boolean success = false;
		try {
			con = dbUtil.dbcon();
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
				dbUtil.dbClose(con, stat,rs);
			}
			return success;
		

	}
	

}
