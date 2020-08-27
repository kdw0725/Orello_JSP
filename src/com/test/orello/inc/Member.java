package com.test.orello.inc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.orello.project.ProjectDAO;
import com.test.orello.project.ProjectDTO;

@WebServlet("/inc/member.do")
public class Member extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//프로젝트 번호 넘겨받음
		String pseq = "1";
		
		//DB에서 프로젝트 정보 가져옴
		ProjectDAO dao = new ProjectDAO();
		ProjectDTO dto = dao.getProjectInfo(pseq);
		dto.setMlist(dao.getMemberList(pseq));
		dao.close();
		
		//System.out.println(dto.getName());
		
		//가져온 정보 넘겨줌
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/inc/member.jsp");
		//서블릿 넘길때..include로 해야함! 
		dispatcher.include(req, resp);
	
	}
	
}
