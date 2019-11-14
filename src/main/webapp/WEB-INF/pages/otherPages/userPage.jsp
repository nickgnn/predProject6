<%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 19.10.2019
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>

<%--<%--%>
      <%--String name = (String) request.getAttribute("userName");--%>
<%--%>--%>

<h1>Welcome, <%=request.getAttribute("name")%> ! :)</h1>

<%--<br>--%>

<p><a href="/admin">To admin page</a></p>


<p><a href="/logout">LogOut</a></p>

<%--<br>--%>
</body>
</html>
