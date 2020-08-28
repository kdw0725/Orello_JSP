package com.test.orello.amember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/amember/userview.do")
public class UserView extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		//1. 데이터 가져오기(seq)
		
		
		//2. DB 작업 -> select 
		
		Calendar c = Calendar.getInstance();
		
		String seq = req.getParameter("seq");
		
		MemberDAO dao = new MemberDAO();
		
		//회원의 프로젝트 목록
		ArrayList<MemberDTO> info = dao.info(seq);
		
		//회원 정보
		ArrayList<MemberDTO> data = dao.data(seq);
		
		//회원의 프로젝트당 작성한 게시글 수
		ArrayList<String> proName = dao.projectName(seq);
		ArrayList<String> board = dao.boardCnt(seq);
		System.out.println(proName);
			
		//회원의 프로젝트 총 개수
		int pcnt = dao.project(seq);
		
		//회원의 게시글 총 개수
		int bcnt = dao.board(seq);
		
		req.setAttribute("info", info);
		req.setAttribute("pcnt", pcnt);
		req.setAttribute("bcnt", bcnt);
		req.setAttribute("data", data);
		req.setAttribute("board", board);
		req.setAttribute("proName", proName);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/amember/userView.jsp");
		dispatcher.forward(req, resp);
	}
}
