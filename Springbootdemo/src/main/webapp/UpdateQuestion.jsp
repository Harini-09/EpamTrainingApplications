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

<b>choice 1->question</b><br>
<b>choice 2->options</b><br>
<b>choice 3->answer</b><br>
<b>choice 4->difficulty level</b><br>
<b>choice 5->tag</b><br>
<form action="QuestionUpdation">
<p>
    <p>
	<label>Enter the QuestionID: <input name="questionID" type="text" /></label>
	</p>
	<p>
	<label>Enter the Choice: <input name="choice" type="text" /></label>
	</p>
	 
	<p>
	<p>
	<label>Enter the Updated Values: <input name="updatedvalue" type="text" /></label>
			
	</p>
	
<p>
	<button type="submit">Submit</button>
	</p>
</form>
<b>Available Questions</b><br>
</body>
</html>