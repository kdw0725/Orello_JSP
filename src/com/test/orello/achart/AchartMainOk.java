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
