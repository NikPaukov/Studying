<%@ page import="com.example.cursach.tasks.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="lib/css/reset.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <link rel="stylesheet" href="lib/css/tasks.css">

    <title>Log In</title>
</head>
<div id="darked"class>${error}</div>
<body>
<main>
    <h1>Планувальник задач</h1>
    <p id="errorMsg"></p>
    <div class="buttons">
        <button id="add" type="button" onclick="showForm('add-form')">Add</button>
        <button id="delete" type="button" onclick="showForm('delete-form')">Delete</button>
        <button id="redact" type="button" onclick="showForm('redact-form')">Redact</button>

    </div>
    <table class="sortable">
        <tr>
            <td class="right-td item">Name</td>
            <td class="center-td item">Priority</td>
            <td class="left-td item">Duration</td>
        </tr>

        <%
            if(request.getAttribute("tasks")!=null){
                List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                for(Task task: tasks){%>
        <tr>
            <td class="right-td item"><%=task.getName()%></td>
            <td class="center-td item"><%=task.getPriority()%></td>
            <td class="left-td item"><%=task.getDuration()%></td>

        </tr>


        <%}}%>
    </table>
</main>

<form style="display: none" id="add-form" action="add" class="tasks-form">
    <button type="button" id="add-close" class="close" onclick="hidForm('add-form')">X</button>
    <input required type="text" name="name" placeholder="Name">
    <input required type="number" min="0" max="10" placeholder="Priority(1-10)">
    <input required type="datetime" placeholder="Time">
    <button class="submit"type="submit">Add Task</button>
</form>

<form style="display: none;" id="delete-form" action="delete" class="tasks-form">
    <button type="button" id="delete-close" class="close" onclick="hidForm('delete-form')">X</button>
    <input required type="text" name="name" placeholder="Name">

    <button class="submit"type="submit">Delete Task</button>
</form>
<form style="display: none" id="redact-form" action="redact" class="tasks-form">
    <button type="button" id="redact-close" class="close" onclick="hidForm('redact-form')">X</button>
    <input required type="text" name="oldName" placeholder="Old name">
    <input required type="text" name="name" placeholder="New name">
    <input required type="number" min="0" max="10" placeholder="Priority(1-10)">
    <input required type="datetime" placeholder="Time">
    <button class="submit"type="submit">Add</button>
</form>


<script>
    function sleep (time) {
        return new Promise((resolve) => setTimeout(resolve, time));
    }
    function showForm(formId){
        document.getElementById(formId).classList.remove("form-show-anim-reversed");
        document.getElementById(formId).style.display = 'flex';
        document.getElementById(formId).classList.add("form-show-anim");
        document.getElementById("darked").classList.add("darked");

    }
    function hidForm(formId){
        document.getElementById(formId).classList.remove("form-show-anim");
        document.getElementById(formId).classList.add("form-show-anim-reversed");
        document.getElementById("darked").classList.remove("darked");
        sleep(500).then(()=>{
            document.getElementById(formId).style.display = 'none';
        })
    }

</script>

<script src="https://www.kryogenix.org/code/browser/sorttable/sorttable.js"></script>

</body>
</html>