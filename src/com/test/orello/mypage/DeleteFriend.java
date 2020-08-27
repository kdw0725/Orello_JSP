package com.test.orello.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/deleteFriend.do")
public class DeleteFriend extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		
		String seq = "87";
		
		String fseq = req.getParameter("fseq");
		System.out.println("fseq" + fseq);
		
		
		
		MypageDAO dao = new MypageDAO();
		
		int result = dao.deleteFriend(seq, fseq);
		
		PrintWriter writer = resp.getWriter();
		writer.print(result);
		writer.close();
	
	}
	
	
}
