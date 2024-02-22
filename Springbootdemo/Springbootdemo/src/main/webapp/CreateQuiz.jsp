<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Quiz</title>
</head>
<body>

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
<h2>Create Quiz:-></h2>
	<form:form method="post" action="addQuiz" modelAttribute="question">
		<p>
			<label>QuizID:<input name="quizID" type="text" /></label>
		</p>
		<p>
			<label>Quiz title : <input name="title" type="text" /></label>
	      
		</p>
		<p>
			<label>Total Marks :<input name="totalMarks" type="text" /></label>
		</p>
		<p>
			<label>>Enter Question IDs You Want to Add:<input name="quizQuestions" type="text" /></label>
		</p>

		<button type="submit">Submit</button>

	</form:form>
</body>
</html>