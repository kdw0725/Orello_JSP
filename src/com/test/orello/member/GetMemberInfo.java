package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/member/getmemberinfo.do")
public class GetMemberInfo extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMemberInfo(seq);
		dao.close();
		
		JSONObject obj = new JSONObject();
		obj.put("seq", dto.getSeq());
		obj.put("name", dto.getName());
		obj.put("file", dto.getFile());
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
		
	}
	
}
