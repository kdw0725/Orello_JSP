package com.test.orello.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FAQ 삭제 기능을 위해 만든 클래스이다.
 * @author 강혜림
 *
 */
@WebServlet("/faq/faqdelete.do")
public class FaqDelete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] value = req.getParameterValues("checkbox");
		for(String val : value) {
			System.out.println(val);
		}
	}
}
