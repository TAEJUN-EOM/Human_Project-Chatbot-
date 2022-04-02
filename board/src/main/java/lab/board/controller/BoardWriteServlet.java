package lab.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.board.dao.BoardDAO;
import lab.board.vo.Board;

import java.io.IOException;
import java.io.PrintWriter;

 @WebServlet("/write.do")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
     
    public BoardWriteServlet() {
        
    }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=utf-8");
		 response.sendRedirect("./boardWrite.jsp");
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		BoardDAO dao = new BoardDAO();
		Board form = new Board();
		String page = null;
		page = request.getParameter("page");
		form.setWriter(request.getParameter("writer"));
		form.setPassword(request.getParameter("password"));
		form.setTitle(request.getParameter("title"));
		form.setContents(request.getParameter("contents"));
		form.setIp(request.getRemoteAddr());
		 
		if (dao.insert(form) > 0) {
			response.sendRedirect("./list.do");
		}
	}

}
