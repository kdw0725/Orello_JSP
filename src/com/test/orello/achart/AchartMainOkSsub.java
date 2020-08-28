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
 * 관리자가 로그인한 현재 날짜로부터 과거 일주일동안 Orello 모든 회원들의 채팅 빈도수를 차트로 나타내기 위한 데이터 보내주는 servlet
 *
 */
@WebServlet("/achart/achartmainokssub.do")
public class AchartMainOkSsub extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ChartDAO dao = new ChartDAO();
		
		ArrayList<ChartDTO> cntlist = dao.getCountCharting();
		
		JSONArray arr = new JSONArray();
		
		for(ChartDTO dto : cntlist) {
			arr.add(dto.getChatingCnt());
		}
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
	}
}
