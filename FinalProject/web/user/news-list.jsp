<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.News" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="model.Category" %>
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
    Category currentCategory = (Category) request.getAttribute("currentCategory");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
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
                    <a class="nav-link <%=(currentCategory == null)? "active" : ""%>" href="/">All news</a>
                </li>
                <%
                    for (Category category : categories) {
                %>
                <li class="nav-item">
                    <a class="nav-link <%=category.equals(currentCategory)? "active" : ""%>" href="/?catId=<%=category.getId()%>">
                        <%=category.getName()%>
                    </a>
                </li>
                <%
                    }
                %>
            </ul>
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <%
                    if (currentUser == null) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
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
    <!-- ALERTS -->
        <%
        String error = (String)request.getAttribute("error");
        String success = (String)request.getAttribute("success");
    %>
    <div>
    <div class="<%=(success == null)? "invisible" : "alert alert-success ms-3"%>" role="alert">
        <%=success%>
    </div>
    <div class="<%=(error == null)? "invisible" : "alert alert-danger ms-3"%>" role="alert">
        <%=error%>
    </div>

    <%
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<News> newsList = (List<News>) request.getAttribute("newsList");
        for (News news : newsList) {
    %>
    <div class="card border-0 mb-5">
        <div class="card-body">
            <h4 class="card-title">
                <a class="link-dark" href="/news?id=<%=news.getId()%>"><%=news.getTitle()%></a>
            </h4>
            <p class="card-text"><%=news.getContent()%></p>
            <p class="card-text fw-bold text-secondary">
                Posted at <%=formatter.format(news.getPostedTime())%> by <%=news.getUser().getName()%> <%=news.getUser().getSurname()%>
            </p>
        </div>
    </div>
    <%
        }
    %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
