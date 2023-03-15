<%@ page import="task1n2.Item" %><%--
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
        .dark-green {
            background-color: #005950;
            color: white;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-expand-md navbar-dark dark-green">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">BITHUB SHOP</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link active" href="/task1n2/show-items">ALL ITEMS</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/task1n2/add-item">ADD ITEM</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container w-25 px-auto pt-4">
    <%
        Item item = (Item) request.getAttribute("item");

        if (item != null) {
    %>
    <div class="mb-3">
        <label for="item-name">NAME:</label>
        <div id="item-name" class="fw-bold"><%=item.getName()%></div>
    </div>
    <div class="mb-3">
        <label for="item-price">PRICE:</label>
        <div id="item-price" class="fw-bold"><%=item.getPrice()%></div>
    </div>
    <div class="mb-3">
        <label for="item-amount">AMOUNT:</label>
        <div id="item-amount" class="fw-bold"><%=item.getAmount()%></div>
    </div>
    <%
        }
    %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>

</html>
