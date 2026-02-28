<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Send Message</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container">
		<div class="logo-container">
			<img src="images/Reliance_Communications_Logo.svg.png"
				alt="Company Logo" class="company-logo">
		</div>
		<h2>Send Message</h2>
		<form method="post" action="SendMessageServlet">
			<textarea name="message" placeholder="Write your message here..."
				rows="6" required></textarea>
			<input type="submit" value="Send Message">
		</form>
		<form action="LogoutServlet" method="get"
			style="text-align: center; margin-top: 20px;">
			<button type="submit" class="logout-btn">Logout</button>
		</form>
		<%
		String success = request.getParameter("success");
		if (success != null) {
		%>
		<p style="color: green;"><%=success%></p>
		<%
		}
		%>
	</div>
	<div class="footer">© 2025 Reliance Communications</div>
</body>
</html>