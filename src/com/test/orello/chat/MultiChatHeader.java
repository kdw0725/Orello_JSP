package com.test.orello.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.orello.checklist.MemberDTO;

/**
 * 프로젝트 채팅방 헤더 출력을 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/chat/multichatheader.do")
public class MultiChatHeader extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//프로젝트 번호 받아와서 프로젝트 이름, 멤버리스트를 채팅 헤더에 출력하기
		
		//정보 받아오기
		String pseq = req.getParameter("pseq");
		
		//DB작업
		ChatDAO dao = new ChatDAO();
		ChatRoomDTO dto = dao.getMultiChatInfo(pseq);
		dto.setList(dao.getMultiChatMember(pseq));
		dao.close();
		
		//데이터 반환을 위해 JSON형식으로 만들기
		JSONObject obj = new JSONObject();
		obj.put("seq", dto.getSeq());
		obj.put("pseq", dto.getPseq());
		obj.put("name", dto.getName());
		
		JSONArray arr = new JSONArray();
		for (MemberDTO mdto : dto.getList()) {
			JSONObject mobj = new JSONObject();
			mobj.put("mseq", mdto.getMseq());
			mobj.put("name", mdto.getName());
			mobj.put("profile", mdto.getProfilepic());
			arr.add(mobj);
		}
		obj.put("list", arr);
		
		//정보 넘겨주기
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
	
	}
	
}
