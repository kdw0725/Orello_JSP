package com.test.orello.calendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calendar/calendar.do")
public class Calendar extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		
		String seq = session.getAttribute("seq").toString(); //member seq
		
		System.out.println("seq " + seq);
		//프로젝트 시퀀스 받아서 체크리스트 목록 불러와야함
		String pseq = req.getParameter("pseq");
		System.out.println("pseq "+ pseq);
		
		CalendarDAO dao = new CalendarDAO();
		ArrayList<ProjectDTO> plist = dao.projectList(seq, pseq);
		
		System.out.println(plist.toString());
		
		String test = "test";
		
		
		req.setAttribute("plist", plist);
		req.setAttribute("test", test);
		req.setAttribute("pseq", pseq);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/calendar/calendar.jsp");
		dispatcher.forward(req, resp);
	
	
	}
	
	
}
