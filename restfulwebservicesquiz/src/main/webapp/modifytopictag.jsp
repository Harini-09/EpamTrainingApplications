<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="modify" method="post" action="modifytopictag">
<input value="${questionid}" type="text"  hidden="hidden" name="id" ><br>
<input type="text" name="topictags" placeholder="topictags"><br>

<input type="submit" value="submit">

</form>
</body>
</html>