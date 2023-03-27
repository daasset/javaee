<%@ page import="model.Student" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: asset
  Date: 27-03-23
  Time: 05:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Showing all students</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">BITHUB</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/">Student's list</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#">Add student</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5">
    <%
        Student student = (Student) request.getAttribute("student");
        if (student != null) {
    %>
    <form action="/edit-student" method="post" class="w-50 mx-auto">
        <div class="invisible">
            <input type="text" name="student-id" value="<%=student.getId()%>">
        </div>
        <!-- Name -->
        <div class="mb-3 row ">
            <label for="student-name" class="col-sm-3 col-form-label">NAME:</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="student-name" name="student-name"
                       value="<%=student.getName()%>">
            </div>
        </div>

        <!-- Surname -->
        <div class="mb-3 row ">
            <label for="student-surname" class="col-sm-3 col-form-label">SURNAME:</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="student-surname" name="student-surname"
                       value="<%=student.getSurname()%>">
            </div>
        </div>

        <!-- Birthdate -->
        <div class="mb-3 row ">
            <label for="student-birthdate" class="col-sm-3 col-form-label">BIRTHDATE:</label>
            <div class="col-sm-9">
                <input type="date" class="form-control" id="student-birthdate" name="student-birthdate"
                       value="<%=student.getBirthDate()%>">
            </div>
        </div>

        <!-- City -->
        <div class="mb-3 row ">
            <label for="student-city" class="col-sm-3 col-form-label">CITY:</label>
            <div class="col-sm-9">
                <select class="form-select" id="student-city" name="student-city">
                    <option <%=(student.getCity().equals("Almaty"))%>>Almaty</option>
                    <option <%=(student.getCity().equals("Astana"))%>>Astana</option>
                    <option <%=(student.getCity().equals("New-York"))%>>New-York</option>
                </select>
            </div>
        </div>
        <div class="mb-3 text-end">
            <button class="btn bg-success text-white" onclick="if(!confirm('Are you sure?')) return false">SAVE STUDENT</button>
            <a href="#" class="btn bg-danger text-white"
               onclick="if(confirm('Are you sure?')) document.getElementById('delete-form').submit()">DELETE STUDENT</a>
            <a href="/student-details?id=<%=student.getId()%>" class="btn bg-secondary text-white">CANCEL</a>
        </div>
    </form>
    <form id="delete-form" action="/delete-student?id=<%=student.getId()%>" method="post" class="invisible">
        <input type="text" name="student-id" value="<%=student.getId()%>">
    </form>
    <%
        }
    %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
