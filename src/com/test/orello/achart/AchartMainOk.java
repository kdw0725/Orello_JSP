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
import org.json.simple.JSONObject;


/**
 * 
 * @author 강혜림
 * 관리자가 접속한 현재 날짜로부터 과거 일주일의 날짜 리스트를 차트에 출력하기 위한 데이터를 보내주는 servlet
 *
 */
@WebServlet("/achart/achartmainok.do")
public class AchartMainOk extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		ChartDAO dao = new ChartDAO();
		
		ArrayList<ChartDTO> datelist = dao.getLoginM();
		
		//System.out.println(datelist);
		
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		String temp = "";
		
		temp += "[";

		for(ChartDTO dto : datelist) {
			
			temp += String.format("\"%s\",", dto.getRegdate());
		}

		temp = temp.substring(0,temp.length()-1);
		temp += "]";
		
		
		System.out.println(temp);
		writer.print(temp);
		writer.close();

	}
}
