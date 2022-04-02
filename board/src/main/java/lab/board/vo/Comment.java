package lab.board.vo;

import java.sql.Date;

public class Comment {
	private int num;
	private int rnum;	 
	private String writer;
	private String password;
	private String contents;	 
	private Date regdate;
	private String ip;

	public Comment() {
		super();
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	 

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	 

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "Comment [num=" + num + ", rnum=" + rnum + ", writer=" + writer + ", password=" + password
				+ ", contents=" + contents + ", regdate=" + regdate + ", ip=" + ip + "]";
	}

	 

}
