<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 13.06.2022
  Time: 15:22  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Страница добавления</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h3>Добавление автора</h3>
</div>
<div class="container">
    <form name="addAuthor" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Сотрудничество с издательствами</legend>
            <div class="input-group mb-3">
            <c:if test="${sessionScope.addedChosenPublishersToAuthor == null}">
                <input type="text" class="form-control" disabled placeholder="Издательства" aria-label="Издательства"
                       aria-describedby="button-addon1">
            </c:if>
            <c:if test="${sessionScope.addedChosenPublishersToAuthor != null}">
                <c:forEach var="publisher" items="${sessionScope.addedChosenPublishersToAuthor}">
                    <label>
                    <input type="text" name="publisher" class="form-control" disabled placeholder="${publisher.name}" aria-label="Издательства"
                           aria-describedby="button-addon1">
                    </label>
                </c:forEach>
            </c:if>
            ${requestScope.errorChoosePublishers}
            ${requestScope.errorAddChosenPublishers}
            <c:choose>
                <c:when test="${sessionScope.addedChosenPublishersToAuthor != null}">
                    <input name="action" type="hidden" value="choose_publishers_for_added_author">
                    <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Изменить выбор издательств</button>
                </c:when>
                <c:otherwise>
                    <input name="action" type="hidden" value="choose_publishers_for_added_author">
                    <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Выбор издательств</button>
                </c:otherwise>
            </c:choose>
            </div>
        </fieldset>
    </form>
    <form name="addAuthor" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Персональная информация</legend>
            <div class="mb-3">
                <label for="surnameInput" class="form-label">Фамилия</label>
                <input type="text" id="surnameInput" name="surname" class="form-control" placeholder="Фамилия" required>
            </div>
            <div class="mb-3">
                <label for="firstNameInput" class="form-label">Имя</label>
                <input type="text" id="firstNameInput" name="first_name" class="form-control" placeholder="Имя" required>
            </div>
            <div class="mb-3">
                <label for="secondNameInput" class="form-label">Отчество</label>
                <input type="text" id="secondNameInput" name="second_name" class="form-control" placeholder="Отчество" required>
            </div>
            <div class="mb-3">
                <label for="countryInput" class="form-label">Страна</label>
                <input type="text" id="countryInput" name="country" class="form-control"
                       placeholder="Страна" required>
            </div>
            <c:forEach var="publisher" items="${sessionScope.addedChosenPublishersToAuthor}">
                <label>
                    <input name="publishers_ids" type="hidden" value="${publisher.id}">
                </label>
            </c:forEach>
        </fieldset>
        <input name="action" type="hidden" value="add_author">
        <button type="submit" class="btn btn-outline-success">Сохранить</button>
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
