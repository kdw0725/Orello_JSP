package com.test.orello.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.orello.checklist.MemberDTO;

@WebServlet("/chat/chatproject.do")
public class ChatProject extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//회원번호 세션에서 받아오기
		HttpSession session = req.getSession();
		String mseq = session.getAttribute("seq").toString();
		
		ChatDAO dao = new ChatDAO();
		ArrayList<ChatRoomDTO> list = dao.getProjectList(mseq);
		list = dao.getChatMember(list);
		
		dao.close();
		
		JSONArray arr = new JSONArray();
		
		for(ChatRoomDTO dto : list) {
			
			JSONObject obj = new JSONObject();
			obj.put("seq", dto.getSeq());
			obj.put("pseq", dto.getPseq());
			obj.put("name", dto.getName());
			
			JSONArray mlist = new JSONArray();
			
			for(MemberDTO mdto : dto.getList()) {
				
				JSONObject mobj = new JSONObject();
				mobj.put("mseq", mdto.getMseq());
				mobj.put("name", mdto.getName());
				mobj.put("profile", mdto.getProfilepic());
				
				mlist.add(mobj);
			}
			obj.put("mlist", mlist);
			
			arr.add(obj);
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(arr);
		writer.close();
	}
	
}
