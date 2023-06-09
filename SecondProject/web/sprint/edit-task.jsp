<%@ page import="sprint.Task" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: asset
  Date: 15-03-23
  Time: 06:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>
        .dark-blue {
            background-color: #1F1A62;
            color: white;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-expand-md navbar-dark dark-blue">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">BITHUB TASK MANAGER</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link" href="/sprint/show-tasks">Все задачи</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container w-25 px-auto pt-4">
    <%
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Task task = (Task)request.getAttribute("task");
        if (task != null) {
    %>
    <form action="/sprint/edit-task" method="post">
        <div class="invisible">
            <div class="mb-3">
                <input type="number" class="form-control" id="task-id" name="task-id" value="<%=task.getId()%>">
            </div>
        </div>
        <div class="form-group mb-3">
            <label for="task-name" class="form-label">Наименование:</label>
            <input type="text" class="form-control" id="task-name" name="task-name" value="<%=task.getName()%>">
        </div>
        <div class="form-group mb-3">
            <label for="task-description" class="form-label">Описание:</label>
            <textarea class="form-control" id="task-description" name="task-description" rows="10"><%=task.getDescription()%></textarea>
        </div>
        <div class="form-group mb-3">
            <label for="task-duedate" class="form-label">Крайний срок:</label>
            <input type="date" class="form-control" id="task-duedate" name="task-duedate" value="<%=format.format(task.getDueDate())%>">
        </div>
        <div class="form-group mb-3">
            <label for="task-isdone" class="form-label">Выполнено:</label>
            <select class="form-control" id="task-isdone" name="task-isdone">
                <option value="true" selected="<%=task.isDone()? "selected" : ""%>">Да</option>
                <option value="false" selected="<%=task.isDone()? "" : "selected"%>">Нет</option>
            </select>
        </div>
        <div class="form-group mb-3 text-end">
            <button class="btn btn-dark bg-success border-0"
                    onclick="if (!confirm('Сохранить изменения?')) return false;">Сохранить</button>
            <a href="#" class="btn btn-dark bg-danger border-0"
               onclick="if (confirm('Вы действительно хотите удалить данную задачу?')) document.getElementById('removeForm').submit();">Удалить</a>
            <a href="/sprint/show-tasks" class="btn btn-dark bg-secondary border-0">Отменить</a>
        </div>
    </form>
    <form action="/sprint/remove-task" method="post" class="invisible" id="removeForm">
        <input type="number" class="form-control" name="task-id" value="<%=task.getId()%>">
    </form>
    <%
        }
    %>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>

</html>
