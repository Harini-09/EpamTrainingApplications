<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
body {
	background-color: #f5f5f5;
	font-family: Arial, sans-serif;
}

h5 {
	color: #333;
	font-size: 24px;
	font-weight: bold;
	text-align: center;
	margin-top: 30px;
}

a {
	display: block;
	width: 200px;
	margin: 0 auto;
	padding: 10px;
	background-color: #333;
	color: #fff;
	text-align: center;
	text-decoration: none;
	border-radius: 3px;
	font-size: 16px;
	margin-top: 30px;
}

a:hover {
	background-color: #555;
}
</style>

</head>
<body>
	<h5>You are successfully logged in as an admin</h5>
	<br> Click on which operation do you want to perform :
	<br>

	<a href="showquestionoperations.jsp">Question Operations</a>
	<br>
	<br>
	<a href="showquizoperations.jsp">Quiz Operations</a>
	<br>
	<br>
	<a href="home.jsp">Home Page</a>
</body>
</html>