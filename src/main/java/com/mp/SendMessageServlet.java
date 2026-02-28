package com.mp;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SendMessageServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userid") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String empCode = (String) session.getAttribute("empCode");
		String email = (String) session.getAttribute("email");
		String messageText = request.getParameter("message");

		try (Connection conn = DBUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO messages (empCode, email, message, sent_time) VALUES (?, ?, ?, SYSDATE)");
			ps.setString(1, empCode);
			ps.setString(2, email);
			ps.setString(3, messageText);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("message.jsp?error=DB%20error");
			return;
		}

		try {
			final String senderEmail = "sravan6976@gmail.com";
			final String senderPassword = "**** **** **** ****";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session mailSession = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(senderEmail, senderPassword);
				}
			});

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(senderEmail));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sravanmannam9999@gmail.com"));
			msg.setSubject("Message from Employee: " + empCode);
			msg.setText("Employee Code: " + empCode + "\nEmail: " + email + "\n\nMessage:\n" + messageText);

			Transport.send(msg);
			response.sendRedirect("message.jsp?success=Message%20Sent");
		} catch (MessagingException e) {
			e.printStackTrace();
			response.sendRedirect("message.jsp?error=Email%20Send%20Failed");
		}
	}
}