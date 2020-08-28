package com.test.orello.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/createproject.do")
public class CreateProject extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String startdate = req.getParameter("startdate");
		String enddate = req.getParameter("enddate");
		String[] workers = req.getParameterValues("member_seq");
		
		String temp = req.getParameter("projectType");
		
		String projectType = ""; 
		if(temp.equals("web")) {
			projectType = "웹 프로젝트";
		} else if(temp.equals("android")) {
			projectType = "안드로이드 프로젝트";
		} else if(temp.equals("ios")) {
			projectType = "IOS 프로젝트";
		} else {
			projectType = "기타 프로젝트";
		}
			
		
		
		HttpSession session = req.getSession();
		
		
		String seq = (String) session.getAttribute("seq"); 
		
		
		MemberDAO dao = new MemberDAO();
		ProjectDTO dto = new ProjectDTO();
		
		dto.setName(name);
		dto.setDescription(description);
		dto.setStartdate(startdate);
		dto.setEnddate(enddate);
		dto.setType(projectType);
		
				
		
		dao.createProject(dto);
		
		dao.insertLeader(seq);
		
		if(workers != null) {
			for (String member : workers) {
				dao.insertMember(member);
			}
		}
		resp.sendRedirect("/orello/member/index.do");
	}
	
}
