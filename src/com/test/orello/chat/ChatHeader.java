package com.test.orello.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.test.orello.checklist.MemberDTO;

/**
 * 선택한 친구와의 채팅방 헤더를 출력하기 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/chat/chatheader.do")
public class ChatHeader extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//내 회원번호 -> 세션에서
		HttpSession session = req.getSession();
		String mseq = session.getAttribute("seq").toString();
		//채팅 상대 회원번호 -> 넘겨받음
		String fmseq = req.getParameter("mseq");	//186

		ChatDAO dao = new ChatDAO();
		MemberDTO dto = dao.getMemberInfo(fmseq);
		dao.close();
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		JSONObject obj = new JSONObject();
		obj.put("seq", dto.getMseq());
		obj.put("name", dto.getName());
		obj.put("profile", dto.getProfilepic());
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();

	}
	
}
