<!DOCTYPE html>
<html lang="en" xmlns:th="http://thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>All Users</title>
</head>
<body>

<div th:each="user : ${users}">
    <a th:href="@{/users/{id}(id=${user.getId()})}" th:text="${user.getName()}">user</a>
</div>
<br/>
<a href="/users/new">Add user</a>
</body>
</html>

