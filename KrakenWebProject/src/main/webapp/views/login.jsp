<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div style="height: 350px;"> </div>
<div class="content">
	<form action="/KrakenWebProject/Login" method="post">
		Username: <input type="text" name="un" />
		Password: <input type="text" name="pw" />
		<input type="submit" value="Login" /> <br><br>
	</form>
</div>
</body>
</html>