<%--
  Created by IntelliJ IDEA.
  User: Elfar
  Date: 13.04.2022
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/style.css">
    <title>ERROR!</title>
</head>
<body>
<div class="centred">
    <h1 class="header">
    Були введені некоректні данні!<br><br>
        ${error}
    </h1><br>
    <a class="docs-link" href="index.jsp">Назад</a>
</div>
</body>
</html>
