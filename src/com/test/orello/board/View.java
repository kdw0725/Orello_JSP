package com.test.orello.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/view.do")
public class View extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.view(seq);
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);
	
	
	}
}