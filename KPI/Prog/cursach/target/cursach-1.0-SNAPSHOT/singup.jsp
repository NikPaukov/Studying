<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
        <form class="login-form" method="post" action="singup"  id="login">
            <fieldset>
                <legend>Sing Up</legend>
            <input required type="text" name="username" minlength="6" max="16" placeholder="username">
            <input required type="password" name="password"  minlength="6"  placeholder="Password">
        </fieldset>
        <div class="buttons">
            <button type="submit" class="main-btn">Sing up</button>
            <p>or go to <a class="secondary-btn" href="login.jsp">Log in</a></p>
        </div>
            <p id="errorMsg" class="show-anim-delay">${error}</p>

        </form>

    </div>
</div>
</body>
</html>