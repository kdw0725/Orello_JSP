package com.test.orello.member;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet("/member/kakaologin.do")
public class KakaoLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String token = req.getParameter("token");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		id = id +"kakao.com.kakao";
		
		dto.setName(name);
		dto.setEmail(id);
		dto.setSocial("KAKAO");
		
		String rndtel = "";
		Random rnd = new Random();
		for (int i = 0; i < rnd.nextInt(4) + 5; i++) {
			rndtel += (char) (rnd.nextInt(26) + 97);
		}
		for (int i = 0; i < rnd.nextInt(10) + 3; i++) {
			rndtel += rnd.nextInt(10);
		}
		dto.setTel(rndtel);
		
		MemberDTO member = dao.signInCheck(dto);
		
		if(member.getSeq() == null) {
			dao.kakaoSignIn(dto);
			member = dao.signInCheck(dto);
		}
		
		HttpSession session = req.getSession();
		
		session.setAttribute("seq", member.getSeq());
		resp.sendRedirect("/orello/member/index.do");
		
		
	}
	
}
