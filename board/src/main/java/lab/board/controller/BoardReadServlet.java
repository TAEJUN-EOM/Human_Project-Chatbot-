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

@WebServlet("/read.do")
public class BoardReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
    public BoardReadServlet() {
        
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");	
		doPost(request, response);
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");	
		RequestDispatcher rd = null;		
		BoardDAO dao = new BoardDAO();
		Board  article = null;
		//파라미터 추출
		int  num = Integer.parseInt(request.getParameter("num"));		
		String cmd = request.getParameter("action");
		if(cmd!=null && cmd.equals("read")){//board테이블에서 조회	
			article = dao.getBoard(num);
			request.setAttribute("article", article);
			request.setAttribute("num", new Integer(num));
		}else if(cmd!=null && cmd.equals("read_r")){ //답변글 보기 , board_reply 테이블에서 조회
			article = dao.getBoardReply(num);
			request.setAttribute("article", article);
			request.setAttribute("num", new Integer(num));
			request.setAttribute("isReply", new Boolean(true));
		}
		if(article!=null){
			rd = request.getRequestDispatcher("/boardView.jsp");
			rd.forward(request, response);
		}
	}//doPost() end

}
