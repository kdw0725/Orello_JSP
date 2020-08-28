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
 * 날짜별 회원가입 수 통계 페이지를 띄우기 위한 servlet
 *
 */
@WebServlet("/achart/ajoin.do")
public class Ajoin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/achart/join.jsp");
		dispatcher.forward(req, resp);
	}
}
