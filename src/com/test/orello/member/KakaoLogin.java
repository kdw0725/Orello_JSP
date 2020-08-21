package com.test.orello.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/member/kakaologin.do")
public class KakaoLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String access_token = req.getParameter("access_token");
		String id = req.getParameter("id");
		String pro = req.getParameter("properties");
		
		System.out.println(access_token);
		System.out.println(id);
		System.out.println(pro);
	}
	
}
