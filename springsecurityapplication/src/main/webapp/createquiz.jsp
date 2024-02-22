<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Create Quiz</title>
</head>
<body>

<h2>Create Quiz: </h2>
	<form:form method="post" action="addquiz" modelAttribute="question">
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


