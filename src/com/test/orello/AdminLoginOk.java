package com.test.orello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminloginok.do")
public class AdminLoginOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		DBUtil util = new DBUtil();
		Connection conn = util.open();
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		String seq = "";
		String sql = "SELECT SEQ FROM TBL_ADMIN WHERE ID = ? AND PW = ? AND DELFLAG = 0";
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setString(2, pw);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				seq = rs.getString("SEQ");
			} else {
				seq = null;
			}
			conn.close();
		} catch (Exception e) {
			System.out.println("AdminLoginOk.doPost()");
			e.printStackTrace();
		}
		
		if(seq == null) {
			resp.setCharacterEncoding("UTF-8");
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
			session.setAttribute("seq", seq);
			resp.sendRedirect("/orello/achart/amain.do");
		}
				
		
	}
	
}
