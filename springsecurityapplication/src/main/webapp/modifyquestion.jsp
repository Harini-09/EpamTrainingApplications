<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
		body {
			background-color: #f2f2f2;
			font-family: Arial, sans-serif;
			margin: 0;
			padding: 0;
		}
		.container {
			max-width: 800px;
			margin: 0 auto;
			padding: 20px;
		}
		h1 {
			color: #333;
			font-size: 36px;
			margin: 20px 0;
			text-align: center;
		}
		.btn {
			background-color: #4CAF50;
			border: none;
			color: white;
			padding: 12px 24px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			margin: 10px;
			cursor: pointer;
			border-radius: 8px;
			transition: background-color 0.3s;
		}
		.btn:hover {
			background-color: #3e8e41;
		}
		.btn:active {
			background-color: #3e8e41;
			box-shadow: 0 5px #666;
			transform: translateY(4px);
		}
	</style>
</head>
<body>

<h4>Click on the modification operation you would like to perform :</h4>

<a href="modifytitleid?id=${questionid}">Modify Title</a><br><br>
<a href="modifyquestiondescriptionid?id=${questionid}">Modify Question Description</a><br><br>
<a href="modifyoptionsid?id=${questionid}">Modify Options</a><br><br>
<a href="modifylevelid?id=${questionid}">Modify Question Level</a><br><br>
<a href="modifytopictagid?id=${questionid}">Modify Topic Tag</a><br><br>
<a href=modifyanswerid?id=${questionid}>Modify Answer</a><br><br>

</body>
</html>

<!-- 1.title 2.question 3.options 4.questionlevel 5.topictag 6.answer 7.exit -->