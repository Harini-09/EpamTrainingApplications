<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="modify" method="post" action="modifyquestionlevel">
<input value="${questionid}" type="text"  hidden="hidden" name="id" ><br>
<input type="text" name="questionlevel" placeholder="questionlevel"><br>

<input type="submit" value="submit">

</form>
</body>
</html>