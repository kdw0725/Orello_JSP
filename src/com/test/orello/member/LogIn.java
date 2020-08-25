package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/login.do")
public class LogIn extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String seq = (String)session.getAttribute("seq");
		
		if(seq == null) {
			String clientId = "HMv69t53qZKJkFt2qZqb";
			String redirectURI = URLEncoder.encode("http://localhost:8090/orello/member/navercallback.do", "UTF-8");
			SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    String naverApiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		    naverApiURL += "&client_id=" + clientId;
		    naverApiURL += "&redirect_uri=" + redirectURI;
		    naverApiURL += "&state=" + state;
		    
		    req.setAttribute("naverApiURL", naverApiURL);
		    
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
			dispatcher.forward(req, resp);

		} else{
			PrintWriter writer = resp.getWriter();
			writer.print("<html>");
			writer.print("<head>");
			writer.print("</head>");
			writer.print("<body>");
			writer.print("</body>");
			writer.print("<script>");
			writer.print("location.href = '/orello/member/index.do'");
			writer.print("</script>");
			writer.print("</html>");
		}
	}
	
}
