package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/index.do")
public class Index extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String seq = (String) session.getAttribute("seq");
		if(seq == null) {
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			
			writer.print("<html>");
			writer.print("<head>");
			writer.print("<meta charset=\"UTF-8\">");
			writer.print("</head>");
			writer.print("<body>");
			writer.print("</body>");
			writer.print("<script>");
			writer.print("alert('로그인이 필요한 서비스입니다.');");
			writer.print("location.href = '/orello/member/login.do';");
			writer.print("</script>");
			writer.print("</html>");
			
			writer.close();
		} else {
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = new MemberDTO();
			
			dto = dao.getProfile(seq);
			req.setAttribute("member", dto);
			
			ArrayList<ProjectDTO> projectList = dao.getProjectList(seq);
			req.setAttribute("projectList", projectList);
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/index.jsp");
			dispatcher.forward(req, resp);
		}
		
	}
}
