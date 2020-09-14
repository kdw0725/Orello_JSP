package com.test.orello.checklist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 댓글 리스트를 출력하기 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/checklist/getselectedcommentlist.do")
public class GetSelectedCommentList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ciseq = req.getParameter("ciseq");
		
		ChecklistDAO dao = new ChecklistDAO();
		ArrayList<ChecklistCommentDTO> list = dao.getCommentList(ciseq);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		JSONArray arr = new JSONArray();
		
		for (ChecklistCommentDTO dto : list) {
			
			JSONObject obj = new JSONObject();
			obj.put("seq", dto.getSeq());
			obj.put("ciseq", dto.getCiseq());
			obj.put("paseq", dto.getPaseq());
			obj.put("regdate", dto.getRegdate());
			obj.put("content", dto.getContent());
			obj.put("writer", dto.getWriter());
			obj.put("profile", dto.getProfilepic());
			
			
			arr.add(obj);
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
	
	}
	
}
