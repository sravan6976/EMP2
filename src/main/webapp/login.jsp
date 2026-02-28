<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container">
		<div class="logo-container">
			<img src="images/Reliance_Communications_Logo.svg.png"
				alt="Company Logo" class="company-logo">
		</div>
		<h2>Employee Message Portal</h2>
		<form action="LoginServlet" method="post" class="form-box">
			<div class="form-group">
				<input type="text" name="userid" placeholder="User ID" required>
			</div>
			<div class="form-group">
				<input type="password" name="password" placeholder="Password"
					required>
			</div>
			<div class="form-group">
				<button type="submit">Login</button>
			</div>
		</form>
	</div>
	<div class="footer">© 2025 Reliance Communications</div>
</body>
</html>