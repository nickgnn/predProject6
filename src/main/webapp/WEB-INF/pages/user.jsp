<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 02.11.2019
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>

<h2>Hello page!</h2>

<a href="/">Start page</a>

<br>
<br>

<a href="/admin">Show users</a>


<br>
<br>

<p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Log Out</a></p>

</body>
</html>
