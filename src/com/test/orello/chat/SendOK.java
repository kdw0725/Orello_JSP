package com.test.orello.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet("/chat/sendok.do")
public class SendOK extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String mseq = session.getAttribute("seq").toString();
		String cseq = req.getParameter("cseq");
		String content = req.getParameter("content");
				
		//DB insert
		ChatDAO dao = new ChatDAO();
		MessageDTO dto = new MessageDTO();
		dto.setContent(content);
		dto.setCseq(cseq);
		
		//로그인한 사람의 채팅참여번호 가져오기
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mseq",mseq);
		map.put("cseq",cseq);
		dto.setCaseq(dao.getCaseq(map));
		
		//데이터 삽입해서 결과 가져오기
		int result = dao.addChatLog(dto);
		
		dao.close();
		
		//결과 반환
		JSONObject obj = new JSONObject();
		
		if (result == 1) {	//성공시
			obj.put("result", 1);
			obj.put("time", String.format("%tF %tT", Calendar.getInstance(), Calendar.getInstance()));
		} else {			//실패시
			obj.put("result", 0);
		}
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
		
	}
	
}
