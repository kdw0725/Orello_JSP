package com.test.orello.faq;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FAQ를 이용하기 위한 클래스이다.
 * @author 강경원
 *
 */
@WebServlet("/faq/faq.do")
public class Faq extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FaqDAO dao = new FaqDAO();
		
		FaqDTO dto = new FaqDTO();
		
		//faq title, content 가져오기
		ArrayList<FaqDTO> faq = dao.faq();
		
		req.setAttribute("faq", faq);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/faq/faq.jsp");
		dispatcher.forward(req, resp);
	}
}
