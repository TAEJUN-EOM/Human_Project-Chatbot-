package lab.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.board.dao.BoardDAO;

import java.io.IOException;

@WebServlet("/delete_comment.do") 
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
    public DeleteCommentServlet() {
        super();        
    }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=utf-8");
		 BoardDAO dao = new BoardDAO();
		 int comment_num = Integer.parseInt(request.getParameter("num"));
		 String pwd = request.getParameter("password");
		 String read_num = request.getParameter("read_num");
		 if (dao.deleteComment(comment_num, pwd) ) {
			 response.sendRedirect("./read.do?action=read&num="+read_num);
		 }
	}

}







