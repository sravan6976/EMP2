<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String userid = (String) session.getAttribute("userid");
    if (userid == null) {
        response.sendRedirect("login.jsp"); // Session expired or invalid access
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        function validateForm() {
            const newPassword = document.getElementById("newPassword").value;
            const confirmPassword = document.getElementById("confirmPassword").value;
            const errorMsg = document.getElementById("error");

            if (newPassword !== confirmPassword) {
                errorMsg.textContent = "Passwords do not match!";
                return false;
            }

            errorMsg.textContent = ""; // Clear any previous errors
            return true;
        }
    </script>
</head>
<body background="images/bg.jpg">
    <div class="container">
        <div class="logo-container">
            <img src="images/Reliance_Communications_Logo.svg.png" alt="Company Logo" class="company-logo">
        </div>

        <h2>Change Password</h2>

        <form action="ChangePasswordServlet" method="post" onsubmit="return validateForm()">
            <input type="text" name="userid" id="userid" value="<%= userid %>" readonly>

            <input type="password" name="newPassword" id="newPassword" placeholder="New Password" required>

            <input type="password" id="confirmPassword" placeholder="Confirm Password" required>

            <div id="error" class="error"></div>

            <input type="submit" value="Change Password">
        </form>
    </div>

    <div class="footer">© 2025 Reliance Communications</div>
</body>
</html>