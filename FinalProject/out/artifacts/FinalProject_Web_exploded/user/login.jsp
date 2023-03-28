<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
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
                    <a class="nav-link" href="/">All news</a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <%
                    if (currentUser == null) {
                %>
                <li class="nav-item">
                    <a class="nav-link active" href="/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Register</a>
                </li>
                <%
                    } else {
                        if (currentUser.getRole() == User.Role.ADMIN) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/list/news">Admin panel</a>
                </li>
                <%
                        }
                %>
                <li class="nav-item">
                    <a class="nav-link" href="/my-page">My page</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <%
                    }
                %>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <form action="/login" method="post" class="w-50 mx-auto">
        <h5 class="mb-5 text-center">LOGIN IN ORDER TO SEE MORE</h5>
        <!-- EMAIL -->
        <div class="mb-3 row ">
            <label for="user-email" class="col-sm-3 col-form-label">EMAIL:</label>
            <div class="col-sm-9">
                <input type="email" class="form-control" id="user-email" name="user-email"
                       placeholder="Enter your email">
            </div>
        </div>

        <!-- PASSWORD -->
        <div class="mb-3 row ">
            <label for="user-password" class="col-sm-3 col-form-label">PASSWORD:</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="user-password" name="user-password"
                       placeholder="Enter your password">
            </div>
        </div>


        <div class="mb-3 text-end">
            <button class="btn bg-success text-white">LOGIN</button>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
