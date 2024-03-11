<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Member</title>
</head>
<body>
<h2>Add Member</h2>
<form action="/member/addMember.do" method="post">
    ID: <input type="text" name="id" required /><br />
    Password: <input type="password" name="pwd" required /><br />
    Name: <input type="text" name="name" required /><br />
    Email: <input type="email" name="email" required /><br />
    <button type="reset">초기화</button>
    <button type="submit">Add Member</button>
</form>
</body>
</html>
