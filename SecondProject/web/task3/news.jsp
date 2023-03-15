<%@ page import="java.util.ArrayList" %>
<%@ page import="task3.News" %><%--
  Created by IntelliJ IDEA.
  User: asset
  Date: 15-03-23
  Time: 07:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <style>
    .dark-green {
      background-color: #005950;
      color: white;
    }
  </style>
</head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>

<body>
<nav class="navbar navbar-expand-md navbar-dark dark-green">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">BITHUB NEWS</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbar">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link active" href="#">Rayan News</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Iliyas News</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Sultanbek News</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Osek News</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search">
        <button class="btn btn-outline-dark dark-green" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
<div class="container">
  <%
    ArrayList<News> newsList = (ArrayList<News>) request.getAttribute("newsList");
    for (News news : newsList) {
  %>
  <div class="card text-dark bg-light my-3 border-0">
    <class class="card-body">
      <h5 class="card-title"><%=news.getTitle()%></h5>
      <p class="card-text text-secondary"><%=news.getText()%></p>
      <p class="card-text">
        <small class="text-muted">Author: <span class="fw-bold"><%=news.getAuthor()%></span></small>
      </p>
    </class>
  </div>
  <%
    }
  %>
</div>
</body>

</html>