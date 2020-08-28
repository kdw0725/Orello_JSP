package com.test.orello.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/list.do")
public class List extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		String pseq = req.getParameter("pseq");
		map.put("pseq", pseq);
		
		
		String search = req.getParameter("search");
		String soption = req.getParameter("soption");
		
		map.put("search", search);
		map.put("soption", soption);
		
		
		BoardDAO dao = new BoardDAO();
		
		//페이지바 제작
		
		//페이징 처리 관련 변수 
		int nowPage = 0;		//현재 페이지 번호
		int totalCount = 0;		//총 게시물 수
		int pageSize = 10;		// 한 페이지 당 출력 개수
		int totalPage = 0;		// 총 페이지 수
		int begin = 0;			//rnum 시작 번호
		int end = 0;			//rnum 끝 번호
		
		int loop = 0;			//페이지바 관련 변수
		int blockSize = 10;		//페이지바 관련 변수
		

		String page = req.getParameter("page");
		
		if(page == null || page == "") nowPage = 1;
		else nowPage = Integer.parseInt(page);
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize -1;
		
		System.out.println(begin);
		System.out.println(end);
		
		
		map.put("begin", begin+"");
		map.put("end", end+"");
		
		totalCount = dao.getTotalCount(map);//총게시물수
		
		totalPage = (int)Math.ceil((double)totalCount/pageSize);//한페이지당 출력개수

		int n =0; //페이지바 번호 출력하는 변수
		
		loop = 1; //루프변수 > 1로 초기화
		n = ((nowPage -1)/blockSize) * blockSize + 1;
		
		String pagebar ="";
		pagebar += "<div id=\"paging\">";
		pagebar += "<nav class=\"pagebar\">";
		pagebar += "<ul class=\"pagination\">";
		
		pagebar += "<li>";
		pagebar += "<a href=\"#\" aria-label=\"Previous\">";
		pagebar += "<span aria-hidden=\"true\">&laquo;</span>";
		pagebar += "</a>";
		pagebar += "</li>";
		
		
		while(!(loop > blockSize || n >totalPage)){
			
		if(n == nowPage) {	
		pagebar += String.format("<li class=\"active\"><a href=\"#\">%d</a></li>", n);
		}else {

			if(search == null) {
			pagebar += String.format("<li><a href=\"/orello/board/list.do?page=%d&pseq=%s\">"
						+ "%d</a></li>", n, pseq, n);
			}else {
				pagebar += String.format(
						"<li><a href=\"/orello/board/list.do?page=%d&search=%s&soption=%s&pseq=%s\">"
						+ "%d</a></li>", n, map.get("search"), map.get("soption"), pseq, n);
				
			}
		}
		loop++;
		n++;
		}
		
		pagebar += "<li>";
		pagebar += "<a href=\"#\" aria-label=\"Next\">";
		pagebar += "<span aria-hidden=\"true\">&raquo;</span>";
		pagebar += "</a>";
		pagebar += "</li>";
		
		pagebar += "</ul>";
		pagebar += "</nav>";
		pagebar += "</div>";
		
		
//		System.out.println(list.toString());
		
		
		
		ArrayList<BoardDTO> list = dao.list(map);
		
		Calendar now = Calendar.getInstance();
		
		for(BoardDTO dto : list) {
			
			if(dto.getRegdate().startsWith(String.format("%tF", now))) {
				dto.setRegdate(dto.getRegdate().substring(11));
			}else {
				
				dto.setRegdate(dto.getRegdate().substring(0, 10));
			}
			
			
			if(search != null && search != "") {
				
			}
			
			
			
		}//for
		
		
		
		
		
		req.setAttribute("list", list);
		req.setAttribute("search",search);
		req.setAttribute("soption",soption);
		
		
		req.setAttribute("page", page);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		
		req.setAttribute("pagebar", pagebar);
		req.setAttribute("pseq", pseq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);
	
	
	}
}
