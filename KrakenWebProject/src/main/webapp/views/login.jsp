<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" href="./styles/styles.css" type="text/css" media="screen">

	<style>
		.content {
			margin: auto;
			width: 40%;
			text-align: center;
			border: 1px solid green;
			padding: 25px;
			background-color: #f5f5f5;
		}
	</style>
</head>

<body>
<div style="height: 100px"> </div>
<div class="content">
	<form action="/KrakenWebProject/Login" method="post">
		Username: <input type="text" name="un" required/>
		<br>
		Password: <input type="text" name="pw" required/>
		<p></p>
		<input type="submit" value="Login" /> <br><br>
	</form>
	<p style="color:red;">${errorMessage}</p>
	<p></p>
	<a href="/resetpassword.jsp"><button>Reset Password</button></a>
</div>
</body>
</html>