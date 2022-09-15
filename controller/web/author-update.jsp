<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 15.06.2022
  Time: 20:52  
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
    <h3>Редактирование автора</h3>
</div>
<div class="container">
    <form name="updatePublishers" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Сотрудничество с издательствами</legend>
            <div class="input-group mb-3">
                <c:choose>
                    <c:when test="${sessionScope.updatedChosenPublishersToAuthor != null}">
                        <c:forEach var="publisherToUpdate" items="${sessionScope.updatedChosenPublishersToAuthor}">
                            <label>
                                <input type="text" name="publisher" class="form-control" disabled
                                       placeholder="${publisherToUpdate.name}"
                                       aria-label="Издательства" aria-describedby="button-addon1">
                            </label>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="publisherExist" items="${sessionScope.author.publishers}">
                            <label>
                                <input type="text" name="publisher" class="form-control" disabled
                                       placeholder="${publisherExist.name}"
                                       aria-label="Издательства" aria-describedby="button-addon1">
                            </label>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            ${requestScope.errorChoosePublishers}
            ${requestScope.errorUpdateChosenPublishers}
            <input name="action" type="hidden" value="choose_publishers_for_updated_author">
            <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Выбор издательств</button>
            </div>
        </fieldset>
    </form>
    <form name="updateAuthor" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Персональная информация</legend>
            <input name="id" type="hidden" required value="${sessionScope.author.id}">
            <div class="mb-3">
                <label for="surnameInput" class="form-label">Фамилия</label>
                <input type="text" id="surnameInput" name="surname" class="form-control" required
                       value="${sessionScope.author.surname}">
            </div>
            <div class="mb-3">
                <label for="firstNameInput" class="form-label">Имя</label>
                <input type="text" id="firstNameInput" name="first_name" class="form-control" required
                       value="${sessionScope.author.firstName}">
            </div>
            <div class="mb-3">
                <label for="secondNameInput" class="form-label">Отчество</label>
                <input type="text" id="secondNameInput" name="second_name" class="form-control" required
                       value="${sessionScope.author.secondName}">
            </div>
            <div class="mb-3">
                <label for="countryInput" class="form-label">Страна</label>
                <input type="text" id="countryInput" name="country" class="form-control" required
                       value="${sessionScope.author.country}">
            </div>
            <c:choose>
                <c:when test="${sessionScope.updatedChosenPublishersToAuthor != null}">
                    <c:forEach var="publisherToUpdate" items="${sessionScope.updatedChosenPublishersToAuthor}">
                        <label>
                            <input name="publishers_ids" type="hidden" value="${publisherToUpdate.id}">
                        </label>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="publisherExist" items="${sessionScope.author.publishers}">
                        <label>
                            <input name="publishers_ids" type="hidden" value="${publisherExist.id}">
                        </label>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </fieldset>
        <input name="action" type="hidden" value="update_author">
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
