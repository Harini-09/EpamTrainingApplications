<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Separate the options with comma (,) [Example : a,b,c represents three options]</h4>
<form name="modify" method="post" action="modifyquestionoptions">
<input value="${questionid}" type="text"  hidden="hidden" name="id" ><br>
<input type="text" name="options" placeholder="questionoptions"><br>

<input type="submit" value="submit">

</form>
</body>
</html>