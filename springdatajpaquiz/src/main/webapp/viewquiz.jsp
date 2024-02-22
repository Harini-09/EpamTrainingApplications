<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    h2 {
        color: #333;
        font-size: 24px;
        font-weight: bold;
        text-align: center;
        margin-top: 30px;
    }

    table {
        border-collapse: collapse;
        margin: 0 auto;
        margin-top: 30px;
    }

    th {
        background-color: #333;
        color: #fff;
        font-size: 16px;
        padding: 10px;
        text-align: center;
    }

    td {
        background-color: #fff;
        color: #333;
        font-size: 16px;
        padding: 10px;
        text-align: center;
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

<h2>${message}</h2>
<div>
<a href="createquiz.jsp" class="btn-link">Create</a>
</div>
<table border="1">
<caption>This table shows a list of quizzes</caption>
<tr>
<th>QuizId</th>
<th>Title</th>
<th>QuestionsList</th>
<th>TotalMarks</th>
<th>Actions</th>
<c:forEach items="${quizes}" var="quiz" >
<tr>
<td>${quiz.quizId}</td>
<td>${quiz.title}</td>
<td>${quiz.questionsList}</td>
<td>${quiz.totalMarks}</td>
<td><a href="deletequiz?id=${quiz.quizId}">Delete</a><br>
<a href="modifyquizops?quizid=${quiz.quizId}">Modify</a>
</td>
</tr>

</c:forEach>
</table>
<br><br>
<a href="showoperations.jsp">Back To Operations</a>
</body>
</html>