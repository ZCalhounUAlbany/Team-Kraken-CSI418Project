<!DOCTYPE html>
<html>
<head>
<title>Todos</title>
<link rel="stylesheet" href="../styles/styles.css" type="text/css" media="screen">

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
		<form action="/login.do" method="post">
			Name: <input type="text" name="name" /> 
			Password:<input type="password" name="password" /> 
			<input type="submit" value="Login" />
		</form> 
	</div>
	
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>

</html>