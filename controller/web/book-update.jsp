<%--
Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 03.06.2022
  Time: 19:56  
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
    <h3>Редактирование книги</h3>
</div>
<div class="container">
    <form name="updateAuthors" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Авторы</legend>
            <div class="input-group mb-3">
                <c:choose>
                    <c:when test="${sessionScope.chosenAuthors != null}">
                        <c:forEach var="authorToChange" items="${sessionScope.chosenAuthors}">
                            <label>
                                <input type="text" name="author" class="form-control" disabled
                                       placeholder="${authorToChange.firstName} ${authorToChange.secondName} ${authorToChange.surname}"
                                       aria-label="Авторы" aria-describedby="button-addon1">
                            </label>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${sessionScope.chosenPublisher != null}">
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="authorExist" items="${sessionScope.book.authors}">
                                    <label>
                                        <input type="text" name="author" class="form-control" disabled
                                               placeholder="${authorExist.firstName} ${authorExist.secondName} ${authorExist.surname}"
                                               aria-label="Авторы" aria-describedby="button-addon1">
                                    </label>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                ${requestScope.errorChooseAuthors}
                ${requestScope.errorAddChosenAuthors}
                <c:if test="${sessionScope.chosenPublisher != null}">
                    <label>
                        <input name="publisher_id" type="hidden" value="${sessionScope.chosenPublisher.id}">
                    </label>
                </c:if>
                <input name="action" type="hidden" value="choose_authors_for_existing_book">
                <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Выбор авторов
                </button>
            </div>
        </fieldset>
    </form>
    <form name="updateGenres" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Жанры</legend>
            <div class="input-group mb-3">
                <c:choose>
                    <c:when test="${sessionScope.chosenGenres != null}">
                        <c:forEach var="genre" items="${sessionScope.chosenGenres}">
                            <label>
                                <input type="text" name="genre" class="form-control" disabled
                                       placeholder="${genre.name}" aria-label="Жанры" aria-describedby="button-addon1">
                            </label>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="genreExist" items="${sessionScope.book.genres}">
                            <label>
                                <input type="text" name="genre" class="form-control" disabled
                                       placeholder="${genreExist.name}" aria-label="Жанры"
                                       aria-describedby="button-addon1">
                            </label>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                ${requestScope.errorChooseGenres}
                ${requestScope.errorAddChosenGenres}
                <input name="action" type="hidden" value="choose_genres_for_existing_book">
                <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Выбор жанров</button>
            </div>
        </fieldset>
    </form>
    <form name="updatePublisher" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Издательство</legend>
            <div class="input-group mb-3">
                <c:choose>
                    <c:when test="${sessionScope.chosenPublisher != null}">
                        <c:set var="publisher_id" scope="session" value="${sessionScope.chosenPublisher.id}"/>
                        <c:set var="publisher_name" scope="session" value="${sessionScope.chosenPublisher.name}"/>
                        <label>
                            <input type="text" name="publisher" class="form-control" disabled
                                   placeholder="${publisher_name}" aria-label="Издательство"
                                   aria-describedby="button-addon1">
                        </label>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${sessionScope.book.publisher != null}">
                            <c:set var="publisher_id" scope="session" value="${sessionScope.book.publisher.id}"/>
                            <c:choose>
                                <c:when test="${sessionScope.chosenAuthors != null}">
                                </c:when>
                                <c:otherwise>
                                    <label>
                                        <input type="text" name="publisher" class="form-control" disabled
                                               placeholder="${sessionScope.book.publisher.name}"
                                               aria-label="Издательство" aria-describedby="button-addon1">
                                    </label>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                ${requestScope.errorChoosePublisher}
                ${requestScope.errorAddChosenPublisher}
                <c:if test="${sessionScope.chosenAuthors != null}">
                    <c:forEach var="authorToChange" items="${sessionScope.chosenAuthors}">
                        <label>
                            <input name="author_ids" type="hidden" value="${authorToChange.id}">
                        </label>
                    </c:forEach>
                </c:if>
                <input name="action" type="hidden" value="choose_publisher_for_existing_book">
                <button class="btn btn-outline-secondary" type="submit" id="button-addon1">Выбор издательства
                </button>
            </div>
        </fieldset>
    </form>
    <form name="updateBook" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Общая информация</legend>
            <input name="id" type="hidden" required value="${sessionScope.book.id}">
            <div class="mb-3">
                <label for="titleInput" class="form-label">Название</label>
                <input type="text" id="titleInput" name="title" class="form-control" required value="${sessionScope.book.title}">
            </div>
            <div class="mb-3">
                <label for="languageInput" class="form-label">Язык</label>
                <input type="text" id="languageInput" name="language" class="form-control" required value="${sessionScope.book.language}">
            </div>
            <div class="mb-3">
                <label for="yearOfPublishingInput" class="form-label">Год издания</label>
                <input type="text" id="yearOfPublishingInput" name="year_of_publishing" class="form-control"
                       required value="${sessionScope.book.yearOfPublishing}">
            </div>
            <div class="mb-3">
                <label for="receiptDateInput" class="form-label">Дата поступления</label>
                <input type="date" id="receiptDateInput" name="receipt_date" class="form-control"
                       required value="${sessionScope.book.receiptDate}">
            </div>
            <c:choose>
                <c:when test="${sessionScope.chosenAuthors != null}">
                    <c:forEach var="authorToChange" items="${sessionScope.chosenAuthors}">
                        <label>
                            <input name="author_ids" type="hidden" value="${authorToChange.id}">
                        </label>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="authorExist" items="${sessionScope.book.authors}">
                        <label>
                            <input name="author_ids" type="hidden" value="${authorExist.id}">
                        </label>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.chosenGenres != null}">
                    <c:forEach var="genre" items="${sessionScope.chosenGenres}">
                        <label>
                            <input name="genre_ids" type="hidden" value="${genre.id}">
                        </label>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="genreExist" items="${sessionScope.book.genres}">
                        <label>
                            <input name="genre_ids" type="hidden" value="${genreExist.id}">
                        </label>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <label>
                <input name="publisher_id" type="hidden" value="${publisher_id}">
            </label>
        </fieldset>
        <input name="action" type="hidden" value="update_book">
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



