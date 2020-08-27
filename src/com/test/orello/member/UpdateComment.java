package com.test.orello.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/updatecomment.do")
public class UpdateComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String seq = (String) session.getAttribute("seq");
		
		req.setCharacterEncoding("UTF-8");
		String comment = req.getParameter("comment");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setSeq(seq);
		dto.setStatusmsg(comment);
		
		dao.updateComment(dto);
		
		
	}
	
}
