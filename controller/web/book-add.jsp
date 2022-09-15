<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 08.06.2022
  Time: 12:36  
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
    <h3>Добавление книги</h3>
</div>
<div class="container">
    <form name="addAuthors" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Авторы</legend>
            <div class="input-group mb-3">
                <c:if test="${sessionScope.chosenAuthorsToAdd == null}">
                    <input type="text" class="form-control" disabled placeholder="Авторы" aria-label="Авторы"
                           aria-describedby="button-addon1">
                </c:if>
                <c:if test="${sessionScope.chosenAuthorsToAdd != null}">
                    <c:forEach var="author" items="${sessionScope.chosenAuthorsToAdd}">
                        <label>
                            <input type="text" name="author" class="form-control" disabled
                                   placeholder="${author.firstName} ${author.secondName} ${author.surname}"
                                   aria-label="Авторы" aria-describedby="button-addon1">
                        </label>
                    </c:forEach>
                </c:if>
                ${requestScope.errorChooseAuthors}
                ${requestScope.errorAddChosenAuthors}
                <c:if test="${sessionScope.chosenPublisherToAdd != null}">
                    <label>
                        <input name="publisher_id" type="hidden" value="${sessionScope.chosenPublisherToAdd.id}">
                    </label>
                </c:if>
                <c:choose>
                    <c:when test="${sessionScope.chosenAuthorsToAdd != null}">
                        <input name="action" type="hidden" value="choose_authors_for_added_book">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Изменить выбор
                            авторов
                        </button>
                    </c:when>
                    <c:otherwise>
                        <input name="action" type="hidden" value="choose_authors_for_added_book">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Выбор авторов
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </fieldset>
    </form>
    <form name="addGenres" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Жанры</legend>
            <div class="input-group mb-3">
                <c:if test="${sessionScope.chosenGenresToAdd == null}">
                    <input type="text" class="form-control" disabled placeholder="Жанры" aria-label="Жанры"
                           aria-describedby="button-addon1">
                </c:if>
                <c:if test="${sessionScope.chosenGenresToAdd != null}">
                    <c:forEach var="genre" items="${sessionScope.chosenGenresToAdd}">
                        <label>
                            <input type="text" name="genre" class="form-control" disabled
                                   placeholder="${genre.name}" aria-label="Жанры" aria-describedby="button-addon1">
                        </label>
                    </c:forEach>
                </c:if>
                ${requestScope.errorChooseGenres}
                ${requestScope.errorAddChosenGenres}
                <c:choose>
                    <c:when test="${sessionScope.chosenGenresToAdd != null}">
                        <input name="action" type="hidden" value="choose_genres_for_added_book">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Изменить выбор
                            жанров
                        </button>
                    </c:when>
                    <c:otherwise>
                        <input name="action" type="hidden" value="choose_genres_for_added_book">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Выбор жанров</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </fieldset>
    </form>
    <form name="addPublisher" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Издательство</legend>
            <div class="input-group mb-3">
                <c:if test="${sessionScope.chosenPublisherToAdd == null}">
                    <input type="text" class="form-control" disabled placeholder="Издательство"
                           aria-label="Издательство"
                           aria-describedby="button-addon1">
                </c:if>
                <c:if test="${sessionScope.chosenPublisherToAdd != null}">
                    <c:set var="publisher_id" scope="session" value="${sessionScope.chosenPublisherToAdd.id}"/>
                    <c:set var="publisher_name" scope="session" value="${sessionScope.chosenPublisherToAdd.name}"/>
                    <label>
                        <input type="text" name="publisher" class="form-control" disabled
                               placeholder="${publisher_name}" aria-label="Издательство"
                               aria-describedby="button-addon1">
                    </label>
                </c:if>
                ${requestScope.errorChoosePublisher}
                ${requestScope.errorAddChosenPublisher}
                <c:if test="${sessionScope.chosenAuthorsToAdd != null}">
                    <c:forEach var="author" items="${sessionScope.chosenAuthorsToAdd}">
                        <label>
                            <input name="author_ids" type="hidden" value="${author.id}">
                        </label>
                    </c:forEach>
                </c:if>
                <c:choose>
                    <c:when test="${sessionScope.chosenPublisherToAdd != null}">
                        <input name="action" type="hidden" value="choose_publisher_for_added_book">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Изменить выбор
                            издательства
                        </button>
                    </c:when>
                    <c:otherwise>
                        <input name="action" type="hidden" value="choose_publisher_for_added_book">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Выбор издательства
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </fieldset>
    </form>
    <form name="addBook" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Общая информация</legend>
            <div class="mb-3">
                <label for="titleInput" class="form-label">Название</label>
                <input type="text" id="titleInput" name="title" class="form-control" placeholder="Название" required>
            </div>
            <div class="mb-3">
                <label for="languageInput" class="form-label">Язык</label>
                <input type="text" id="languageInput" name="language" class="form-control" placeholder="Язык" required>
            </div>
            <div class="mb-3">
                <label for="yearOfPublishingInput" class="form-label">Год издания</label>
                <input type="text" id="yearOfPublishingInput" name="year_of_publishing" class="form-control"
                       placeholder="Год издания" required>
            </div>
            <div class="mb-3">
                <label for="receiptDateInput" class="form-label">Дата поступления</label>
                <input type="date" id="receiptDateInput" name="receipt_date" class="form-control"
                       placeholder="Дата поступления" required>
            </div>
            <c:forEach var="author" items="${sessionScope.chosenAuthorsToAdd}">
                <label>
                    <input name="author_ids" type="hidden" value="${author.id}">
                </label>
            </c:forEach>
            <c:forEach var="genre" items="${sessionScope.chosenGenresToAdd}">
                <label>
                    <input name="genre_ids" type="hidden" value="${genre.id}">
                </label>
            </c:forEach>
            <label>
                <input name="publisher_id" type="hidden" value="${publisher_id}">
            </label>
        </fieldset>
        <input name="action" type="hidden" value="add_book">
        <button type="submit" class="btn btn-outline-success">Сохранить</button>
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
