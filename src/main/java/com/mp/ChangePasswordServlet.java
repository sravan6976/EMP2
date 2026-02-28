package com.mp;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ChangePasswordServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userid") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String userid = request.getParameter("userid");
		String newPassword = request.getParameter("newPassword");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
					"8142299799");

			PreparedStatement ps = conn.prepareStatement("UPDATE employees SET password = ? WHERE userid = ?");
			ps.setString(1, newPassword);
			ps.setString(2, userid);
			int rows = ps.executeUpdate();

			if (rows > 0) {
				response.sendRedirect("message.jsp");
			} else {
				response.sendRedirect("changePassword.jsp?error=Update%20failed");
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("changePassword.jsp?error=Database%20error");
		}
	}
}