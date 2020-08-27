package com.test.orello.allsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/allsearch/allsearchfree.do")
public class AllSearchFree extends HttpServlet {

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
		ArrayList<SearchDTO> free = dao.free(map);
		
		dao.close();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		/*
		[
			{
				
				"title": "글제목",
				"content": "이름",
			}
			,
		]
		
		
		 */
		PrintWriter writer = resp.getWriter();
		
		String temp = "";
		
		temp += "[";
		for (SearchDTO dto : free) {
			
		temp += "{";
		temp += String.format("\"title\": \"%s\",", dto.getTitle());
		temp += String.format("\"content\": \"%s\"", dto.getContent());
		temp += "}";
		temp += ",";
	
		}
		temp = temp.substring(0, temp.length()-1);
		temp += "]";
		
		writer.print(temp);
		writer.close();
	}
}
