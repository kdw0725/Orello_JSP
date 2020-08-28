package com.test.orello.achart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author 강혜림
 * 포인트 사용량 페이지를 띄우기 위한 servlet
 *
 */
@WebServlet("/achart/apoint.do")
public class Apoint extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/achart/point.jsp");
		dispatcher.forward(req, resp);
	}
}
