<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 05.10.2022
  Time: 19:34  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Страница удаления</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h3>Удаление пользователя</h3>
</div>
<div class="container">
    <form name="deleteUser" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Информация о пользователе</legend>
            <div class="mb-3">
                <label for="loginInput" class="form-label">Логин</label>
                <input type="text" id="loginInput" name="login" class="form-control" disabled placeholder="${param.login}">
            </div>
            <div class="mb-3">
                <label for="emailInput" class="form-label">Электронная почта</label>
                <input type="text" id="emailInput" name="email" class="form-control" disabled placeholder="${param.email}">
            </div>
            <div class="mb-3">
                <label for="roleInput" class="form-label">Роль</label>
                <input type="text" id="roleInput" name="role" class="form-control" disabled placeholder="${param.role}">
            </div>
        </fieldset>
        <input name="id" type="hidden" value="${param.id}">
        <input name="action" type="hidden" value="delete_user_by_admin">
        <button type="submit" class="btn btn-danger">Удалить</button>
    </form>
    <a href="users.jsp">К списку пользователей</a>
</div>
<p>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
