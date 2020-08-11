package com.test.orello.checklist;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checklist/list.do")
public class List extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//프로젝트 번호 임의로 설정
		String pseq = "1";

		//데이터 받아오기
		ChecklistDAO dao = new ChecklistDAO();
		
		ArrayList<ChecklistDTO> list = dao.getList(pseq);
		dao.close();

		//인코딩하기
		req.setCharacterEncoding("UTF-8");

		//데이터 넘겨주기
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/checklist/list.jsp");
		dispatcher.forward(req, resp);
	
	}
	
}
