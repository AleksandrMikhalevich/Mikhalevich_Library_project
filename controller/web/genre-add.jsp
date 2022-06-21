<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 13:57  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Страница добавления</title>
</head>
<body>
<div style="text-align: center;">

    <h2>Добавление жанра</h2>
    <form name="addGenre" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Информация</legend>
            Название: <label>
            <input name="name" type="text" required value="">
        </label>
            Описание: <label>
            <input name="description" type="text" required value="">
        </label>
        </fieldset>
        <input name="action" type="hidden" value="add_genre">
        <button>Сохранить</button>
    </form>

    <a href="genres.jsp">К списку жанров</a>

</div>
</body>
</html>
