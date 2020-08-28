package com.test.orello.inc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author 강혜림
 * 로그인한 관리자의 seq값을 관리자 모든 servlet에 전송하기 위한 servlet
 *
 */
@WebServlet("/inc/admin.do")
public class Admin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//관리자 번호 넘겨받음
		String aseq = "1";
		//System.out.println(aseq);
		
		//DB에서 프로젝트 정보 가져옴
		AdminDAO dao = new AdminDAO();
		AdminDTO dto = dao.getAdminInfo(aseq);
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/inc/admin.jsp");
		//서블릿 넘길때..include로 해야함! 
		dispatcher.include(req, resp);
			}
}
