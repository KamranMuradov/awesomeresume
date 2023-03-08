<%@ page import="org.company.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: KamranMuradov
  Date: 2/18/2023
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Title</title>
</head>
<link rel="stylesheet" href="assets/css/users.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<body>
<script src="https://kit.fontawesome.com/b8714a14d2.js" crossorigin="anonymous"></script>
<%

    User user = (User) request.getAttribute("user");

%>
<div class="container" style="margin-bottom: 10px;margin-top: 10px">
    <div class="row" style="flex-direction: row-reverse">
        <div class="col-md-8 order-md-1">
            <h4 class="mb-3">Details</h4>
            <form method="post" action="userdetail">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input class="form-control" id="name" type="text" name="name"
                                   value="<%=user.getName()%>"/>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                            <label for="surname">Surname</label>
                            <input class="form-control" id="surname" type="text" name="surname"
                                   value="<%=user.getSurname()%>"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" id="email" type="email" name="email"
                                   value="<%=user.getEmail()%>">
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input class="form-control" id="phone" type="text" name="phone"
                                   value="<%=user.getPhone()%>">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input class="form-control" id="address" type="text" name="address"
                                   value="<%=user.getAddress()%>">
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                            <label for="birthdate">Birthdate</label>
                            <input class="form-control" id="birthdate" type="date" name="birthdate"
                                   value="<%=user.getBirthdate()%>">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                            <label for="birthplace">Birthplace</label>
                            <input class="form-control" id="birthplace" type="text" name="birthplace"
                                   value="<%=user.getBirthPlace().getName()%>">
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <div class="form-group">
                            <label for="nationality">Nationality</label>
                            <input class="form-control" id="nationality" type="text" name="nationality"
                                   value="<%=user.getNationality().getNationality()%>">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="profile description">Profile Description</label>
                            <textarea class="form-control" id="profile description" name="profile description"
                                      rows="10" cols="20" style="resize: none"><%=user.getProfileDesc()%></textarea>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="action" value="update"/>
                <input class="btn btn-primary" type="submit" name="save" value="Save"/>
            </form>
        </div>
    </div>
</div>
<%
%>
</body>
</html>
