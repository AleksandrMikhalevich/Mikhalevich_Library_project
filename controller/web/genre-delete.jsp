<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 13:17  
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
    <h3>Удаление жанра</h3>
</div>
<div class="container">
    <form name="updateGenre" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Информация</legend>
            <div class="mb-3">
                <label for="nameInput" class="form-label">Название</label>
                <input type="text" id="nameInput" name="name" class="form-control" disabled placeholder="${param.name}">
            </div>
            <div class="mb-3">
                <label for="descriptionInput" class="form-label">Описание</label>
                <input type="text" id="descriptionInput" name="description" class="form-control"
                       disabled placeholder="${param.description}">
            </div>
        </fieldset>
        <input name="id" type="hidden" value="${param.id}">
        <input name="action" type="hidden" value="delete_genre">
        <p>
            Вниманию администратора!<br>
            Удаление жанра из базы данных библиотеки приведет к удалению всех книг данного жанра.
        </p>
        <button type="submit" class="btn btn-danger">Удалить</button>
    </form>
    <a href="genres.jsp">К списку жанров</a>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
