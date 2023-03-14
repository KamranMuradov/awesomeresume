<%@ page import="org.company.entity.User" %>
<%@ page import="java.util.List" %>
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

    <link rel="stylesheet" href="assets/css/users.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script type="text/javascript" src="assets/js/users.js"></script>
</head>
<body>
<script src="https://kit.fontawesome.com/b8714a14d2.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<%List<User> userList = (List<User>) request.getAttribute("users");%>
<% User u = (User) session.getAttribute("loggedInUser");%>
<%="Welcome, " + u.getName()%>

<div class="container mycontainer">
    <div class="row">
        <div class="col-4">
            <form method="get" action="users">
                <%--        <input type="hidden" name="id" value=""/>--%>
                <div class="form-group">
                    <label for="name">name:</label>
                    <input id="name" class="form-control" placeholder="Enter your name"
                           type="text" name="name"
                           value=""/>
                </div>
                <div class="form-group">
                    <label for="surname">surname:</label>
                    <input id="surname" class="form-control" placeholder="Enter your surname" type="text" name="surname"
                           value=""/>
                </div>
                <input visible="true" class="btn btn-primary" type="submit" name="search" value="Search"
                       id="btnsearch"/>
            </form>
        </div>
    </div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Nationality</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                for (User user : userList) {
            %>
            <tr>
                <td><%=user.getName()%>
                </td>
                <td><%=user.getSurname()%>
                </td>
                <td><%=user.getNationality().getNationality() == null ? "N/A" : user.getNationality().getNationality()%>
                </td>
                <td style="width: 5px">
                    <%--                    <form method="post" action="userdetail">--%>
                    <input type="hidden" name="id" value="<%=user.getId()%>"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button class="btn btn-danger" type="submit" title="Delete this user" value="delete">
                        <i class="fa-solid fa-trash-can" data-bs-toggle="modal" data-bs-target="#exampleModal"
                           onclick="setIdForDelete(<%=user.getId()%>)"></i>
                    </button>
                    <%--                    </form>--%>
                </td>
                <td style="width: 5px">
                    <form method="get" action="userdetail">
                        <input type="hidden" name="id" value="<%=user.getId()%>"/>
                        <%--                        <input type="hidden" name="action" value="update"/>--%>
                        <button class="btn btn-secondary" type="submit" title="Edit this user" value="update">
                            <i class="fa-solid fa-square-pen"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <form action="userdetail" method="post">
                    <input type="hidden" name="id" value="" id="idForDelete"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>
