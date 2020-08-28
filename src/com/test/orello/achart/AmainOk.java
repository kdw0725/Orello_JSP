package com.test.orello.achart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;


/**
 * 
 * @author 강혜림
 * 자주 사용하는 Programming Language 차트를 표현하기 위한 Orello 모든 회원의 pl 사용량 데이터 통계낸 servlet
 *
 */
@WebServlet("/achart/amainok.do")
public class AmainOk extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ChartDAO dao = new ChartDAO();
		ArrayList<PlDTO> list = dao.pllist();
		System.out.println(list);
		
		JSONArray arr = new JSONArray();
		
		for (PlDTO dto : list) {
			arr.add(dto.getCnt());
		}
		
		//System.out.println(arr);
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();

	}
}
