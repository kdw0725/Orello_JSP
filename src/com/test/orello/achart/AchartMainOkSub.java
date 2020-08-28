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
 * 날짜별로 홈페이지에 접속한 회원수를 통계내기 위한 servlet
 *
 */
@WebServlet("/achart/achartmainoksub.do")
public class AchartMainOkSub extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ChartDAO dao = new ChartDAO();

		ArrayList<ChartDTO> cntlist = dao.getCountM();

		JSONArray arr = new JSONArray();
		
		for(ChartDTO dto : cntlist) {
			arr.add(dto.getLoginMemberCnt());
		}
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
	}
}
