<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz</title>
</head>
<body>
<h2>Quiz title:${Quizes.title}</h2>
<h2>Total Marks:${Quizes.totalMarks}</h2>


<form action="submitQuiz">
<input value="${Quizes.quizID}" hidden  type="text" required="required" name="quizID">

<c:forEach items="${Quizes.quizQuestions}" var="questions">
<b>Question ID:<c:out value="${questions.questionID}"/></b><br>
Difficulty Level:<c:out value="${questions.difficultyLevel}"/><br>
Topic Tags:<c:out value="${questions.tag}"/><br>
Question:<c:out value="${questions.question}"/><br>
Options<c:out value="${questions.options}"/><br>
Enter the option:<br>
<p>
<input name="answers"  type="text" required="required">
</p>
<p>---------------------------------------------------------------------------------------------------------</p>

</c:forEach>
<p>
<button type="submit">Submit</button>
</p>
</form>

</body>
</html>