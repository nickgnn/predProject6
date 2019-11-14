<%--
  Created by IntelliJ IDEA.
  User: nick
  Date: 25.09.2019
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

   <h1>Fill form below</h1>

   <form action="/login" method="post">
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
