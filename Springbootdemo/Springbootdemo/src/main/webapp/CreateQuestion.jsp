<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Create Question:-></h2>
	<form:form method="post" action="addQuestion" modelAttribute="question">
		<p>
			<label>QuestionID:<input name="questionID" type="text" /></label>
		</p>
		<p>
			<label>Question : <input name="question" type="text" /></label>
		</p>

		<label for="cars">Difficulty Level:</label> <select name="difficultyLevel"
			id="cars">
			<option value="easy">Easy</option>
			<option value="medium">Medium</option>
			<option value="hard">Hard</option>

		</select>
		<p>
			<label>Topic Tags :<input name="tag" type="text" /></label>
		</p>
		<p>Options:
		<p>
		<p>
			<textarea name="options" rows="6" cols="50">Write something here</textarea>
		</p>
		<p>
		<p>
			<label>Answer : <input name="answer" type="text" /></label>
		</p>

		<button type="submit">Submit</button>

	</form:form>
</body>
</html>