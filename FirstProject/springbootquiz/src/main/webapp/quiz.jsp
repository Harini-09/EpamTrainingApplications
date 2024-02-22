<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<style>
body {
  font-family: Arial, sans-serif;
  background-color: #f2f2f2;
}

h2 {
  font-size: 24px;
  color: #333;
}

b {
  font-size: 18px;
  color: #666;
}

input[type="text"] {
  padding: 6px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
}

button[type="submit"] {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 12px 24px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #3e8e41;
}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Quiz title:${Quizes.title}</h2>
<h2>Total Marks:${Quizes.totalMarks}</h2>


<form action="submitQuiz">
<input value="${Quizes.quizId}" hidden  type="text" required="required" name="quizID">

<c:forEach items="${Quizes.questionsList}" var="questions">
<b>Question ID: ${questions.questionId}</b><br>
Difficulty Level: ${questions.questionlevel}<br>
Topic Tags: ${questions.topictag}<br>
Question: ${questions.questionDescription}<br>
Options: ${questions.options}<br>
Enter the option:<br>
<p>
<input name="answers"  type="text" required="required">
</p>

</c:forEach>
<p>
<button type="submit">Submit</button>
</p>
</form>

</body>
</html>