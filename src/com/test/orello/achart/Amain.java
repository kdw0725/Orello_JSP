package com.test.orello.achart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 강혜림
 * 로그인한 관리자의 seq값을 받아와서 관리자 메인 페이지 불러오는 servlet
 *
 */
@WebServlet("/achart/amain.do")
public class Amain extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/achart/chartmain.jsp");
		dispatcher.forward(req, resp);
	}
}
