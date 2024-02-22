<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html lang="en" xml:lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            font-size: 16px;
            line-height: 1.5;
            margin: 0;
            padding: 0;
        }
        form {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin: 50px auto;
            max-width: 600px;
            padding: 20px;
        }
        input[type="text"] {
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
            display: block;
            font-size: 16px;
            margin-bottom: 10px;
            padding: 10px;
            width: 100%;
        }
        input[type="submit"] {
            background-color: #007bff;
            border: none;
            border-radius: 3px;
            color: #fff;
            cursor: pointer;
            display: block;
            font-size: 16px;
            margin-top: 20px;
            padding: 10px;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0069d9;
        }
    </style>
</head>
<body>
    <form name="addquestion" method="post" action="addquestiontoquiz">
        <input type="text" name="quizid" placeholder="Quiz ID" required="required">
        <input type="text" name="questionid" placeholder="Question ID" required="required">
        <input type="submit" value="Submit">
    </form>
</body>
</html>





