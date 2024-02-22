<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>${message}</h2>

<table border="1" >
<caption>This table shows a list of quizzes</caption>
<tr>
<th>QuizId</th>
<th>Title</th>
<th>TotalMarks</th>
</tr>
<c:forEach items="${quizes}" var="quiz" >
<tr>
<td>${quiz.quizId}</td>
<td>${quiz.title}</td>
<td>${quiz.totalMarks}</td>
<td><a href="TakeQuiz?quizID=${quiz.quizId}">Take Quiz</a></td>
</tr>

</c:forEach>
</table>
</body>
</html>