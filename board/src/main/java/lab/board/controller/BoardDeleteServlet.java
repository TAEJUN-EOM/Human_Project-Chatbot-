package lab.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.board.dao.BoardDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete.do") 
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
    public BoardDeleteServlet() {
        super();
        
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();				
		BoardDAO dao = new BoardDAO();
		int num = Integer.parseInt(request.getParameter("num"));
		String pwd = request.getParameter("password");
		String cmd = request.getParameter("action"); //delete, delete_r
		int rows = 0;
		if(cmd.equals("delete")){
			rows =dao.delete(num, pwd);
		}else if(cmd.equals("delete_r")){
			rows = dao.deleteReply(num, pwd);
		}
		if(rows>0) {
		   response.sendRedirect("./list.do");
		}else {
		   out.println("<script>");
		   out.println("window.alert('비밀번호가 일치하지 않습니다.');");
		   out.println("location.href='./read.do?action=read&num="+num+"'");
		   out.println("</script>");
		}
		
	}

}
