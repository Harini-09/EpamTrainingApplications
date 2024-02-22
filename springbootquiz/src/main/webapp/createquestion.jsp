<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Create Question </h4><br>
<form name="createquestion" method="post" action="createquestion">
<input type="text" name="title" placeholder="Title" required="required"><br>
<input type="text" name="questionDescription" placeholder="Question" required="required"><br>
<input type="text" name="options" placeholder="Options" required="required"><br>
<input type="text" name="questionlevel" placeholder="QuestionLevel" required="required"><br>
<input type="text" name="topictag" placeholder="Topictag" required="required"><br>
<input type="text" name="answer" placeholder="Answer" required="required"><br>
<input type="submit" value="submit">

</form>
</body>
</html>