<%@ page import="java.util.ArrayList" %>
<%@ page import="task1n2.Item" %>
<%@ page import="sprint.Task" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: asset
  Date: 15-03-23
  Time: 06:09 PM
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
                    <a class="nav-link active" href="/sprint/show-tasks">Все задачи</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="py-4">
        <a href="/sprint/add-task" class="btn btn-dark dark-blue">+ Добавить задание</a>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Наименование</th>
            <th scope="col">Крайний срок</th>
            <th scope="col">Выполнено</th>
            <th scope="col" class="text-end">Детали&nbsp;&nbsp;&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <%
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            ArrayList<Task> tasks = (ArrayList<Task>) request.getAttribute("tasks");

            for (Task task : tasks) {
        %>
        <tr>
            <td class="align-middle"><%=task.getId()%></td>
            <td class="align-middle"><%=task.getName()%></td>
            <td class="align-middle"><%=format.format(task.getDueDate())%></td>
            <td class="align-middle"><%=task.isDone()? "Да" : "Нет"%></td>
            <td class="align-middle text-end"><a href="/sprint/edit-task?id=<%=task.getId()%>" class="btn btn-dark dark-blue">Детали</a></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>

</html>