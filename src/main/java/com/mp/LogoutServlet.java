package com.mp;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false); // don't create if it doesn't exist
		if (session != null) {
			session.invalidate(); // destroy session
		}
		
		response.sendRedirect("login.jsp"); // redirect to login page
	}
}