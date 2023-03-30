<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.News" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="model.Comment" %>
<%@ page import="model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User registration page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
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
    <%
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        News news = (News) request.getAttribute("news");
    %>
    <div class="card border-0 mb-5">
        <div class="card-body">
            <h4 class="card-title"><%=news.getTitle()%></h4>
            <p class="card-text"><%=news.getContent()%></p>
            <p class="card-text fw-bold">
                Posted at <%=formatter.format(news.getPostedTime())%> by <%=news.getUser().getName()%> <%=news.getUser().getSurname()%>
            </p>
        </div>
    </div>
    <div class="ms-5">
        <!-- ALERTS -->
        <%
            String error = (String)request.getAttribute("error");
            String success = (String)request.getAttribute("success");
        %>
        <div>
        <div class="<%=(success == null)? "invisible" : "alert alert-success mx-3"%>" role="alert">
            <%=success%>
        </div>
        <div class="<%=(error == null)? "invisible" : "alert alert-danger mx-3"%>" role="alert">
            <%=error%>
        </div>

        <%
            if (currentUser != null) {
        %>
        <form action="/news?id=<%=news.getId()%>" method="post" class="mx-3" >
            <div class="mb-3">
                <label for="comment-text" class="form-label">Write your comment below</label>
                <textarea rows="4" class="form-control" id="comment-text" name="comment-text"></textarea>
            </div>
            <div class="mb-3 text-end">
                <button class="btn bg-success text-white">POST</button>
            </div>
        </form>
        <%
            }
            List<Comment> comments = (List<Comment>) request.getAttribute("comments");
            for (Comment comment : comments) {
        %>
        <div class="card border-0 mt-3">
            <div class="card-body">
                <p class="card-title fw-bold">
                    Posted by
                    <span class="text-secondary">
                        <%=comment.getUser().getName()%> <%=comment.getUser().getSurname()%>
                        at <%=formatter.format(comment.getPostedTime())%>
                    </span>
                </p>
                <p class="card-text"><%=comment.getText()%></p>
            </div>
        </div>
        <%
            }

        %>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
