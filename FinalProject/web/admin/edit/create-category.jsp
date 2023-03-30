<%@ page import="model.User" %>
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
                    <a class="nav-link" href="/admin/list/news">News</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/admin/list/categories">Categories</a>
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
    <form action="/admin/create/category" method="post" class="w-50 mx-auto">
        <h5 class="mb-5 text-center">CREATING NEW CATEGORY</h5>
        <!-- NAME -->
        <div class="mb-3 row">
            <label for="category-name" class="col-sm-3 col-form-label">CATEGORY NAME:</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="category-name" name="category-name"
                       placeholder="Enter name of the category">
            </div>
        </div>

        <div class="mb-3 text-end">
            <button class="btn bg-success text-white">CREATE</button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
