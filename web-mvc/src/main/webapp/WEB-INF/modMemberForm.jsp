<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Member</title>
</head>
<body>
<h2>Edit Member</h2>
<form action="/member/modMember.do" method="post">
    <input type="hidden" name="id" value="${memberVO.id}"/>
    Password: <input type="password" name="pwd" value="${memberVO.pwd}" required/><br/>
    Name: <input type="text" name="name" value="${memberVO.name}" required/><br/>
    Email: <input type="email" name="email" value="${memberVO.email}" required/><br/>

    <button type="submit">Update Member</button>
</form>
</body>
</html>
