package com.test.email;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/email/mailcheck.do")
public class SendMail extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		Random rnd = new Random();
		
		String code = "";
		for (int i = 0; i < 8; i++) {
			int flag = rnd.nextInt(2);
			if(flag == 0) {
				code += rnd.nextInt(10);
			} else {
				code += (char) (rnd.nextInt(26) + 97);
			}
		}
		
		String mail_from = "kimaron0725@gmail.com";
		String mail_to = email;
		String title = "Orello 회원가입 인증 메일입니다.";
		String contents = "Orello 회원가입 인증 메일입니다.\n인증번호["+code+"]를 정확하게 입력해주시기 바랍니다.";
		
		try {
			mail_from = new String(mail_from.getBytes("UTF-8"), "UTF-8");
            mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");
 
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.auth", "true");
 
            Authenticator auth = new SMTPAuthenticator();
 
            Session sess = Session.getDefaultInstance(props, auth);
 
            MimeMessage msg = new MimeMessage(sess);
 
            msg.setFrom(new InternetAddress(mail_from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
            msg.setSubject(title, "UTF-8");
            msg.setContent(contents, "text/html; charset=UTF-8");
            msg.setHeader("Content-type", "text/html; charset=UTF-8");
 
            Transport.send(msg);
            

		} catch (Exception e) {
			System.out.println("SendMail.doPost()");
			e.printStackTrace();
		}
		// 
		PrintWriter writer= resp.getWriter();
		writer.write("{");
		writer.write(String.format("\"code\" : \"%s\"",code));
		writer.write("}");
		writer.close();
	}
	
}
