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
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h3>Редактирование издательства</h3>
</div>
<div class="container">
    <form name="updatePublisher" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Название</legend>
            <div class="mb-3">
                <label for="nameInput" class="form-label">Название</label>
                <input type="text" id="nameInput" name="name" class="form-control" required value="${param.name}">
            </div>
        </fieldset>
        <fieldset>
            <legend>Адрес</legend>
            <div class="mb-3">
                <label for="countryInput" class="form-label">Страна</label>
                <input type="text" id="countryInput" name="country" class="form-control" required value="${param.country}">
            </div>
            <div class="mb-3">
                <label for="cityInput" class="form-label">Город</label>
                <input type="text" id="cityInput" name="city" class="form-control" required value="${param.city}">
            </div>
            <div class="mb-3">
                <label for="streetInput" class="form-label">Улица</label>
                <input type="text" id="streetInput" name="street" class="form-control" required value="${param.street}">
            </div>
            <div class="mb-3">
                <label for="houseInput" class="form-label">Дом</label>
                <input type="text" id="houseInput" name="house" class="form-control" required value="${param.house}">
            </div>
            <div class="mb-3">
                <label for="zipcodeInput" class="form-label">Почтовый индекс</label>
                <input type="text" id="zipcodeInput" name="zipcode" class="form-control" required value="${param.zipcode}">
            </div>
        </fieldset>
        <input name="publisher_id" type="hidden" value="${param.id}">
        <input name="action" type="hidden" value="update_publisher">
        <button type="submit" class="btn btn-outline-success">Сохранить</button>
    </form>
    <a href="publishers.jsp">К списку издательств</a>
</div>
<p>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>