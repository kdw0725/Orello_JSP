package com.test.orello.checklist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checklist/deletechecklistok.do")
public class DeleteChecklistOk extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1. 데이터 받아오기 (체크리스트 seq)
		//2. DB작업 (update delflag=1)
		//3. 결과처리
	
		//1.
		String cseq = req.getParameter("cseq");
		
		//2.
		ChecklistDAO dao = new ChecklistDAO();
		int result = dao.delChecklist(cseq);
		dao.close();
		
		//3.
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.printf("{\"result\": \"%d\", \"cseq\": \"%s\"}", result, cseq);
		writer.close();
	
	
	
	
	}

}
