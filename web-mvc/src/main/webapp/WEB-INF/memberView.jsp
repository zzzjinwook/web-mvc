<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Member</title>
</head>
<body>
<h2>Member Details</h2>
<p>ID: <c:out value="${memberVO.id}" /></p>
<p>Name: <c:out value="${memberVO.name}" /></p>
<p>Email: <c:out value="${memberVO.email}" /></p>
<a href="/member/listMembers.do">Back to List</a>
</body>
</html>
