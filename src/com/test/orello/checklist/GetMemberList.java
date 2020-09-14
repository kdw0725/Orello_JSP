package com.test.orello.checklist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 프로젝트 참여 멤버를 출력하기 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/checklist/getmemberlist.do")
public class GetMemberList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1. 데이터 받아오기(cseq)
		//2. DB작업(select)
		//3. 결과 반환하기
	
		//1.
		String cseq = req.getParameter("cseq");
//		System.out.println(cseq);
		
		
		//2. 
		ChecklistDAO dao = new ChecklistDAO();
		ArrayList<MemberDTO> list = dao.getMemberList(cseq);
		dao.close();
		
		//3.
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
	
		PrintWriter writer = resp.getWriter();
		/*
			{
				"paseq": "0",
				"mseq": "0",
				"name": "name",
				"position": "p",
				"profile": "p"
			}
		*/
		String temp = "";
		temp += "[";
		
		for(MemberDTO dto : list) {
			
			temp += "{";
			temp += String.format("\"paseq\": \"%s\",", dto.getPaseq());
			temp += String.format("\"mseq\": \"%s\",", dto.getMseq());
			temp += String.format("\"name\": \"%s\",", dto.getName());
			temp += String.format("\"position\": \"%s\",", dto.getPosition());
			temp += String.format("\"profile\": \"%s\"", dto.getProfilepic());
			temp += "}";
			temp += ",";
		}
		
		temp = temp.substring(0, temp.length()-1);
		temp += "]";
		writer.print(temp);
		
		writer.close();
		
	}
	
	
	
}
