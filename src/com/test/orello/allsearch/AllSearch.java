package com.test.orello.allsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/allsearch/allsearch.do")
public class AllSearch extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String searchAll = req.getParameter("searchAll");
		
		
		//나중에 session에서 회원 번호 가져오기
		String mseq = "4";
		
		HttpSession session = req.getSession();
		
		SearchDAO dao = new SearchDAO();
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("begin", "1");
		map.put("end", "5");
		map.put("searchAll", searchAll);
		map.put("mseq", mseq);
		
		//자유 게시판
		ArrayList<SearchDTO> free = dao.free(map);
		int freeCount = dao.countFree(map);
		
		//전체 공지사항
		ArrayList<SearchDTO> notice = dao.notice(map);
		int noticeCount = dao.countNotice(map);
		
		//프로젝트 공지사항
		ArrayList<SearchDTO> pNotice = dao.pNotice(map);
		int pNoticeCount = dao.countPNotice(map);
		
		
		req.setAttribute("searchAll", searchAll);
		
		req.setAttribute("notice", notice);
		req.setAttribute("pNotice", pNotice);
		req.setAttribute("free", free);
		
		req.setAttribute("map", map);
		
		req.setAttribute("freeCount", freeCount);
		req.setAttribute("noticeCount", noticeCount);
		req.setAttribute("pNoticeCount", pNoticeCount);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/allsearch/allsearch.jsp");
		dispatcher.forward(req, resp);
		
		
	}
}
