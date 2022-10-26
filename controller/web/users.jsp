<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 28.09.2022
  Time: 13:35  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Пользователи</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    Добро пожаловать к Пользователям!
</div>
<p>
<div class="d-flex justify-content-center">
    <form name="search" method="post" action="controller">
        <div class="input-group">
            <input type="text" class="d-flex flex-row form-control" name="search_query"
                   aria-describedby="searchInput" aria-label="Search users" placeholder="Поиск пользователей">
            <input name="action" type="hidden" value="find_user_by_name">
            <button class="d-flex flex-row btn btn-outline-success" type="submit" id="searchInput">Искать</button>
        </div>
    </form>
</div>
<div class="d-flex justify-content-center">
    <form name="sorting" method="post" action="controller">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="sorting" id="inlineRadio1"
                   value="sort_by_name" checked="checked">
            <label class="form-check-label" for="inlineRadio1">По логину</label>
        </div>
        ${requestScope.errorSortUsers}
        <c:forEach var="user" items="${sessionScope.userList}">
            <input name="users_ids" type="hidden" value="${user.id}">
        </c:forEach>
        <input name="action" type="hidden" value="sort_users">
        <button type="submit" class="btn btn-outline-secondary">Сортировать</button>
    </form>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <p>
        <c:if test="${sessionScope.searchUserResults != null || sessionScope.searchUserResults != null && sessionScope.sortUserResults != null}">
            ${sessionScope.searchUserResults}
            ${requestScope.errorSearchUserResults}
        </c:if>
    </p>
    <table class="table table-bordered table-hover" style="text-align: center;">
        <thead>
        <tr>
            <th scope="col">Логин</th>
            <th scope="col">Электронная почта</th>
            <th scope="col">Роль</th>
            <th scope="col">Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${sessionScope.userList}">
            <tr>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <c:out value="${user.email}"/>
                </td>
                <td>
                    <c:out value="${user.role.name}"/>
                </td>
                <td>
                    <c:if test="${sessionScope.get('user').getId() ne user.id}">
                    <form name="delete" method="post" action="user-delete.jsp">
                        <input name="id" type="hidden" value="${user.id}">
                        <input name="login" type="hidden" value="${user.login}">
                        <input name="email" type="hidden" value="${user.email}">
                        <input name="role" type="hidden" value="${user.role.name}">
                        <input name="action" type="hidden" value="user-delete.jsp">
                        <button type="submit" class="btn btn-outline-danger">Удалить</button>
                    </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    ${requestScope.successDeleteUser}
    ${requestScope.errorDeleteUser}
    <p>
        <a href="index.jsp">На главную страницу</a>
    <p>
        <c:if test="${sessionScope.searchUserResults != null || sessionScope.searchUserResults != null && sessionScope.sortUserResults != null}">
        <a href="controller?action=find_all_users">Назад</a>
        </c:if>
</div>
</body>
</html>
