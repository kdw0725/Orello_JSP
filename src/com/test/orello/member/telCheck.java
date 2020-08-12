package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/telCheck.do")
public class telCheck extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tel = req.getParameter("tel");
		
		MemberDAO dao = new MemberDAO();
		int result = dao.telCheck(tel);
		
		dao.close();
		
		PrintWriter writer = resp.getWriter();
		writer.print("{");
		writer.printf("\"result\" : \"%d\"", result);
		writer.print("}");
		
	}
	
}
