package com.test.orello.member;

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

@WebServlet("/member/listmember.do")
public class ListMember extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberDTO> list =dao.getMemberByName(name);
		
		JSONArray arr = new JSONArray();
		
		for (MemberDTO dto : list) {
			JSONObject obj = new JSONObject();
			obj.put("seq", dto.getSeq());
			obj.put("name", dto.getName());
			obj.put("file", dto.getFile());
			obj.put("email", dto.getEmail());
			obj.put("social", dto.getSocial());
			
			arr.add(obj);
		}
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
		
	}
	
}
