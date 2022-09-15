<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 12:32  
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
    <h3>Удаление автора</h3>
</div>
<div class="container">
    <form name="deleteAuthor" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Сотрудничество с издательствами</legend>
            <div class="input-group mb-3">
                <c:forEach var="publisherExist" items="${sessionScope.authorToDelete.publishers}">
                    <label>
                        <input type="text" name="publisher" class="form-control" disabled
                               placeholder="${publisherExist.name}" aria-label="Издательства"
                               aria-describedby="button-addon1">
                    </label>
                </c:forEach>
            </div>
        </fieldset>
        <fieldset>
            <legend>Персональная информация</legend>
            <div class="mb-3">
                <label for="surnameInput" class="form-label">Фамилия</label>
                <input type="text" id="surnameInput" name="surname" class="form-control" disabled
                       placeholder="${sessionScope.authorToDelete.surname}">
            </div>
            <div class="mb-3">
                <label for="firstNameInput" class="form-label">Имя</label>
                <input type="text" id="firstNameInput" name="first_name" class="form-control" disabled
                       placeholder="${sessionScope.authorToDelete.firstName}">
            </div>
            <div class="mb-3">
                <label for="secondNameInput" class="form-label">Отчество</label>
                <input type="text" id="secondNameInput" name="second_name" class="form-control" disabled
                       placeholder="${sessionScope.authorToDelete.secondName}">
            </div>
            <div class="mb-3">
                <label for="countryInput" class="form-label">Страна</label>
                <input type="text" id="countryInput" name="country" class="form-control" disabled
                       placeholder="${sessionScope.authorToDelete.country}">
            </div>
        </fieldset>
        <input name="id" type="hidden" value="${sessionScope.authorToDelete.id}">
        <input name="action" type="hidden" value="delete_author">
        <p>
            Вниманию администратора!<br>
            Удаление автора из базы данных библиотеки приведет к удалению всех книг данного автора.
        </p>
        <button type="submit" class="btn btn-danger">Удалить</button>
    </form>
    <a href="authors.jsp">К списку авторов</a>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
