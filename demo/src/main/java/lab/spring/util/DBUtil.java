package lab.spring.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import lab.spring.service.UserService;

public class DBUtil {
	private String driver;
	private String url;
	private String user;
	private String pwd;
//	db연결에 필요한 정보를 스프링 설정파일에서 읽어와서 멤버 필드에 저장
//	set메서드 추가
	
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public DBUtil() {
	super();
}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Connection dbcon() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pwd);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public void dbClose(Connection con, Statement stat, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stat!=null) stat.close();
			if(con!=null) con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
