package com.test.orello.allsearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 통합 검색에서 관리자가 올린 공지사항을 검색하기 위한 클래스이다.
 * @author 강경원
 *
 */
@WebServlet("/allsearch/allsearchnotice.ok")
public class AllSearchNotice extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String searchAll = req.getParameter("searchAll");
		String begin = req.getParameter("begin");
		String end = req.getParameter("end");
		String mseq = "4";
		
		SearchDAO dao = new SearchDAO();
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("searchAll", searchAll);
		map.put("mseq", mseq);
		
		//자유 게시판
		ArrayList<SearchDTO> notice = dao.notice(map);
		
		dao.close();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		/*
		[
			{
				
				"ntitle": "글제목",
				"ncontent": "이름",
			}
			,
		]
		
		
		 */
		PrintWriter writer = resp.getWriter();
		
		String temp = "";
		
		temp += "[";
		for (SearchDTO dto : notice) {
			
		temp += "{";
		temp += String.format("\"ntitle\": \"%s\",", dto.getNtitle());
		temp += String.format("\"ncontent\": \"%s\"", dto.getNcontent());
		temp += "}";
		temp += ",";
	
		}
		temp = temp.substring(0, temp.length()-1);
		temp += "]";
		
		writer.print(temp);
		writer.close();
	}
	
}
