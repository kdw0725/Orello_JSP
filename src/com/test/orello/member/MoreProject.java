package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/member/moreproject.do")
public class MoreProject extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String seq = (String) session.getAttribute("seq");
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<ProjectDTO> projectList = dao.getMoreProject(seq);
		
		JSONArray arr = new JSONArray();
		for (ProjectDTO dto : projectList) {
			JSONObject obj = new JSONObject();
			obj.put("seq", dto.getSeq());
			obj.put("name",dto.getName());
			obj.put("type",dto.getType());
			obj.put("color",dto.getColor());
			obj.put("firstpopular",dto.getFirstpopular());
			obj.put("secondpopular",dto.getSecondpopular());
			arr.add(obj);
		}
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		
	}
	
}
