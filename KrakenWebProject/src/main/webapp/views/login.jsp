<!DOCTYPE html>
<html>
	<head>
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
				Username: <input type="text" name="name" /> 
				Password: <input type="password" name="password" /> 
				<input type="submit" name="button" value="Login" /> <br><br>
				<input type="submit" name="button" value="Fake Login" />
			</form> 
		</div>
	</body>
</html>