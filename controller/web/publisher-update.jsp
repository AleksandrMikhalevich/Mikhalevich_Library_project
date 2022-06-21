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
    <title>Страница редактирования</title>
</head>
<body>
<div style="text-align: center;">

    <h2>Редактирование издательства</h2>
    <form name="updatePublisher" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Название</legend>
            Название: <label>
            <input name="name" type="text" required value="${param.name}">
        </label>
        </fieldset>
        <fieldset>
            <legend>Адрес</legend>
            Страна: <label>
            <input name="country" type="text" required value="${param.country}">
        </label>
            Город: <label>
            <input name="city" type="text" required value="${param.city}">
        </label>
            Улица: <label>
            <input name="street" type="text" required value="${param.street}">
        </label>
            Дом: <label>
            <input name="house" type="text" required value="${param.house}">
        </label>
            Почтовый индекс: <label>
            <input name="zipcode" type="text" required value="${param.zipcode}">
        </label>
        </fieldset>
        <input name="publisher_id" type="hidden" value="${param.id}">
        <input name="action" type="hidden" value="update_publisher">
        <button>Сохранить изменения </button>
    </form>

    <a href="publishers.jsp">К списку издательств</a>

</div>
</body>
</html>