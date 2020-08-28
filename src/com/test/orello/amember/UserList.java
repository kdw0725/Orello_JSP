package com.test.orello.amember;

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




@WebServlet("/amember/userlist.do")
public class UserList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 가져오기
		//2. DB 작업 -> select
		//3. 결과 반환 + JSP 호출하기
		
		HttpSession session = req.getSession();
		
		
		String search = req.getParameter("search");
		String page = req.getParameter("page");
		String soption = req.getParameter("soption");
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("search", search);
		map.put("soption", soption);
		
		
		//페이징 처리 관련 변수 생성
		int nowPage = 0;	//현재 페이지 번호
		int totalCount = 0;	//총 게시물 수
		int pageSize = 10;	//한페이지 당 출력 갯수
		int totalPage = 0;	//총 페이지 수
		int begin = 0;		//rnum 시작 번호
		int end = 0;		//rnum 끝 번호
		int n = 0;			//페이지바 관련 변수
		int loop = 0;		//페이지바 관련 변수
		int blockSize = 10;	//페이지바 관련 변수
		
		//list.do -> list.do?page=1 변경 기본값 처리			
		//list.do?page=1	변경후
		
		
		if (page == null || page == "") {
			
			nowPage = 1; //default 처리
			
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		MemberDAO dao = new MemberDAO();
		
		totalCount = dao.getTotalCount(map);
		
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		ArrayList<MemberDTO> list = dao.list(map);
		
		for (MemberDTO dto : list) {
			
			//검색 결과 강조
			if (search != null && search != "") {
				// 작성자
				String name = dto.getName();
				name = name.replace(search, "<span style='font-weight:bold; color:tomato;'>" + search + "</span>");
				dto.setName(name);
				
			}
			
			if (search != null && search != "") {
				// 이메일
				String email = dto.getEmail();
				email = email.replace(search, "<span style='font-weight:bold; color:tomato;'>" + search + "</span>");
				dto.setEmail(email);
				
			}
		}

		//페이지바 제작
				loop = 1;
				n = ((nowPage - 1) / blockSize) * blockSize + 1; // n은 출발 시드값
				
				String pagebar = "";
				
				pagebar += "<nav id=\"paging\">";
				pagebar += "<ul class=\"pagination\">";
				
				//이전 10페이지
				
				if (n == 1) {
					pagebar += "<li class='disabled'>";
					pagebar += "<a href=\"#!\" aria-label=\"Previous\">";
					pagebar += "<span aria-hidden=\"true\">&laquo;</span>";
					pagebar += "</a>";
					pagebar += "</li>";
					
				} else {
					if(search == null) {
						pagebar += "<li>";
						pagebar += String.format("<a href=\"/orello/amember/UserList.do?page=%d\" aria-label=\"Previous\">", n - 1);
						pagebar += "<span aria-hidden=\"true\">&laquo;</span>";
						pagebar += "</a>";
						pagebar += "</li>";
					} else {
						pagebar += "<li>";
						pagebar += String.format("<a href=\"/orello/amember/UserList.do?page=%d&search=%s&soption=%s\" aria-label=\"Previous\">", n - 1, map.get("search"), map.get("soption"));
						pagebar += "<span aria-hidden=\"true\">&laquo;</span>";
						pagebar += "</a>";
						pagebar += "</li>";
					}
					
				}

				
				while (!(loop > blockSize || n > totalPage)) {
					// 페이지 번호
					if (n == nowPage) {
						pagebar += "<li class='active'>";
						pagebar += String.format("<a href=\"#!\">%d</a>", n);
						pagebar += "</li>";
						
					} else {
						
						if(search == null) {
							
							pagebar += String.format("<li><a href=\"/orello/amember/UserList.do?page=%d\">"
									+ "%d</a></li>", n,n);
						} else {
							
							pagebar += "<li>";
							pagebar += String.format("<a href=\"/orello/amember/UserList.do?page=%d&search=%s&soption=%s\">%d</a>", 
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
						pagebar += String.format("<a href=\"/orello/amember/UserList.do?page=%d\" aria-label=\"Next\">", n);
						pagebar += "<span aria-hidden=\"true\">&raquo;</span>";
						pagebar += "</a>";
						pagebar += "</li>";
						
						
						//pagebar += String.format("<li><a href=\"/orello/amember/UserList.do?page=%d\" aria-label=\"Next\"></li>", n);
						
					} else {
						
						
						pagebar += "<li>";
						pagebar += String.format("<a href=\"/orello/amember/UserList.do?page=%d&search=%s&soption=%s\" aria-label=\"Next\">", n, map.get("search"), map.get("soption"));
						pagebar += "<span aria-hidden=\"true\">&raquo;</span>";
						pagebar += "</a>";
						pagebar += "</li>";
						
						
						
//						pagebar += "<li>";
//						pagebar += String.format("<a href=\"/orello/amember/UserList.do?page=%d&search=%s&soption=%s\" aria-label=\"Next\">", 
//								n, map.get("search"), map.get("soption"));
//						pagebar += "</li>";
					}
					
				}

				pagebar += "</ul>";
				pagebar += "</nav>";
				
				//2.
				req.setAttribute("list", list);
				req.setAttribute("search", search);
				req.setAttribute("map", map);
				req.setAttribute("soption", soption);
				req.setAttribute("page", page);
				req.setAttribute("totalCount", totalCount);
				req.setAttribute("totalPage", totalPage);
				req.setAttribute("pagebar", pagebar);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/amember/userList.jsp");
		dispatcher.forward(req, resp);
	}

}
