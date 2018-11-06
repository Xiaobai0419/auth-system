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
    <title>hello</title>
</head>
<body>
    <h2>Hello!</h2>
    <p>User:${requestScope.get("xiaobai")}</p>
    <p>Session:${user}</p>
    <p>Context GlobalId:${applicationScope.get("globalId")}</p>
    <p>AccessToken:${param.get("accessToken")}</p>
</body>
</html>
