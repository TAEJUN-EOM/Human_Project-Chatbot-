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

 @WebServlet("/update.do")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public BoardUpdateServlet() {
        super();        
    }	  
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		BoardDAO dao = new BoardDAO();
		Board form = new Board();
		String cmd = request.getParameter("action");
		int num = Integer.parseInt(request.getParameter("num"));
		form.setNum(num);
		form.setWriter(request.getParameter("writer"));
		form.setPassword(request.getParameter("password"));
		form.setTitle(request.getParameter("title"));
		form.setContents(request.getParameter("contents"));
		form.setIp(request.getRemoteAddr());		
		 
		if(cmd!=null && cmd.equals("modify")) {
			if(dao.update(form, num)>0) {
				response.sendRedirect("./list.do");
			}
		}else if(cmd!=null && cmd.equals("modify_r")) {
			if(dao.updateReply(form, num)>0) {
				response.sendRedirect("./list.do");
			}
		}
	}

}
