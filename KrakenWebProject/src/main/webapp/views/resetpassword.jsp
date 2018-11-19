<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <title>password reset</title>
</head>
<body>
<div align="center">
    <form action="/KrakenWebProject/resetPW" method="Post">
        <h1>Reset Your Password Here:</h1>
        Username : <input type="text" name="un" required ><br>
        <p></p>
        Old Password : <input type="password" name="opw" required><br>
        <p></p>
        New Password : <input type="password" name="npw" required><br>
        <p></p>
        <input id="enter" type="submit" value="reset">
    </form>
    <p style="color:red;">${errorMessage}</p>
</div>
</body>
</html>
