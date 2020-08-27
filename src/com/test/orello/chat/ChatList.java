package com.test.orello.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/chat/chatlist.do")
public class ChatList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//내 회원번호 -> 세션에서
		String mseq = "2";
		//채팅 상대 회원번호 -> 넘겨받음
		String fmseq = req.getParameter("mseq");	//186
		
		//DB에서 대화내용 받아오기
		ChatDAO dao = new ChatDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mseq", mseq);
		map.put("fmseq", fmseq);
		ArrayList<MessageDTO> list = dao.getChatList(map);
		dao.close();
		
		JSONArray arr = new JSONArray();
		
		for(MessageDTO dto : list) {
			
			JSONObject obj = new JSONObject();
			obj.put("seq", dto.getSeq());
			obj.put("mseq", dto.getMseq());
			obj.put("cseq", dto.getCseq());
			obj.put("caseq", dto.getCaseq());
			obj.put("content", dto.getContent());
			obj.put("regdate", dto.getRegdate());
			obj.put("regdate", dto.getRegdate());
			
			arr.add(obj);
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
	
	}
}
