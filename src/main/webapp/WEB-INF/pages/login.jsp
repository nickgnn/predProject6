<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 10.11.2019
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>


<h1>Fill form below</h1>

<form action='<spring:url value="/login"/>' method="post">
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
