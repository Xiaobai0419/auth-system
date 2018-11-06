<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/5
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>mvc</title>
</head>
<body>
    <form method="post" action="/auth/authentication">
        <c:choose>
        <c:when test="${lgout != null}">
        <h2>${lgout}</h2>
        </c:when>
        <c:otherwise>
        <h2>Login</h2>
        </c:otherwise>
        </c:choose>
        <p>Username:<input name="username" type="text"/></p>
        <p>Password:<input name="password" type="password"/></p>
        <p><input name="submit" value="登录" type="submit"/></p>
        ${error}
    </form>
</body>
</html>
