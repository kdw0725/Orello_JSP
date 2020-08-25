package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/member/googlelogin.do")
public class GoogleLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String token = req.getParameter("token");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		email += ".gmail";
		
		dto.setName(name);
		dto.setEmail(email);
		
		String rndtel = "";
		Random rnd = new Random();
		for (int i = 0; i < rnd.nextInt(4) + 5; i++) {
			rndtel += (char) (rnd.nextInt(26) + 97);
		}
		for (int i = 0; i < rnd.nextInt(10) + 3; i++) {
			rndtel += rnd.nextInt(10);
		}
		
		dto.setTel(rndtel);
		dto.setSocial("GOOGLE");
		MemberDTO member = dao.signInCheck(dto);
		
		if(member.getSeq() == null) {
			dao.socialImg();
			dao.socialSignIn(dto);
			member = dao.signInCheck(dto);
		}
		dao.close();
		
		HttpSession session = req.getSession();
		
		session.setAttribute("seq", member.getSeq());
		JSONObject obj = new JSONObject();
		obj.put("result", 1);
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
	}
	
}
