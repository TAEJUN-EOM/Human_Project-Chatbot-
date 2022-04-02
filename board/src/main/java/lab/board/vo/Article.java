package lab.board.vo;

import java.sql.Date;
import java.util.Vector;

public class Article {
	private String  writer;
	private String  title;
	private int num;
	private int rcount;
	private Date regdate;
	private Vector<Article> replies;
	
	public Article() {
		replies = new Vector<Article>();
	}

	public Article(int num, String writer, String title, int rcount, Date regdate ) {		 
		this.writer = writer;
		this.title = title;
		this.num = num;
		this.rcount = rcount;
		this.regdate = regdate;
		replies = new Vector<Article>();
	}

	public String getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public int getNum() {
		return num;
	}

	public int getRcount() {
		return rcount;
	}

	public Date getRegdate() {
		return regdate;
	}

	public Vector<Article> getReplies() {
		return replies;
	}
	
	//�亯�� ���� �����ϴ� �޼ҵ�
	public int getSize() { 
		return replies.size(); 
	}	
	//�亯�� ����
	public void addReply(Article r) {
		replies.add(r);
	}
}
