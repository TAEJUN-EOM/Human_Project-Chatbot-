package lab.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.board.vo.Article;
import lab.board.vo.Board;
import lab.board.vo.Comment;

public class BoardDAO {
	public Connection dbConnect() {
		Connection con = null;
		try {
			// WAS 의 디렉토리 네이밍 컨텍스트를 사용하기 위한 객체 생성
			Context initCtx = new InitialContext();
			// 톰캣의 디렉토리 네이밍 컨텍스트를 Access할 수 있는 Context객체를 받아옴
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// 톰캣의 디렉토리 네이밍 컨텍스트에 등록된 DataSource 객체를 받아옴( pool에서 꺼내옴)
			DataSource ds = (DataSource) envCtx.lookup("jdbc/oracle");
			// DB 연결
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void dbClose(Connection con, Statement stat, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 전체 게시글 개수 조회 후 전체 페이지수 계산 후 리턴
	public int getPageCount(int numPerPage) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from board";
		int totalCount = 0;
		try {
			con = dbConnect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, rs);
		}
		int pageCount = (int) Math.ceil(totalCount / (double) numPerPage);
		pageCount = Math.max(pageCount, 1);
		return pageCount;
	}// getPageCount() end

	public List<Article> getBoardList(int page, int numPerPage) {
		List<Article> boards = new ArrayList<Article>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int start = (page - 1) * numPerPage;
		int end = page * numPerPage;
		StringBuffer sql = new StringBuffer();
		sql.append("select  rnum,  num,  writer,  title,  regdate,  rcount ");
		sql.append("from (select rownum as rnum,  num,  writer,  title,  regdate,  rcount ");
		sql.append("      from ( ");
		sql.append("          select num, writer, title,  regdate, rcount ");
		sql.append("          from board ");
		sql.append("           order by regdate desc ) A ");
		sql.append("        where rownum <= ? ) B ");
		sql.append(" where B.rnum>= ? ");
		try {
			con = dbConnect();
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, end);
			stmt.setInt(2, start);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Article header = new Article(rs.getInt("num"), rs.getString("writer"), rs.getString("title"),
						rs.getInt("rcount"), rs.getDate("regdate"));
				// checkReplies(header, "-" + rs.getInt("num"));
				checkReplies(header, "" + rs.getInt("num"));

				boards.add(header);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, rs);
		}

