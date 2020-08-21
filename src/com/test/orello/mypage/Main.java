package com.test.orello.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/main.do")
public class Main extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String seq = "87";
		
		MypageDAO dao = new MypageDAO();
		MypageDTO dto = dao.getProfile(seq); 
		
		ArrayList<MyFriendDTO> flist = dao.getFriendList(seq);

		ArrayList<MyProjectDTO> plist = dao.getProjectList(seq);
		
		
		
		
		
		
		req.setAttribute("plist", plist);
		req.setAttribute("flist", flist);

		req.setAttribute("dto", dto);
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/mypage/main.jsp");
		dispatcher.forward(req, resp);
	
	}
}
