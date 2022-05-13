<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="lib/css/reset.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <link rel="stylesheet" href="lib/css/login.css">

    <title>Log In</title>
</head>
<body>
<div class="darker">
    <div class="login-menu show-anim">
        <h1>Task Manager</h1>
        <form class="login-form" action="login" method="post" id="login">
            <fieldset>
                <legend>Log In</legend>
            <input required type="text" name="username" placeholder="Username">
            <input required type="password" name="password" placeholder="Password">
        </fieldset>
        <div class="buttons">
            <button type="submit" class="main-btn">Log in</button>
            <p>or go to <a class="secondary-btn" href="singup.jsp">Sign up</a></p>
        </div>
            <p id="errorMsg" class="show-anim-delay">${error}</p>

        </form>
    </div>
</div>
</body>
</html>