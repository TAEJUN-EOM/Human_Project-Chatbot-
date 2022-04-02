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

@WebServlet("/modify.do") 
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardModifyServlet() {
        super();         
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		 BoardDAO dao =new BoardDAO();
		 int num = Integer.parseInt(request.getParameter("num"));
		 String cmd = null;
		 cmd =request.getParameter("action");
		 Board article = null;
		 RequestDispatcher rd = null;		  
		 
		 if (cmd != null && cmd.equals("modify")) {
				article = dao.getBoard(num);
		 } else if (cmd != null && cmd.equals("modify_r")) {
			 article = dao.getBoardReply(num);
			 request.setAttribute("isReply", new Boolean(true));
		 }
		 if(article!=null) {
			 rd = request.getRequestDispatcher("/boardEdit.jsp");
			 request.setAttribute("num", new Integer(num));
			 request.setAttribute("article", article);
			 rd.forward(request, response);
		 }
	}

}
