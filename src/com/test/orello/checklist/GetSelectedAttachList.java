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

@WebServlet("/checklist/getselectedattachlist.do")
public class GetSelectedAttachList extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ciseq = req.getParameter("ciseq");
		
		ChecklistDAO dao = new ChecklistDAO();
		ArrayList<AttachmentDTO> list = dao.getAttachList(ciseq);
		dao.close();
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		JSONArray arr = new JSONArray();
		
		for(AttachmentDTO dto : list) {
			
			JSONObject obj = new JSONObject();
			obj.put("seq", dto.getSeq());
			obj.put("ciseq", dto.getCiseq());
			obj.put("filename", dto.getFilename());
			obj.put("orgfilename", dto.getOrgfilename());
			obj.put("regdate", dto.getRegdate());
			
			arr.add(obj);
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
		
	}
	
}
