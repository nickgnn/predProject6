<html xmlns:th="https://www.thymeleaf.org">
<head th:include="layout :: head(title=~{::title},links=~{})">
    <title>Please Login</title>
</head>
<body th:include="layout :: body" th:with="content=~{::content}">
<div th:fragment="content">
    <form name="f" th:action="@{/login}" method="post">
            <legend><h1>Fill form below</h1></legend>

            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>

        <br>


            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">

                <br>

                <button type="submit" class="btn">Log in</button>
            </div>
    </form>
</div>
</body>
</html>

