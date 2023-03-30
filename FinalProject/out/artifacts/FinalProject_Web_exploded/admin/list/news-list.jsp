<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User registration page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark bg-gradient">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">BITHUB NEWS PORTAL</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="/admin/list/news">News</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/list/categories">Categories</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/list/users">Users</a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/">Member panel</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="my-3">
        <a href="/admin/create/news" class="btn bg-primary text-white">+ ADD NEWS</a>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">POSTED TIME</th>
            <th scope="col">CATEGORY</th>
            <th scope="col">TITLE</th>
            <th scope="col">CONTENT</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <%
            List<News> newsList = (List<News>) request.getAttribute("newsList");
            for (News news : newsList) {
        %>
        <tr>
            <th scope="row" class="align-middle"><%=news.getId()%>
            </th>
            <td class="align-middle"><%=news.getPostedTime()%>
            </td>
            <td class="align-middle"><%=news.getCategory().getName()%>
            </td>
            <td class="align-middle"><%=news.getTitle()%>
            </td>
            <td class="align-middle"><%=news.getContent()%>
            </td>
            <td class="text-end align-middle">
                <a href="/admin/edit/news?id=<%=news.getId()%>" class="btn bg-primary text-white">EDIT</a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
