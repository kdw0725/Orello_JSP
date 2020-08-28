package com.test.orello.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/writeok.do")
public class WriteOk extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req
			, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String seq = session.getAttribute("seq").toString(); //member seq
		String pseq = req.getParameter("pseq");
		
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		dto.setContent(content);
		dto.setTitle(title);
		dto.setPseq(pseq);
		dto.setMseq(seq);
		
		int result = dao.write(dto);
		
		if(result == 1) {
			//글쓰기 성공 -> 게시판 목록 보기로 이동
			resp.sendRedirect("/orello/board/list.do?pseq=" + pseq);
			
		}else {
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
