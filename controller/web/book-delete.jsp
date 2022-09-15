<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 03.06.2022
  Time: 12:29  
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
    <h3>Удаление книги</h3>
</div>
<div class="container">
    <form name="deleteBook" method="post" action="controller" autocomplete="off">
        <input name="id" type="hidden" required value="${sessionScope.bookToDelete.id}">
        <fieldset>
            <legend>Авторы</legend>
            <div class="input-group mb-3">
                <c:forEach var="authorExist" items="${sessionScope.bookToDelete.authors}">
                    <label>
                        <input type="text" name="author" class="form-control" disabled
                               placeholder="${authorExist.firstName} ${authorExist.secondName} ${authorExist.surname}"
                               aria-label="Авторы" aria-describedby="button-addon1">
                    </label>
                </c:forEach>
            </div>
        </fieldset>
        <fieldset>
            <legend>Жанры</legend>
            <div class="input-group mb-3">
                <c:forEach var="genreExist" items="${sessionScope.bookToDelete.genres}">
                    <label>
                        <input type="text" name="genre" class="form-control" disabled
                               placeholder="${genreExist.name}" aria-label="Жанры"
                               aria-describedby="button-addon1">
                    </label>
                </c:forEach>
            </div>
        </fieldset>
        <fieldset>
            <legend>Издательство</legend>
            <div class="input-group mb-3">
                <c:if test="${sessionScope.bookToDelete.publisher != null}">
                    <c:set var="publisher_id" scope="session" value="${sessionScope.bookToDelete.publisher.id}"/>
                    <label>
                        <input type="text" name="publisher" class="form-control" disabled
                               placeholder="${sessionScope.bookToDelete.publisher.name}"
                               aria-label="Издательство" aria-describedby="button-addon1">
                    </label>
                </c:if>
            </div>
        </fieldset>
        <fieldset>
            <legend>Общая информация</legend>
            <div class="mb-3">
                <label for="titleInput" class="form-label">Название</label>
                <input type="text" id="titleInput" name="title" class="form-control" disabled
                       placeholder="${sessionScope.bookToDelete.title}">
            </div>
            <div class="mb-3">
                <label for="languageInput" class="form-label">Язык</label>
                <input type="text" id="languageInput" name="language" class="form-control" disabled
                       placeholder="${sessionScope.bookToDelete.language}">
            </div>
            <div class="mb-3">
                <label for="yearOfPublishingInput" class="form-label">Год издания</label>
                <input type="text" id="yearOfPublishingInput" name="year_of_publishing" class="form-control"
                       disabled placeholder="${sessionScope.bookToDelete.yearOfPublishing}">
            </div>
            <div class="mb-3">
                <label for="receiptDateInput" class="form-label">Дата поступления</label>
                <input type="date" id="receiptDateInput" name="receipt_date" class="form-control"
                       disabled value="${sessionScope.bookToDelete.receiptDate}">
            </div>
        </fieldset>
        <input name="action" type="hidden" value="delete_book">
        <button type="submit" class="btn btn-danger">Удалить</button>
    </form>
    <a href="books.jsp">К списку книг</a>
</div>
<p>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
