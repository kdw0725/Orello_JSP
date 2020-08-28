package com.test.orello.checklist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checklist/list.do")
public class List extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		//프로젝트 번호 임의로 설정
		String pseq = req.getParameter("pseq");
		String mseq = session.getAttribute("seq").toString();
		


		//데이터 받아오기
		ChecklistDAO dao = new ChecklistDAO();
		
		
		//프로젝트 참여번호 받아오기
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pseq", pseq);
		map.put("mseq", mseq);
		String paseq = dao.getPaseqByMap(map);

		ArrayList<ChecklistDTO> list = dao.getList(pseq);
		dao.close();

		//인코딩하기
		req.setCharacterEncoding("UTF-8");

		//데이터 넘겨주기
		req.setAttribute("list", list);
		req.setAttribute("pseq", pseq);
		req.setAttribute("paseq", paseq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/checklist/list.jsp");
		dispatcher.forward(req, resp);
	
	}
	
}
