package lab.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.board.dao.BoardDAO;

import java.io.IOException;

 @WebServlet("/vote.do")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public VoteServlet() {
        super();         
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String action = null;
		String num = null;
		BoardDAO dao = new BoardDAO();
		 
		action = request.getParameter("action"); //vote, vote_r
		num =request.getParameter("num");
		
		if (action.equals("vote")) {
			dao.vote(Integer.parseInt(num));			
		}else{
			dao.voteReply(Integer.parseInt(num));
		}	
		
		response.sendRedirect("./vote.html");
		
	}

}
 
 
 
 
 
 
 
