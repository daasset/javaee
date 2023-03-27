<%@ page import="kz.bitlab.javaee.example.Item" %><%--
  Created by IntelliJ IDEA.
  User: asset
  Date: 18-03-23
  Time: 01:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<div class="container">
  <%
    Item item = (Item) request.getAttribute("item");
    if (item != null) {
  %>
  <div class="row">
    <form action="/example/details" method="post">
      <input type="text" class="invisible" name="item-id" value="<%=item.getId()%>">
      <div class="form-group">
        <label class="form-label">ITEM NAME:</label>
        <input type="text" class="form-controller" name="item-name" value="<%=item.getName()%>">
      </div>
      <div class="form-group">
        <label class="form-label">ITEM PRICE:</label>
        <input type="text" class="form-controller" name="item-price" value="<%=item.getPrice()%>">
      </div>
      <div class="form-group">
        <label class="form-label">ITEM AMOUNT:</label>
        <input type="text" class="form-controller" name="item-amount" value="<%=item.getAmount()%>">
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-success" onclick="if(!confirm('Save changes?')) return false;">SAVE CHANGES</button>
        <a href="#" class="btn btn-danger" onclick="if(confirm('Do you really want to delete this item?')) document.getElementById('delete-form').submit();">
          DELETE ITEM</a>
      </div>
    </form>
    <form action="/example/delete" method="post" class="invisible" id="delete-form">
      <input type="text" class="invisible" name="item-id" value="<%=item.getId()%>">
    </form>
  </div>
  <%
    } else {
  %>
  <h1>Id is not passed!</h1>
  <%
    }
  %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
