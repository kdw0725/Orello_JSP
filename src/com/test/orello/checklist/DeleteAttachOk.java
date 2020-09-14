package com.test.orello.checklist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * 첨부파일 삭제를 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/checklist/deleteattachok.do")
public class DeleteAttachOk extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String aseq = req.getParameter("aseq");
		
		ChecklistDAO dao = new ChecklistDAO();
		int result = dao.deleteAttach(aseq);
		
		PrintWriter writer = resp.getWriter();

		if (result == 1) {
			
			JSONObject obj = new JSONObject();
			obj.put("result", 1);
			writer.print(obj);
			writer.close();
			
		} else {
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
