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
<body>
<div style="text-align: center;">

    <h2>Удаление жанра</h2>
    <form name="deleteGenre" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Информация</legend>
            Название: <label>
            <input name="name" type="text" disabled value="${param.name}">
        </label>
            Описание: <label>
            <input name="description" type="text" disabled value="${param.description}">
        </label>
        </fieldset>
        <input name="id" type="hidden" value="${param.id}">
        <input name="action" type="hidden" value="delete_genre">
        <p>
            Вниманию администратора!<br>
            Удаление жанра из базы данных библиотеки приведет к удалению всех книг данного жанра.
        </p>
        <button>Удалить</button>
    </form>

    <a href="genres.jsp">К списку жанров</a>

</div>
</body>
</html>
