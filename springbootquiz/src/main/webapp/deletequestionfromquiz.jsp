<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="deletequestion" method="post" action="removequestionfromquiz">
<input type="text" name="quizid" placeholder="quizid" required="required"><br>
<input type="text" name="questionid" placeholder="questionid" required="required"><br>

<input type="submit" value="submit">
</form>
</body>
</html>