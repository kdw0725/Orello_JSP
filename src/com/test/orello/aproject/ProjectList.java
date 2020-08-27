package com.test.orello.aproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/aproject/projectlist.do")
public class ProjectList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ProjectDAO dao = new ProjectDAO();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("begin", "1");
		map.put("end", "10");

		// 페이징 처리 관련 변수
		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 10; // 한페이지 당 출력 갯수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // rnum 시작 번호
		int end = 0; // rnum 끝 번호
		int n = 0; // 페이지바 관련 변수
		int loop = 0; // 페이지바 관련 변수
		int blockSize = 10; // 페이지바 관련 변수

		String page = req.getParameter("page");
		String search = req.getParameter("searchtxt");
		String soption = req.getParameter("soption");

	
//	
//		System.out.println(search);
//		System.out.println(soption);
		
		map.put("search", search);
		map.put("soption", soption);

		
		if (page == null || page == "")
			nowPage = 1; // default
		else
			nowPage = Integer.parseInt(page); //

		// nowPage -> 보려는 페이지 번호!!
		// 1page -> where rnum >= 1 and rnum <= 10
		// 2page -> where rnum >= 11 and rnum <= 20
		// 3page -> where rnum >= 21 and rnum <= 30
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;

		map.put("begin", begin + "");
		map.put("end", end + "");

		// 1.

		// 총 페이지 수 계산하기
		// 총 페이지 수 = 총 게시물 수 / 한페이지당 출력 게시물 수
		// ? = 175 / 10 -> 17.5 ->18
		totalCount = dao.getTotalCount(map);
		//System.out.println(search);
		 //System.out.println(totalCount);

		totalPage = (int) Math.ceil((double) totalCount / pageSize);
		// System.out.println(totalPage);

		ArrayList<ProjectDTO> list = dao.projectlist(map);

		// 페이지바 제작
		loop = 1;
		n = ((nowPage - 1) / blockSize) * blockSize + 1;

		// list.do?page=1
		// 1 2 3 4 5 6 7 8 9 10

		// list.do?page=3
		// 1 2 3 4 5 6 7 8 9 10

		// list.do?page=11
		// 11 12 13 14 15 16 17 18 19 20

		String pagebar = "";

		pagebar += "<nav class=\"pagebar\">";
		pagebar += "<ul class=\"pagination\">";

		// 이전 10페이지
		if (n == 1) {
			pagebar += "<li class='disabled'>";
			pagebar += "<a href=\"#!\" aria-label=\"Previous\">";
			pagebar += "<span aria-hidden=\"true\">&laquo;</span>";
			pagebar += "</a>";
			pagebar += "</li>";
		} else {
			if(search == null) {
				pagebar += "<li>";
				pagebar += String.format("<a href=\"/orello/aproject/projectlist.do?page=%d\" aria-label=\"Previous\">",
											n - 1);
				pagebar += "<span aria-hidden=\"true\">&laquo;</span>";
				pagebar += "</a>";
				pagebar += "</li>";
			} else {
			pagebar += "<li>";
			pagebar += String.format("<a href=\"/orello/aproject/projectlist.do?page=%d&search=%s&soption=%s\" aria-label=\"Previous\">",
										n - 1, map.get("search"), map.get("soption"));
			pagebar += "<span aria-hidden=\"true\">&laquo;</span>";
			pagebar += "</a>";
			pagebar += "</li>";
			}
		}

			


		// for (int i=1; i<=totalPage; i++) {
		while (!(loop > blockSize || n > totalPage)) {
			// 페이지 번호
			if (n == nowPage) {
				pagebar += "<li class='active'>";
				pagebar += String.format("<a href=\"#!\">%d</a>", n);
				pagebar += "</li>";
			} else {
				if(search == null) {
					pagebar += String.format("<li><a href=\"/orello/aproject/projectlist.do?page=%d\">"
							+ "%d</a></li>", n,n);
				} else {
					pagebar += "<li>";
					pagebar += String.format("<a href=\"/orello/aproject/projectlist.do?page=%d&searchtxt=%s&soption=%s\">%d</a>", 
							n, map.get("search"), map.get("soption"), n);
					pagebar += "</li>";
				}
				
			}

			loop++;
			n++;
		}

		// 다음 10페이지
		if (n > totalPage) {
			pagebar += "<li class='disabled'>";
			pagebar += "<a href=\"#!\" aria-label=\"Next\">";
			pagebar += "<span aria-hidden=\"true\">&raquo;</span>";
			pagebar += "</a>";
			pagebar += "</li>";
		} else {
			if(search == null) {
				pagebar += "<li>";
				pagebar += String.format("<a href=\"/orello/aproject/projectlist.do?page=%d\" aria-label=\"Next\">", n);
				pagebar += "<span aria-hidden=\"true\">&raquo;</span>";
				pagebar += "</a>";
				pagebar += "</li>";
			} else {
				pagebar += "<li>";
				pagebar += String.format("<a href=\"/orello/aproject/projectlist.do?page=%d&searchtxt=%s&soption=%s\" aria-label=\"Next\">", n, map.get("search"), map.get("soption"));
				pagebar += "<span aria-hidden=\"true\">&raquo;</span>";
				pagebar += "</a>";
				pagebar += "</li>";
			}
			

		}

		pagebar += "</ul>";
		pagebar += "</nav>";

		req.setAttribute("plist", list);
		req.setAttribute("searchtxt", search);
		req.setAttribute("soption", soption);
		req.setAttribute("map", map);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);
		req.setAttribute("pagebar", pagebar);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/aproject/projectlist.jsp");
		dispatcher.forward(req, resp);
	}

}
