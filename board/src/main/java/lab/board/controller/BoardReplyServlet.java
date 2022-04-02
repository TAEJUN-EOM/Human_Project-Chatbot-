package lab.board.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.board.dao.BoardDAO;
import lab.board.vo.Board;

import java.io.IOException;

@WebServlet("/reply.do") 
public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public BoardReplyServlet() {
        super();         
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");		 
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/boardWrite.jsp");
		rd.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");			 
		BoardDAO dao = new BoardDAO();
		Board form = new Board();
		String page = null;
		page = request.getParameter("page");
		String cmd = null;
		cmd = request.getParameter("action");
		String rnum = null;
		rnum = request.getParameter("rnum");
		form.setWriter(request.getParameter("writer"));
		form.setPassword(request.getParameter("password"));
		form.setTitle(request.getParameter("title"));
		form.setContents(request.getParameter("contents"));
		form.setIp(request.getRemoteAddr());
		int rows = 0;
		if(cmd!=null&& cmd.equals("reply")) {
			form.setRnum(rnum);
			rows = dao.insertReply(form);
		}else if(cmd!=null&& cmd.equals("reply_r")) {
			form.setRnum("-"+rnum);
			rows = dao.insertReply(form);
		}
		
		if(rows>0) {
			response.sendRedirect("./list.do");
		}
	}

}
