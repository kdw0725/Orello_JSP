package com.test.orello.checklist;

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

@WebServlet("/checklist/getselectedmemberlist.do")
public class GetSelectedMemberlist extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//체크리스트 항목 번호 받아오기
		String ciseq = req.getParameter("ciseq");
		System.out.println(ciseq);
		//DB Select
		//뭐필요한지..? -> 멤버참여번호, 담당자인지 여부, 이름, 프로필사진
		
		ChecklistDAO dao = new ChecklistDAO();
		ArrayList<MemberDTO> list = dao.getSelectedMemberList(ciseq);
		dao.close();
		
		
		//결과 반환
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		
		/*
		JSONObject o2 = new JSONObject();
		o2.put("name", "아무개");
		o2.put("age", "25");
		System.out.println(o2);		//{"name":"아무개","age":"25"}

		JSONArray arr = new JSONArray();
		arr.add(o1);
		arr.add(o2);
		System.out.println(arr);	//[{"name":"홍길동","age":"20"},{"name":"아무개","age":"25"}]
		*/
		
		JSONArray arr = new JSONArray();
		
		for(MemberDTO dto : list) {
			
			JSONObject member = new JSONObject();
		
			member.put("mseq", dto.getMseq());
			member.put("name", dto.getName());
			member.put("profile", dto.getProfilepic());
			member.put("assign", dto.getAssignFlag());

			arr.add(member);
			
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		
	}
	
}
