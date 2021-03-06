<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javamentor.model.User" %><%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 28.09.2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Users</title>
</head>

<body>
<p><a href="/">Main</a></p>

<form action="/add" method="get">
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td>
                <input type="text" required placeholder="fill username" name="username">
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type="password" required placeholder="fill password" name="password">
            </td>
        </tr>
        <tr>
            <td>Age:</td>
            <td>
                <input type="number" name="age">
            </td>
        </tr>
        <tr>
            <td>Role:</td>
            <td>
                <input type="text" name="role">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Add User">
            </td>
        </tr>
        </tbody>
    </table>
</form>

<br>
<br>

<p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Log Out</a></p>

<br>

<h1>List of users</h1>
<form action="/admin" method="get">
    <input type="submit" value="Refresh list">
</form>


<table border="3">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Password</th>
        <th>Age</th>
        <th>Role</th>
        <th>Role_ID</th>
        <th>Edit user</th>
        <th>Delete user</th>
    </tr>
    </thead>
    <%
        List<User> userList = (List<User>) request.getAttribute("admin");
    %>
    <%
        for (User user : userList) {
    %>

    <tr>
        <th><%=user.getId()%></th>
        <th><%=user.getUsername()%></th>
        <th><%=user.getPassword()%></th>
        <th><%=user.getAge()%></th>
        <th><%=user.getRole()%></th>
        <th><%=user.getRole_id()%></th>
        <td><form name="user" action="/edit" method="get">
            <table>
                <tbody>
                <tr>
                    <input type="hidden" name="id" value=<%=user.getId()%>>

                    <td>Edit username:</td>

                    <td>
                        <input type="text" name="username">
                    </td>
                    <tr></tr>

                    <td>Edit password:</td>
                    <td>
                        <input type="text" name="password">
                    </td>

                    <tr></tr>

                    <td>Edit age:</td>
                    <td>
                        <input type="number" name="age">
                    </td>
                    <tr></tr>

                    <td>Edit role:</td>
                    <td>
                        <input type="text" name="role">
                    </td>
                    <tr></tr>

                    <input type="hidden" name="role_id" value=<%=user.getRole_id()%>>

                    <td>
                        <input type="submit" value="Edit user">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        </td>

        <td>
            <form action="/delete" method="get">
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <input type="hidden" name="id" value=<%=user.getId()%>>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Remove user">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form><%}%>
        </td>
    </tr>
</table>

</body>

</html>