		return boards;
	}// getBoardList() end

	// 답변글 조회, 재귀호출 메서드
	protected void checkReplies(Article origin, String rnum) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select num, title, writer, regdate, rcount ");
		sql.append("from board_reply where rnum = ? ");
		try {
			con = dbConnect();
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, Integer.parseInt(rnum));
			rs = stmt.executeQuery();
			while (rs.next()) {
				Article header = new Article(rs.getInt("num"), rs.getString("writer"), rs.getString("title"),
						rs.getInt("rcount"), rs.getDate("regdate"));
				checkReplies(header, "-" + rs.getInt("num"));
				origin.addReply(header);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, rs);
		}
	}// checkReplies() end

	public int insert(Board form) {
		return insert(form, false);
	}

	public int insertReply(Board form) {
		return insert(form, true);
	}

	public int insert(Board form, boolean isReply) {
		int cnt = 0;
		PreparedStatement stmt = null;
		Connection con = null;
		StringBuffer sql = null;
		if (isReply) {
			sql = new StringBuffer();
			sql.append("insert into board_reply (num, title, writer, password, ")
					.append(" regdate, contents, ip , rnum ) ")
					.append(" values (reply_seq.nextval, ?, ?, ?, sysdate, ?, ?, ? )");
		} else {
			sql = new StringBuffer();
			sql.append("insert into board (num, title, writer, password, ").append(" regdate, contents, ip ) ")
					.append(" values (board_seq.nextval, ?, ?, ?, sysdate, ?, ? )");
		}
		try {
			con = dbConnect();
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, form.getTitle());
			stmt.setString(2, form.getWriter());
			stmt.setString(3, form.getPassword());
			stmt.setString(4, form.getContents());
			stmt.setString(5, form.getIp());

			if (isReply) {
				stmt.setString(6, form.getRnum());
			}

			cnt = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, null);
		}
		return cnt;
	}// insert() end

	public Board getBoard(int num) {
		return getBoard(num, "board");
	}

	public Board getBoardReply(int num) {
		return getBoard(num, "board_reply");
	}

	public Board getBoard(int num, String table) {
		Board board = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select num, title, writer, password, regdate, ").append("contents, ip, rcount, vcount  from  ")
				.append(table).append(" where num =").append(num);
		try {
			con = dbConnect();
			stmt = con.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery();
			while (rs.next()) {
				board = new Board();
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setPassword(rs.getString("password"));
				board.setRegdate(rs.getDate("regdate"));
				board.setContents(rs.getString("contents"));
				board.setIp(rs.getString("ip"));
				board.setVcount(rs.getInt("vcount"));
				int rcount = rs.getInt("rcount");
				rs.updateLong("rcount", rcount + 1);
				rs.updateRow();
				board.setRcount(rcount + 1);
			}
			rs.close();
			if (table.endsWith("reply")) {
				sql = new StringBuffer();
				sql.append("select count(num) from board_reply ").append(" where rnum=-").append(num);
			} else {
				sql = new StringBuffer();
				sql.append("select count(num) from board_reply ").append(" where rnum= ").append(num);
			}
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				board.setSize(rs.getInt(1));
			}
			rs.close();
			if (table.endsWith("reply")) {
				sql = new StringBuffer();
				sql.append("select num, writer, regdate, contents, ").append("password, ip  from board_comment ")
						.append(" where rnum=-").append(num);
			} else {
				sql = new StringBuffer();
				sql.append("select num, writer, regdate, contents, ").append("password, ip  from board_comment ")
						.append(" where rnum= ").append(num);
			}
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				Comment cmt = new Comment();
				cmt.setNum(rs.getInt("num"));
				cmt.setRnum(num);
				cmt.setWriter(rs.getString("writer"));
				cmt.setRegdate(rs.getDate("regdate"));
				cmt.setContents(rs.getString("contents"));
				cmt.setPassword(rs.getString("password"));
				cmt.setIp(rs.getString("ip"));
				board.addComment(cmt);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, rs);
		}

		return board;
	}// getBoard() end

	public int insertComment(Comment form) {
		PreparedStatement stmt = null;
		Connection con = null;
		StringBuffer sql = new StringBuffer();
		int rows = 0;
		sql.append("insert into board_comment (num, rnum, writer, ")
				.append("regdate, contents, password, ip) values ( ")
				.append("comment_seq.NEXTVAL, ?, ?, sysdate , ?,?,?)");
		try {
			con = dbConnect();
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, form.getRnum());
			stmt.setString(2, form.getWriter());
			stmt.setString(3, form.getContents());
			stmt.setString(4, form.getPassword());
			stmt.setString(5, form.getIp());
			rows = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, null);
		}
		return rows;
	}// insertComment() end

	public boolean deleteComment(int num, String password) {
		boolean success = false;
		PreparedStatement stmt = null;
		Connection con = null;
		String sql = "delete from board_comment where num =? and password=? ";
		try {
			con = dbConnect();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			stmt.setString(2, password);
			if (stmt.executeUpdate() > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, null);
		}
		return success;
	}// deleteComment( ) end

	public void vote(int num) {
		vote(num, "board");
	}

	public void voteReply(int num) {
		vote(num, "board_reply");
	}

	public void vote(int num, String table) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select num, vcount from " + table + " where num = " + num;
		try {
			con = dbConnect();
			stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int vcount = rs.getInt("vcount");
				rs.updateLong("vcount", vcount + 1);
				rs.updateRow();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, rs);
		}
	}// vote() end

	public int delete(int num, String pwd) {
		return delete(num, pwd, "board", false);
	}

	public int deleteReply(int num, String pwd) {
		return delete(num, pwd, "board_reply", true);
	}

	public int delete(int num, String pwd, String table, boolean isReply) {
		PreparedStatement stmt = null;
		Connection con = null;
		String sql = "delete from  " + table + " where num = ? ";
		int rows = 0;
		String sql2 = null;
		if (checkPassword(num, pwd, table)) {
			try {
				con = dbConnect();
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, num);
				rows = stmt.executeUpdate();
				if (isReply) {
					sql2 = "delete from board_comment where rnum=-?";
				} else {
					sql2 = "delete from board_comment where rnum=?";
				}
				stmt = con.prepareStatement(sql2);
				stmt.setInt(1, num);
				rows += stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbClose(con, stmt, null);
			}
		} // if 패스워드 일치할때
		return rows;
	}

	public boolean checkPassword(int num, String pwd, String table) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select password from " + table + " where " + " num = ? ";
		boolean flag = false;
		try {
			con = dbConnect();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String p = rs.getString("password");
				if (pwd.equals(p)) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, rs);
		}
		return flag;
	}// checkPassword() end;

	public int update(Board form, int num) {
		return update(form, num, "board");
	}

	public int updateReply(Board form, int num) {
		return update(form, num, "board_reply");
	}

	public int update(Board form, int num, String table) {
		int rows = 0;
		PreparedStatement stmt = null;
		Connection con = null;
		String sql = "update " + table + " set title=?, contents=? where num= ? ";
		try {
			con = dbConnect();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, form.getTitle());
			stmt.setString(2, form.getContents());
			stmt.setInt(3,  num );
			rows =  stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stmt, null);
		}
		return rows;
	}

}
