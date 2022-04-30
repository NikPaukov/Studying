<%@ page import="com.example.lab2.param.processing.ParamsServletContainer" %>
<%@ page import="com.example.lab2.param.processing.CountServletFormContainer" %><%--
  Created by IntelliJ IDEA.
  User: Elfar
  Date: 13.04.2022
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/style.css">
    <title>Результат обчислень</title>
</head>
<body>
<div class="centred">
    <% CountServletFormContainer container = (CountServletFormContainer) request.getAttribute("container"); %>
            <h1>
        Результат обчислень при a=<%=container.getParam("a")%>, b=<%=container.getParam("a")%>,
                c=<%=container.getParam("c")%>, d=<%=container.getParam("d")%>:</h1>
    <h1> ${res}</h1>
    <a href="index.jsp" class="docs-link">Назад</a>
</div>
</body>
</html>
