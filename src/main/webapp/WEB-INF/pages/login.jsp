<%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 17.11.2019
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
</head>
<body>

<h2>Login page</h2>

<br>


<a href="/">Start page</a>

<br>
<br>


<h1>Fill form below</h1>

<form action="/login" method="get">
    <table>
        <tbody>
        <tr>
            <td>Login:</td>
            <td>
                <input type="text" required placeholder="name" name="name" >
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type="password" required placeholder="password" name="pass" >
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Enter" >
            </td>
        </tr>
        </tbody>
    </table>
</form>

</body>
</html>
