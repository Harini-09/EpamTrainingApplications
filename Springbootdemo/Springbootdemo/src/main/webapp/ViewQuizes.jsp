<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>"${message}"</h2>

<table border=1>
<tr>
<th>QuizID</th>
<th>Quiz title</th>
<th>Total Marks</th>
<th>Set Of Questions</th>
<th>Delete Quiz</th>
<th>Update Quiz</th>
</tr>

<c:forEach items="${Quizes}" var="quizes">
<tr>
<td><c:out value="${quizes.quizID}"/></td>
<td><c:out value="${quizes.title}"/></td>
<td><c:out value="${quizes.totalMarks}"/></td>
<td><c:out value="${quizes.quizQuestions}"/></td>
<td><a href="deleteQuizByID?quizID=${quizes.quizID}">Delete Quiz</a></td>
<td><a href="updateQuiz?quizID=${quizes.quizID}">Update Quiz</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>