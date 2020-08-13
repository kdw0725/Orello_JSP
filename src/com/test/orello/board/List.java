package com.test.orello.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/list.do")
public class List extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		
		//String pseq = (String)session.getAttribute("pseq");
		
		String pseq = "113";
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.list(pseq);
		
		Calendar now = Calendar.getInstance();
		
		for(BoardDTO dto : list) {
			
			if(dto.getRegdate().startsWith(String.format("%tF", now))) {
				dto.setRegdate(dto.getRegdate().substring(11));
			}else {
				
				dto.setRegdate(dto.getRegdate().substring(0, 10));
			}
			
			
			
		}
		
//		System.out.println(list.toString());
		
		
		req.setAttribute("list", list);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);
	
	
	}
}
