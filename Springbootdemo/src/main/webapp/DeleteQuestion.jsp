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
<form action="deleteQuestionByID">
<p>
	<label>Enter the ID For the question You Want To Delete: <input name="id" type="text" /></label>
	</p>
<p>
	<button type="submit">Submit</button>
	</p>
</form>
<table border=1>
<tr>
<th>QuestionID</th>
<th>question</th>
<th>Options</th>
<th>Answer</th>
<th>Difficulty Level</th>
<th>Topic Tags</th>
</tr>

<c:forEach items="${Questions}" var="questions">
<tr>
<td><c:out value="${questions.questionID}"/></td>
<td><c:out value="${questions.question}"/></td>
<td><c:out value="${questions.options}"/></td>
<td><c:out value="${questions.answer}"/></td>
<td><c:out value="${questions.difficultyLevel}"/></td>
<td><c:out value="${questions.tag}"/></td>
</tr>
</c:forEach>
</table>

</body>
</html>