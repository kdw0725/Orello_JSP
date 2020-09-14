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
 * 체크리스트 항목에 댓글 작성 시 데이터베이스에 저장하기 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/checklist/addcommentok.do")
public class AddCommentOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String paseq = req.getParameter("paseq");
		String content = req.getParameter("content");
		String ciseq = req.getParameter("ciseq");
		
		ChecklistCommentDTO dto = new ChecklistCommentDTO();
		dto.setCiseq(ciseq);
		dto.setPaseq(paseq);
		dto.setContent(content);
		
		ChecklistDAO dao = new ChecklistDAO();
		int result = dao.addComment(dto);
		
		resp.setContentType("applicaion/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		JSONObject obj = new JSONObject();
		
		if(result == 1) {

			ChecklistCommentDTO ndto = dao.getNewComment(ciseq);
			obj.put("result", 1);
			obj.put("seq", ndto.getSeq());
			obj.put("ciseq", ndto.getCiseq());
			obj.put("paseq", ndto.getPaseq());
			obj.put("regdate", ndto.getRegdate());
			obj.put("content", ndto.getContent());
			obj.put("writer", ndto.getWriter());
			obj.put("profile", ndto.getProfilepic());

			writer.print(obj);
			
		} else {
			obj.put("result", 0);
			writer.print(obj);
		}
		writer.close();
		dao.close();
	
	}
	
}
