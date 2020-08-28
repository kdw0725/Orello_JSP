package com.test.orello.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/view.do")
public class View extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		String pseq = req.getParameter("pseq");
		String bseq = req.getParameter("seq"); //현재 게시글의 게시글 번호 list에서 받아옴
		String seq = "104"; //member seq
		
		String page = req.getParameter("page").equals("") ? "1" : req.getParameter("page");
		System.out.println("page:" + page);
		String search = req.getParameter("search") == null ? "" : req.getParameter("search");
		System.out.println(search);
		System.out.println("search: "+ search);
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.view(bseq);
		
		dto.setSeq(bseq);
		
		String commentCount = dao.commentCount(bseq); //댓글수
		
		
		ArrayList<CommentDTO> clist = dao.commentList(bseq);//댓글 리스트
		
		
		
		req.setAttribute("ccnt", commentCount);
		req.setAttribute("pseq", pseq);
		
		
		
		req.setAttribute("clist", clist);
		req.setAttribute("bdto", dto);
		req.setAttribute("page", page);
		req.setAttribute("search", search);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);
	
	
	}
}
