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
	
	//답변글 개수 리턴하는 메소드
	public int getSize() { 
		return replies.size(); 
	}	
	//답변글 저장
	public void addReply(Article r) {
		replies.add(r);
	}
}
