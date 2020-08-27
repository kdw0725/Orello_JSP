package com.test.orello.checklist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checklist/deletechecklistitemok.do")
public class DeleteChecklistItemOk extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 데이터 받아오기
		//2. DB작업(update)
		//3. 결과 반환
		
		//1.
		String ciseq = req.getParameter("ciseq");
		
		//2.
		ChecklistDAO dao = new ChecklistDAO();
		int result = dao.delChecklistItem(ciseq);
		
		//3.
		if (result == 1) {
			resp.sendRedirect("/orello/checklist/list.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<html>");
			writer.print("<body>");
			writer.print("<script>");
			writer.print("alert('failed'); history.back();");
			writer.print("</script>");
			writer.print("</body>");
			writer.print("</html>");
			writer.close();
		}
		
	}
	
}
