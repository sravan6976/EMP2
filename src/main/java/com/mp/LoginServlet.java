package com.mp;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "******",
					"9143344844");

			PreparedStatement ps = conn
					.prepareStatement("SELECT password, emp_code, email FROM employees WHERE userid = ?");
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String dbPassword = rs.getString("password");
				String empCode = rs.getString("emp_code");
				String email = rs.getString("email");

				if (password.equals(dbPassword)) {
					HttpSession session = request.getSession();
					session.setAttribute("userid", userid);
					session.setAttribute("empCode", empCode);
					session.setAttribute("email", email);

					if ("123456".equals(dbPassword)) {
						response.sendRedirect("changePassword.jsp");
					} else {
						response.sendRedirect("message.jsp");
					}
				} else {
					response.sendRedirect("login.jsp?error=Invalid%20credentials");
				}
			} else {
				response.sendRedirect("login.jsp?error=Invalid%20credentials");
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp?error=Database%20error");
		}
	}
}