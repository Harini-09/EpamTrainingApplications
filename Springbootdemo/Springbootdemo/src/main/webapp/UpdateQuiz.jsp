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
"${Quiz}""
<b>Available Questions</b>
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
<b>choice 1->title</b>
<b>choice 2->Total Marks</b>
<b>choice 3->Add Question</b>
<b>choice 4->Remove Question</b>

	<form action="QuizUpdation">
		<p>
		<p>
			<label>Enter the QuizID: <input name="quizID" type="text" /></label>
		</p>
		<p>
			<label>Enter the choice: <input name="choice" type="text" /></label>
		</p>
		    for adding or removing question Please Enter Question IDs:
			<label>Enter the Updated values: <input name="updatedvalue"
				type="text" /></label>
		</p>

		<p>
		<p>
			<button type="submit">Submit</button>
		</p>
	</form>
</body>
</html>