<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 15:52  
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

    <h2>Добавление издательства</h2>
    <form name="addPublisher" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Название</legend>
            Название: <label>
            <input name="name" type="text" required value="">
        </label>
        </fieldset>
        <fieldset>
            <legend>Адрес</legend>
            Страна: <label>
            <input name="country" type="text" required value="">
        </label>
            Город: <label>
            <input name="city" type="text" required value="">
        </label>
            Улица: <label>
            <input name="street" type="text" required value="">
        </label>
            Дом: <label>
            <input name="house" type="text" required value="">
        </label>
            Почтовый индекс: <label>
            <input name="zipcode" type="text" required value="">
        </label>
        </fieldset>
        <input name="action" type="hidden" value="add_publisher">
        <button>Сохранить</button>
    </form>

    <a href="publishers.jsp">К списку издательств</a>

</div>
</body>
</html>
