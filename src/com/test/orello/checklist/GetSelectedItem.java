package com.test.orello.checklist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * 체크리스트 항목의 상세 정보를 출력하기 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/checklist/getselecteditem.do")
public class GetSelectedItem extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 정보 받아오기
		//2. DB 작업(select)
		//3. 결과 반환하기
	
		//1.
		String ciseq = req.getParameter("ciseq");
		
		//2.
		ChecklistDAO dao = new ChecklistDAO();
		ChecklistItemDTO dto = dao.getChecklistItem(ciseq);
		dao.close();
		
		//3.
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		JSONObject obj = new JSONObject();
		obj.put("seq", dto.getSeq());		
		obj.put("title", dto.getTitle());		
		obj.put("content", dto.getContent());		
		obj.put("startdate", dto.getStartdate());		
		obj.put("enddate", dto.getEnddate());		
				
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
		
	}
	
}
