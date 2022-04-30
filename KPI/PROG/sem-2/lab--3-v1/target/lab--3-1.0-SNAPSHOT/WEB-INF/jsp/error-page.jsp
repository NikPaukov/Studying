<jsp:useBean id="error" scope="request" type="java.lang.String"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ErrorPage</title>
<link rel="stylesheet" href="../../style.css">
</head>
<body>
<p>${error}</p>
</body>
</html>
