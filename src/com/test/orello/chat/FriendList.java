package com.test.orello.chat;

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

import com.test.orello.checklist.MemberDTO;

@WebServlet("/chat/friendlist.do")
public class FriendList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//회원번호 세션에서 받아왔다고 가정
		String mseq = "2";
		
		//DB작업
		ChatDAO dao = new ChatDAO();
		ArrayList<MemberDTO> list = dao.getFriendList(mseq);
		
		dao.close();
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		JSONArray arr = new JSONArray();
		for (MemberDTO dto : list) {
			JSONObject obj = new JSONObject();
			obj.put("mseq", dto.getMseq());
			obj.put("name", dto.getName());
			obj.put("profile", dto.getProfilepic());
			
			arr.add(obj);
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
		
	}
	
	
}
