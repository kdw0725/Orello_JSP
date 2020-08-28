package com.test.orello.aproject;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/aproject/projectlistsub.do")
public class ProjectListSub extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			//seq : 해당 프로젝트 키값
		String seq = req.getParameter("seq");
		
		
		ProjectDAO dao = new ProjectDAO();
		ProjectDTO dto = dao.projectsublist(seq);
		ArrayList<ProjectDTO> list = dao.memberlist(seq);
		ProjectDTO dto2 = dao.memberLeader(seq);
		
		//팀원참여도
		ArrayList<String> chartmemberlist = dao.joinmemberlist(seq);
		ArrayList<Integer> chartjoinlist = dao.joinlist(seq);
		//System.out.println(chartjoinlist + "@@@@@@");
		
		//날짜별 활성화율
		ArrayList<String> projectDate = dao.pDatelist(seq);
		
		//날짜별 활성화율 자유게시판 데이터
		ArrayList<Integer> projectuseBoard = dao.pUselistBoard(seq);
		System.out.println(projectuseBoard + " 자유게시판 데이터");
		//날짜별 활성화율 자료실 데이터
		ArrayList<Integer> projectuseArchive = dao.pUselistArchive(seq);
		System.out.println(projectuseArchive + " 자료실 데이터");

		//날짜별 활성화율 전체 데이터
		ArrayList<Integer> projectuseTotal = dao.pUselistTotal(seq);
		System.out.println(projectuseTotal + " 전체 데이터");
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("list", list);
		req.setAttribute("dto2", dto2);
		//팀원 참여도
		req.setAttribute("membername", chartmemberlist);
		req.setAttribute("joinchart", chartjoinlist);
		
		//날짜별 활성화 차트
		req.setAttribute("projectDate", projectDate);
		req.setAttribute("projectuseBoard", projectuseBoard);
		req.setAttribute("projectuseArchive", projectuseArchive);
		req.setAttribute("projectuseTotal", projectuseTotal);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/aproject/projectlistsub.jsp");
		dispatcher.forward(req, resp);
	}
	
}
