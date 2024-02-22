<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Question Creation : </h2>
<form name="questionForm" action="createQuestion">

Title : <input type="text" name="title"> <br><br>
Question : <input type="text" name = "questionDescription"> <br><br>
Options : <input type = "text" name = "options"> <br><br>
QuestionLevel : <input type = "text" name = "questionlevel"> <br><br>
TopicTag : <input type = "text" name = "topictag"> <br><br>
Answer : <input type = "text" name = "answer"> <br><br>

<input type="submit">

</form>
</body>
</html>