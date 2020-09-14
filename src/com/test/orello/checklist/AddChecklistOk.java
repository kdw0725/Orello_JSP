package com.test.orello.checklist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 체크리스트를 추가하기 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/checklist/addchecklistok.do")
public class AddChecklistOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1. 데이터 받아오기(pseq)
		//2. DB작업(insert)
		//3. 결과처리
		
		//1.
		String pseq = req.getParameter("pseq");
		
		//2.
		ChecklistDAO dao = new ChecklistDAO();
		ChecklistDTO dto = dao.addChecklist(pseq);
		dao.close();
		
		
		//3.
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/jason");
		PrintWriter writer = resp.getWriter();
		/*
			"seq": "1",
			"pseq": "2",
			"title": "title"
		*/
		String temp = "";
		temp += "{";
		temp += String.format("\"seq\": \"%s\",", dto.getSeq());
		temp += String.format("\"pseq\": \"%s\",", dto.getPseq());
		temp += String.format("\"title\": \"%s\"", dto.getTitle());
		temp += "}";
		
		writer.print(temp);
		writer.close();
		
	}
	
}
