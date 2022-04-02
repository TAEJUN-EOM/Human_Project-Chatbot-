package lab.board.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.board.dao.BoardDAO;
import lab.board.vo.Article;

import java.io.IOException;
import java.util.List;

@WebServlet("/list.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
	public static int numPerBlock = 10;
	public static int numPerPage = 10;
	
    public BoardListServlet() {
        super();         
    }
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);		 
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;	
		BoardDAO dao = new BoardDAO();	
		List<Article> headers = null;
		String page= null;
		page = request.getParameter("page");	
		int pageNo = 1;	
		if(page == null){
			pageNo = dao.getPageCount(numPerPage);
			headers = dao.getBoardList(pageNo, numPerPage);			
		}else {
			pageNo = Integer.parseInt(page);
			headers = dao.getBoardList(pageNo, numPerPage);	
		}
		Integer totalPage = new Integer(dao.getPageCount(numPerPage));
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageNo", new Integer(pageNo));
		request.setAttribute("headers", headers);
		rd =request.getRequestDispatcher("/boardList.jsp");
		rd.forward(request, response);
	}

}
