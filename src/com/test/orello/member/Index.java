package com.test.orello.member;

import java.io.IOException;
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
