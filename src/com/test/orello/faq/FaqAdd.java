package com.test.orello.faq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/faq/faqadd.do")
public class FaqAdd extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//1. 데이터 가져오기 -> title, content
		//2. DB 작업 -> insert
		//3. 결과 반환
		

		
		//1.
		String title = req.getParameter("txtTitle");
		String content = req.getParameter("txtContent");
		String[] value = req.getParameterValues("checkbox");
		HashMap<String,String> map = new HashMap<>();
		map.put("title",title);
		map.put("content",content);
		
//		for(String v : value) {
//			System.out.println(v);
//		}
		
		//2.
		FaqDAO dao = new FaqDAO();
		dao.addContent(map);
		dao.deleteContent(value);
		
		//faq title, content 가져오기
		ArrayList<FaqDTO> faq = dao.faq();
		
		req.setAttribute("faq", faq);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/faq/faq.jsp");
		dispatcher.forward(req, resp);
		
		
		
	}
}
