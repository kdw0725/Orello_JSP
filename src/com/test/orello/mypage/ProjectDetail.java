package com.test.orello.mypage;

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

@WebServlet("/mypage/projectdetail.do")
public class ProjectDetail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pseq = req.getParameter("seq");

		// System.out.println(pseq);

		MypageDAO dao = new MypageDAO();

		MyProjectDTO pdto = dao.getProjectDetail(pseq);

		ArrayList<MemberDTO> mlist = dao.getAttendant(pseq);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		PrintWriter writer = resp.getWriter();

		/*
		 * { "name":name, "startdate":ddd, "enddate":ddd, "regdate":ddd, "type":ddd 
		 * 		"list": []
		 * }
		 * 
		 * 
		 */

			JSONArray arr = new JSONArray();


			ArrayList<Object> list = new ArrayList<Object>();
			
			for(MemberDTO dto : mlist) {
				list.add(dto.getName());
			}
			
			System.out.println(list);
			
			
			JSONObject obj = new JSONObject();
			
			obj.put("name", pdto.getName());
			obj.put("startdate", pdto.getStartdate().substring(0,10));
			obj.put("enddate", pdto.getEnddate().substring(0,10));
			obj.put("gegdate", pdto.getRegdate().substring(0,10));
			obj.put("type", pdto.getType());
			obj.put("member",list);
			
			

		
		writer.print(obj);

		writer.close();
		// System.out.println(pdto);

		// req.setAttribute("pdto", pdto);

	}

}
