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

<form action="deleteQuizByID">
<p>
	<label>Enter the ID For the quiz You Want To Delete: <input name="quizID" type="text" /></label>
	</p>
<p>
	<button type="submit">Submit</button>
	</p>
</form>
<table border=1>
<tr>
<th>QuizID</th>
<th>Quiz title</th>
<th>Total Marks</th>
<th>Set Of Questions</th>
</tr>

<c:forEach items="${Quizes}" var="quizes">
<tr>
<td><c:out value="${quizes.quizID}"/></td>
<td><c:out value="${quizes.title}"/></td>
<td><c:out value="${quizes.totalMarks}"/></td>
<td><c:out value="${quizes.quizQuestions}"/></td>
</tr>
</c:forEach>
</table>

</body>
</html>