package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/signin.do")
public class SignIn extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String seq = (String)session.getAttribute("seq");
		System.out.println(seq);
		if(seq == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/signin.jsp");
			dispatcher.forward(req, resp);
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<html>");
			writer.print("<head>");
			writer.print("</head>");
			writer.print("<body>");
			writer.print("</body>");
			writer.print("<script>");
			writer.print("location.href = '/orello/member/index.do'");
			writer.print("</script>");
			writer.print("</html>");
		}
		
	}
	
}
