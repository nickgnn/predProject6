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
                        <input type="text" username="username">
                    </td>
                </tr>
                <tr>
                    <td>Age:</td>
                    <td>
                        <input type="number" username="age">
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <input type="text" username="password">
                    </td>
                </tr>
                <tr>
                    <td>Role:</td>
                    <td>
                        <input type="text" username="role">
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
<form action="/users" method="get">
    <input type="submit" value="Refresh list">
</form>


<table border="3">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Password</th>
            <th>Role</th>
            <th>Edit user</th>
            <th>Delete user</th>
        </tr>
    </thead>
    <%
        List<User> userList = (List<User>) request.getAttribute("usersList");
    %>
    <%
        for (User user : userList) {
    %>

    <tr>
        <th><%=user.getId()%></th>
        <th><%=user.getUsername()%></th>
        <th><%=user.getAge()%></th>
        <th><%=user.getPassword()%></th>
        <th><%=user.getRole()%></th>
        <td><form username="user" action="/edit" method="get">
            <table>
                <tbody>
                <%--<tr>--%>
                    <%--<td>Edit id:</td>--%>
                    <%--<td>--%>
                        <%--<input type="number" username="newId">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td>Edit username:</td>
                    <td>
                        <input type="text" username="newName">
                    </td>
                </tr>
                <tr>
                    <td>Edit age:</td>
                    <td>
                        <input type="number" username="newAge">
                    </td>
                </tr>
                <tr>
                    <td>Edit password:</td>
                    <td>
                        <input type="text" username="newPassword">
                    </td>
                </tr>
                <tr>
                    <td>Edit role:</td>
                    <td>
                        <input type="text" username="newRole">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" username="id" value=<%=user.getId()%>>
                        <input type="hidden" username="username" value=<%=user.getUsername()%>>
                        <input type="hidden" username="age" value=<%=user.getAge()%>>
                        <input type="hidden" username="password" value=<%=user.getPassword()%>>
                        <input type="hidden" username="role" value=<%=user.getRole()%>>

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
                            <input type="hidden" username="id" value=<%=user.getId()%>>
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
