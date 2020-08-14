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
		String page = req.getParameter("page").equals("") ? "1" : req.getParameter("page");
		System.out.println("page:" + page);
		String search = req.getParameter("search") == null ? "" : req.getParameter("search");
		System.out.println(search);
		System.out.println("search: "+ search);
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.view(seq);
		
		dto.setSeq(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("page", page);
		req.setAttribute("search", search);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);
	
	
	}
}
