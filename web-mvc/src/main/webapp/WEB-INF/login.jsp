<%--
  Created by IntelliJ IDEA.
  User: hongjinwook
  Date: 3/11/24
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<c:if test="${param.result == 'error'}">
    <div class="error-message">
        <h1>로그인 에러입니다. 다시 시도하세요!</h1>
    </div>
</c:if>
<form action="/login" method="post">
    <table>
        <tr>
            <td>아이디</td>
            <td><input type="text" name="id"></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="pwd"></td>
        </tr>
        <tr>
            <td><input type="submit" value="로그인"></td>
        </tr>
    </table>
</form>
</body>
</html>