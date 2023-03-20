<%--
  Created by IntelliJ IDEA.
  User: KamranMuradov
  Date: 3/14/2023
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="login_background">

<form method="post" action="login">
    <div class="col-4 container login_fix">
        <div style="text-align: center;">
            <h1>Login</h1>
        </div>
        <div class="form-group">
            <label for="email">Email address </label>
            <input id="email" type="email" class="form-control" placeholder="email@example.com" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password </label>
            <input id="password" type="password" class="form-control" placeholder="Password" name="password">
        </div>
        <button type="submit" class="btn btn-primary" name="login">Login</button>
    </div>
</form>

</body>
</html>
