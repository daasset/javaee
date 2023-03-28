<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User registration page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("currentUser");
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
    <form action="/admin/create/news" method="post" class="w-50 mx-auto">
        <h5 class="mb-5 text-center">ADDING NEW NEWS</h5>

        <!-- CATEGORY -->
        <div class="mb-3 row ">
            <label for="news-category" class="col-sm-3 col-form-label">CATEGORY:</label>
            <div class="col-sm-9">
                <select class="form-select" id="news-category" name="news-category">
                    <%
                        List<Category> categories = (List<Category>) request.getAttribute("categories");
                        if (categories != null) {
                            for (Category category : categories) {
                    %>
                    <option><%=category.getName()%></option>
                    <%
                            }
                        }

                    %>
                </select>
            </div>
        </div>

        <!-- TITLE -->
        <div class="mb-3 row ">
            <label for="news-title" class="col-sm-3 col-form-label">TITLE:</label>
            <div class="col-sm-9">
                <input type="text" rows="30" class="form-control" id="news-title" name="news-title" placeholder="Enter news title">
            </div>
        </div>

        <!-- CONTENT -->
        <div class="mb-3 row ">
            <label for="news-content" class="col-sm-3 col-form-label">CONTENT:</label>
            <div class="col-sm-9">
                <textarea class="form-control" id="news-content" name="news-content" placeholder="Enter news content"></textarea>
            </div>
        </div>

        <div class="mb-3 text-end">
            <button class="btn bg-success text-white">ADD NEWS</button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
