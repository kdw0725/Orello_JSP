package com.test.orello.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/comment.do")
public class Comment extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		
		//댓글쓰는 서블릿
		String comment_content = req.getParameter("comment_content");
		String mseq = "104"; //member seq
		 
		String pseq = "1"; //project seq
		String bseq = req.getParameter("seq");//board seq
		String page = req.getParameter("page");
		
		BoardDAO dao = new BoardDAO();
		
		CommentDTO cdto = new CommentDTO();
		cdto.setBseq(bseq);
		cdto.setMseq(mseq);
		cdto.setPsaq(pseq);
		cdto.setContent(comment_content);
		
		
		int result = dao.writeComment(cdto);

		if(result == 1) {
			//댓글쓰기 성공
			resp.sendRedirect(String.format("/orello/board/view.do?seq=%d&page=%d",bseq,page));
			
		}else {
			//댓글쓰기 실패
			PrintWriter writer = resp.getWriter();
			writer.print("<html>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('failed'); histoty.back();");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");
			
			writer.close();

		}
		
		
		
		
	}
	
	
	
}
