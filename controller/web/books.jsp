<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 31.05.2022
  Time: 12:06  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Книги</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    Добро пожаловать к Книгам!
</div>
<p>
<div class="d-flex justify-content-center">
    <form name="search" method="post" action="controller">
        <div class="input-group">
            <input type="text" class="d-flex flex-row form-control" name="search_query"
                   aria-describedby="searchInput" aria-label="Search books" placeholder="Поиск книг">
            <input name="action" type="hidden" value="find_book_by_name">
            <button class="d-flex flex-row btn btn-outline-success" type="submit" id="searchInput">Искать</button>
        </div>
    </form>
</div>
<div class="d-flex justify-content-center">
    <form name="sorting" method="post" action="controller">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="sorting" id="inlineRadio1"
                   value="sort_by_name" checked="checked">
            <label class="form-check-label" for="inlineRadio1">По названию</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="sorting" id="inlineRadio2"
                   value="sort_by_date">
            <label class="form-check-label" for="inlineRadio2">По дате поступления</label>
        </div>
        ${requestScope.errorSortBooks}
        <c:forEach var="book" items="${sessionScope.bookList}">
            <input name="books_ids" type="hidden" value="${book.id}">
        </c:forEach>
        <input name="action" type="hidden" value="sort_books">
        <button type="submit" class="btn btn-outline-secondary">Сортировать</button>
    </form>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <p>
        <c:if test="${sessionScope.searchBookResults != null || sessionScope.searchBookResults != null && sessionScope.sortBookResults != null}">
            ${sessionScope.searchBookResults}
            ${requestScope.errorSearchBookResults}
        </c:if>
    <table class="table table-bordered table-hover" style="text-align: center;">
        <thead class="align-middle">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Язык</th>
            <th scope="col">Авторы</th>
            <th scope="col">Жанры</th>
            <th scope="col">Издатель</th>
            <th scope="col">Год издания</th>
            <th scope="col">Дата поступления</th>
            <th colspan="2">Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${sessionScope.bookList}">
            <tr>
                <td>
                    <c:out value="${book.title}"/>
                </td>
                <td>
                    <c:out value="${book.language}"/>
                </td>
                <td>
                    <c:forEach var="author" items="${book.authors}">
                    <c:set var="author_id" scope="session" value="${author.id}"/>
                    <c:set var="author_name" scope="session" value="${author.firstName}"/>
                    <c:set var="author_surname" scope="session" value="${author.surname}"/>
                    <p>
                            <c:out value="${author_name}"/>
                            <c:out value="${author_surname}"/>
                        </c:forEach>
                </td>
                <td>
                    <c:forEach var="genre" items="${book.genres}">
                    <c:set var="genre_id" scope="session" value="${genre.id}"/>
                    <c:set var="genre_name" scope="session" value="${genre.name}"/>
                    <p>
                            <c:out value="${genre.name}"/>
                        </c:forEach>
                </td>
                <td>
                    <c:out value="${book.publisher.name}"/>
                </td>
                <td>
                    <c:out value="${book.yearOfPublishing}"/>
                </td>
                <td>
                    <c:out value="${book.receiptDate}"/>
                </td>
                <td>
                    <form name="delete" method="post" action="controller">
                        <input name="id" type="hidden" value="${book.id}">
                        <input name="action" type="hidden" value="find_book_by_id_to_delete">
                        <button type="submit" class="btn btn-outline-danger">Удалить</button>
                    </form>
                </td>
                <td>
                    <form name="update" method="post" action="controller">
                        <c:remove var="chosenAuthors"/>
                        <c:remove var="chosenGenres"/>
                        <c:remove var="chosenPublisher"/>
                        <input name="id" type="hidden" value="${book.id}">
                        <input name="action" type="hidden" value="find_book_by_id_to_update">
                        <button type="submit" class="btn btn-outline-warning">Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="text-align: center;">
    <br>
    ${requestScope.successAddBook}
    ${requestScope.errorAddBook}
    ${requestScope.successUpdateBook}
    ${requestScope.errorUpdateBook}
    ${requestScope.successDeleteBook}
    ${requestScope.errorDeleteBook}
    ${requestScope.errorFindBookToDelete}
    ${requestScope.errorFindBookToUpdate}
    <p>
        <a      <c:remove var="chosenAuthorsToAdd"/>
                <c:remove var="chosenGenresToAdd"/>
                <c:remove var="chosenPublisherToAdd"/>
                href="book-add.jsp">Добавить книгу</a>
    <p>
        <a href="index.jsp">На главную страницу</a>
    <p>
        <c:if test="${sessionScope.searchBookResults != null || sessionScope.searchBookResults != null && sessionScope.sortBookResults != null}">
            <a href="controller?action=find_all_books">Назад</a>
        </c:if>
    </p>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
