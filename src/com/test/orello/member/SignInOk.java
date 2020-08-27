package com.test.orello.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.orello.member.MemberDAO;
import com.test.orello.member.MemberDTO;

@WebServlet("/member/signinok.do")
public class SignInOk extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		MultipartRequest multi = new MultipartRequest(
				req,
				req.getRealPath("/files/profile"),
				1024 * 1024 * 10,
				"UTF-8",
				new DefaultFileRenamePolicy()
			);

		System.out.println(req.getRealPath("/pic"));
		
		String name = multi.getParameter("name");
		String email = multi.getParameter("email");
		String pw = multi.getParameter("pw");
		String tel = multi.getParameter("tel");
		//multi.getOriginalFileName("profile")
		//multi.getFilesystemName("profile")
		String ori_file = multi.getOriginalFileName("profile") != null ? multi.getOriginalFileName("profile") : "nopic.png" ;
		String file = multi.getFilesystemName("profile") != null ? multi.getFilesystemName("profile") : "nopic.png";
		// String file = multi.getParameter("profile") != null ? multi.getParameter("profile") : "nopic.png";
		String company = multi.getParameter("company");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setName(name);
		dto.setEmail(email);
		dto.setPw(pw);
		dto.setTel(tel);
		dto.setOri_file(ori_file);
		dto.setFile(file);
		dto.setCompany(company);
		
		System.out.println(dto.toString());
		int result = dao.signIn(dto);
		dao.close();
		if(result == 1) {
			resp.sendRedirect("/orello/index.do");
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
