package com.test.orello.achart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;


/**
 * 
 * @author 강혜림
 * Orello 홈페이지에 생성된 모든 프로젝트 개수를 월 별로 통계낸 데이터를 보내주는 servlet
 *
 */
@WebServlet("/achart/aprojectmainok.do")
public class AprojectmainOk extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ChartDAO dao = new ChartDAO();
		ArrayList<MonproDTO> list = dao.plist();
		System.out.println(list);
		
		JSONArray arr = new JSONArray();
		
		for(MonproDTO dto : list) {
			arr.add(dto.getCnt());
		}
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
		
	}
}
