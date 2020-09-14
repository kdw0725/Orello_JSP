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
 * 체크리스트 체크/체크해제 여부를 데이터베이스에 저장하기 위한 서블릿입니다.
 * @author Doyun Lee
 *
 */
@WebServlet("/checklist/changecheckok.do")
public class ChangeCheckOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String ciseq = req.getParameter("ciseq");
		String flag = req.getParameter("flag");
		
		ChecklistItemDTO dto =  new ChecklistItemDTO();
		dto.setSeq(ciseq);
	
		if (flag.equals("true")) {		//완료된 상태로 업데이트를 원하는 경우
			dto.setCompleteflag("1");
		} else {						//미완료 상태로 업데이트를 원하는 경우
			dto.setCompleteflag("0");
		}
		
		ChecklistDAO dao = new ChecklistDAO();
		int result = dao.changeCheck(dto);
		
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
