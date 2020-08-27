package com.test.orello.calendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calendar/calendar.do")
public class Calendar extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		
		String seq = "2"; //member seq
		
		CalendarDAO dao = new CalendarDAO();
		ArrayList<ProjectDTO> plist = dao.projectList(seq);
		
//		System.out.println(plist);
		
		
		String test = "test";
		
		
		req.setAttribute("plist", plist);
		req.setAttribute("test", test);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/calendar/calendar.jsp");
		dispatcher.forward(req, resp);
	
	
	}
	
	
}
