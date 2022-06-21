<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 15:51  
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

    <h2>Удаление издательства</h2>
    <form name="deletePublisher" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Название</legend>
            Название: <label>
            <input name="name" type="text" disabled value="${param.name}">
        </label>
        </fieldset>
        <fieldset>
            <legend>Адрес</legend>
            Страна: <label>
            <input name="country" type="text" disabled value="${param.country}">
        </label>
            Город: <label>
            <input name="city" type="text" disabled value="${param.city}">
        </label>
            Улица: <label>
            <input name="street" type="text" disabled value="${param.street}">
        </label>
            Дом: <label>
            <input name="house" type="text" disabled value="${param.house}">
        </label>
            Почтовый индекс: <label>
            <input name="zipcode" type="text" disabled value="${param.zipcode}">
        </label>
        </fieldset>
        <input name="publisher_id" type="hidden" value="${param.id}">
        <input name="action" type="hidden" value="delete_publisher">
        <p>
            Вниманию администратора!<br>
            Удаление издательства из базы данных библиотеки приведет к удалению всех книг данного издательства.
        </p>
        <button>Удалить</button>
    </form>

    <a href="publishers.jsp">К списку издательств</a>

</div>
</body>
</html>
