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
<style>
    table, th, td {
        border: 1px solid black
    }

    .center {
        margin-left: auto;
        margin-right: auto;
    }
</style>
<body>
<div style="text-align: center;">

    Добро пожаловать к Книгам!
    <p>
    <form name="search" method="post" action="controller">
        <label>
            <input name="search_query" type="text" required placeholder="Поиск книг">
        </label>
        ${requestScope.errorSearchBookResults}
        <input name="action" type="hidden" value="find_book_by_name">
        <button>Искать</button>
    </form>

    <form name="sorting" method="post" action="controller">
        <label>
            <input type="radio" name="sorting" value="sort_by_name" checked>
        </label> По имени
        <label>
            <input type="radio" name="sorting" value="sort_by_date">
        </label> По дате поступления
        ${requestScope.errorSortBooks}
        <c:forEach var="book" items="${sessionScope.bookList}">
            <input name="books_ids" type="hidden" value="${book.id}">
        </c:forEach>
        <input name="action" type="hidden" value="sort_books">
        <button>Сортировать</button>
    </form>

    <table class="center">
        <p>
            <c:if test="${sessionScope.searchBookResults != null || sessionScope.searchBookResults != null && sessionScope.sortBookResults != null}">
                ${sessionScope.searchBookResults}
                ${requestScope.errorSearchBookResults}
            </c:if>
        </p>
        <caption><b>
            Список книг
        </b></caption>
        <tr>
            <th>Название</th>
            <th>Язык</th>
            <th>Авторы</th>
            <th>Жанры</th>
            <th>Издатель</th>
            <th>Год издания</th>
            <th>Дата поступления</th>
            <th>Действия</th>
        </tr>
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
                        <button>Удалить</button>
                    </form>
                    <form name="update" method="post" action="controller">
                        <c:remove var="chosenAuthors"/>
                        <c:remove var="chosenGenres"/>
                        <c:remove var="chosenPublisher"/>
                        <input name="id" type="hidden" value="${book.id}">
                        <input name="action" type="hidden" value="find_book_by_id_to_update">
                        <button>Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
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
        <a href="main.jsp">На главную страницу</a>
    <p>
        <c:if test="${sessionScope.searchBookResults != null || sessionScope.searchBookResults != null && sessionScope.sortBookResults != null}">
        <a href="controller?action=find_all_books">Назад</a>
        </c:if>
</div>
</body>
</html>
