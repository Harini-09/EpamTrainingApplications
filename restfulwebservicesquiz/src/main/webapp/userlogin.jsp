<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    body {
        background-color: #f5f5f5;
        font-family: Arial, sans-serif;
    }

    h4 {
        color: #333;
        font-size: 24px;
        font-weight: bold;
        text-align: center;
    }

    form {
        width: 300px;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
    }

    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: none;
        border-radius: 3px;
        box-sizing: border-box;
        font-size: 16px;
    }

    input[type="submit"] {
        width: 100%;
        background-color: #333;
        color: #fff;
        padding: 10px;
        border: none;
        border-radius: 3px;
        font-size: 16px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #555;
    }
</style>

</head>
<body>
<h4>Login Page</h4> <br>

<form name="userlogin" method="post" action="validateuser">
 <input type="text" name="id" placeholder="id" required="required"> <br>
 <input type="password" name="password" placeholder="password" required="required"><br>
<br>
<input type="submit" value="submit">

</form>
</body>
</html>