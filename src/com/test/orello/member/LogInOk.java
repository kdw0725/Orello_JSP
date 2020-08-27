package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/loginok.do")
public class LogInOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setEmail(email);
		dto.setPw(pw);
		
		MemberDAO dao = new MemberDAO();
		MemberDTO member = new MemberDTO();
		member = dao.login(dto);
		dao.close();
		resp.setCharacterEncoding("UTF-8");
		if(member.getSeq() == null) {
			PrintWriter writer = resp.getWriter();
			writer.print("<html>");
			writer.print("<head>");
			writer.print("<meta charset=\"UTF-8\">");
			writer.print("</head>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('유효하지 않은 아이디/비밀번호입니다.'); history.back();");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");
			writer.close();
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("seq", member.getSeq());
			resp.sendRedirect("/orello/member/index.do");
		}
		
	}
	
}
