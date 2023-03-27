<%--
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
        <div class="row">
            <form action="/example/add-item" method="post">
                <div class="form-group">
                    <label class="form-label">ITEM NAME:</label>
                    <input type="text" class="form-controller" name="item-name">
                </div>
                <div class="form-group">
                    <label class="form-label">ITEM PRICE:</label>
                    <input type="text" class="form-controller" name="item-price">
                </div>
                <div class="form-group">
                    <label class="form-label">ITEM AMOUNT:</label>
                    <input type="text" class="form-controller" name="item-amount">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-success">ADD NEW ITEM</button>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
</body>
</html>
