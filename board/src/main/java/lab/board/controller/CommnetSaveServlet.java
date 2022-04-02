package lab.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.board.dao.BoardDAO;
import lab.board.vo.Comment;

import java.io.IOException;

@WebServlet("/comment.do")
public class CommnetSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommnetSaveServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		BoardDAO dao = new BoardDAO();
		Comment comment =  new Comment();
		String rnum = null;
		rnum = request.getParameter("num");
		comment.setWriter(request.getParameter("writer"));
		comment.setPassword(request.getParameter("password"));
		comment.setContents(request.getParameter("contents"));
		comment.setIp(request.getRemoteAddr());
		comment.setRnum(Integer.parseInt(rnum));
		if (dao.insertComment(comment) > 0) {			
			response.sendRedirect("./read.do?action=read&num="+rnum);
		}
	}//doPost() end

}
