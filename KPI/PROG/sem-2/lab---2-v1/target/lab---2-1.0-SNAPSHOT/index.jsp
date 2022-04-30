<%@ page import="java.net.CookieHandler" %>
<%@ page import="java.net.CookieManager" %>
<%@ page import="com.example.lab2.utils.CookiesUtils" %><%--
  Created by IntelliJ IDEA.
  User: Elfar
  Date: 13.04.2022
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Cookie[] cookies = request.getCookies();%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="stylesheets/style.css">
    <title>Лабораторна работа №2</title>
</head>
<body>
<h1 class="header"> Лабораторна работа №2</h1>
<h2 class="header">Виконав студент ІА-12<br> Пауков Нікіта</h2>

<div class="centred">
    <a class="docs-link" target="_blank"
       href="https://docs.google.com/document/d/1Tx6rmAlc-yGBn9O1qQ_G6LTtOiEvFIX0/edit">
        Посилання на завдання</a>
    <a class="docs-link" target="_blank"
       href="https://docs.google.com/document/d/1VeLglPsjZx5UCiYpYYb7OLIt0amv8B3A/edit">
        Завдання минулого року</a>
    <form action="result" style="margin-top: 15px" method="post">
        <label for="a-param">Параметр "а"   </label>
        <input type="number" name="a" step="any" value="<%=CookiesUtils.findCookieValue("a", cookies)%>" id="a-param" required>
        <label>Параметр "b"</label>
        <input type="number" step="any" value="<%=CookiesUtils.findCookieValue("b", cookies)%>" name="b" id="b-param" required>
        <label>Параметр "c"</label>
        <input type="number" step="any" value="<%=CookiesUtils.findCookieValue("c", cookies)%>" name="c" id="c-param" required>
        <label>Параметр "d"</label>
        <input type="number"  step="any"value="<%=CookiesUtils.findCookieValue("d", cookies)%>" name="d" id="d-param" required>
        <br>
        <input class="submit" type="submit" value="Підрахувати">
    </form>
    <img src="imgs/func.png">
</div>

</body>
</html>